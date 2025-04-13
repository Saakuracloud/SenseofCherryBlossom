package com.sakurac1oud.SenseofCherryBlossom.utils.rewrite;

import java.util.Objects;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

import org.jetbrains.annotations.Nullable;

import gregtech.api.util.GTUtility;

public class CherryBlossomItemID extends GTUtility.ItemId {

    // region Member Variables
    private Item item;
    private int metaData;
    private NBTTagCompound nbt;
    // endregion

    // region Class Constructors
    public CherryBlossomItemID(Item item, int metaData, NBTTagCompound nbt) {
        this.item = item;
        this.metaData = metaData;
        this.nbt = nbt;
    }

    public CherryBlossomItemID(Item item, int metaData) {
        this.item = item;
        this.metaData = metaData;
    }

    public CherryBlossomItemID(Item item) {
        this.item = item;
        this.metaData = 0;
    }

    public CherryBlossomItemID() {}
    // endregion

    // region Static Methods
    public static final CherryBlossomItemID NULL = new CherryBlossomItemID();

    public static CherryBlossomItemID create(ItemStack itemStack) {
        if (null == itemStack) return NULL;
        return new CherryBlossomItemID(itemStack.getItem(), itemStack.getItemDamage(), itemStack.getTagCompound());
    }

    public static CherryBlossomItemID createNoNBT(ItemStack itemStack) {
        if (null == itemStack) return NULL;
        return new CherryBlossomItemID(itemStack.getItem(), itemStack.getItemDamage());
    }

    public static CherryBlossomItemID createAsWildcard(ItemStack itemStack) {
        if (null == itemStack) return NULL;
        return new CherryBlossomItemID(itemStack.getItem(), OreDictionary.WILDCARD_VALUE);
    }
    // endregion

    // region Special Methods
    public ItemStack getItemStack() {
        return new ItemStack(item, 1, metaData);
    }

    public ItemStack getItemStack(int amount) {
        return new ItemStack(item, amount, metaData);
    }

    public ItemStack getItemStackWithNBT() {
        ItemStack itemStack = new ItemStack(item, 1, metaData);
        itemStack.setTagCompound(nbt);
        return itemStack;
    }

    public ItemStack getItemStackWithNBT(int amount) {
        ItemStack itemStack = new ItemStack(item, amount, metaData);
        itemStack.setTagCompound(nbt);
        return itemStack;
    }

    // endregion

    // region General Methods
    public boolean isWildcard() {
        return this.metaData == OreDictionary.WILDCARD_VALUE;
    }

    public CherryBlossomItemID setItem(Item item) {
        this.item = item;
        return this;
    }

    public CherryBlossomItemID setMetaData(int metaData) {
        this.metaData = metaData;
        return this;
    }

    public CherryBlossomItemID setNbt(NBTTagCompound nbt) {
        this.nbt = nbt;
        return this;
    }

    @Override
    protected Item item() {
        return item;
    }

    @Override
    protected int metaData() {
        return metaData;
    }

    @Nullable
    @Override
    protected NBTTagCompound nbt() {
        return nbt;
    }

    @Nullable
    @Override
    protected Integer stackSize() {
        // todo
        return null;
    }

    @Nullable
    protected NBTTagCompound getNBT() {
        return nbt;
    }

    protected Item getItem() {
        return item;
    }

    protected int getMetaData() {
        return metaData;
    }

    public boolean equalItemStack(ItemStack itemStack) {
        return this.equals(isWildcard() ? createAsWildcard(itemStack) : createNoNBT(itemStack));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CherryBlossomItemID)) {
            return false;
        }
        CherryBlossomItemID othitemid = (CherryBlossomItemID) o;
        return metaData == othitemid.metaData && Objects.equals(item, othitemid.item)
            && Objects.equals(nbt, othitemid.nbt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, metaData, nbt);
    }
}
