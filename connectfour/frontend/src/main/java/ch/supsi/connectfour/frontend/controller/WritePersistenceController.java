package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.backend.utility.Match;

public interface WritePersistenceController {

    public void initializeExplicit();
    public boolean hasSaved();
    public boolean saveIsUpToDate();
    public void save();
    public void saveAs();
    public Match load();
    public void reset();
    public void wantToQuit();
}
