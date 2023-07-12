package ch.supsi.connectfour.frontend.controller.observer;

public interface WriteObserver {

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
