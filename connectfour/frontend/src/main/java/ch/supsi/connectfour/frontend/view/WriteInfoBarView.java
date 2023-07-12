package ch.supsi.connectfour.frontend.view;

import javafx.scene.text.Text;

public interface WriteInfoBarView {

    public void changeTurnToPlayerOne(Text turnBar);

    public void changeTurnToPlayerTwo(Text turnBar);

    public void changeColorFirstPlayerSuccess(Text infoBar);

    public void changeColorFirstPlayerFail(Text infoBar);

    public void changeSymbolFirstPlayerSuccess(Text infoBar);

    public void changeSymbolFirstPlayerFail(Text infoBar);

    public void changeColorSecondPlayerSuccess(Text infoBar);

    public void changeColorSecondPlayerFail(Text infoBar);

    public void changeSymbolSecondPlayerSuccess(Text infoBar);

    public void changeSymbolSecondPlayerFail(Text infoBar);

    public void changePathSuccess(Text infoBar);

    public void changePathFail(Text infoBar);

    public void changeLanguage(Text infoBar);

    public void loadMatch(Text infoBar);
}
