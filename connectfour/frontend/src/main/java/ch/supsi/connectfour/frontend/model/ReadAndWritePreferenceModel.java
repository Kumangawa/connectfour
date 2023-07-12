package ch.supsi.connectfour.frontend.model;

import ch.supsi.connectfour.backend.utility.ReadPreference;
public interface ReadAndWritePreferenceModel {

    public void initializeExplicit();
    public boolean isInitialized();
    public void changePreferedPath(String preferedPath);
    public void setSimbolPlayerFirst(String simbolPlayer);
    public void setSimbolPlayerSecond(String simbolPlayer);
    public void setColorPlayerFirst(String colorPlayer);
    public void setColorPlayerSecond(String colorPlayer);
    public void setLanguage(String language);
    public ReadPreference getReadPreference();
    public ReadPreference getReadPreferenceToSave();
}
