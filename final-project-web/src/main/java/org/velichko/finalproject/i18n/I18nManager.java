package org.velichko.finalproject.i18n;

import org.velichko.finalproject.logic.pool.PropertyLoader;

import java.net.URL;
import java.util.*;

public class I18nManager {

    private static final String SUPPORTED_LOCALES = "supported.locales";
    private Map<String, ResourceBundle> bundleMap = new HashMap<>();

    public I18nManager() {
        List<String> localeList = getAllLocales();

        for (String locale : localeList) {
            ResourceBundle localization = ResourceBundle.getBundle("localization", Locale.forLanguageTag(locale));
            bundleMap.put(locale, localization);
        }
    }

    private List<String> getAllLocales() {
        List<String> result = new ArrayList<>();
        URL resource = getClass().getClassLoader().getResource("common.properties");
        Properties properties = PropertyLoader.loadPropertiesData(resource);
        String property = properties.getProperty(SUPPORTED_LOCALES);
        for (String next : property.split(",")) {
            result.add(next.trim());
        }

        return result;
    }

    public String getMassage(String key, String locale) {
        if (locale == null) {
            locale = "ru-RU";
        }
        return bundleMap.get(locale).getString(key);
    }
//
//    public static I18nManager getInstance() {
//        return I18nManager.I18nManagerHolder.HOLDER_INSTANCE;
//    }
//
//    private static class I18nManagerHolder {
//        public static final I18nManager HOLDER_INSTANCE = new I18nManager();
//    }

}
