package com.sakurac1oud.SenseofCherryBlossom.common.machine.multiblock;

import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.ISurvivalBuildEnvironment;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;
import com.sakurac1oud.SenseofCherryBlossom.common.machine.MultiMachineBase;
import com.sakurac1oud.SenseofCherryBlossom.common.machine.processingLogics.SoCBProcessingLogic;
import com.sakurac1oud.SenseofCherryBlossom.common.misc.OverclockType;
import com.sakurac1oud.SenseofCherryBlossom.common.recipe.RecipeMap.CherryBlossomRecipe;
import com.sakurac1oud.SenseofCherryBlossom.utils.TextLocalization;
import gregtech.api.GregTechAPI;
import gregtech.api.enums.HeatingCoilLevel;
import gregtech.api.gui.modularui.GTUITextures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.recipe.check.CheckRecipeResultRegistry;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GTRecipe;
import gregtech.api.util.HatchElementBuilder;
import gregtech.api.util.MultiblockTooltipBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.*;
import static gregtech.api.enums.HatchElement.*;
import static gregtech.api.enums.Textures.BlockIcons.*;
import static gregtech.api.util.GTStructureUtility.ofCoil;

public class CherryBlossomLCR extends MultiMachineBase<CherryBlossomLCR> {

    private static final String STRUCTURE_PIECE_MAIN = "main";
    private final int horizontalOffSet = 3;
    private final int verticalOffSet = 4;
    private final int depthOffSet = 0;
    private static final int SpeedUpMultiplierCherryBlossomLCR_1 = 1;
    private static final int SpeedUpMultiplierCherryBlossomLCR_2 = 5;
    private static final int ParallelMultiplierCherryBlossomLCR_1 = 16;
    private static final int ParallelMultiplierCherryBlossomLCR_2 = 64;
    private static IStructureDefinition<CherryBlossomLCR> STRUCTURE_DEFINITION = null;

