package ch.supsi.connectfour.frontend.controller.observer;

import ch.supsi.connectfour.frontend.controller.WriteInfoBarController;

public class Observer implements WriteObserver{

    private WriteInfoBarController infoBarController;

    private boolean inizialized=false;

    public Observer(WriteInfoBarController infoBarController) {
        this.infoBarController = infoBarController;
        this.inizialized=true;
    }

    public void changeTurnToPlayerOne(){
        infoBarController.changeTurnToPlayerOne();
    }

    public void changeTurnToPlayerTwo(){
        infoBarController.changeTurnToPlayerTwo();
    }

    public void changeColorFirstPlayerSuccess(){
        infoBarController.changeColorFirstPlayerSuccess();
    }

    public void changeColorFirstPlayerFail(){
        infoBarController.changeColorFirstPlayerFail();
    }

    public void changeSymbolFirstPlayerSuccess(){
        infoBarController.changeSymbolFirstPlayerSuccess();
    }

    public void changeSymbolFirstPlayerFail(){
        infoBarController.changeSymbolFirstPlayerFail();
    }

    public void changeColorSecondPlayerSuccess(){
        infoBarController.changeColorSecondPlayerSuccess();
    }

    public void changeColorSecondPlayerFail(){
        infoBarController.changeColorSecondPlayerFail();
    }

    public void changeSymbolSecondPlayerSuccess(){
        infoBarController.changeSymbolSecondPlayerSuccess();
    }

    public void changeSymbolSecondPlayerFail(){
        infoBarController.changeSymbolSecondPlayerFail();
    }

    public void changePathSuccess(){
        infoBarController.changePathSuccess();
    }

    public void changePathFail(){
        infoBarController.changePathFail();
    }

    public void changeLanguage(){
        infoBarController.changeLanguage();
    }

    public void loadMatch() {
        infoBarController.loadMatch();
    }
}
