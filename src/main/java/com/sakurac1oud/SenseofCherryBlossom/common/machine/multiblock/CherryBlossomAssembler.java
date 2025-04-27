package com.sakurac1oud.SenseofCherryBlossom.common.machine.multiblock;

// Java标准库
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;

import com.sakurac1oud.SenseofCherryBlossom.SenseofCherryBlossom;
import com.sakurac1oud.SenseofCherryBlossom.utils.Utils;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.util.StatCollector;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;

// Minecraft相关
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

// GT相关
import gregtech.api.GregTechAPI;
import gregtech.api.enums.HeatingCoilLevel;
import gregtech.api.enums.Textures.BlockIcons.*;
import gregtech.api.gui.modularui.GTUITextures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.interfaces.tileentity.IWirelessEnergyHatchInformation;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.metatileentity.implementations.MTEHatch;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.recipe.check.CheckRecipeResultRegistry;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.*;
import static gregtech.api.enums.HatchElement.*;
import static gregtech.api.enums.Textures.BlockIcons.*;
import static gregtech.api.util.GTStructureUtility.ofCoil;

// 结构库
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.ISurvivalBuildEnvironment;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.*;

// 项目内部
import com.sakurac1oud.SenseofCherryBlossom.common.machine.MultiMachineBase;
import com.sakurac1oud.SenseofCherryBlossom.common.machine.WirelessEnergyMultiMachineBase;
import com.sakurac1oud.SenseofCherryBlossom.common.machine.processingLogics.SoCBProcessingLogic;
import com.sakurac1oud.SenseofCherryBlossom.common.misc.OverclockType;
import com.sakurac1oud.SenseofCherryBlossom.utils.TextLocalization;
import static com.sakurac1oud.SenseofCherryBlossom.utils.TextLocalization.Tooltip_DoNotNeedMaintenance;
import static com.sakurac1oud.SenseofCherryBlossom.utils.TextLocalization.textUseBlueprint;

// 其他Mod
import bartworks.API.BorosilicateGlass;
import goodgenerator.api.recipe.GoodGeneratorRecipeMaps;
import goodgenerator.loader.Loaders;

// 静态导入
import static com.gtnewhorizon.structurelib.structure.StructureUtility.*;
import static gregtech.api.enums.HatchElement.*;
import static gregtech.api.enums.Textures.BlockIcons.*;
import static gregtech.api.util.GTStructureUtility.ofCoil;
import static com.sakurac1oud.SenseofCherryBlossom.utils.TextLocalization.Tooltip_DoNotNeedMaintenance;
import static com.sakurac1oud.SenseofCherryBlossom.utils.TextLocalization.textUseBlueprint;

public class CherryBlossomAssembler extends WirelessEnergyMultiMachineBase<CherryBlossomAssembler> implements IWirelessEnergyHatchInformation {
    /**
     * 基础处理时间(ticks)
     */
    private static final int BASE_PROCESSING_TIME = 100;

    /**
     * 速度计算指数系数
     */
    private static final float SPEED_EXPONENT = 0.6f;

    /**
     * 速度计算乘数系数
     */
    private static final float SPEED_MULTIPLIER = 0.5f;

    /**
     * 最大并行数限制
     */
    private static final int MAX_PARALLEL = 256;

    /**
     * 并行计算基数
     */
    private static final int PARALLEL_BASE = 16;
    private static final Log log = LogFactory.getLog(CherryBlossomAssembler.class);

    // ==============================================
    // 性能缓存变量
    // ==============================================

    /**
     * 当前速度加成
     */
    private float cachedSpeedBonus = 1.0f;

    /**
     * 当前并行数
     */
    private int cachedParallel = 1;

    /**
     * 当前处理时间
     */
    private int cachedProcessingTime = BASE_PROCESSING_TIME;
    private int lastComponentCasing = -1;
    private int lastMachineCasing = -1;
    private HeatingCoilLevel lastCoilLevel = HeatingCoilLevel.None;
    private final int lastGlassTier = 0;

