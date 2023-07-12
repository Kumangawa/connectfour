package ch.supsi.connectfour.frontend.view;

import java.io.*;

public interface WritePreferenceView {
    public File showDirChooser(String initialPath);
    public String showPopUpEditSymbolPlayerSecond(String symbolPlayerSecond, String symbolFuturePlayerFirst);
    public String showPopUpEditSymbolPlayerFirst(String symbolPlayerFirst, String symbolFuturePlayerSecond);
    public String showPopUpEditColor(String color);
}
