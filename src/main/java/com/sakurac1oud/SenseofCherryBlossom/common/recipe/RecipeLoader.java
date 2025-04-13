package com.sakurac1oud.SenseofCherryBlossom.common.recipe;

import com.sakurac1oud.SenseofCherryBlossom.common.recipe.MachineRecipe.BorderofLife;
import com.sakurac1oud.SenseofCherryBlossom.common.recipe.MachineRecipe.ThePassagetoYominoHirasaka;

public class RecipeLoader {
    public static void loadRecipes() {
        ThePassagetoYominoHirasaka.loadRecipes();
        BorderofLife.loadRecipes();
    }

    public static void loadRecipemixin() {
        // new Mode3SimulatorFakeRecipe().loadRecipes();
    }
}