    public CherryBlossomAssembler(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public CherryBlossomAssembler(String aName) {
        super(aName);
    }

    @Override
    public String getMachineModeName(int mode) {
        return StatCollector.translateToLocal("CherryBlossomAssembler.modeMsg." + mode);
    }

    /**
     * 设置加热线圈等级
     * @param aCoilLevel 线圈等级
     */
    public void setCoilLevel(HeatingCoilLevel aCoilLevel) {
        this.coilLevel = aCoilLevel;
        updatePerformanceCache();
    }

    /**
     * 获取当前加热线圈等级
     * @return 线圈等级
     */
    public HeatingCoilLevel getCoilLevel() {
        return this.coilLevel;
    }

    public int getCoilTier() {
        return Utils.getVoltageForCoil(coilLevel);
    }

    @Override
    public void getWailaNBTData(EntityPlayerMP player, TileEntity tile, NBTTagCompound tag, World world, int x, int y,
                                int z) {
        super.getWailaNBTData(player, tile, tag, world, x, y, z);
        final IGregTechTileEntity tileEntity = getBaseMetaTileEntity();
    }

    @Override
    public void getWailaBody(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor,
                             IWailaConfigHandler config) {
        super.getWailaBody(itemStack, currentTip, accessor, config);
        final NBTTagCompound tag = accessor.getNBTData();
        if (tag.getBoolean("wirelessMode")) {
        //WIP
        }
    }

    /**
     * 保存机器数据到NBT
     * @param aNBT 要保存的NBT标签
     */
    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        super.saveNBTData(aNBT);
        aNBT.setByte("mode", (byte) machineMode);
        aNBT.setInteger("tierComponentCasing", tierComponentCasing);
        aNBT.setByte("glassTier", glassTier);
        aNBT.setByte("coilLevel", (byte) coilLevel.ordinal());
        aNBT.setInteger("tierMachineCasing", tierMachineCasing);
    }

