package com.sakurac1oud.SenseofCherryBlossom.common.material;

import static bartworks.util.BWUtil.subscriptNumbers;

import bartworks.API.WerkstoffAdderRegistry;
import bartworks.system.material.Werkstoff;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TextureSet;

import com.sakurac1oud.SenseofCherryBlossom.SenseofCherryBlossom;

/**
 * Register new material here by Bartworks Material System
 */
public class MaterialPool implements Runnable {

    /*----------- Test the forge item register -----------*/

    // public static ItemAdder_Basic testItem = new ItemAdder_Basic("Testing Item","testingItem.01");
    // public static ItemStack testItem4 = new ItemStack(testItem,1,1);

    /*----------- Test the forge item register -----------*/

    // ID manager
    private static final int offsetID_01 = 20_000;

    // spotless:off

    public static final Werkstoff.GenerationFeatures gf = new Werkstoff.GenerationFeatures();

    public static Werkstoff eventHorizonDiffusers;

    public static Werkstoff entropyReductionProcess;

    public static Werkstoff realSingularity;

    public static final Werkstoff SakuraCloudDream = new Werkstoff(
        new short[] { 106, 100, 205 },
        "SakuraCloud Dream",
        subscriptNumbers("❀❀"),
        new Werkstoff.Stats(),
        Werkstoff.Types.MIXTURE,
        new Werkstoff.GenerationFeatures().disable()
            .onlyDust()
            .addCells(),
        offsetID_01 + 12,
        TextureSet.SET_FLUID);

    @Override
    public void run() {
        for (var prefix : OrePrefixes.values()) {
            gf.addPrefix(prefix);
        }
        gf.removePrefix(OrePrefixes.ore);
    }
}
