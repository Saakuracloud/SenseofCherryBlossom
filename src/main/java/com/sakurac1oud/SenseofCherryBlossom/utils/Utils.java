package com.sakurac1oud.SenseofCherryBlossom.utils;

import static net.minecraft.util.StatCollector.translateToLocalFormatted;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.IntFunction;

import gregtech.api.metatileentity.implementations.MTEExtendedPowerMultiBlockBase;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.Lists;
import com.sakurac1oud.SenseofCherryBlossom.SenseofCherryBlossom;

import gregtech.api.enums.HeatingCoilLevel;
import gregtech.api.metatileentity.MetaTileEntity;

public final class Utils {

    public static final double LOG2 = Math.log(2);
    public static final double LOG4 = Math.log(4);
    public static final BigInteger NEGATIVE_ONE = BigInteger.valueOf(-1);
    public static final BigInteger INTEGER_MAX_VALUE = BigInteger.valueOf(Integer.MAX_VALUE);
    public static final BigInteger BIG_INTEGER_100 = BigInteger.valueOf(100);

    // region about game

    /**
     * Create a new {@link ItemStack} of given Item with meta.
     *
     * @param item the Item
     * @param meta the meta value
     * @return a new ItemStack of given Item with meta
     */
    public static ItemStack newItemWithMeta(Item item, int meta) {
        return new ItemStack(item, 1, meta);
    }

    /**
     * Create a new {@link ItemStack} of given Block with meta.
     *
     * @param block the Block
     * @param meta  the meta value
     * @return a new ItemStack of given Item with meta
     */
    public static ItemStack newItemWithMeta(Block block, int meta) {
        return new ItemStack(block, 1, meta);
    }

    /**
     * LV = 1, MAX = 14
     */
    public static int getCoilTier(HeatingCoilLevel coilLevel) {
        return coilLevel.getTier() + 1;
    }

    /**
     * One method to handle multi survivialBuildPiece at once.
     *
     * @param buildPieces All result of `survivialBuildPiece`.
     * @return If all result is -1, return -1. Otherwise, return the sum of all non-negative values.
     */
    public static int multiBuildPiece(int... buildPieces) {
        int out = 0x80000000;
        for (int v : buildPieces) {
            out &= (v & 0x80000000) | 0x7fffffff;
            if (v != -1) out += v;
        }
        return out < 0 ? -1 : out;
    }

    public static ItemStack addStringToStackName(ItemStack itemStack, String extra) {

        String originName = itemStack.getDisplayName();
        String newName = originName + " " + extra;
        itemStack.setStackDisplayName(newName);

        return itemStack;
    }

    // endregion

    // region about ItemStack
    public static boolean metaItemEqual(ItemStack a, ItemStack b) {
        if (a == null || b == null) return false;
        if (a == b) return true;
        return a.getItem() == b.getItem() && a.getItemDamage() == b.getItemDamage();
    }

    public static ItemStack[] copyItemStackArray(ItemStack... array) {
        ItemStack[] result = new ItemStack[array.length];
        for (int i = 0; i < result.length; i++) {
            if (array[i] == null) continue;
            result[i] = array[i].copy();
        }
        return result;
    }

    public static boolean isValid(ItemStack... itemStacks) {
        if (itemStacks == null || itemStacks.length < 1) return false;
        for (int i = 0; i < itemStacks.length; i++) {
            if (!isStackValid(itemStacks[i])) return false;
        }
        return true;
    }