    /**
     * 从NBT加载机器数据
     * @param aNBT 包含数据的NBT标签
     */
    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        super.loadNBTData(aNBT);
        machineMode = aNBT.getByte("mode");
        tierComponentCasing = aNBT.getInteger("tierComponentCasing");
        glassTier = aNBT.getByte("glassTier");
        coilLevel = HeatingCoilLevel.values()[aNBT.getByte("coilLevel")];
        tierMachineCasing = aNBT.getInteger("tierMachineCasing");
    }


    /**
     * 获取机器模式总数
     * @return 机器模式总数
     * @note 模式说明:
     *   0 - Assembler (装配器模式)
     *   1 - Precise (精密模式)
     */
    @Override
    public int totalMachineMode() {
        return 1;
    }

    @Override
    public int getMachineMode() {
        return super.getMachineMode() % 2;
    }

    /**
     * 设置机器模式图标
     */
    @Override
    public void setMachineModeIcons() {
        // 确保列表初始化
        if (machineModeIcons == null) {
            machineModeIcons = new ArrayList<>();
        } else {
            machineModeIcons.clear();
        }
        // 添加模式图标
        machineModeIcons.add(GTUITextures.OVERLAY_BUTTON_MACHINEMODE_COMPRESSING); // 装配模式(0)
    }

    /**
     * 获取当前模式对应的配方图
     * @return 配方图实例
     */
    public RecipeMap<?> getRecipeMap() {
        return switch (machineMode) {
            default -> RecipeMaps.assemblerRecipes;
        };
    }

    @NotNull
    @Override
    public Collection<RecipeMap<?>> getAvailableRecipeMaps() {
        return Arrays.asList(
            RecipeMaps.assemblerRecipes,
            GoodGeneratorRecipeMaps.preciseAssemblerRecipes);
    }

    @Override
    protected boolean isEnablePerfectOverclock() {
        return true;
    }

    @Override
    public int getWirelessModeProcessingTime() {
        return cachedProcessingTime;
    }

    @Override
    protected int getMaxParallelRecipes() {
        return cachedParallel;
    }

    @Override
    protected float getSpeedBonus() {
        return cachedSpeedBonus;
    }

    @Override
    public boolean explodesOnComponentBreak(ItemStack aStack) {
        return true;
    }

    @Override
    public boolean supportsVoidProtection() {
        return true;
    }

    @Override
    public boolean supportsInputSeparation() {
        return true;
    }

    @Override
    public boolean supportsSingleRecipeLocking() {
        return true;
    }

    /**
     * 更新性能缓存，仅在相关参数变化时执行
     */
    private void updatePerformanceCache() {
        // 检查是否需要更新
        if (lastComponentCasing == tierComponentCasing &&
            lastMachineCasing == tierMachineCasing &&
            lastCoilLevel == coilLevel &&
            lastGlassTier == glassTier) {
            return;
        }

        // 计算并行数
        int coilOrdinal = coilLevel.ordinal();
        cachedParallel = coilLevel == HeatingCoilLevel.MAX
            ? maxParallel
            : Math.min(MAX_PARALLEL, PARALLEL_BASE * (1 << coilOrdinal));

        // 计算速度加成
        if (tierMachineCasing >= 8) {
            float expValue = SPEED_EXPONENT * (tierComponentCasing - 6);
            cachedSpeedBonus = 1F / (1 + SPEED_MULTIPLIER * (float)Math.exp(expValue));
        } else {
            cachedSpeedBonus = 1F;
        }

        // 更新处理时间
        cachedProcessingTime = (int)(BASE_PROCESSING_TIME * cachedSpeedBonus);

        // 更新缓存状态
        lastComponentCasing = tierComponentCasing;
        lastMachineCasing = tierMachineCasing;
        lastCoilLevel = coilLevel;
    }

    @Override
    protected float getEuModifier() {
        return 1-glassTier*0.05F;
    }


    @Override
    protected ProcessingLogic createProcessingLogic() {
        return new SoCBProcessingLogic() {

            {
                setMaxParallelSupplier(CherryBlossomAssembler.this::getLimitedMaxParallel);
            }

            @NotNull
            @Override
            protected CheckRecipeResult validateRecipe(@Nonnull GTRecipe recipe) {

                setOverclockType(glassTier <= 11 ?
                    OverclockType.PerfectOverclock :
                    OverclockType.NormalOverclock);
                return CheckRecipeResultRegistry.SUCCESSFUL;
            }

            @NotNull
            @Override
            public CheckRecipeResult process() {
                setEuModifier(getEuModifier());
                setSpeedBonus(getSpeedBonus());
                return super.process();
            }

            @Nonnull
            @Override
            protected OverclockCalculator createOverclockCalculator(@Nonnull GTRecipe recipe) {
                // 无线模式特殊处理
                return wirelessMode ?
                    OverclockCalculator.ofNoOverclock(recipe) :
                    super.createOverclockCalculator(recipe);
            }
        };
    }

    @Override
    protected void prepareProcessing() {
        super.prepareProcessing();
    }

    public int getExtraEUCostMultiplier() {
        return 1;
    }

    @Override
    public String[] getInfoData() {
        try {
            String[] origin = super.getInfoData();
            String[] ret = new String[origin.length + 3];
            System.arraycopy(origin, 0, ret, 0, origin.length);
            ret[origin.length] = EnumChatFormatting.AQUA + "Glass Tier: " + EnumChatFormatting.GOLD + this.glassTier;
            ret[origin.length + 1] = EnumChatFormatting.AQUA + "Coil Level: "
                + EnumChatFormatting.GOLD
                + this.coilLevel.getTier();
            ret[origin.length + 2] = EnumChatFormatting.AQUA + "MachineCasing Level" + EnumChatFormatting.GOLD + this.tierMachineCasing;
            ret[origin.length + 3] = EnumChatFormatting.AQUA + "ComponentCasing Level" + EnumChatFormatting.GOLD + this.tierComponentCasing;
            ret[origin.length + 4] = EnumChatFormatting.AQUA + "Max Parallel: " + EnumChatFormatting.GOLD + this.getMaxParallelRecipes();
            ret[origin.length + 5] = EnumChatFormatting.AQUA + "Enabled Perfect Overclock: "
                + EnumChatFormatting.GOLD
                + this.isEnablePerfectOverclock();
            return ret;
        } catch (Exception e) {
            return new String[]{"Error getting machine info"};
        }
    }


    // endregion

    // region Structure
    // 结构相关常量
    private static final String STRUCTURE_PIECE_MAIN = "main";
    private final int horizontalOffSet = 11;
    private final int verticalOffSet = 7;
    private final int depthOffSet = 0;
    private static volatile IStructureDefinition<CherryBlossomAssembler> STRUCTURE_DEFINITION = null;

    @Override
    public int survivalConstruct(ItemStack stackSize, int elementBudget, ISurvivalBuildEnvironment env) {
        if (this.mMachine) return -1;
        return this.survivialBuildPiece(
            STRUCTURE_PIECE_MAIN,
            stackSize,
            horizontalOffSet,
            verticalOffSet,
            depthOffSet,
            elementBudget,
            env,
            false,
            true);
    }

    @Override
    public void construct(ItemStack stackSize, boolean hintsOnly) {
        repairMachine();
        buildPiece(STRUCTURE_PIECE_MAIN, stackSize, hintsOnly, horizontalOffSet, verticalOffSet, depthOffSet);
    }

    @Override
    public boolean isCorrectMachinePart(ItemStack aStack) {
        return super.isCorrectMachinePart(aStack);
    }

    // spotless:off
    private final String[][] shape = new String[][]{
        {"                       ","                       ","                       ","   KK             KK   ","   KK             KK   ","                       ","                       ","                       "},
        {"                       ","  JKKJ           JKKJ  "," JAAAAJXXXXXXXXXJAAAAJ "," KAEEAKXXXXXXXXXKAEEAK "," KAEEAKXXXXXXXXXKAEEAK "," JAAAAJXXXXXXXXXJAAAAJ ","  JKKJ           JKKJ  ","                       "},
        {"                       ","  J  J           J  J  "," JAAAAJ         JAAAAJ ","  AEEA    HGH    AEEA  ","  AEEA    HGH    AEEA  "," JAAAAJ         JAAAAJ ","  J  J           J  J  ","                       "},
        {"                       ","  J  J           J  J  "," JAAAAJ         JAAAAJ ","  AEEA    HGH    AEEA  ","  AEEA    HGH    AEEA  "," JAAAAJ         JAAAAJ ","  J  J           J  J  ","                       "},
        {"                       ","  J  J           J  J  "," JAAAAJ         JAAAAJ ","  AEEA    HGH    AEEA  ","  AEEA    HGH    AEEA  "," JAAAAJ         JAAAAJ ","  J  J           J  J  ","                       "},
        {"                       ","  J  J           J  J  "," JAAAAJ         JAAAAJ ","  AEEA    HHH    AEEA  ","  AEEA    HHH    AEEA  "," JAAAAJ         JAAAAJ ","  J  J           J  J  ","                       "},
        {"                       ","  J  J           J  J  "," JAAAAJ   HHH   JAAAAJ ","  AEEA   H   H   AEEA  ","  AEEA   H   H   AEEA  "," JAAAAJ   HHH   JAAAAJ ","  J  J           J  J  ","                       "},
        {"  HBBH    F~F    HBBH  "," HHBBHH   FFF   HHBBHH ","HHHBBHHH  EEE  HHHBBHHH","BBBFFBBBFDDDDDFBBBFFBBB","BBBFFBBBFDDDDDFBBBFFBBB","HHHBBHHH  EEE  HHHBBHHH"," HHBBHH   FFF   HHBBHH ","  HBBH           HBBH  "}
    };
    // spotless:on
    /**
     * 获取机器结构定义(线程安全)
     * @return 机器结构定义
     */
    public IStructureDefinition<CherryBlossomAssembler> getStructureDefinition() {
        IStructureDefinition<CherryBlossomAssembler> result = STRUCTURE_DEFINITION;
        if (result == null) {
            synchronized (CherryBlossomAssembler.class) {
                result = STRUCTURE_DEFINITION;
                if (result == null) {
                    STRUCTURE_DEFINITION = result = StructureDefinition.<CherryBlossomAssembler>builder()
                        .addShape(STRUCTURE_PIECE_MAIN, transpose(shape))
                        .addElement('A', withChannel(
                            "glass",
                            BorosilicateGlass.ofBoroGlass(
                                (byte) 0,
                                (byte) 1,
                                Byte.MAX_VALUE,
                                (te, t) -> te.glassTier = t,
                                te -> te.glassTier
                            )))
                        .addElement('B', withChannel(
                            "component",
                            ofBlocksTiered(
                                (block, meta) -> block == Loaders.componentAssemblylineCasing ? meta : -1,
                                IntStream.range(0, 14)
                                    .mapToObj(i -> Pair.of(Loaders.componentAssemblylineCasing, i))
                                    .collect(Collectors.toList()),
                                -2,
                                (t, meta) -> t.tierComponentCasing = meta,
                                t -> t.tierComponentCasing)
                        ))
                        .addElement('D', withChannel(
                            "machinecasing",
                            ofBlocksTiered(
                                (block, meta) -> block == GregTechAPI.sBlockCasings1 ? meta : 0,
                                IntStream.range(1, 10)
                                    .mapToObj(i -> Pair.of(GregTechAPI.sBlockCasings1, i))
                                    .collect(Collectors.toList()),
                                -2,
                                (t, meta) -> t.tierMachineCasing = meta,
                                t -> t.tierMachineCasing)
                        ))
                        .addElement('E', ofBlock(GregTechAPI.sBlockCasings2, 5))
                        .addElement('F',
                            HatchElementBuilder.<CherryBlossomAssembler>builder()
                            .atLeast(Energy.or(ExoticEnergy))
                            .adder(CherryBlossomAssembler::addEnergyHatchOrExoticEnergyHatchToMachineList)
                            .casingIndex(25)
                            .dot(1)
                            .buildAndChain(GregTechAPI.sBlockCasings2, 9))
                        .addElement('H', ofBlock(GregTechAPI.sBlockCasings8, 7))
                        .addElement('J', ofBlock(GregTechAPI.sBlockFrames, 316))
                        .addElement('K', ofBlock(GregTechAPI.sBlockReinforced, 2))
                        .addElement('G', withChannel(
                            "coil",
                            ofCoil(
                                CherryBlossomAssembler::setCoilLevel,
                                CherryBlossomAssembler::getCoilLevel
                            )))
                        .addElement('X',
                            HatchElementBuilder.<CherryBlossomAssembler>builder()
                                    .atLeast(InputBus,InputHatch,OutputBus)
                                    .adder(CherryBlossomAssembler::addToMachineList)
                                    .casingIndex(25)
                                    .dot(2)
                                    .buildAndChain(GregTechAPI.sBlockCasings2, 9))
                        .build();
                }
            }
        }
        return result;
    }

    /**
     * 创建新的元实体实例
     * @param aTileEntity 关联的GT方块实体
     * @return 新的CherryBlossomAssembler实例
     */
    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new CherryBlossomAssembler(this.mName);
    }

    @Override
    public boolean addToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return super.addToMachineList(aTileEntity, aBaseCasingIndex)
            || addExoticEnergyInputToMachineList(aTileEntity, aBaseCasingIndex);
    }

    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        repairMachine();
        tierComponentCasing = -2;
        glassTier = 0;
        coilLevel = HeatingCoilLevel.None;
        tierMachineCasing = 0;

       checkPiece(STRUCTURE_PIECE_MAIN, horizontalOffSet, verticalOffSet, depthOffSet);

        if (!wirelessMode) {
            for (MTEHatch hatch : mExoticEnergyHatches) {
                if (hatch.getConnectionType() == MTEHatch.ConnectionType.LASER && glassTier < 6) {
                    SenseofCherryBlossom.LOG.info("Machine check result:" + "ExoticEnergyHatches check failed.");
                    return false;
                }
            }
            for (MTEHatch hatch : mEnergyHatches) {
                if (glassTier < hatch.mTier) {
                    SenseofCherryBlossom.LOG.info("Machine check result:" + "EnergyHatches check failed.");
                    return false;
                }
            }
        } else {
            wirelessMode = tierMachineCasing >= 10 && mEnergyHatches.isEmpty() && mExoticEnergyHatches.isEmpty();
        }

        return true;
    }


    // 机器状态变量
    public byte glassTier;
    public HeatingCoilLevel coilLevel = HeatingCoilLevel.None;
    private int tierComponentCasing = -2;
    private int tierMachineCasing = 0;

    // endregion

    // region General


    @Override
    protected MultiblockTooltipBuilder createTooltip() {
        final MultiblockTooltipBuilder tt = new MultiblockTooltipBuilder();
        tt.addMachineType(TextLocalization.Tooltip_CherryBlossomAssembler_MachineType)
            .addInfo(TextLocalization.Tooltip_CherryBlossomAssembler_Controller)
            .addInfo(TextLocalization.Tooltip_CherryBlossomAssembler_01)
            .addStructureInfo(EnumChatFormatting.GOLD + "-----------------------------------------")
            .addInfo(TextLocalization.Tooltip_CherryBlossomAssembler_02)
            .addInfo(TextLocalization.Tooltip_CherryBlossomAssembler_03)
            .addInfo(TextLocalization.Tooltip_CherryBlossomAssembler_04)
            .addInfo(TextLocalization.Tooltip_CherryBlossomAssembler_05)
            .addInfo(TextLocalization.Tooltip_CherryBlossomAssembler_06)
            .addStructureInfo(EnumChatFormatting.GOLD + "-----------------------------------------")
            .addInfo(TextLocalization.StructureTooComplex)
            .addInfo(TextLocalization.BLUE_PRINT_INFO)
            .addSeparator()
            .addStructureInfo(TextLocalization.Tooltip_Details)
            .addStructureInfo(EnumChatFormatting.GOLD + "-----------------------------------------")
            .addStructureInfo(EnumChatFormatting.GOLD + "-----------------------------------------")
            .addStructureInfo(Tooltip_DoNotNeedMaintenance)
            .addInputBus(textUseBlueprint, 2)
            .addInputHatch(textUseBlueprint, 2)
            .addOutputBus(textUseBlueprint, 2)
            .addEnergyHatch(textUseBlueprint, 1)
            .toolTipFinisher(TextLocalization.ModName);
        return tt;
    }

    @Override
    public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, ForgeDirection side, ForgeDirection aFacing,
                                 int colorIndex, boolean aActive, boolean redstoneLevel) {
        if (side == aFacing) {
            if (aActive) {
                return new ITexture[] { casingTexturePages[0][12], TextureFactory.builder()
                    .addIcon(OVERLAY_DTPF_ON)
                    .extFacing()
                    .build(),
                    TextureFactory.builder()
                        .addIcon(OVERLAY_FUSION1_GLOW)
                        .extFacing()
                        .glow()
                        .build() };
            }

            return new ITexture[] { casingTexturePages[0][12], TextureFactory.builder()
                .addIcon(OVERLAY_DTPF_OFF)
                .extFacing()
                .build() };
        }

        return new ITexture[] { casingTexturePages[0][12] };
    }
}
