package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.backend.model.Match;

public interface PersistenceControllerInterface {

    public void initializeExplicit();
    public boolean hasSaved();
    public boolean saveIsUpToDate();
    public void save();
    public void saveAs();
    public Match load();
    public void reset();
    public void wantToQuit();
}
