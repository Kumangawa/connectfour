package ch.supsi.connectfour.frontend.model;

import ch.supsi.connectfour.backend.utility.Match;

import java.io.*;

public interface ReadAndWritePersistenceModel {

    public boolean isSaved();
    public void reset();
    public <T> void save(T t);
    public <T> void saveAs(T t, String absolutePath);
    public Match load(File file);
}
