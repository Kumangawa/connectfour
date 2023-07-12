package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.backend.utility.ReadPreference;
import ch.supsi.connectfour.frontend.controller.observer.WriteObserver;

public interface WritePreferenceController {
    public void initializeExplicit();
    public void initializeInfoBar(WriteObserver observer);
    public void setLanguage(String language);
    public void editPath();
    public void editSymbolPlayerSecond();
    public void editSymbolPlayerFirst();
    public void editColorPlayerSecond();
    public void editColorPlayerFirst();
    public ReadPreference getReadPreference();
    public ReadPreference getReadPreferenceToSave();
}
