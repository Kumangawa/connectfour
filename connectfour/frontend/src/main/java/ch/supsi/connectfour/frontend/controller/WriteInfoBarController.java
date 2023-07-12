package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.frontend.view.WriteInfoBarView;

public interface WriteInfoBarController {

    public void initializeExplicit(WriteInfoBarView infoBarView);

    public void changeTurnToPlayerOne();

    public void changeTurnToPlayerTwo();

    public void changeColorFirstPlayerSuccess();

    public void changeColorFirstPlayerFail();

    public void changeSymbolFirstPlayerSuccess();

    public void changeSymbolFirstPlayerFail();
    public void changeColorSecondPlayerSuccess();

    public void changeColorSecondPlayerFail();

    public void changeSymbolSecondPlayerSuccess();

    public void changeSymbolSecondPlayerFail();
    public void changePathSuccess();

    public void changePathFail();

    public void changeLanguage();

    public void loadMatch();
}
