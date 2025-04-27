package com.sakurac1oud.SenseofCherryBlossom.common;

import com.sakurac1oud.SenseofCherryBlossom.common.creativetab.CreativeTabCherryBlossom;
import gregtech.api.GregTechAPI;
import gregtech.api.util.GTUtility;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.sakurac1oud.SenseofCherryBlossom.utils.Utils;

import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.util.GTLog;

@SuppressWarnings("SpellCheckingInspection")
public enum CherryBlossomItemList {

    // items
    TestItem0,
    SakuraCloud,
    initial_core,
    Hangonchou,

    //machines
    CherryBlossomLCR,
    CherryBlossomBorderofLife,
    CherryBlossomAssembler,

    //circuit
    CherryBlossomCircuit_ULV,
    CherryBlossomCircuit_LV,
    CherryBlossomCircuit_MV,
    CherryBlossomCircuit_HV,
    CherryBlossomCircuit_EV,
    CherryBlossomCircuit_IV,
    CherryBlossomCircuit_LuV,
    CherryBlossomCircuit_ZPM,
    CherryBlossomCircuit_UV,
    CherryBlossomCircuit_UHV,
    CherryBlossomCircuit_UEV,
    CherryBlossomCircuit_UIV,
    CherryBlossomCircuit_UMV,
    CherryBlossomCircuit_UXV,
    CherryBlossomCircuit_MAX,
    ;


    private boolean mHasNotBeenSet;
    private boolean mDeprecated;
    private boolean mWarned;

    private ItemStack mStack;

    // endregion

    CherryBlossomItemList() {
        mHasNotBeenSet = true;
    }

    CherryBlossomItemList(boolean aDeprecated) {
        if (aDeprecated) {
            mDeprecated = true;
            mHasNotBeenSet = true;
        }
    }

    public Item getItem() {
        sanityCheck();
        if (GTUtility.isStackInvalid(mStack)) return null;// TODO replace a default issue item
        return mStack.getItem();
    }

    public Block getBlock() {
        sanityCheck();
        return Block.getBlockFromItem(getItem());
    }

    public ItemStack get(int aAmount, Object... aReplacements) {
        sanityCheck();
        // if invalid, return a replacements
        if (GTUtility.isStackInvalid(mStack)) {
            GTLog.out.println("Object in the ItemList is null at:");
            new NullPointerException().printStackTrace(GTLog.out);
            return GTUtility.copyAmountUnsafe(aAmount, TestItem0.get(1));
        }
        return GTUtility.copyAmountUnsafe(aAmount, mStack);
    }

    public int getMeta() {
        return mStack.getItemDamage();
    }

    public CherryBlossomItemList set(Item aItem) {
        mHasNotBeenSet = false;
        if (aItem == null) return this;
        ItemStack aStack = new ItemStack(aItem, 1, 0);
        mStack = GTUtility.copyAmountUnsafe(1, aStack);
        return this;
    }

    public CherryBlossomItemList set(ItemStack aStack) {
        if (aStack != null) {
            mHasNotBeenSet = false;
            mStack = GTUtility.copyAmountUnsafe(1, aStack);
        }
        return this;
    }

    public CherryBlossomItemList set(IMetaTileEntity metaTileEntity) {
        if (metaTileEntity == null) throw new IllegalArgumentException();
        return set(metaTileEntity.getStackForm(1L));
    }

    public boolean hasBeenSet() {
        return !mHasNotBeenSet;
    }

    /**
     * Returns the internal stack. This method is unsafe. It's here only for quick operations. DON'T CHANGE THE RETURNED
     * VALUE!
     */
    public ItemStack getInternalStack_unsafe() {
        return mStack;
    }

    private void sanityCheck() {
        if (mHasNotBeenSet)
            throw new IllegalAccessError("The Enum '" + name() + "' has not been set to an Item at this time!");
        if (mDeprecated && !mWarned) {
            new Exception(this + " is now deprecated").printStackTrace(GTLog.err);
            // warn only once
            mWarned = true;
        }
    }
}
