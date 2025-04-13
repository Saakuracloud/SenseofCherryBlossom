package com.sakurac1oud.SenseofCherryBlossom.common.recipe.MachineRecipe;

import javax.annotation.ParametersAreNonnullByDefault;

import bartworks.system.material.WerkstoffLoader;
import com.dreammaster.gthandler.CustomItemList;
import com.gtnewhorizons.modularui.api.math.Pos2d;
import com.sakurac1oud.SenseofCherryBlossom.common.CherryBlossomItemList;
import com.sakurac1oud.SenseofCherryBlossom.common.material.MaterialCherryBlossom;
import com.sakurac1oud.SenseofCherryBlossom.common.recipe.RecipeMap.CherryBlossomRecipe;
import gregtech.api.enums.Materials;
import com.sakurac1oud.SenseofCherryBlossom.common.recipe.RecipeMap.Frontend.CherryBlossomGeneralFrontend;
import com.sakurac1oud.SenseofCherryBlossom.utils.SoCBRecipeBuilder;
import gregtech.api.enums.OrePrefixes;
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
