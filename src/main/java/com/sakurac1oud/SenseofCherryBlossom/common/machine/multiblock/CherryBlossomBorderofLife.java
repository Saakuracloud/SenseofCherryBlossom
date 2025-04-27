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
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
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
import gtPlusPlus.core.block.ModBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.*;
import static gregtech.api.enums.HatchElement.*;
import static gregtech.api.enums.Textures.BlockIcons.*;
import static gregtech.api.util.GTStructureUtility.ofCoil;
import static tectech.thing.casing.TTCasingsContainer.sBlockCasingsBA0;
import static tectech.thing.casing.TTCasingsContainer.sBlockCasingsTT;

public class CherryBlossomBorderofLife extends MultiMachineBase<CherryBlossomBorderofLife> {

    private static final String STRUCTURE_PIECE_MAIN = "main";
    private final int horizontalOffSet = 8;
    private final int verticalOffSet = 28;
    private final int depthOffSet = 5;
    private static final int SpeedUpMultiplierBounderofLife_1 = 16;
    private static final int SpeedUpMultiplierBounderofLife_2 = 8;
    private static final int ParallelMultiplierBounderofLife_1 = 16384;
    private static final int ParallelMultiplierBounderofLife_2 = 128;
    private static IStructureDefinition<CherryBlossomBorderofLife> STRUCTURE_DEFINITION = null;

    private static final RecipeMap<?> BoL = CherryBlossomRecipe.TheBorderofLife;
    public static final HashMap<ItemStack, ItemStack> circuitItemsToWrapped = new HashMap<>();
    private static final HashSet<Materials> superConductorMaterialList = new HashSet<>();
    private static final HashSet<OrePrefixes> targetModifyOreDict = new HashSet<>();
    private static final HashMap<ItemStack, FluidStack> specialMaterialCantAutoModify = new HashMap<>();

    private int mHeatingCapacity = 14400;

    // region Processing Logic
    private HeatingCoilLevel coilLevel;

