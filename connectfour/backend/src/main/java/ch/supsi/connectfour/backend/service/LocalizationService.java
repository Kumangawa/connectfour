package ch.supsi.connectfour.backend.service;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LocalizationService implements LocalizationServiceHandler {

    private boolean initialized=false;

    private String bundleName;

    private Locale locale;

    private ResourceBundle translations;



    //constructor
    public LocalizationService(String bundleName, Locale locale) {
        this.bundleName = bundleName;
        this.locale = locale;
        this.translations = ResourceBundle.getBundle(bundleName, locale);
    }

    //static

    //private

    //public

    @Override
    public void initializeExplicit() {
        this.initialized=true;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }
    @Override
    public String getBundleName() {
        return this.bundleName;
    }

    @Override
    public Locale getLocale() {
        return this.locale;
    }

    @Override
    public ResourceBundle getResourceBundle() {
        return this.translations;
    }

    @Override
    public String localize(String key) {
        if (key == null || key.isEmpty()) {
            // should handle exception

            return "";
        }

        String translation;

        try {
            translation = translations.getString(key);

        } catch (MissingResourceException e) {
            translation = key;
        }

        return translation;
    }

}
