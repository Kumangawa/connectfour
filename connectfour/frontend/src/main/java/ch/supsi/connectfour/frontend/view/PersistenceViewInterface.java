package ch.supsi.connectfour.frontend.view;

import java.io.File;

public interface PersistenceViewInterface {
    public String askNameToSaveAs(String initialPath);
    public File selectFileToLoad(String initialPath);
    public void showPopUpQuit();
    public boolean showPopUpOpenGame();
    public void showPopUpError(String message);
}
