package com.sakurac1oud.SenseofCherryBlossom.common.api;

import gregtech.api.enums.SubTag;
import gregtech.api.util.GTOreDictUnificator;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * A combination of {@link IHasVariant} and {@link IHasTooltips} with enhanced variant and tooltip registration.
 *
 * @author Zulu
 * @version 1.2
 */
public interface IHasVariantAndTooltips extends IHasVariant, IHasTooltips {

    /**
     * Register a variant with basic tooltips.
     *
     * @param meta     Metadata for the variant
     * @param tooltips Array of tooltips to set
     * @return Registered ItemStack
     * @throws IllegalArgumentException if meta is invalid or tooltips are null
     */
    default ItemStack registerVariantWithTooltips(int meta, String[] tooltips) {
        validateInputInternal(meta, tooltips);

        ItemStack stack = registerVariant(meta);
        setTooltips(meta, tooltips, false);
        return stack;
    }

    /**
     * Register a variant with basic and advanced tooltips.
     *
     * @param meta             Metadata for the variant
     * @param tooltips         Array of basic tooltips
     * @param advancedTooltips Array of advanced tooltips
     * @return Registered ItemStack
     * @throws IllegalArgumentException if inputs are invalid
     */
    default ItemStack registerVariantWithTooltips(int meta, String[] tooltips, String[] advancedTooltips) {
        validateInputInternal(meta, tooltips, advancedTooltips);

        ItemStack stack = registerVariant(meta);
        setTooltips(meta, tooltips, false);
        setTooltips(meta, advancedTooltips, true);
        return stack;
    }

    /**
     * Register a variant with tooltips and optional random data processing.
     *
     * @param meta        Metadata for the variant
     * @param tooltips    Array of tooltips
     * @param randomData  Optional additional data for processing
     * @return Registered ItemStack
     * @throws IllegalArgumentException if inputs are invalid
     */
    default ItemStack registerVariantWithTooltips(int meta, String[] tooltips, Object... randomData) {
        validateInputInternal(meta, tooltips);

        ItemStack stack = registerVariant(meta);
        setTooltips(meta, tooltips, false);

        processRandomDataInternal(stack, randomData);

        return stack;
    }

    /**
     * Validate input parameters for variant registration.
     *
     * @param meta     Metadata for the variant
     * @param tooltips Tooltips to validate
     * @throws IllegalArgumentException if validation fails
     */
    default void validateInputInternal(int meta, String[]... tooltips) {
        if (meta < 0) {
            throw new IllegalArgumentException("Metadata must be non-negative");
        }

        if (tooltips != null) {
            for (String[] tips : tooltips) {
                if (tips != null && tips.length == 0) {
                    throw new IllegalArgumentException("Tooltip arrays must not be empty");
                }
            }
        }
    }

    /**
     * Process random data for an ItemStack.
     *
     * @param stack      ItemStack to process
     * @param randomData Array of random data objects
     */
    default void processRandomDataInternal(ItemStack stack, Object... randomData) {
        if (randomData == null) return;

        for (Object data : randomData) {
            if (data != null) {
                processDataItemInternal(stack, data);
            }
        }
    }

    /**
     * Process individual data item for an ItemStack.
     *
     * @param stack ItemStack to process
     * @param data  Data object to process
     */
    default void processDataItemInternal(ItemStack stack, Object data) {
        if (data instanceof SubTag) {
            processSubTagInternal(stack, (SubTag) data);
        } else {
            registerOreDictIfPossibleInternal(stack, data);
        }
    }

    /**
     * Process SubTag for an ItemStack.
     *
     * @param stack   ItemStack to process
     * @param subTag  SubTag to process
     */
    default void processSubTagInternal(ItemStack stack, SubTag subTag) {
        if (subTag == SubTag.NO_UNIFICATION) {
            GTOreDictUnificator.addToBlacklist(stack);
        }
    }

    /**
     * Register ore dictionary for an ItemStack if possible.
     *
     * @param stack ItemStack to register
     * @param data  Data to use for registration
     */
    default void registerOreDictIfPossibleInternal(ItemStack stack, Object data) {
        if (isValidOreDictInternal(data)) {
            GTOreDictUnificator.registerOre(data, stack);
        }
    }

    /**
     * Check if an object is valid for ore dictionary registration.
     *
     * @param data Object to validate
     * @return true if the object can be registered in ore dictionary
     */
    default boolean isValidOreDictInternal(Object data) {
        return data != null &&
               !(data instanceof SubTag) &&
               !(data instanceof String && ((String) data).isEmpty());
    }
}
