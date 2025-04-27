package com.sakurac1oud.SenseofCherryBlossom.common.recipe.RecipeMap;

import com.sakurac1oud.SenseofCherryBlossom.common.CherryBlossomItemList;
import com.sakurac1oud.SenseofCherryBlossom.common.recipe.RecipeMap.Frontend.CherryBlossomGeneralFrontend;
import gregtech.api.gui.modularui.GTUITextures;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMapBuilder;
import gregtech.api.recipe.RecipeMapFrontend;
import gregtech.nei.formatter.HeatingCoilSpecialValueFormatter;

public class CherryBlossomRecipe
{
    public static final RecipeMap<CherryBlossomRecipeMapBackend> ThePassagetoYominoHirasaka = RecipeMapBuilder
        // At the same time , the localization key of the NEI Name
        // of this page.
        .of("senseofcherryblossom.recipe.ThePassagetoYominoHirasaka", CherryBlossomRecipeMapBackend::new)
        .maxIO(16, 16, 16, 16)
        .neiSpecialInfoFormatter(HeatingCoilSpecialValueFormatter.INSTANCE)
        .progressBar(GTUITextures.PROGRESSBAR_ARROW_MULTIPLE)
        .frontend(CherryBlossomGeneralFrontend::new)
        .neiHandlerInfo(
            builder -> builder.setDisplayStack(CherryBlossomItemList.CherryBlossomLCR.get(1))
                .setMaxRecipesPerPage(1))
        .disableOptimize()
        .build();

    public static final RecipeMap<CherryBlossomRecipeMapBackend> TheBorderofLife = RecipeMapBuilder
        .of("senseofcherryblossom.recipe.TheBorderofLife", CherryBlossomRecipeMapBackend::new)
        .maxIO(8, 20, 8, 0)
        .neiSpecialInfoFormatter(HeatingCoilSpecialValueFormatter.INSTANCE)
        .progressBar(GTUITextures.PROGRESSBAR_ARROW_MULTIPLE)
        .frontend(CherryBlossomGeneralFrontend::new)
        .neiHandlerInfo(
            builder -> builder.setDisplayStack(CherryBlossomItemList.CherryBlossomBorderofLife.get(1))
                .setMaxRecipesPerPage(1))
        .disableOptimize()
        .build();

    public static final RecipeMap<CherryBlossomRecipeMapBackend> PromiseTickettotheLandofImmortality = RecipeMapBuilder
        .of("senseofcherryblossom.recipe.PromiseTickettotheLandofImmortality", CherryBlossomRecipeMapBackend::new)
        .maxIO(6, 4, 6, 4)
        .neiSpecialInfoFormatter(HeatingCoilSpecialValueFormatter.INSTANCE)
        .progressBar(GTUITextures.PROGRESSBAR_ARROW_MULTIPLE)
        .frontend(CherryBlossomGeneralFrontend::new)
        .neiHandlerInfo(
            builder -> builder.setDisplayStack(CherryBlossomItemList.Hangonchou.get(2))
                .setMaxRecipesPerPage(2))
        .disableOptimize()
        .build();
}
