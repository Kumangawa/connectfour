package ch.supsi.connectfour.frontend.model;

import ch.supsi.connectfour.backend.model.PreferenceInterface;

public interface PreferenceModelInterface {

    public void initializeExplicit();
    public boolean isInitialized();
    public void changePreferedPath(String preferedPath);
    public void setSimbolPlayerFirst(String simbolPlayer);
    public void setSimbolPlayerSecond(String simbolPlayer);
    public void setColorPlayerFirst(String colorPlayer);
    public void setColorPlayerSecond(String colorPlayer);
    public void setLanguage(String language);
    public PreferenceInterface getReadPreferenceToSave();
}
