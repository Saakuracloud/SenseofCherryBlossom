package com.sakurac1oud.SenseofCherryBlossom.common.recipe.MachineRecipe;

import javax.annotation.ParametersAreNonnullByDefault;

import bartworks.system.material.WerkstoffLoader;
import com.dreammaster.gthandler.CustomItemList;
import com.gtnewhorizons.modularui.api.math.Pos2d;
import com.sakurac1oud.SenseofCherryBlossom.common.CherryBlossomItemList;
import com.sakurac1oud.SenseofCherryBlossom.common.material.MaterialCherryBlossom;
import com.sakurac1oud.SenseofCherryBlossom.common.recipe.RecipeMap.CherryBlossomRecipe;
import gregtech.api.enums.*;
import com.sakurac1oud.SenseofCherryBlossom.common.recipe.RecipeMap.Frontend.CherryBlossomGeneralFrontend;
import com.sakurac1oud.SenseofCherryBlossom.utils.SoCBRecipeBuilder;
import gregtech.api.objects.ItemData;
import gregtech.api.recipe.BasicUIPropertiesBuilder;
import gregtech.api.recipe.NEIRecipePropertiesBuilder;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMapFrontend;
import gregtech.api.util.GTOreDictUnificator;
import gregtech.api.util.GTRecipe;
import gregtech.api.util.GTUtility;
import gregtech.nei.GTNEIDefaultHandler;
import gregtech.nei.GTNEIDefaultHandler.FixedPositionedStack;
import codechicken.nei.PositionedStack;
import gtPlusPlus.core.material.MaterialStack;
import gtPlusPlus.core.material.MaterialsOther;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static gregtech.api.enums.Materials.Water;
import static gregtech.api.enums.TierEU.*;

@ParametersAreNonnullByDefault
public class ThePassagetoYominoHirasaka {
    private static final RecipeMap<?> TPYH = CherryBlossomRecipe.ThePassagetoYominoHirasaka;

    public static void loadRecipes() {
        SoCBRecipeBuilder.builder()
                    .itemInputs(ItemList.Circuit_Board_Bio_Ultra.get(512),ItemList.Circuit_Chip_Biocell.get(1024),ItemList.Circuit_Wafer_Bioware.get(64))
                    .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(8000),Materials.UUMatter.getFluid(16000))
            .itemOutputs(CherryBlossomItemList.Hangonchou.get(64))
                    .eut(RECIPE_UIV)
                    .duration(1000)
                    .addTo(TPYH);

        SoCBRecipeBuilder.builder()
            .itemInputs(ItemList.Circuit_Board_Wetware_Extreme.get(2048),ItemList.Circuit_Chip_QPIC.get(512),ItemList.Circuit_Wetwaremainframe.get(64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(8000),Materials.UUMatter.getFluid(32000),Materials.Mutation.getFluid(1600),Materials.Radon.getGas(3200))
            .itemOutputs(CherryBlossomItemList.Hangonchou.get(4))
            .eut(RECIPE_UV)
            .duration(10000)
            .addTo(TPYH);

        SoCBRecipeBuilder.builder()
            .itemInputs(GTUtility.copyAmountUnsafe(0,CherryBlossomItemList.Hangonchou.get(1)))
            .fluidOutputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(250))
            .eut(RECIPE_UV)
            .duration(10000)
            .addTo(TPYH);

