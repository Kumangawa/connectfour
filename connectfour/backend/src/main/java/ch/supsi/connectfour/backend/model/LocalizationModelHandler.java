package ch.supsi.connectfour.backend.model;

import ch.supsi.connectfour.backend.utility.Handler;

import java.util.Locale;
import java.util.ResourceBundle;

public interface LocalizationModelHandler extends Handler {
    String getBundleName();
    Locale getLocale();
    ResourceBundle getResourceBundle();
    String localize(String key);
    void initializeExplicit();
}