    public CherryBlossomBorderofLife(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public CherryBlossomBorderofLife(String aName) {
        super(aName);
    }

    @Override
    public int totalMachineMode() {
        /*
         * 0 - CircuitAssembler
         * 1 - BorderofLife
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
        return CherryBlossomRecipe.TheBorderofLife;
    }

    @NotNull
    @Override
    public Collection<RecipeMap<?>> getAvailableRecipeMaps() {
        return Arrays.asList(CherryBlossomRecipe.ThePassagetoYominoHirasaka,CherryBlossomRecipe.TheBorderofLife);
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
        return machineMode == 0 ? 1F / SpeedUpMultiplierBounderofLife_1
            : 1F / SpeedUpMultiplierBounderofLife_2;
    }

    @Override
    protected float getEuModifier() {
        return 100;
    }

    @Override
    protected int getMaxParallelRecipes() {
        return machineMode == 0 ? ParallelMultiplierBounderofLife_1
            : ParallelMultiplierBounderofLife_2;
    }

    public String getMachineModeName(int mode) {
        return StatCollector.translateToLocal("CherryBlossomBorderofLife.modeMsg." + mode);
    }


    private final String[][] shape = new String[][] {
        // Layer 1
        { "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   " },
        // Layer 2
        { "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                  ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   " },
        // Layer 3
        { "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "        A          ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   " },
        // Layer 4
        { "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "        A          ", "       AAA         ", "        A          ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   " },
        // Layer 5
        { "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "        A          ", "        A          ", "        A          ", "     KKKAKKK       ", "        A          ", "        A          ", "        A          ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   " },
        // Layer 6
        { "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "      K E K        ", "     K AEA K       ", "      AgAgA        ", "     EEAAAEE       ", "      AgAgA        ", "     K AEA K       ", "      K E K        ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   " },
        // Layer 7
        { "                   ", "                   ", "                   ", "                   ", "       EEE         ", "     AEEEEEA       ", "    AEEEEEEEA      ", "    EEE   EEE      ", "   EEE     EEE     ", "   EEE  A  EEE     ", "   EEE     EEE     ", "    EEE   EEE      ", "    AEEEEEEEA      ", "     AEEEEEA       ", "       EEE         ", "                   ", "                   ", "                   ", "                   " },
        // Layer 8
        { "                   ", "                   ", "                   ", "       EEE         ", "    AEE   EEA      ", "   AE       EA     ", "   E         E     ", "   E         E     ", "  E     d     E    ", "  E    mPm    E    ", "  E     d     E    ", "   E         E     ", "   E         E     ", "   AE       EA     ", "    AEE   EEA      ", "       EEE         ", "                   ", "                   ", "                   " },
        // Layer 9
        { "                   ", "                   ", "       EEE         ", "    AEE   EEA      ", "   E         E     ", "  A           A    ", "  E           E    ", "  E           E    ", " E             E   ", " E      Q      E   ", " E             E   ", "  E           E    ", "  E           E    ", "  A           A    ", "   E         E     ", "    AEE   EEA      ", "       EEE         ", "                   ", "                   " },
        // Layer 10
        { "                   ", "                   ", "    A EEEEE A      ", "    EE     EE      ", "   E         E     ", " AE           EA   ", "  E           E    ", " E             E   ", " E             E   ", " E      T      E   ", " E             E   ", " E             E   ", "  E           E    ", " AE           EA   ", "   E         E     ", "    EE     EE      ", "    A EEEEE A      ", "                   ", "                   " },
        // Layer 11
        { "                   ", "        E          ", "    AEEE EEEA      ", "    E       E      ", "   E         E     ", " AE           EA   ", " E             E   ", " E             E   ", " E             E   ", "E       T       E  ", " E             E   ", " E             E   ", " E             E   ", " AE           EA   ", "   E         E     ", "    E       E      ", "    AEEE EEEA      ", "        E          ", "                   " },
        // Layer 12
        { "                   ", "    AF EEE FA      ", "    EEE   EEE      ", "   E         E     ", "  EE         EE    ", "AE             EA  ", "FE             EF  ", " E             E   ", "E               E  ", "E       T       E  ", "E               E  ", " E             E   ", "FE             EF  ", "AE             EA  ", "  EE         EE    ", "   E         E     ", "    EEE   EEE      ", "    AF EEE FA      ", "                   " },
        // Layer 13
        { "                   ", "    ABDEEEDBA      ", "    B       B      ", "   E         E     ", "  E           E    ", "AB             BA  ", "B               B  ", "D               D  ", "E               E  ", "E       Q       E  ", "E               E  ", "D               D  ", "B               B  ", "AB             BA  ", "  E           E    ", "   E         E     ", "    B       B      ", "    ABDEEEDBA      ", "                   " },
        // Layer 14
        { "                   ", "     ABDDDBA       ", "   BJ       JB     ", "   E         E     ", " BE     O     EB   ", " J      N      J   ", "A       N       A  ", "B       O       B  ", "D               D  ", "D  ONNO Q ONNO  D  ", "D               D  ", "B       O       B  ", "A       N       A  ", " J      N      J   ", " BE     O     EB   ", "   E         E     ", "   BJ       JB     ", "     ABDDDBA       ", "                   " },
        // Layer 15
        { "                   ", "      ABBBA        ", "  BJ         JB    ", " B             B   ", " J      T      J   ", "                   ", "                   ", "A               A  ", "B       d       B  ", "B  T   rTr   T  B  ", "B       d       B  ", "A               A  ", "                   ", "                   ", " J      T      J   ", "  B           B    ", "   J         J     ", "      ABBBA        ", "                   " },
        // Layer 16
        { "        B          ", "       A A         ", "                   ", "  J           J    ", "        S          ", "                   ", "                   ", "                   ", "A       d       A  ", "   S   ATA   S   B ", "A       d       A  ", "                   ", "                   ", "                   ", "        S          ", "  J           J    ", "                   ", "       A A         ", "        B          " },
        // Layer 17
        { "        A          ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "A       k        A ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "        A          " },
        // Layer 18
        { "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "        T          ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   " },
        // Layer 19
        { "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "        T          ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   " },
        // Layer 20
        { "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "        T          ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   " },
        // Layer 21
        { "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "        k          ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   " },
        // Layer 22
        { "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "        A          ", "       AZA         ", "        A          ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   " },
        // Layer 23
        { "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "        k          ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   " },
        // Layer 24
        { "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "        T          ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   " },
        // Layer 25
        { "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "        T          ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   " },
        // Layer 26
        { "                   ", "                   ", "                   ", "  O           O    ", "                   ", "                   ", "                   ", "                   ", "                   ", "        k          ", "                   ", "                   ", "                   ", "                   ", "                   ", "  O           O    ", "                   ", "                   ", "                   " },
        // Layer 27
        { "                   ", "                   ", "     I     I       ", "   NI       IN     ", "  NB         BN    ", "  I           I    ", " I             I   ", "                   ", "        A          ", "       AkA         ", "        A          ", "                   ", " I             I   ", "  I           I    ", "  NB         BN    ", "   NI       IN     ", "     I     I       ", "                   ", "                   " },
        // Layer 28
        { "                   ", "                   ", "     H     H       ", "    M       M      ", "   M         M     ", "  M  VWX XWY  M    ", " H  K       K  H   ", "    K  XKX  K      ", "    X XPAPX X      ", "      KjkjK        ", "    X XPAPX X      ", "    K  XKX  K      ", " H           H     ", "  M  YX XY  M      ", "   M         M     ", "    M       M      ", "     H     H       ", "                   ", "                   " },
        // Layer 29
        { "                   ", "                   ", "                   ", "     BH   HB       ", "    BRR   RRB      ", "   BRRRC~CRRRB     ", "  BRRRRRRRRRRRB    ", "  HRRRRCRCRRRRH    ", "    CRCRRRCRC      ", "    URRRRRRRU      ", "    CRCRRRCRC      ", "  HRRRRCRCRRRRH    ", "  BRRRRRRRRRRRB    ", "   BRRRCUCRRRB     ", "    BRR   RRB      ", "     BH   HB       ", "                   ", "                   ", "                   " },
        // Layer 30
        { "                   ", "                   ", "    GGG   GGG      ", "   GLLGG GGLLG     ", "  GLPQQQHQQQPLG    ", " GLPQQQQQQQQQPLG   ", " GLQQQQQQQQQQQLG   ", " GGQQQQQQQQQQQGG   ", "  GQQQQQQQQQQQG    ", "   HQQQQQQQQQH     ", "  GQQQQQQQQQQQG    ", " GGQQQQQQQQQQQGG   ", " GLQQQQQQQQQQQLG   ", " GLPQQQQQQQQQPLG   ", "  GLPQQQHQQQPLG    ", "   GLLGG GGLLG     ", "    GGG   GGG      ", "                   ", "                   " },
        // Layer 31
        { "                   ", "    AAC   CAA      ", "       C C   A     ", "  A     H     A    ", " A             A   ", "A               A  ", "A    Z     Z    A  ", "C               C  ", " C             C   ", "  H           H    ", " C             C   ", "C               C  ", "A    Z     Z   KA  ", "A              KA  ", " A             A   ", "  A     H     A    ", "   A   C C   A     ", "    AAC   CAA      ", "                   " }};
    public IStructureDefinition<CherryBlossomBorderofLife> getStructureDefinition() {
        if (STRUCTURE_DEFINITION == null) {
            STRUCTURE_DEFINITION = StructureDefinition.<CherryBlossomBorderofLife>builder()
                .addShape(STRUCTURE_PIECE_MAIN, transpose(shape))
                .addElement('A', withChannel(
                    "coil",
                    ofCoil(
                        CherryBlossomBorderofLife::setCoilLevel,
                        CherryBlossomBorderofLife::getCoilLevel)))
                // 简化的结构定义
                .addElement('B', ofBlock(GregTechAPI.sBlockCasings8, 0))
                .addElement('H', ofBlock(sBlockCasingsTT, 1))
                .addElement('D', ofBlock(GregTechAPI.sBlockFrames, 316))
                .addElement('E', ofBlock(GregTechAPI.sBlockCasings2, 1))
                .addElement('F', ofBlock(GregTechAPI.sBlockCasings9, 5))
                .addElement('G', ofBlock(sBlockCasingsTT, 9))
                .addElement(
                    'C',
                    HatchElementBuilder.<CherryBlossomBorderofLife>builder()
                        .atLeast(InputHatch, OutputHatch, InputBus, OutputBus,Energy.or(ExoticEnergy))
                        // blockcasings1:11 的正确casingIndex计算:
                        // (blockId=12 << 4) | meta=11 = 203
                        .casingIndex(203)
                        .dot(1)
                        .buildAndChain(GregTechAPI.sBlockCasings2, 5))
                .addElement(
                    'I',
                        ofBlock(GregTechAPI.sBlockCasings9, 5))
                .addElement('J', ofBlock(GregTechAPI.sBlockCasings4, 2))
                .addElement('K', ofBlock(sBlockCasingsBA0, 12))
                .addElement('L', ofBlock(sBlockCasingsTT, 4))
                .addElement('M', ofBlock(sBlockCasingsTT, 5))
                .addElement('N', ofBlock(GregTechAPI.sBlockCasings4, 6))
                .addElement('O', ofBlock(GregTechAPI.sBlockCasings4, 7))
                .addElement('P', ofBlock(ModBlocks.blockCasingsMisc, 6))
                .addElement('Q',
                    HatchElementBuilder.<CherryBlossomBorderofLife>builder()
                        .atLeast(InputHatch, OutputHatch, InputBus, OutputBus,Energy.or(ExoticEnergy))
                        // blockcasings1:11 的正确casingIndex计算:
                        // (blockId=12 << 4) | meta=11 = 203
                        .casingIndex(203)
                        .dot(1)
                        .buildAndChain(GregTechAPI.sBlockCasings4, 9))
                .addElement('R', ofBlock(GregTechAPI.sBlockCasings4, 10))
                .addElement('S', ofBlock(GregTechAPI.sBlockCasings4, 11))
                .addElement('T', ofBlock(GregTechAPI.sBlockCasings4, 12))
                .addElement('U', ofBlock(GregTechAPI.sBlockCasings4, 13))
                .addElement('V', ofBlock(GregTechAPI.sBlockCasings4, 14))
                .addElement('W', ofBlock(GregTechAPI.sBlockCasings4, 15))
                .addElement('X', ofBlock(GregTechAPI.sBlockCasings10, 1))
                .addElement('Y', ofBlock(GregTechAPI.sBlockCasings10, 2))
                .addElement('Z', ofBlock(GregTechAPI.sBlockCasings10, 3))
                .addElement('a', ofBlock(GregTechAPI.sBlockCasings10, 4))
                .addElement('b', ofBlock(GregTechAPI.sBlockCasings10, 5))
                .addElement('c', ofBlock(GregTechAPI.sBlockCasings10, 6))
                .addElement('d', ofBlock(GregTechAPI.sBlockCasings10, 7))
                .addElement('e', ofBlock(GregTechAPI.sBlockCasings10, 8))
                .addElement('f', ofBlock(GregTechAPI.sBlockCasings11, 1))
                .addElement('g', ofBlock(sBlockCasingsBA0, 11))
                .addElement('h', ofBlock(GregTechAPI.sBlockCasings11, 3))
                .addElement('i', ofBlock(GregTechAPI.sBlockCasings11, 4))
                .addElement('j', ofBlock(GregTechAPI.sBlockCasings11, 5))
                .addElement('k', ofBlock(GregTechAPI.sBlockCasings11, 6))
                .addElement('l', ofBlock(GregTechAPI.sBlockCasings2, 1))
                .addElement('m', ofBlock(GregTechAPI.sBlockCasings2, 2))
                .addElement('n', ofBlock(GregTechAPI.sBlockCasings2, 3))
                .addElement('o', ofBlock(GregTechAPI.sBlockCasings2, 4))
                .addElement('p', ofBlock(GregTechAPI.sBlockCasings2, 5))
                .addElement('q', ofBlock(GregTechAPI.sBlockCasings2, 6))
                .addElement('r', ofBlock(GregTechAPI.sBlockCasings2, 7))
                .addElement('s', ofBlock(GregTechAPI.sBlockCasings2, 8))
                .addElement('t', ofBlock(GregTechAPI.sBlockCasings2, 9))
                .addElement('u', ofBlock(GregTechAPI.sBlockCasings2, 10))
                .addElement('v', ofBlock(GregTechAPI.sBlockCasings5, 1))
                .addElement('w', ofBlock(GregTechAPI.sBlockCasings5, 2))
                .addElement('x', ofBlock(GregTechAPI.sBlockCasings5, 3))
                .addElement('y', ofBlock(GregTechAPI.sBlockCasings5, 4))
                .addElement('z', ofBlock(GregTechAPI.sBlockCasings5, 5))
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

    @Override
    public boolean checkMachine(IGregTechTileEntity aBaseMetaTileEntity, ItemStack aStack) {
        repairMachine();
        coilLevel = HeatingCoilLevel.None;
        return checkPiece(STRUCTURE_PIECE_MAIN, horizontalOffSet, verticalOffSet, depthOffSet);
    }

    @Override
    public int getMaxEfficiency(ItemStack aStack) {
        return 100000;
    }

    @Override
    public int getDamageToComponent(ItemStack aStack) {
        return 0;
    }

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

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new CherryBlossomBorderofLife(this.mName);
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
                        .addIcon(OVERLAY_FUSION3_GLOW)
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

    // Tooltips
    @Override
    protected MultiblockTooltipBuilder createTooltip() {
        final MultiblockTooltipBuilder tt = new MultiblockTooltipBuilder();
        tt.addMachineType(TextLocalization.Tooltip_CherryBlossomBorderofLife_MachineType)
            .addInfo(TextLocalization.Tooltip_CherryBlossomBorderofLife_00)
            .addInfo(TextLocalization.Tooltip_CherryBlossomBorderofLife_01)
            .addInfo(TextLocalization.Tooltip_CherryBlossomBorderofLife_02)
            .addInfo(TextLocalization.Tooltip_CherryBlossomBorderofLife_03)
            .addInfo(TextLocalization.Tooltip_CherryBlossomBorderofLife_04)
            .addInfo(TextLocalization.Tooltip_CherryBlossomBorderofLife_05)
            .addInfo(TextLocalization.Tooltip_CherryBlossomBorderofLife_06)
            .addInfo(TextLocalization.Tooltip_CherryBlossomBorderofLife_07)
            .addInfo(TextLocalization.Tooltip_CherryBlossomBorderofLife_08)
            .addInfo(TextLocalization.Tooltip_CherryBlossomBorderofLife_09)
            .addInfo(TextLocalization.StructureTooComplex)
            .addInfo(TextLocalization.BLUE_PRINT_INFO)
            .addSeparator()
            .beginStructureBlock(19, 16, 19, false)
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
