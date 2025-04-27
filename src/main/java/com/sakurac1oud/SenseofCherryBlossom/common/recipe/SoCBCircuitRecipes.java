package com.sakurac1oud.SenseofCherryBlossom.common.recipe;

import com.dreammaster.gthandler.CustomItemList;
import com.sakurac1oud.SenseofCherryBlossom.common.CherryBlossomItemList;
import com.sakurac1oud.SenseofCherryBlossom.common.material.MaterialCherryBlossom;
import com.sakurac1oud.SenseofCherryBlossom.common.recipe.RecipeMap.CherryBlossomRecipe;
import gregtech.api.enums.*;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GTOreDictUnificator;
import gregtech.api.util.GTUtility;

import static gregtech.api.enums.TierEU.*;
import static gregtech.api.util.GTRecipeConstants.RESEARCH_ITEM;
import static gregtech.api.util.GTRecipeConstants.RESEARCH_TIME;

public class SoCBCircuitRecipes {
    public static void loadRecipes() {
        GTValues.RA
            .stdBuilder()
            .metadata(RESEARCH_ITEM,CherryBlossomItemList.Hangonchou.get(1))
            .metadata(RESEARCH_TIME,100)
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1), ItemList.Circuit_Wafer_Simple_SoC.get(64),ItemList.Circuit_Parts_CapacitorASMD.get(64),ItemList.Circuit_Parts_TransistorASMD.get(64),ItemList.Circuit_Parts_ResistorASMD.get(64),ItemList.Circuit_Parts_InductorASMD.get(64),ItemList.Circuit_Parts_DiodeASMD.get(64), GTOreDictUnificator.get(OrePrefixes.nanite, Materials.Neutronium,64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(144), Materials.SuperCoolant.getFluid(8000))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_ULV.get(8192))
            .eut(RECIPE_LuV)
            .ignoreCollision()
            .duration(5000)
            .addTo(RecipeMaps.assemblylineVisualRecipes);

        GTValues.RA
            .stdBuilder()
            .metadata(RESEARCH_ITEM,CherryBlossomItemList.Hangonchou.get(1))
            .metadata(RESEARCH_TIME,100)
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1), ItemList.Circuit_Wafer_Simple_SoC.get(64),ItemList.Circuit_Parts_CapacitorXSMD.get(16),ItemList.Circuit_Parts_TransistorXSMD.get(16),ItemList.Circuit_Parts_ResistorXSMD.get(16),ItemList.Circuit_Parts_InductorXSMD.get(16),ItemList.Circuit_Parts_DiodeXSMD.get(16),GTOreDictUnificator.get(OrePrefixes.nanite,Materials.Glowstone,64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(144), Materials.SuperCoolant.getFluid(4000))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_ULV.get(8192))
            .eut(RECIPE_LuV)
            .ignoreCollision()
            .duration(5000)
            .addTo(RecipeMaps.assemblylineVisualRecipes);

        GTValues.RA
            .stdBuilder()
            .metadata(RESEARCH_ITEM,CherryBlossomItemList.CherryBlossomCircuit_ULV.get(1))
            .metadata(RESEARCH_TIME,100)
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1), ItemList.Circuit_Wafer_Ram.get(64),ItemList.Circuit_Parts_CapacitorASMD.get(64),ItemList.Circuit_Parts_TransistorASMD.get(64),ItemList.Circuit_Parts_ResistorASMD.get(64),ItemList.Circuit_Parts_InductorASMD.get(64),ItemList.Circuit_Parts_DiodeASMD.get(64), GTOreDictUnificator.get(OrePrefixes.nanite,Materials.Neutronium,64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(144), Materials.SuperCoolant.getFluid(8000))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_LV.get(7168))
            .eut(RECIPE_LuV)
            .duration(5000)
            .addTo(RecipeMaps.assemblylineVisualRecipes);

        GTValues.RA
            .stdBuilder()
            .metadata(RESEARCH_ITEM,CherryBlossomItemList.CherryBlossomCircuit_LV.get(1))
            .metadata(RESEARCH_TIME,100)
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1), ItemList.Circuit_Wafer_CPU.get(64),ItemList.Circuit_Parts_CapacitorASMD.get(64),ItemList.Circuit_Parts_TransistorASMD.get(64),ItemList.Circuit_Parts_ResistorASMD.get(64),ItemList.Circuit_Parts_InductorASMD.get(64),ItemList.Circuit_Parts_DiodeASMD.get(64), GTOreDictUnificator.get(OrePrefixes.nanite,Materials.Neutronium,64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(144), Materials.SuperCoolant.getFluid(8000))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_MV.get(6144))
            .eut(RECIPE_LuV)
            .duration(5000)
            .addTo(RecipeMaps.assemblylineVisualRecipes);

        GTValues.RA
            .stdBuilder()
            .metadata(RESEARCH_ITEM,CherryBlossomItemList.CherryBlossomCircuit_MV.get(1))
            .metadata(RESEARCH_TIME,100)
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1), ItemList.Circuit_Wafer_NanoCPU.get(64),ItemList.Circuit_Parts_CapacitorASMD.get(64),ItemList.Circuit_Parts_TransistorASMD.get(64),ItemList.Circuit_Parts_ResistorASMD.get(64),ItemList.Circuit_Parts_InductorASMD.get(64),ItemList.Circuit_Parts_DiodeASMD.get(64), GTOreDictUnificator.get(OrePrefixes.nanite,Materials.Neutronium,64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(144), Materials.SuperCoolant.getFluid(8000))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_HV.get(5120))
            .eut(RECIPE_LuV)
            .duration(5000)
            .addTo(RecipeMaps.assemblylineVisualRecipes);

        GTValues.RA
            .stdBuilder()
            .metadata(RESEARCH_ITEM,CherryBlossomItemList.CherryBlossomCircuit_HV.get(1))
            .metadata(RESEARCH_TIME,100)
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1), ItemList.Circuit_Chip_QuantumCPU.get(64),ItemList.Circuit_Parts_CapacitorASMD.get(64),ItemList.Circuit_Parts_TransistorASMD.get(64),ItemList.Circuit_Parts_ResistorASMD.get(64),ItemList.Circuit_Parts_InductorASMD.get(64),ItemList.Circuit_Parts_DiodeASMD.get(64), GTOreDictUnificator.get(OrePrefixes.nanite,Materials.Neutronium,64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(288), Materials.SuperCoolant.getFluid(8000))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_EV.get(4096))
            .eut(RECIPE_LuV)
            .duration(500)
            .addTo(RecipeMaps.assemblylineVisualRecipes);

        GTValues.RA
            .stdBuilder()
            .metadata(RESEARCH_ITEM,CherryBlossomItemList.CherryBlossomCircuit_EV.get(1))
            .metadata(RESEARCH_TIME,100)
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1), ItemList.Circuit_Chip_NanoCPU.get(64),ItemList.Circuit_Parts_CapacitorASMD.get(64),ItemList.Circuit_Parts_TransistorASMD.get(64),ItemList.Circuit_Parts_ResistorASMD.get(64),ItemList.Circuit_Parts_InductorASMD.get(64),ItemList.Circuit_Parts_DiodeASMD.get(64), GTOreDictUnificator.get(OrePrefixes.nanite,Materials.Neutronium,64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(288*2), Materials.SuperCoolant.getFluid(8000))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_IV.get(3072))
            .eut(RECIPE_LuV)
            .duration(500)
            .addTo(RecipeMaps.assemblylineVisualRecipes);

        GTValues.RA
            .stdBuilder()
            .metadata(RESEARCH_ITEM,CherryBlossomItemList.CherryBlossomCircuit_LuV.get(1))
            .metadata(RESEARCH_TIME,100)
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1),ItemList.Circuit_Chip_NeuroCPU.get(64),ItemList.Circuit_Parts_CapacitorASMD.get(64),ItemList.Circuit_Parts_TransistorASMD.get(64),ItemList.Circuit_Parts_ResistorASMD.get(64),ItemList.Circuit_Parts_InductorASMD.get(64),ItemList.Circuit_Parts_DiodeASMD.get(64), GTOreDictUnificator.get(OrePrefixes.nanite,Materials.Neutronium,64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(72/16*144), Materials.SuperCoolant.getFluid(8000))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_LuV.get(2048))
            .eut(RECIPE_ZPM)
            .duration(500)
            .addTo(RecipeMaps.assemblylineVisualRecipes);

        GTValues.RA
            .stdBuilder()
            .metadata(RESEARCH_ITEM,CherryBlossomItemList.CherryBlossomCircuit_LuV.get(1))
            .metadata(RESEARCH_TIME,100)
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1), ItemList.Circuit_Chip_BioCPU.get(64),ItemList.Circuit_Parts_CapacitorASMD.get(64),ItemList.Circuit_Parts_TransistorASMD.get(64),ItemList.Circuit_Parts_ResistorASMD.get(64),ItemList.Circuit_Parts_InductorASMD.get(64),ItemList.Circuit_Parts_DiodeASMD.get(64), GTOreDictUnificator.get(OrePrefixes.nanite,Materials.Neutronium,64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(144*144/16), Materials.SuperCoolant.getFluid(8000))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_ZPM.get(1024))
            .eut(RECIPE_UV)
            .duration(500)
            .addTo(RecipeMaps.assemblylineVisualRecipes);

        GTValues.RA
            .stdBuilder()
            .metadata(RESEARCH_ITEM,CherryBlossomItemList.CherryBlossomCircuit_ZPM.get(1))
            .metadata(RESEARCH_TIME,100)
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1), ItemList.Circuit_Board_Optical.get(64),ItemList.Circuit_Chip_Optical.get(64),ItemList.Circuit_Parts_CapacitorASMD.get(64),ItemList.Circuit_Parts_TransistorASMD.get(64),ItemList.Circuit_Parts_ResistorASMD.get(64),ItemList.Circuit_Parts_InductorASMD.get(64),ItemList.Circuit_Parts_DiodeASMD.get(64), GTOreDictUnificator.get(OrePrefixes.nanite,Materials.Neutronium,64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(288*144/16), Materials.SuperCoolant.getFluid(8000))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_UV.get(512))
            .eut(RECIPE_UHV)
            .duration(500)
            .addTo(RecipeMaps.assemblylineVisualRecipes);

        GTValues.RA
            .stdBuilder()
            .metadata(RESEARCH_ITEM,CherryBlossomItemList.CherryBlossomCircuit_ULV.get(1))
            .metadata(RESEARCH_TIME,100)
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1), ItemList.Circuit_Wafer_Ram.get(64),ItemList.Circuit_Parts_CapacitorXSMD.get(16),ItemList.Circuit_Parts_TransistorXSMD.get(16),ItemList.Circuit_Parts_ResistorXSMD.get(16),ItemList.Circuit_Parts_InductorXSMD.get(16),ItemList.Circuit_Parts_DiodeXSMD.get(16),GTOreDictUnificator.get(OrePrefixes.nanite,Materials.Glowstone,64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(144), Materials.SuperCoolant.getFluid(8000))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_LV.get(7168))
            .eut(RECIPE_LuV)
            .duration(500)
            .addTo(RecipeMaps.assemblylineVisualRecipes);

        GTValues.RA
            .stdBuilder()
            .metadata(RESEARCH_ITEM,CherryBlossomItemList.CherryBlossomCircuit_LV.get(1))
            .metadata(RESEARCH_TIME,100)
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1), ItemList.Circuit_Wafer_CPU.get(64),ItemList.Circuit_Parts_CapacitorXSMD.get(16),ItemList.Circuit_Parts_TransistorXSMD.get(16),ItemList.Circuit_Parts_ResistorXSMD.get(16),ItemList.Circuit_Parts_InductorXSMD.get(16),ItemList.Circuit_Parts_DiodeXSMD.get(16),GTOreDictUnificator.get(OrePrefixes.nanite,Materials.Glowstone,64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(144), Materials.SuperCoolant.getFluid(8000))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_MV.get(6144))
            .eut(RECIPE_LuV)
            .duration(500)
            .addTo(RecipeMaps.assemblylineVisualRecipes);

        GTValues.RA
            .stdBuilder()
            .metadata(RESEARCH_ITEM,CherryBlossomItemList.CherryBlossomCircuit_MV.get(1))
            .metadata(RESEARCH_TIME,100)
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1), ItemList.Circuit_Wafer_NanoCPU.get(64),ItemList.Circuit_Parts_CapacitorXSMD.get(16),ItemList.Circuit_Parts_TransistorXSMD.get(16),ItemList.Circuit_Parts_ResistorXSMD.get(16),ItemList.Circuit_Parts_InductorXSMD.get(16),ItemList.Circuit_Parts_DiodeXSMD.get(16),GTOreDictUnificator.get(OrePrefixes.nanite,Materials.Glowstone,64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(144), Materials.SuperCoolant.getFluid(8000))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_HV.get(5120))
            .eut(RECIPE_LuV)
            .duration(500)
            .addTo(RecipeMaps.assemblylineVisualRecipes);

        GTValues.RA
            .stdBuilder()
            .metadata(RESEARCH_ITEM,CherryBlossomItemList.CherryBlossomCircuit_HV.get(1))
            .metadata(RESEARCH_TIME,100)
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1), ItemList.Circuit_Chip_QuantumCPU.get(64),ItemList.Circuit_Parts_CapacitorXSMD.get(16),ItemList.Circuit_Parts_TransistorXSMD.get(16),ItemList.Circuit_Parts_ResistorXSMD.get(16),ItemList.Circuit_Parts_InductorXSMD.get(16),ItemList.Circuit_Parts_DiodeXSMD.get(16),GTOreDictUnificator.get(OrePrefixes.nanite,Materials.Glowstone,64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(288), Materials.SuperCoolant.getFluid(8000))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_EV.get(4096))
            .eut(RECIPE_LuV)
            .duration(500)
            .addTo(RecipeMaps.assemblylineVisualRecipes);

        GTValues.RA
            .stdBuilder()
            .metadata(RESEARCH_ITEM,CherryBlossomItemList.CherryBlossomCircuit_EV.get(1))
            .metadata(RESEARCH_TIME,100)
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1), ItemList.Circuit_Chip_NanoCPU.get(64),ItemList.Circuit_Parts_CapacitorXSMD.get(16),ItemList.Circuit_Parts_TransistorXSMD.get(16),ItemList.Circuit_Parts_ResistorXSMD.get(16),ItemList.Circuit_Parts_InductorXSMD.get(16),ItemList.Circuit_Parts_DiodeXSMD.get(16),GTOreDictUnificator.get(OrePrefixes.nanite,Materials.Glowstone,64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(288*2), Materials.SuperCoolant.getFluid(8000))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_IV.get(3072))
            .eut(RECIPE_LuV)
            .duration(500)
            .addTo(RecipeMaps.assemblylineVisualRecipes);

        GTValues.RA
            .stdBuilder()
            .metadata(RESEARCH_ITEM,CherryBlossomItemList.CherryBlossomCircuit_IV.get(1))
            .metadata(RESEARCH_TIME,100)
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1),ItemList.Circuit_Chip_NeuroCPU.get(64),ItemList.Circuit_Parts_CapacitorXSMD.get(16),ItemList.Circuit_Parts_TransistorXSMD.get(16),ItemList.Circuit_Parts_ResistorXSMD.get(16),ItemList.Circuit_Parts_InductorXSMD.get(16),ItemList.Circuit_Parts_DiodeXSMD.get(16),GTOreDictUnificator.get(OrePrefixes.nanite,Materials.Glowstone,64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(72/16*144), Materials.SuperCoolant.getFluid(8000))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_LuV.get(2048))
            .eut(RECIPE_ZPM)
            .duration(500)
            .addTo(RecipeMaps.assemblylineVisualRecipes);

        GTValues.RA
            .stdBuilder()
            .metadata(RESEARCH_ITEM,CherryBlossomItemList.CherryBlossomCircuit_LuV.get(1))
            .metadata(RESEARCH_TIME,100)
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1), ItemList.Circuit_Chip_BioCPU.get(64),ItemList.Circuit_Parts_CapacitorXSMD.get(16),ItemList.Circuit_Parts_TransistorXSMD.get(16),ItemList.Circuit_Parts_ResistorXSMD.get(16),ItemList.Circuit_Parts_InductorXSMD.get(16),ItemList.Circuit_Parts_DiodeXSMD.get(16),GTOreDictUnificator.get(OrePrefixes.nanite,Materials.Glowstone,64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(144*144/16), Materials.SuperCoolant.getFluid(8000))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_ZPM.get(1024))
            .eut(RECIPE_UV)
            .duration(500)
            .addTo(RecipeMaps.assemblylineVisualRecipes);

        GTValues.RA
            .stdBuilder()
            .metadata(RESEARCH_ITEM,CherryBlossomItemList.CherryBlossomCircuit_ZPM.get(1))
            .metadata(RESEARCH_TIME,100)
            .itemInputs(CherryBlossomItemList.Hangonchou.get(1), ItemList.Circuit_Board_Optical.get(64),ItemList.Circuit_Chip_Optical.get(64),ItemList.Circuit_Parts_CapacitorXSMD.get(16),ItemList.Circuit_Parts_TransistorXSMD.get(16),ItemList.Circuit_Parts_ResistorXSMD.get(16),ItemList.Circuit_Parts_InductorXSMD.get(16),ItemList.Circuit_Parts_DiodeXSMD.get(16),GTOreDictUnificator.get(OrePrefixes.nanite,Materials.Glowstone,64))
            .fluidInputs(MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(288*144/16), Materials.SuperCoolant.getFluid(8000))
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_UV.get(512))
            .eut(RECIPE_UHV)
            .duration(500)
            .addTo(RecipeMaps.assemblylineVisualRecipes);

        //UHV
        GTValues.RA
            .stdBuilder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(4),
                CherryBlossomItemList.Hangonchou.get(1),
                ItemList.NuclearStar.get(64),
                ItemList.Field_Generator_UV.get(64)
                )
            .fluidInputs(
                MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(288*144/16),
                Materials.SuperCoolant.getFluid(8000),
                Materials.SuperconductorUHV.getFluid(14400)
            )
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_UHV.get(256))
            .eut(RECIPE_UEV)
            .duration(5000)
            .addTo(RecipeMaps.plasmaForgeRecipes);

        GTValues.RA
            .stdBuilder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(2),
                CherryBlossomItemList.Hangonchou.get(4),
                ItemList.NuclearStar.get(64),
                ItemList.Field_Generator_UV.get(64),
                ItemList.Circuit_Board_Optical.get(64),
                ItemList.Circuit_Chip_Optical.get(64)
            )
            .fluidInputs(
                MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(288*144/16),
                Materials.SuperCoolant.getFluid(8000),
                Materials.SuperconductorUHV.getFluid(14400)
            )
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_UHV.get(512))
            .eut(RECIPE_UEV)
            .duration(500)
            .addTo(RecipeMaps.plasmaForgeRecipes);

        //UEV
        GTValues.RA.stdBuilder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(2),
                CherryBlossomItemList.Hangonchou.get(4),
                ItemList.NuclearStar.get(512),
                GTOreDictUnificator.get(OrePrefixes.frame,Materials.Infinity,256),
                ItemList.Circuit_Board_Optical.get(1024),
                ItemList.Circuit_Chip_Optical.get(1024),
                CustomItemList.RadoxPolymerLens.get(16)
            )
            .fluidInputs(
                MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(288*144/16),
                Materials.SuperCoolant.getFluid(16000),
                Materials.SuperconductorUEV.getFluid(28800),
                MaterialsUEVplus.DimensionallyTranscendentCrudeCatalyst.getFluid(28800)
            )
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_UEV.get(512))
            .eut(RECIPE_UIV)
            .duration(500)
            .addTo(RecipeMaps.plasmaForgeRecipes);

        GTValues.RA.stdBuilder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(1),
                CherryBlossomItemList.Hangonchou.get(4),
                ItemList.NuclearStar.get(512),
                GTOreDictUnificator.get(OrePrefixes.frame,Materials.Infinity,256)
            )
            .fluidInputs(
                MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(288*144/16),
                Materials.SuperCoolant.getFluid(16000),
                Materials.SuperconductorUEV.getFluid(28800),
                MaterialsUEVplus.DimensionallyTranscendentCrudeCatalyst.getFluid(28800)
            )
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_UEV.get(512))
            .eut(RECIPE_UIV)
            .duration(5000)
            .addTo(RecipeMaps.plasmaForgeRecipes);

        //UIV
        GTValues.RA.stdBuilder()
            .itemInputs(
                GTUtility.getIntegratedCircuit(1),
                CherryBlossomItemList.Hangonchou.get(16),
                ItemList.NuclearStar.get(2048),
                GTOreDictUnificator.get(OrePrefixes.frame,MaterialsUEVplus.TranscendentMetal,256)
            )
            .fluidInputs(
                MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(288*144/16),
                MaterialsUEVplus.SpaceTime.getFluid(16000),
                Materials.SuperconductorUIV.getFluid(28800),
                MaterialsUEVplus.DimensionallyTranscendentCrudeCatalyst.getFluid(28800)
            )
            .itemOutputs(CherryBlossomItemList.CherryBlossomCircuit_UIV.get(512))
            .eut(RECIPE_UMV)
            .duration(500)
            .addTo(CherryBlossomRecipe.TheBorderofLife);
    }
}