        SoCBRecipeBuilder.builder()
            .itemInputs(GTUtility.getIntegratedCircuit(1),GTUtility.copyAmountUnsafe(0,CherryBlossomItemList.Hangonchou.get(1)))
            .fluidInputs(Materials.UUMatter.getFluid(500))
            .fluidOutputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(500))
            .eut(RECIPE_UV)
            .duration(10000)
            .addTo(TPYH);

        SoCBRecipeBuilder.builder()
            .itemInputs(GTUtility.getIntegratedCircuit(2),GTUtility.copyAmountUnsafe(0,CherryBlossomItemList.Hangonchou.get(1)))
            .fluidInputs(Materials.GrowthMediumRaw.getFluid(1000))
            .fluidOutputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(1500))
            .eut(RECIPE_UHV)
            .duration(10000)
            .addTo(TPYH);

        SoCBRecipeBuilder.builder()
            .itemInputs(GTUtility.getIntegratedCircuit(3),GTUtility.copyAmountUnsafe(0,CherryBlossomItemList.Hangonchou.get(1)))
            .fluidInputs(Materials.GrowthMediumSterilized.getFluid(1000))
            .fluidOutputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(2000))
            .eut(RECIPE_UHV)
            .duration(10000)
            .addTo(TPYH);

        SoCBRecipeBuilder.builder()
            .itemInputs(GTUtility.getIntegratedCircuit(4),GTUtility.copyAmountUnsafe(0,CherryBlossomItemList.Hangonchou.get(1)))
            .fluidInputs(Materials.BioMediumRaw.getFluid(1000))
            .fluidOutputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(2500))
            .eut(RECIPE_UHV)
            .duration(10000)
            .addTo(TPYH);

        SoCBRecipeBuilder.builder()
            .itemInputs(GTUtility.getIntegratedCircuit(5),GTUtility.copyAmountUnsafe(0,CherryBlossomItemList.Hangonchou.get(1)))
            .fluidInputs(Materials.BioMediumSterilized.getFluid(1000))
            .fluidOutputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(3000))
            .eut(RECIPE_UHV)
            .duration(10000)
            .addTo(TPYH);

        SoCBRecipeBuilder.builder()
            .itemInputs(GTUtility.getIntegratedCircuit(6),GTUtility.copyAmountUnsafe(0,CherryBlossomItemList.Hangonchou.get(1)))
            .fluidInputs(Materials.Grade7PurifiedWater.getFluid(1000))
            .fluidOutputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(4000))
            .eut(RECIPE_UEV)
            .duration(10000)
            .addTo(TPYH);

        SoCBRecipeBuilder.builder()
            .itemInputs(GTUtility.getIntegratedCircuit(7),GTUtility.copyAmountUnsafe(0,CherryBlossomItemList.Hangonchou.get(1)))
            .fluidInputs(Materials.Grade8PurifiedWater.getFluid(1000))
            .fluidOutputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(5000))
            .eut(RECIPE_UEV)
            .duration(10000)
            .addTo(TPYH);

        SoCBRecipeBuilder.builder()
            .itemInputs(GTUtility.getIntegratedCircuit(8),GTUtility.copyAmountUnsafe(0,CherryBlossomItemList.Hangonchou.get(1)))
            .fluidInputs(Materials.Infinity.getMolten(1000))
            .fluidOutputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(10000))
            .eut(RECIPE_UEV)
            .duration(10000)
            .addTo(TPYH);

        SoCBRecipeBuilder.builder()
            .itemInputs(GTUtility.getIntegratedCircuit(9),GTUtility.copyAmountUnsafe(0,CherryBlossomItemList.Hangonchou.get(1)))
            .fluidInputs(MaterialsUEVplus.SpaceTime.getFluid(1000))
            .fluidOutputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(50000))
            .eut(RECIPE_UIV)
            .duration(10000)
            .addTo(TPYH);

        SoCBRecipeBuilder.builder()
            .itemInputs(GTUtility.getIntegratedCircuit(10),GTUtility.copyAmountUnsafe(0,CherryBlossomItemList.Hangonchou.get(1)))
            .fluidInputs(MaterialsUEVplus.QuarkGluonPlasma.getFluid(1000))
            .fluidOutputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(100000),MaterialCherryBlossom.CherryBlossomDreamMatter.getPlasma(100000))
            .eut(RECIPE_UIV)
            .duration(10000)
            .addTo(TPYH);

        SoCBRecipeBuilder
            .builder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(1)
            )
            .fluidInputs(
                MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(1000),
                Water.getFluid(1000)
            )
            .fluidOutputs(
                Materials.Grade1PurifiedWater.getFluid(5000)
            )
            .specialValue(5400)
            .eut(RECIPE_ZPM)
            .duration(20 * 2)
            .addTo(TPYH);

        SoCBRecipeBuilder
            .builder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(2)
            )
            .fluidInputs(
                MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(1000),
                Water.getFluid(1000)
            )
            .fluidOutputs(
                Materials.Grade2PurifiedWater.getFluid(5000)
            )
            .specialValue(7800)
            .eut(RECIPE_ZPM)
            .duration(20 * 6)
            .addTo(TPYH);

        SoCBRecipeBuilder
            .builder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(3)
            )
            .fluidInputs(
                MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(1000),
                Materials.Grade2PurifiedWater.getFluid(1000)
            )
            .fluidOutputs(
                Materials.Grade3PurifiedWater.getFluid(4000)
            )
            .specialValue(7800)
            .eut(RECIPE_UV)
            .duration(20 * 18)
            .addTo(TPYH);

        SoCBRecipeBuilder
            .builder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(4)
            )
            .fluidInputs(
                MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(1000),
                Materials.Grade3PurifiedWater.getFluid(2000)
            )
            .fluidOutputs(
                Materials.Grade4PurifiedWater.getFluid(4000)
            )
            .specialValue(9000)
            .eut(RECIPE_UV)
            .duration(20 * 54)
            .addTo(TPYH);

        SoCBRecipeBuilder
            .builder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(5)
            )
            .fluidInputs(
                MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(2000),
                Materials.Grade4PurifiedWater.getFluid(2000)
            )
            .fluidOutputs(
                Materials.Grade5PurifiedWater.getFluid(8000)
            )
            .specialValue(9000)
            .eut(RECIPE_UV)
            .duration(20 * 54)
            .addTo(TPYH);

        SoCBRecipeBuilder
            .builder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(6)
            )
            .fluidInputs(
                MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(2000),
                Materials.Grade5PurifiedWater.getFluid(3000)
            )
            .fluidOutputs(
                Materials.Grade6PurifiedWater.getFluid(8000)
            )
            .specialValue(9000)
            .eut(RECIPE_UHV)
            .duration(20 * 54)
            .addTo(TPYH);

        SoCBRecipeBuilder
            .builder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(7)
            )
            .fluidInputs(
                MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(3000),
                Materials.Grade6PurifiedWater.getFluid(4000)
            )
            .fluidOutputs(
                Materials.Grade7PurifiedWater.getFluid(10000)
            )
            .specialValue(10500)
            .eut(RECIPE_UHV)
            .duration(20 * 54)
            .addTo(TPYH);

        SoCBRecipeBuilder
            .builder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(8),
                GTUtility.copyAmountUnsafe(0, CustomItemList.RadoxPolymerLens.get(1))
            )
            .fluidInputs(
                MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(3000),
                Materials.Grade7PurifiedWater.getFluid(6000)
            )
            .fluidOutputs(
                Materials.Grade8PurifiedWater.getFluid(10000),
                Materials.StableBaryonicMatter.getFluid(5000)
            )
            .specialValue(12500)
            .eut(RECIPE_UEV)
            .duration(20 * 54)
            .addTo(TPYH);

        SoCBRecipeBuilder
            .builder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(9),
                GTUtility.copyAmountUnsafe(0, CherryBlossomItemList.initial_core.get(1))
            )
            .fluidInputs(
                MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(100000),
                Water.getFluid(1000000)
            )
            .fluidOutputs(
                Materials.Grade1PurifiedWater.getFluid(100000),
                Materials.Grade2PurifiedWater.getFluid(100000),
                Materials.Grade3PurifiedWater.getFluid(100000),
                Materials.Grade4PurifiedWater.getFluid(100000),
                Materials.Grade5PurifiedWater.getFluid(100000),
                Materials.Grade6PurifiedWater.getFluid(100000),
                Materials.Grade7PurifiedWater.getFluid(100000),
                Materials.Grade8PurifiedWater.getFluid(100000),
                Materials.StableBaryonicMatter.getFluid(50000)
            )
            .specialValue(14400)
            .eut(RECIPE_UIV)
            .duration(20 * 756)
            .addTo(TPYH);

//        SoCBRecipeBuilder
//            .builder()
//            .itemInputs(
//                GTUtility.copyAmountUnsafe(0,)
//            )
    }
}
