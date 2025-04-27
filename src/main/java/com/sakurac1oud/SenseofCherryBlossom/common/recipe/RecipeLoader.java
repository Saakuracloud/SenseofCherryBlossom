package com.sakurac1oud.SenseofCherryBlossom.common.recipe;

import com.sakurac1oud.SenseofCherryBlossom.common.recipe.MachineRecipe.BorderofLife;
import com.sakurac1oud.SenseofCherryBlossom.common.recipe.MachineRecipe.PromiseTickettotheLandofImmortality;
import com.sakurac1oud.SenseofCherryBlossom.common.recipe.MachineRecipe.ThePassagetoYominoHirasaka;
import com.sakurac1oud.SenseofCherryBlossom.common.recipe.OriginalRecipe.laserEngraverRecipe;
import com.sakurac1oud.SenseofCherryBlossom.common.recipe.OriginalRecipe.assemblylineVisualRecipes;
public class RecipeLoader {
    public static void loadRecipes() {
        SoCBCircuitRecipes.loadRecipes();
        SoCBMiscRecipes.loadRecipes();
        SoCBMachineRecipes.loadRecipes();
        assemblylineVisualRecipes.loadRecipes();
        ThePassagetoYominoHirasaka.loadRecipes();
        BorderofLife.loadRecipes();
        laserEngraverRecipe.loadRecipes();
        PromiseTickettotheLandofImmortality.loadRecipes();
    }

    public static void loadRecipemixin() {
        // new Mode3SimulatorFakeRecipe().loadRecipes();
    }
}
