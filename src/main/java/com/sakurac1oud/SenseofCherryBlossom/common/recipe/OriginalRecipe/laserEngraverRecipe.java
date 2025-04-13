package com.sakurac1oud.SenseofCherryBlossom.common.recipe.OriginalRecipe;

import com.sakurac1oud.SenseofCherryBlossom.common.material.MaterialCherryBlossom;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.Materials;
import gregtech.api.enums.TierEU;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GTUtility;

public class laserEngraverRecipe {
    public static void loadRecipes() {
        // Get Quantum Anomaly from MiscUtils mod

        GTValues.RA.stdBuilder()
            .itemInputs(Materials.Gold.getDust(1))
            .fluidInputs(Materials.UUMatter.getFluid(500))
            .fluidOutputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(100))
            .eut(TierEU.RECIPE_IV)
            .duration(20 * 30)
            .outputChances(50)
            .addTo(RecipeMaps.laserEngraverRecipes);

    }
}
