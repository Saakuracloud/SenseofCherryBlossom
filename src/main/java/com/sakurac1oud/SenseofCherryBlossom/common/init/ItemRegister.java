package com.sakurac1oud.SenseofCherryBlossom.common.init;

import com.sakurac1oud.SenseofCherryBlossom.common.CherryBlossomItemList;
import com.sakurac1oud.SenseofCherryBlossom.common.item.ItemAdderCircuit;
import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.SubTag;
import net.minecraft.item.Item;

import static com.sakurac1oud.SenseofCherryBlossom.common.item.CherryBlossomItems.MetaItem;
import static com.sakurac1oud.SenseofCherryBlossom.common.item.CherryBlossomItems.MetaItemCircuit;
import static com.sakurac1oud.SenseofCherryBlossom.utils.Utils.tr;

public class ItemRegister {
    private static boolean itemsRegistered = false;

    public static void registryItems() {
        if (itemsRegistered) {
            return;
        }

        Item[] itemsToReg = { MetaItem,MetaItemCircuit };

        for (Item item : itemsToReg) {
            GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
        }

        itemsRegistered = true;
    }

    public static void registryItemContainers() {
        CherryBlossomItemList.TestItem0.set(MetaItem.registerVariant(0));
        CherryBlossomItemList.SakuraCloud.set(MetaItem.registerVariantWithTooltips(1,new String[]{tr("tooltips.SakuraCloud.line1"),tr("Adder")}));
        CherryBlossomItemList.initial_core.set(MetaItem.registerVariantWithTooltips(2,new String[]{tr("tooltips.initial_core.line1"),tr("Adder")}));
        CherryBlossomItemList.Hangonchou.set(MetaItem.registerVariantWithTooltips(3,new String[]{tr("tooltips.Hangonchou.line1"),tr("Adder")}));

        CherryBlossomItemList.CherryBlossomCircuit_ULV.set(MetaItemCircuit.registerVariantWithTooltips(0,new String[]{tr("tooltips.CherryBlossomCircuit_ULV.line1"),tr("tooltips.CherryBlossomCircuit.line00"),tr("tooltips.CherryBlossomCircuit.line01"),tr("Adder")}, OrePrefixes.circuit.get(Materials.ULV),
            SubTag.NO_UNIFICATION));
        CherryBlossomItemList.CherryBlossomCircuit_LV.set(MetaItemCircuit.registerVariantWithTooltips(1,new String[]{tr("tooltips.CherryBlossomCircuit_LV.line1"),tr("tooltips.CherryBlossomCircuit.line00"),tr("tooltips.CherryBlossomCircuit.line01"),tr("Adder")}, OrePrefixes.circuit.get(Materials.LV),
            SubTag.NO_UNIFICATION));
        CherryBlossomItemList.CherryBlossomCircuit_MV.set(MetaItemCircuit.registerVariantWithTooltips(2,new String[]{tr("tooltips.CherryBlossomCircuit_MV.line1"),tr("tooltips.CherryBlossomCircuit.line00"),tr("tooltips.CherryBlossomCircuit.line01"),tr("Adder")}, OrePrefixes.circuit.get(Materials.MV),
            SubTag.NO_UNIFICATION));
        CherryBlossomItemList.CherryBlossomCircuit_HV.set(MetaItemCircuit
                .registerVariantWithTooltips(3,new String[]{tr("tooltips.CherryBlossomCircuit_HV.line1"),tr("tooltips.CherryBlossomCircuit.line00"),tr("tooltips.CherryBlossomCircuit.line01"),tr("Adder")}, OrePrefixes.circuit.get(Materials.HV),
                    SubTag.NO_UNIFICATION));
        CherryBlossomItemList.CherryBlossomCircuit_EV.set(MetaItemCircuit.registerVariantWithTooltips(4,new String[]{tr("tooltips.CherryBlossomCircuit_EV.line1"),tr("tooltips.CherryBlossomCircuit.line00"),tr("tooltips.CherryBlossomCircuit.line01"),tr("Adder")}, OrePrefixes.circuit.get(Materials.EV),
            SubTag.NO_UNIFICATION));
        CherryBlossomItemList.CherryBlossomCircuit_IV.set(MetaItemCircuit.registerVariantWithTooltips(5,new String[]{tr("tooltips.CherryBlossomCircuit_IV.line1"),tr("tooltips.CherryBlossomCircuit.line00"),tr("tooltips.CherryBlossomCircuit.line01"),tr("Adder")}, OrePrefixes.circuit.get(Materials.IV),
            SubTag.NO_UNIFICATION));
        CherryBlossomItemList.CherryBlossomCircuit_LuV.set(MetaItemCircuit
                .registerVariantWithTooltips(6,new String[]{tr("tooltips.CherryBlossomCircuit_LUV.line1"),tr("tooltips.CherryBlossomCircuit.line00"),tr("tooltips.CherryBlossomCircuit.line01"),tr("Adder")}, OrePrefixes.circuit.get(Materials.LuV),
                    SubTag.NO_UNIFICATION));
        CherryBlossomItemList.CherryBlossomCircuit_ZPM.set(MetaItemCircuit.registerVariantWithTooltips(7,new String[]{tr("tooltips.CherryBlossomCircuit_ZPM.line1"),tr("tooltips.CherryBlossomCircuit.line00"),tr("tooltips.CherryBlossomCircuit.line01"),tr("Adder")}, OrePrefixes.circuit.get(Materials.ZPM),
            SubTag.NO_UNIFICATION));
        CherryBlossomItemList.CherryBlossomCircuit_UV.set(MetaItemCircuit.registerVariantWithTooltips(8,new String[]{tr("tooltips.CherryBlossomCircuit_UV.line1"),tr("tooltips.CherryBlossomCircuit.line00"),tr("tooltips.CherryBlossomCircuit.line01"),tr("Adder")}, OrePrefixes.circuit.get(Materials.UV),
            SubTag.NO_UNIFICATION));
        CherryBlossomItemList.CherryBlossomCircuit_UHV.set(MetaItemCircuit
                .registerVariantWithTooltips(9,new String[]{tr("tooltips.CherryBlossomCircuit_UHV.line1"),tr("tooltips.CherryBlossomCircuit.line00"),tr("tooltips.CherryBlossomCircuit.line01"),tr("Adder")}, OrePrefixes.circuit.get(Materials.UHV),
                    SubTag.NO_UNIFICATION));
        CherryBlossomItemList.CherryBlossomCircuit_UEV.set(MetaItemCircuit.registerVariantWithTooltips(10,new String[]{tr("tooltips.CherryBlossomCircuit_UEV.line1"),tr("tooltips.CherryBlossomCircuit.line00"),tr("tooltips.CherryBlossomCircuit.line01"),tr("Adder")}, OrePrefixes.circuit.get(Materials.UEV),
            SubTag.NO_UNIFICATION));
        CherryBlossomItemList.CherryBlossomCircuit_UIV.set(MetaItemCircuit
                .registerVariantWithTooltips(11,new String[]{tr("tooltips.CherryBlossomCircuit_UIV.line1"),tr("tooltips.CherryBlossomCircuit.line00"),tr("tooltips.CherryBlossomCircuit.line01"),tr("Adder")}, OrePrefixes.circuit.get(Materials.UIV),
                    SubTag.NO_UNIFICATION));
        CherryBlossomItemList.CherryBlossomCircuit_UMV
                .set(MetaItemCircuit.registerVariantWithTooltips(12,new String[]{tr("tooltips.CherryBlossomCircuit_UMV.line1"),tr("tooltips.CherryBlossomCircuit.line00"),tr("tooltips.CherryBlossomCircuit.line01"),tr("Adder")}, OrePrefixes.circuit.get(Materials.UMV),
                    SubTag.NO_UNIFICATION));
        CherryBlossomItemList.CherryBlossomCircuit_UXV.set(MetaItemCircuit.registerVariantWithTooltips(13,new String[]{tr("tooltips.CherryBlossomCircuit_UXV.line1"),tr("tooltips.CherryBlossomCircuit.line00"),tr("tooltips.CherryBlossomCircuit.line01"),tr("Adder")}, OrePrefixes.circuit.get(Materials.UXV),
            SubTag.NO_UNIFICATION));
        CherryBlossomItemList.CherryBlossomCircuit_MAX
                .set(MetaItemCircuit.registerVariantWithTooltips(14,new String[]{tr("tooltips.CherryBlossomCircuit_MAX.line1"),tr("tooltips.CherryBlossomCircuit.line00"),tr("tooltips.CherryBlossomCircuit.line01"),tr("Adder")}, OrePrefixes.circuit.get(Materials.MAX),
                    SubTag.NO_UNIFICATION));
    }

    public static void registry() {
        registryItems();
        registryItemContainers();
    }
}
