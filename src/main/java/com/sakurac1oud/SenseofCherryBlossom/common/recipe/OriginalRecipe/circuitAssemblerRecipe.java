package com.sakurac1oud.SenseofCherryBlossom.common.recipe.OriginalRecipe;

import com.sakurac1oud.SenseofCherryBlossom.SenseofCherryBlossom;
import com.sakurac1oud.SenseofCherryBlossom.common.CherryBlossomItemList;
import com.sakurac1oud.SenseofCherryBlossom.common.material.MaterialCherryBlossom;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.ItemList;
import gregtech.api.recipe.RecipeMaps;

import static gregtech.api.enums.TierEU.*;

public class circuitAssemblerRecipe {
    public static void loadRecipes(){
        GTValues.RA
            .stdBuilder()
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1), ItemList.Circuit_Wafer_Simple_SoC.get(64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(16))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_ULV.get(2048))
            .eut(RECIPE_LuV)
            .duration(500)
            .addTo(RecipeMaps.circuitAssemblerRecipes);

        GTValues.RA
            .stdBuilder()
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1), ItemList.Circuit_Wafer_Ram.get(64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(16))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_LV.get(1792))
            .eut(RECIPE_LuV)
            .duration(500)
            .addTo(RecipeMaps.circuitAssemblerRecipes);

        GTValues.RA
            .stdBuilder()
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1), ItemList.Circuit_Wafer_CPU.get(64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(16))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_MV.get(1536))
            .eut(RECIPE_LuV)
            .duration(500)
            .addTo(RecipeMaps.circuitAssemblerRecipes);

        GTValues.RA
            .stdBuilder()
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1), ItemList.Circuit_Wafer_NanoCPU.get(64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(16))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_HV.get(1280))
            .eut(RECIPE_LuV)
            .duration(500)
            .addTo(RecipeMaps.circuitAssemblerRecipes);

        GTValues.RA
            .stdBuilder()
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1), ItemList.Circuit_Chip_QuantumCPU.get(64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(32))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_EV.get(1024))
            .eut(RECIPE_LuV)
            .duration(500)
            .addTo(RecipeMaps.circuitAssemblerRecipes);

        GTValues.RA
            .stdBuilder()
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1), ItemList.Circuit_Chip_NanoCPU.get(64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(48))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_IV.get(768))
            .eut(RECIPE_LuV)
            .duration(500)
            .addTo(RecipeMaps.circuitAssemblerRecipes);

        GTValues.RA
            .stdBuilder()
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1),ItemList.Circuit_Chip_NeuroCPU.get(64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(72))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_LuV.get(512))
            .eut(RECIPE_ZPM)
            .duration(500)
            .addTo(RecipeMaps.circuitAssemblerRecipes);

        GTValues.RA
            .stdBuilder()
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1), ItemList.Circuit_Chip_BioCPU.get(64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(144))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_ZPM.get(256))
            .eut(RECIPE_UV)
            .duration(500)
            .addTo(RecipeMaps.circuitAssemblerRecipes);

        GTValues.RA
            .stdBuilder()
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1), ItemList.Circuit_Board_Optical.get(64),ItemList.Circuit_Chip_Optical.get(64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(288))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_UV.get(128))
            .eut(RECIPE_UHV)
            .duration(500)
            .addTo(RecipeMaps.circuitAssemblerRecipes);

    }
}
