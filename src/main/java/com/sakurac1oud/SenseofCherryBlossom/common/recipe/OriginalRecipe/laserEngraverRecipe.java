package com.sakurac1oud.SenseofCherryBlossom.common.recipe.OriginalRecipe;

import com.sakurac1oud.SenseofCherryBlossom.common.material.MaterialCherryBlossom;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.Materials;
import gregtech.api.enums.TierEU;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GTUtility;
import gtPlusPlus.xmod.gregtech.api.enums.GregtechItemList;

public class laserEngraverRecipe {
    public static void loadRecipes() {
        // Get Quantum Anomaly from MiscUtils mod

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(1))
            .fluidInputs(Materials.UUMatter.getFluid(500))
            .fluidOutputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(100))
            .eut(TierEU.RECIPE_IV)
            .duration(20 * 30)
            .addTo(RecipeMaps.laserEngraverRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(2))
            .fluidInputs(Materials.UUMatter.getFluid(500),Materials.Americium.getMolten(500))
            .fluidOutputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(500))
            .eut(TierEU.RECIPE_ZPM)
            .duration(20 * 30)
            .addTo(RecipeMaps.laserEngraverRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(3),GTUtility.copyAmountUnsafe(0,GregtechItemList.Laser_Lens_Special.get(1)))
            .fluidInputs(Materials.UUMatter.getFluid(1000))
            .fluidOutputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(500))
            .eut(TierEU.RECIPE_UHV)
            .duration(20 * 30)
            .addTo(RecipeMaps.laserEngraverRecipes);
    }
}

