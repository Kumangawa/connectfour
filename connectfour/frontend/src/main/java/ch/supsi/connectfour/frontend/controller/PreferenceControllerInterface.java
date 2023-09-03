package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.frontend.view.InfoBarViewInterface;

public interface PreferenceControllerInterface {
    public void initializeExplicit();
    public void initializeInfoBar(InfoBarViewInterface infoBarView);
    public void setLanguage(String language);
    public void editPath();
    public void editSymbolPlayerSecond();
    public void editSymbolPlayerFirst();
    public void editColorPlayerSecond();
    public void editColorPlayerFirst();
}
