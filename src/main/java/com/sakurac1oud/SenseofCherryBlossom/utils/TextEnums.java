package com.sakurac1oud.SenseofCherryBlossom.utils;

import org.jetbrains.annotations.ApiStatus;

/**
 *
 */
public enum TextEnums {

    Mod_SenseofCherryBlossom("Mod_SenseofCherryBlossom");

    @ApiStatus.Obsolete
    // spotless:on
    public static String tr(String key) {
        return Utils.tr(key);
    }

    @ApiStatus.Obsolete
    public static String tr(String key, Object... format) {
        return Utils.tr(key, format);
    }

    private final String text;
    private final String key;

    TextEnums(String key) {
        this.key = key;
        this.text = tr(key);
    }

    @Override
    public String toString() {
        return text;
    }

    public String getKey() {
        return key;
    }

    public String getText() {
        return text;
    }

}
