package com.sakurac1oud.SenseofCherryBlossom.common.recipe;

import com.sakurac1oud.SenseofCherryBlossom.common.CherryBlossomItemList;
import com.sakurac1oud.SenseofCherryBlossom.common.material.MaterialCherryBlossom;
import gregtech.api.enums.*;
import gregtech.api.interfaces.IRecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GTOreDictUnificator;
import gregtech.api.util.GTRecipeConstants;
import gregtech.api.util.GTUtility;
import static gregtech.api.util.GTRecipeConstants.RESEARCH_ITEM;
import static gregtech.api.util.GTRecipeConstants.RESEARCH_TIME;
import gtPlusPlus.xmod.gregtech.api.enums.GregtechItemList;
import tectech.TecTech;
import tectech.thing.casing.BlockGodforgeCasings;
import tectech.util.TTUtility;
import thaumic.tinkerer.common.research.TTResearchItem;

import static gregtech.api.enums.TierEU.*;
import static gregtech.api.util.GTModHandler.getModItem;

import static bartworks.common.loaders.ItemRegistry.megaMachines;

public class SoCBMachineRecipes {

    //

    public static void loadRecipes() {
        final IRecipeMap assemblyLine = GTRecipeConstants.AssemblyLine;
        final IRecipeMap assembler = RecipeMaps.assemblerRecipes;

        //CrafingInputBus
        GTValues.RA.stdBuilder()
            .itemInputs(
                    CherryBlossomItemList.Hangonchou.get(1),
                    getModItem("appliedenergistics2","item.ItemMultiMaterial",16,52)
                    )
            .fluidInputs(
                Materials.Tin.getMolten(144)
            )
            .itemOutputs(
                getModItem("gregtech","gt.blockmachines",16,2714)
            )
            .eut(RECIPE_LV)
            .duration(20*60)
            .addTo(assembler);


        //CherryBlossomLCR
        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.getIntegratedCircuit(14),
                GTUtility.copyAmountUnsafe(4, megaMachines[3]),
                getModItem("gregtech","gt.blockcasings2",16,9),
                ItemList.Emitter_IV.get(16),
                ItemList.Field_Generator_IV.get(8)
                )
            .fluidInputs(
                MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(144)
            )
            .itemOutputs(
                CherryBlossomItemList.CherryBlossomLCR.get(1)
            )
            .eut(RECIPE_LuV)
            .duration(20 * 160)
            .addTo(assembler);

        //CherryBlossomBorderofLife
        GTValues.RA.stdBuilder()
            .metadata(RESEARCH_ITEM,CherryBlossomItemList.CherryBlossomCircuit_UEV.get(1))
            .metadata(RESEARCH_TIME, 100)
            .itemInputs(
                getModItem("gregtech","gt.blockmachines",4,15410),
                getModItem("gregtech","gt.blockmachines",4,15411),
                getModItem("tectech","gt.godforgecasing",16,7),
                ItemList.Sensor_UMV.get(16),
                ItemList.Emitter_UMV.get(16),
                ItemList.Field_Generator_UMV.get(8),
                new Object[]{OrePrefixes.circuit.get(Materials.UXV), 16},
                new Object[]{OrePrefixes.circuit.get(Materials.UXV), 16},
                new Object[]{OrePrefixes.circuit.get(Materials.UXV), 16},
                new Object[]{OrePrefixes.circuit.get(Materials.UXV), 16},
                GTOreDictUnificator.get(OrePrefixes.nanite, MaterialsUEVplus.WhiteDwarfMatter,16),
                GTOreDictUnificator.get(OrePrefixes.nanite, MaterialsUEVplus.MagMatter,16)
            )
            .fluidInputs(
                MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(14400),
                MaterialCherryBlossom.CherryBlossomDreamMatter.getPlasma(28800),
                MaterialsUEVplus.BlackDwarfMatter.getFluid(14400),
                Materials.RadoxPolymer.getFluid(57600)
            )
            .itemOutputs(CherryBlossomItemList.CherryBlossomBorderofLife.get(1))
            .eut(RECIPE_UMV)
            .duration(20 * 1500)
            .addTo(assemblyLine);

        //CherryBlossomAssembler
        GTValues.RA.stdBuilder()
            .itemInputs(
                getModItem("gregtech","gt.blockmachines",8,876),
                getModItem("gregtech","gt.blockcasings2",16,9),
                getModItem("gregtech","gt.blockcasings2",16,5),
                getModItem("gregtech","gt.blockmachines",8,32018)
            )
            .fluidInputs(
                MaterialCherryBlossom.CherryBlossomDreamMatter.getMolten(288)
            )
            .itemOutputs(
                CherryBlossomItemList.CherryBlossomAssembler.get(1)
            )
            .eut(RECIPE_ZPM)
            .duration(20 * 160)
            .addTo(assembler);

    }

    public static void loadRecipemixin() {
        // new Mode3SimulatorFakeRecipe().loadRecipes();
    }
}
