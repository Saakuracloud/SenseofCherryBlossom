package com.sakurac1oud.SenseofCherryBlossom.utils;

import static net.minecraft.util.StatCollector.translateToLocal;

import java.util.HashMap;
import java.util.Map;

import com.sakurac1oud.SenseofCherryBlossom.SenseofCherryBlossom;

public class TextHandler {

    public static Map<String, String> LangMap = new HashMap<>();
    public static Map<String, String> LangMapNeedToWrite = new HashMap<>();

    public static String texter(String aTextLine, String aKey) {
        // Log the translation attempt
        SenseofCherryBlossom.LOG.debug("Attempting to translate key: " + aKey + " (default: " + aTextLine + ")");

        // Get the translated text
        String translatedText = translateToLocal(aKey);

        // In dev mode, handle missing translations
        if (SenseofCherryBlossom.isInDevMode) {
            if (translatedText.equals(aKey)) { // This means translation was not found
                SenseofCherryBlossom.LOG.info("Adding new translation - Key: " + aKey + " - Text: " + aTextLine);
                LangMapNeedToWrite.put(aKey, aTextLine);
                return aTextLine;
            }
        }

        // Return translated text if it exists, otherwise return the default text
        return !translatedText.equals(aKey) ? translatedText : aTextLine;
    }

    public static String texterButKey(String aTextLine, String aKey) {
        // Log the translation attempt
        SenseofCherryBlossom.LOG.debug("Recording translation key: " + aKey + " (default: " + aTextLine + ")");

        // In dev mode, handle missing translations
        if (SenseofCherryBlossom.isInDevMode) {
            String translatedText = translateToLocal(aKey);
            if (translatedText.equals(aKey)) { // This means translation was not found
                SenseofCherryBlossom.LOG.info("Adding new translation - Key: " + aKey + " - Text: " + aTextLine);
                LangMapNeedToWrite.put(aKey, aTextLine);
            }
        }
        return aKey;
    }

    /**
     * Auto generate the Key of textLine
     * {@link TextHandler#texter(String aTextLine, String aKey)}
     *
     * @param aTextLine The default String to where you use.
     * @return
     */
    public static String texter(String aTextLine) {
        String aKey = "Auto." + aTextLine + ".text";
        return texter(aTextLine, aKey);
    }
}
