package ch.supsi.connectfour.frontend;

import javafx.scene.text.Text;

public class InfoBar {
    private boolean inizialized=false;

    public Text infoBar;

    public void showMessage(String message) {
        this.infoBar.setText(message);
    }


    public void initializeExplicit() {
        this.inizialized=true;
    }
}