    public static ItemStack[] mergeItemStackArray(ItemStack[] array1, ItemStack[] array2) {
        if (array1 == null || array1.length < 1) {
            return array2;
        }
        if (array2 == null || array2.length < 1) {
            return array1;
        }
        ItemStack[] newArray = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, newArray, array1.length, array2.length);
        return newArray;
    }

    public static ItemStack[] mergeItemStackArrays(ItemStack[]... itemStacks) {
        return Arrays.stream(itemStacks)
            .filter(Objects::nonNull)
            .flatMap(Arrays::stream)
            .toArray(ItemStack[]::new);
    }

    public static <T> T[] mergeArray(T[] array1, T[] array2) {
        if (array1 == null || array1.length < 1) {
            return array2;
        }
        if (array2 == null || array2.length < 1) {
            return array1;
        }
        T[] newArray = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, newArray, array1.length, array2.length);
        return newArray;
    }

    @SafeVarargs
    public static <T> T[] mergeArrayss(T[]... arrays) {
        IntFunction<T[]> generator = null;
        for (T[] array : arrays) {
            if (array == null) continue;
            @SuppressWarnings("unchecked")
            IntFunction<T[]> tempGenerator = size -> (T[]) Array.newInstance(
                array.getClass()
                    .getComponentType(),
                size);
            generator = tempGenerator;
            break;
        }
        if (generator == null) return null;

        return Arrays.stream(arrays)
            .filter(a -> a != null && a.length > 0)
            .flatMap(Arrays::stream)
            .toArray(generator);
    }

    @SafeVarargs
    public static <T> T[] mergeArrays(T[]... arrays) {
        int totalLength = 0;
        T[] pattern = null;
        int indexFirstNotNull = -1;
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] == null || arrays[i].length < 1) continue;
            totalLength += arrays[i].length;
            if (pattern == null) {
                pattern = arrays[i];
                indexFirstNotNull = i;
            }
        }

        if (pattern == null) return null;

        T[] output = Arrays.copyOf(pattern, totalLength);
        int offset = pattern.length;
        for (int i = indexFirstNotNull; i < arrays.length; i++) {
            if (arrays[i] == null || arrays[i].length < 1) continue;
            if (arrays[i] != pattern) {
                System.arraycopy(arrays[i], 0, output, offset, arrays[i].length);
                offset += arrays[i].length;
            }
        }
        return output;
    }

    /**
     *
     * @param isa1 The ItemStack Array 1.
     * @param isa2 The ItemStack Array 2.
     * @return The elements of these two arrays are identical and in the same order.
     */
    public static boolean itemStackArrayEqualAbsolutely(ItemStack[] isa1, ItemStack[] isa2) {
        if (isa1.length != isa2.length) return false;
        for (int i = 0; i < isa1.length; i++) {
            if (!metaItemEqual(isa1[i], isa2[i])) return false;
            if (isa1[i].stackSize != isa2[i].stackSize) return false;
        }
        return true;
    }

    public static boolean itemStackArrayEqualFuzzy(ItemStack[] isa1, ItemStack[] isa2) {
        if (isa1.length != isa2.length) return false;
        for (ItemStack itemStack1 : isa1) {
            boolean flag = false;
            for (ItemStack itemStack2 : isa2) {
                if (metaItemEqual(itemStack1, itemStack2)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) return false;
        }
        return true;
    }

    public static ItemStack copyAmount(int aAmount, ItemStack aStack) {
        if (isStackInvalid(aStack)) return null;
        ItemStack rStack = aStack.copy();
        // if (aAmount > 64) aAmount = 64;
        if (aAmount == -1) aAmount = 111;
        else if (aAmount < 0) aAmount = 0;
        rStack.stackSize = aAmount;
        return rStack;
    }

    public static boolean isStackValid(ItemStack aStack) {
        return (aStack != null) && aStack.getItem() != null && aStack.stackSize >= 0;
    }

    public static boolean isStackInvalid(ItemStack aStack) {
        return aStack == null || aStack.getItem() == null || aStack.stackSize < 0;
    }

    public static ItemStack setStackSize(ItemStack itemStack, int amount) {
        if (itemStack == null) return null;
        if (amount < 0) {
            SenseofCherryBlossom.LOG
                .info("Error! Trying to set a item stack size lower than zero! " + itemStack + " to amount " + amount);
            return itemStack;
        }
        itemStack.stackSize = amount;
        return itemStack;
    }
    // endregion

    // region About FluidStack

    public static boolean fluidStackEqualAbsolutely(FluidStack[] fsa1, FluidStack[] fsa2) {
        if (fsa1.length != fsa2.length) return false;
        for (int i = 0; i < fsa1.length; i++) {
            if (!fluidEqual(fsa1[i], fsa2[i])) return false;
            if (fsa1[i].amount != fsa2[i].amount) return false;
        }
        return true;
    }

    public static boolean fluidStackEqualFuzzy(FluidStack[] fsa1, FluidStack[] fsa2) {
        if (fsa1.length != fsa2.length) return false;
        for (FluidStack fluidStack1 : fsa1) {
            boolean flag = false;
            for (FluidStack fluidStack2 : fsa2) {
                if (fluidEqual(fluidStack1, fluidStack2)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) return false;
        }
        return true;
    }

    public static boolean fluidEqual(FluidStack a, FluidStack b) {
        return a.getFluid() == b.getFluid();
    }

    public static FluidStack setStackSize(FluidStack fluidStack, int amount) {
        if (fluidStack == null) return null;
        if (amount < 0) {
            SenseofCherryBlossom.LOG
                .info("Error! Trying to set a item stack size lower than zero! " + fluidStack + " to amount " + amount);
            return fluidStack;
        }
        fluidStack.amount = amount;
        return fluidStack;
    }

    // endregion

    // region About Text
    public static String i18n(String key) {
        return translateToLocalFormatted(key);
    }

    // endregion

    // region Rewrites

    /**
     * Localize by key and given formats.
     * If the key does not exist in both of the currently used language and fallback language (English), the key itself
     * is returned.
     *
     * @param key    the localization key
     * @param format the formats
     * @return the localized text by the key and formats, or key if the key does not exist.
     * @see StatCollector#translateToLocalFormatted(String, Object...)
     */
    public static String tr(String key, Object... format) {
        return StatCollector.translateToLocalFormatted(key, format);
    }

    /**
     * Localize by the key.
     * If the key does not exist in both of the currently used language and fallback language (English), the key itself
     * is returned.
     *
     * @param key the localization key
     * @return the localized text by the key, or key if the key does not exist.
     * @see StatCollector#translateToLocal(String)
     */
    public static String tr(String key) {
        return StatCollector.translateToLocal(key);
    }

    public static <T extends Collection<?>> T filterValidMTE(T metaTileEntities) {
        metaTileEntities.removeIf(o -> {
            if (o == null) {
                return true;
            }
            if (o instanceof MetaTileEntity) {
                MetaTileEntity mte = (MetaTileEntity) o;
                return !mte.isValid();
            }
            return false;
        });
        return metaTileEntities;
    }

    /**
     * Build the machine info data array.
     *
     * @param builder the builder that puts info into the list
     * @return the built array of info data
     */
    public static String[] buildInfoData(Consumer<ArrayList<@NotNull String>> builder) {
        return buildInfoData(null, builder);
    }

    public static String[] buildInfoData(String[] superInfoData,
        Consumer<ArrayList<String>> builder) {
        ArrayList<String> ret = superInfoData != null ? Lists.newArrayList(superInfoData) : new ArrayList<>();
        builder.accept(ret);
        return ret.toArray(new String[0]);
    }

    // endregion

    // region Generals

    public static <T> T withNull(T main, T instead) {
        return null == main ? instead : main;
    }

    public static int safeInt(long number, int margin) {
        return number > Integer.MAX_VALUE - margin ? Integer.MAX_VALUE - margin : (int) number;
    }

    public static ItemStack[] sortNoNullArray(ItemStack... itemStacks) {
        if (itemStacks == null) return null;
        List<ItemStack> list = new ArrayList<>();
        for (int i = 0; i < itemStacks.length; i++) {
            if (itemStacks[i] == null) continue;
            list.add(itemStacks[i]);
        }
        if (list.isEmpty()) return new ItemStack[0];
        return list.toArray(new ItemStack[0]);
    }

    public static FluidStack[] sortNoNullArray(FluidStack... fluidStacks) {
        if (fluidStacks == null) return null;
        List<FluidStack> list = new ArrayList<>();
        for (int i = 0; i < fluidStacks.length; i++) {
            if (fluidStacks[i] == null) continue;
            list.add(fluidStacks[i]);
        }
        if (list.isEmpty()) return new FluidStack[0];
        return list.toArray(new FluidStack[0]);
    }

    public static Object[] sortNoNullArray(Object... objects) {
        if (objects == null) return null;
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == null) continue;
            list.add(objects[i]);
        }
        if (list.isEmpty()) return new Object[0];
        return list.toArray(new Object[0]);
    }

    public static <T extends Collection<E>, E extends MetaTileEntity> T filterValidMTEs(T metaTileEntities) {
        metaTileEntities.removeIf(mte -> mte == null || !mte.isValid());
        return metaTileEntities;
    }

    public static int min(int... values) {
        Arrays.sort(values);
        return values[0];
    }

    public static int max(int... values) {
        Arrays.sort(values);
        return values[values.length - 1];
    }

    public static long min(long... values) {
        Arrays.sort(values);
        return values[0];
    }

    public static long max(long... values) {
        Arrays.sort(values);
        return values[values.length - 1];
    }

    public static double calculatePowerTier(double voltage) {
        return 1 + Math.max(0, (Math.log(voltage) / LOG2) - 5) / 2;
    }

    /**
     * To get the machine maximum EU/t can get from energy hatches.
     *
     * @param machine The machine to calculate.
     * @return Total EU/t that all energy hatches supply.
     */
    public static long getMachineTotalPower(@NotNull MTEExtendedPowerMultiBlockBase<?> machine) {
        return machine.getMaxInputEu();
    }

    public static double calculateVoltageTier(double voltage) {
        return 1 + Math.max(0, (Math.log(voltage) / LOG2) - 5) / 2;
    }

    /**
     * To get the voltage tier of the machine total EU/t , allow the voltage tier over MAX tier.
     *
     * @param machine The machine to calculate.
     * @return Which voltage tier the machine's maximum EU/t from its energy hatches should be in.
     */
    public static int getMachineTotalPowerTier(@NotNull MTEExtendedPowerMultiBlockBase<?> machine) {
        return (int) Math.ceil(calculateVoltageTier(getMachineTotalPower(machine)));
    }
}
