package com.sakurac1oud.SenseofCherryBlossom.common.machine.processingLogics;

import javax.annotation.Nonnull;

import com.sakurac1oud.SenseofCherryBlossom.common.misc.OverclockType;

import gregtech.api.util.GTRecipe;

public class SoCBProcessingLogic extends gregtech.api.logic.ProcessingLogic {

    /**
     * Override to tweak parallel logic if needed.
     */
    @Nonnull
    @Override
    protected gregtech.api.util.ParallelHelper createParallelHelper(@Nonnull GTRecipe recipe) {
        return new SoCBParallelHelper().setRecipe(recipe)
            .setItemInputs(inputItems)
            .setFluidInputs(inputFluids)
            .setAvailableEUt(availableVoltage * availableAmperage)
            .setMachine(machine, protectItems, protectFluids)
            .setRecipeLocked(recipeLockableMachine, isRecipeLocked)
            .setMaxParallel(maxParallel)
            .setEUtModifier(euModifier)
            .enableBatchMode(batchSize)
            .setConsumption(true)
            .setOutputCalculation(true);
    }

    public SoCBProcessingLogic setOverclockType(OverclockType t) {
        setOverclock(t.timeReduction, t.powerIncrease);
        return this;
    }
}