    public CherryBlossomLCR(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public CherryBlossomLCR(String aName) {
        super(aName);
    }

    private int mHeatingCapacity = 0;

    // region Processing Logic
    private HeatingCoilLevel coilLevel;

    @Override
    public int totalMachineMode() {
        /*
         * 0 - ThePassagetoYominoHirasaka
         * 1 - Chemical Reactor
         */
        return 2;
    }
    @Override
    public void setMachineModeIcons() {
        machineModeIcons.add(GTUITextures.OVERLAY_BUTTON_MACHINEMODE_SINGULARITY);
        machineModeIcons.add(GTUITextures.OVERLAY_BUTTON_MACHINEMODE_CHEMBATH);
    }

    public void setCoilLevel(HeatingCoilLevel aCoilLevel) {
        this.coilLevel = aCoilLevel;
    }

    public HeatingCoilLevel getCoilLevel() {
        return this.coilLevel;
    }

    @Override
    public RecipeMap<?> getRecipeMap() {
        if(machineMode == 0) return CherryBlossomRecipe.ThePassagetoYominoHirasaka;
        return RecipeMaps.multiblockChemicalReactorRecipes;
    }

    @NotNull
    @Override
    public Collection<RecipeMap<?>> getAvailableRecipeMaps() {
        return Arrays.asList(CherryBlossomRecipe.ThePassagetoYominoHirasaka,RecipeMaps.multiblockChemicalReactorRecipes);
    }

    @Override
    protected ProcessingLogic createProcessingLogic() {
        return new SoCBProcessingLogic() {

            @NotNull
            @Override
            public CheckRecipeResult process() {

                setEuModifier(getEuModifier());
                setSpeedBonus(getSpeedBonus());
                setOverclockType(
                    isEnablePerfectOverclock() ? OverclockType.PerfectOverclock : OverclockType.NormalOverclock);
                return super.process();
            }

            @Override
            protected @NotNull CheckRecipeResult validateRecipe(GTRecipe recipe) {
                return recipe.mSpecialValue <= coilLevel.getHeat() ? CheckRecipeResultRegistry.SUCCESSFUL
                    : CheckRecipeResultRegistry.insufficientHeat(recipe.mSpecialValue);
            }

        }.enablePerfectOverclock()
            .setMaxParallelSupplier(this::getMaxParallelRecipes);

    }

    @Override
    protected boolean isEnablePerfectOverclock() {
        return true;
    }

    @Override
    protected float getSpeedBonus() {
        return machineMode == 0 ? 1F / SpeedUpMultiplierCherryBlossomLCR_1
            : 1F / SpeedUpMultiplierCherryBlossomLCR_2;
    }

    @Override
    protected int getMaxParallelRecipes() {
        return machineMode == 0 ? ParallelMultiplierCherryBlossomLCR_1
            : ParallelMultiplierCherryBlossomLCR_2;
    }

    public String getMachineModeName(int mode) {
        return StatCollector.translateToLocal("CherryBlossomLCR.modeMsg." + mode);
    }


    private final String[][] shape =new String[][]{
        {"       ","       ","   G   ","  GGG  ","   G   ","       ","       "},
        {"       ","   D   ","  GAG  "," DA AD ","  GAG  ","   D   ","       "},
        {"  EHE  ","  GDG  ","EGDADGE","HDA ADH","EGDADGE","  GDG  ","  EHE  "},
        {"  EHE  "," DGDGD ","EGDADGE","HDA ADH","EGDADGE"," DGDGD ","  EHE  "},
        {"D E~E D","  GDG  ","EGDADGE","HDA ADH","EGDADGE","  GDG  ","D EHE D"},
        {"B BBB B"," BCCCB ","BCFFFCB","BCFIFCB","BCFFFCB"," BCCCB ","B BBB B"}
    };

    public IStructureDefinition<CherryBlossomLCR> getStructureDefinition(){
        if (STRUCTURE_DEFINITION == null) {
            STRUCTURE_DEFINITION = StructureDefinition.<CherryBlossomLCR>builder()
                .addShape(STRUCTURE_PIECE_MAIN, transpose(shape))
                .addElement('A', withChannel(
                    "coil",
                    ofCoil(
                        CherryBlossomLCR::setCoilLevel,
                        CherryBlossomLCR::getCoilLevel)))
                // 针对化学反应器优化的外壳选择
                .addElement('B', ofBlock(GregTechAPI.sBlockCasings8, 0)) //
                .addElement('E', ofBlock(GregTechAPI.sBlockCasings2, 1)) //
                .addElement('D', ofBlock(GregTechAPI.sBlockFrames, 316)) //
                .addElement('G', ofBlock(GregTechAPI.sBlockCasings4, 1)) //
                .addElement('C', ofBlock(GregTechAPI.sBlockCasings2, 9)) //
                .addElement('F', ofBlock(GregTechAPI.sBlockCasings9, 5)) //
                .addElement(
                    'H',
                    HatchElementBuilder.<CherryBlossomLCR>builder()
                        .atLeast(InputHatch, OutputHatch, InputBus, OutputBus)
                        // blockcasings1:11 的正确casingIndex计算:
                        // (blockId=12 << 4) | meta=11 = 203
                        .casingIndex(25)
                        .dot(1)
                        .buildAndChain(GregTechAPI.sBlockCasings2, 5))
                .addElement(
                    'I',
                    HatchElementBuilder.<CherryBlossomLCR>builder()
                .atLeast(Energy.or(ExoticEnergy))
                // blockcasings1:11 的正确casingIndex计算:
                // (blockId=12 << 4) | meta=11 = 203
                .casingIndex(25)
                .dot(1)
                .buildAndChain(GregTechAPI.sBlockCasings9, 5)) //
                .build();
        }
        return STRUCTURE_DEFINITION;
    }

    @Override
    public boolean drainEnergyInput(long aEUt) {
        return true;
    }

    public int getmHeatingCapacity() {
        return mHeatingCapacity;
    }

    @Override
    public boolean addToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
        return super.addToMachineList(aTileEntity, aBaseCasingIndex)
            || addExoticEnergyInputToMachineList(aTileEntity, aBaseCasingIndex);
    }

    @Override
    public void construct(ItemStack stackSize, boolean hintsOnly) {
        buildPiece(STRUCTURE_PIECE_MAIN, stackSize, hintsOnly, horizontalOffSet, verticalOffSet, depthOffSet);
    }

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
    public boolean isCorrectMachinePart(ItemStack aStack) {
        return true;
    }

