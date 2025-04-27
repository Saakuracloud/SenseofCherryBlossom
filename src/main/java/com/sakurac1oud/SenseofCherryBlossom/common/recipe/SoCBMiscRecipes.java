package com.sakurac1oud.SenseofCherryBlossom.common.recipe;

import com.sakurac1oud.SenseofCherryBlossom.common.CherryBlossomItemList;
import com.sakurac1oud.SenseofCherryBlossom.common.material.MaterialCherryBlossom;
import gregtech.api.enums.GTValues;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GTRecipe;
import net.minecraft.item.ItemStack;
import static gregtech.api.enums.TierEU.*;

public class SoCBMiscRecipes {
    public static void loadRecipes() {
        GTValues.RA.stdBuilder()
            .itemInputs(
                CherryBlossomItemList
                    .CherryBlossomCircuit_ULV.get(16),
                CherryBlossomItemList.CherryBlossomCircuit_LV.get(16),
                CherryBlossomItemList.CherryBlossomCircuit_MV.get(16),
                CherryBlossomItemList.CherryBlossomCircuit_HV.get(16),
                CherryBlossomItemList.CherryBlossomCircuit_EV.get(16),
                CherryBlossomItemList.CherryBlossomCircuit_IV.get(16),
                CherryBlossomItemList.CherryBlossomCircuit_LuV.get(16),
                CherryBlossomItemList.CherryBlossomCircuit_ZPM.get(16),
                CherryBlossomItemList.CherryBlossomCircuit_UV.get(16),
                CherryBlossomItemList.CherryBlossomCircuit_UHV.get(16),
                CherryBlossomItemList.CherryBlossomCircuit_UEV.get(16),
                CherryBlossomItemList.CherryBlossomCircuit_UIV.get(16),
                CherryBlossomItemList.CherryBlossomCircuit_UMV.get(16),
                CherryBlossomItemList.CherryBlossomCircuit_UXV.get(16),
                CherryBlossomItemList.CherryBlossomCircuit_MAX.get(16)
            )
            .fluidInputs(
                MaterialCherryBlossom.CherryBlossomDreamMatter.getFluid(1440)
            )
            .itemOutputs(new ItemStack[]{CherryBlossomItemList.initial_core.get(1)}, new int[]{1})
            .eut(RECIPE_UIV)
            .duration(2000)
            .addTo(RecipeMaps.mixerRecipes);
    }
}
