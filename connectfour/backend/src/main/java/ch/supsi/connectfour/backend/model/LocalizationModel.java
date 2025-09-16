package ch.supsi.connectfour.backend.model;

import ch.supsi.connectfour.backend.service.LocalizationServiceHandler;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationModel implements LocalizationModelHandler{

    private LocalizationServiceHandler localizationHandler;
    private boolean isInizialized=false;

    public LocalizationModel(LocalizationServiceHandler handler) {
        this.localizationHandler = handler;
    }


    public void initializeExplicit() {
        this.isInizialized=true;
    }

    @Override
    public String getBundleName() {
        if (localizationHandler.isInitialized()) {
            return localizationHandler.getBundleName();
        }

        return null;
    }

    @Override
    public Locale getLocale() {
        if (localizationHandler.isInitialized()) {
            return localizationHandler.getLocale();
        }

        return null;
    }
    @Override
    public ResourceBundle getResourceBundle() {
        if (localizationHandler.isInitialized()) {
            return localizationHandler.getResourceBundle();
        }

        return null;
    }
    @Override
    public String localize(String key) {
        if (key == null || key.isEmpty()) {
            return "";
        }

        if (localizationHandler.isInitialized()) {
            return localizationHandler.localize(key);
        }

        return key;
    }

    @Override
    public boolean isInitialized() {
        return isInizialized;
    }
}
