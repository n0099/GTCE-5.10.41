package gregtech.api.util;


import gregtech.api.GregTech_API;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.Locale;
import net.minecraft.util.text.translation.LanguageMap;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import static gregtech.api.enums.GT_Values.E;

public class GT_LanguageManager {

    private GT_LanguageManager() {}

    public static final HashMap<String, String>
            LOCALIZATION = new HashMap<>(),
            BUFFERMAP = new HashMap<>();

    public static Configuration sEnglishFile;
    //TODO implement 03f6c9e

    public static String addStringLocalization(String aKey, String aEnglish) {
        return addStringLocalization(aKey, aEnglish, false);
    }

    public static String addStringLocalization(String aKey, String aEnglish, boolean aWriteIntoLangFile) {
        if (aKey == null)
            return E;
        if (aWriteIntoLangFile)
            aEnglish = writeToLangFile(aKey, aEnglish);
        LOCALIZATION.put(aKey, aEnglish);
        return aEnglish;
    }

    public static synchronized HashMap readFromLangFile(String langFilePath) {
        List<String> langFileRows = new ArrayList<String>();
        File langFile = new File(langFilePath);
        if (langFile.exists() && langFile.isFile()) {
            InputStreamReader streamReader = new InputStreamReader(new FileInputStream(langFile), "UTF-8");
            BufferedReader bufferReader = new BufferedReader(streamReader);
            String readerCurrentRow = null;
            while ((readerCurrentRow = bufferReader.readLine()) != null) {
                langFileRows.add(readerCurrentRow);
            }
            readerCurrentRow = null;
            bufferReader.close();
            streamReader.close();
            HashMap<String, String> returnHashMap = new HashMap<>();
            for (String langFileCurrentRow : langFileRows) {
                String langKey = langFileCurrentRow.substring(langFileCurrentRow.indexOf("S:\"") + 3, langFileCurrentRow.indexOf("\"="));
                String langContent = langFileCurrentRow.substring(langFileCurrentRow.indexOf("\"=") + 2);
                GT_Log.out.println("reading lang file line: " + langKey + " | " + langContent);
                returnHashMap.put(langKey, langContent);
            }
        }
    }

    private static synchronized String writeToLangFile(String aKey, String aEnglish) {
        if (aKey == null) return E;
        if (sEnglishFile == null) {
            BUFFERMAP.put(aKey.trim(), aEnglish);
        } else {
            if (!BUFFERMAP.isEmpty()) {
                for (Entry<String, String> tEntry : BUFFERMAP.entrySet()) {
                    Property tProperty = sEnglishFile.get("LanguageFile", tEntry.getKey(), tEntry.getValue());
                    if (!tProperty.wasRead() && GregTech_API.sPostloadFinished) sEnglishFile.save();
                }
                BUFFERMAP.clear();
            }
            Property tProperty = sEnglishFile.get("LanguageFile", aKey.trim(), aEnglish);
            if (!tProperty.wasRead() && GregTech_API.sPostloadFinished) sEnglishFile.save();
            if (sEnglishFile.get("EnableLangFile", "UseThisFileAsLanguageFile", false).getBoolean(false))
                aEnglish = tProperty.getString();
        }
        return aEnglish;
    }

    public static String getTranslation(String aKey) {
        if (aKey == null) {
            return E;
        }
        if (LOCALIZATION.containsKey(aKey) && !LOCALIZATION.get(aKey).equals(net.minecraft.util.text.translation.I18n.translateToLocal(aKey))) {
            injectAllLocales();
        }
        GT_Log.out.println("getTranslation, key: " + aKey + " return: " + net.minecraft.util.text.translation.I18n.translateToLocal(aKey));
        return net.minecraft.util.text.translation.I18n.translateToLocal(aKey);
    }

    public static String getTranslation(String aKey, String aSeperator) {
        if (aKey == null) return E;
        String rTranslation = E;
        for (String tString : aKey.split(aSeperator)) {
            rTranslation += getTranslation(tString);
        }
        return rTranslation;
    }

    public static void injectAllLocales() {
        injectCommonLocales();

        if(FMLCommonHandler.instance().getSide().isClient()) {
            injectClientLocales();
        }

        GT_Log.out.println("Localization injected.");
    }

    public static void injectCommonLocales() {
        LanguageMap languageMap = ObfuscationReflectionHelper.getPrivateValue(net.minecraft.util.text.translation.I18n.class, null, 0);
        Map<String, String> properties2 = ObfuscationReflectionHelper.getPrivateValue(LanguageMap.class, languageMap, 3);
        properties2.putAll(LOCALIZATION);
    }

    @SideOnly(Side.CLIENT)
    public static void injectClientLocales() {
        Locale i18nLocale = ObfuscationReflectionHelper.getPrivateValue(I18n.class, null, 0);
        Map<String, String> properties = ObfuscationReflectionHelper.getPrivateValue(Locale.class, i18nLocale, 2);
        properties.putAll(LOCALIZATION);
    }

}