    /**
     * Checks the Machine. You have to assign the MetaTileEntities for the Hatches here.
     *
     * @param aBaseMetaTileEntity
     * @param aStack
     */
    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        repairMachine();
        coilLevel = HeatingCoilLevel.None;
        // this.casingAmountActual = 0; // re-init counter
        return checkPiece(STRUCTURE_PIECE_MAIN, horizontalOffSet, verticalOffSet, depthOffSet);
    }

    /**
     * Gets the maximum Efficiency that spare Part can get (0 - 10000)
     *
     * @param aStack
     */
    @Override
    public int getMaxEfficiency(ItemStack aStack) {
        return 100000;
    }

    /**
     * Gets the damage to the ItemStack, usually 0 or 1.
     *
     * @param aStack
     */
    @Override
    public int getDamageToComponent(ItemStack aStack) {
        return 0;
    }

    /**
     * If it explodes when the Component has to be replaced.
     *
     * @param aStack
     */
    @Override
    public boolean explodesOnComponentBreak(ItemStack aStack) {
        return false;
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

    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        super.saveNBTData(aNBT);

        aNBT.setInteger("mode", machineMode);
    }

    @Override
    public void loadNBTData(final NBTTagCompound aNBT) {
        super.loadNBTData(aNBT);

        machineMode = aNBT.getInteger("mode");
    }

    /**
     * @param aTileEntity is just because the internal Variable "mBaseMetaTileEntity" is set after this Call.
     * @return a newly created and ready MetaTileEntity
     */
    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new CherryBlossomLCR(this.mName);
    }

    /**
     * Icon of the Texture. If this returns null then it falls back to getTextureIndex.
     *
     * @param aBaseMetaTileEntity
     * @param side                is the Side of the Block
     * @param facing              is the direction the Block is facing
     * @param aColorIndex         The Minecraft Color the Block is having
     * @param aActive             if the Machine is currently active (use this instead of calling
     *                            {@code mBaseMetaTileEntity.mActive)}. Note: In case of Pipes this means if this Side
     *                            is
     *                            connected to something or not.
     * @param aRedstone           if the Machine is currently outputting a RedstoneSignal (use this instead of calling
     *                            {@code mBaseMetaTileEntity.mRedstone} !!!)
     */
    @Override
    public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, ForgeDirection side, ForgeDirection facing,
                                 int aColorIndex, boolean aActive, boolean aRedstone) {
        if (side == facing) {
            if (aActive) return new ITexture[] { casingTexturePages[1][48], TextureFactory.builder()
                .addIcon(OVERLAY_FRONT_LARGE_CHEMICAL_REACTOR_ACTIVE)
                .extFacing()
                .build(),
                TextureFactory.builder()
                    .addIcon(OVERLAY_FRONT_LARGE_CHEMICAL_REACTOR_ACTIVE_GLOW)
                    .extFacing()
                    .glow()
                    .build() };
            return new ITexture[] { casingTexturePages[1][48], TextureFactory.builder()
                .addIcon(OVERLAY_FRONT_LARGE_CHEMICAL_REACTOR)
                .extFacing()
                .build(),
                TextureFactory.builder()
                    .addIcon(OVERLAY_FRONT_LARGE_CHEMICAL_REACTOR_GLOW)
                    .extFacing()
                    .glow()
                    .build() };
        }
        return new ITexture[] { casingTexturePages[1][48] };
    }

    // Tooltips
    @Override
    protected MultiblockTooltipBuilder createTooltip() {
        final MultiblockTooltipBuilder tt = new MultiblockTooltipBuilder();
        tt.addMachineType(TextLocalization.Tooltip_CherryBlossomLCR_MachineType)
            .addInfo(TextLocalization.Tooltip_CherryBlossomLCR_00)
            .addInfo(TextLocalization.Tooltip_CherryBlossomLCR_01)
            .addInfo(TextLocalization.Tooltip_CherryBlossomLCR_02)
            .addInfo(TextLocalization.Tooltip_CherryBlossomLCR_03)
            .addInfo(TextLocalization.Tooltip_CherryBlossomLCR_04)
            .addInfo(TextLocalization.StructureTooComplex)
            .addInfo(TextLocalization.BLUE_PRINT_INFO)
            .addSeparator()
            .beginStructureBlock(11, 13, 11, false)
            .addController(TextLocalization.textFrontBottom)
            .addCasingInfoRange(TextLocalization.textCasing, 8, 26, false)
            .addInputHatch(TextLocalization.textAnyCasing, 1)
            .addOutputHatch(TextLocalization.textAnyCasing, 1)
            .addInputBus(TextLocalization.textAnyCasing, 2)
            .addOutputBus(TextLocalization.textAnyCasing, 2)
            .addEnergyHatch(TextLocalization.textAnyCasing, 3)
            .toolTipFinisher(TextLocalization.ModName);
        return tt;
    }

}
