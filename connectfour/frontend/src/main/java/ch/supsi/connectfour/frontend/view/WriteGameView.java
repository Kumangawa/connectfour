package ch.supsi.connectfour.frontend.view;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public interface WriteGameView {
    public void showMove(Button clickedButton, Color colore, String simbolo);
    public boolean showPopUpEnd(int ending);
    public boolean showPopUpNewGame();
}
