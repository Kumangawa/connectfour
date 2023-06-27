package ch.supsi.connectfour.backend.controller;

import ch.supsi.connectfour.backend.controller.handler.LocalizationControllerHandler;
import ch.supsi.connectfour.backend.model.handler.LocalizationModelHandler;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationController implements LocalizationControllerHandler {

    private LocalizationModelHandler localizationHandler;
    private boolean isInizialized=false;

    public LocalizationController(LocalizationModelHandler handler) {
        this.localizationHandler = handler;
    }

    @Override
    public void initializeExplicit() {
        this.isInizialized=true;
    }

    @Override
    public String getBundleName() {
        if (localizationHandler.isInitialized()) {
            return localizationHandler.getBundleName();
        }
        // should handle exception

        return null;
    }

    @Override
    public Locale getLocale() {
        if (localizationHandler.isInitialized()) {
            return localizationHandler.getLocale();
        }
        // should handle exception

        return null;
    }

    @Override
    public ResourceBundle getResourceBundle() {
        if (localizationHandler.isInitialized()) {
            return localizationHandler.getResourceBundle();
        }
        // should handle exception

        return null;
    }

    @Override
    public String localize(String key) {
        if (key == null || key.isEmpty()) {
            // should handle exception
            return "";
        }

        if (localizationHandler.isInitialized()) {
            return localizationHandler.localize(key);
        }

        return key;
    }

    @Override
    public boolean isInitialized() {
        return false;
    }


}
