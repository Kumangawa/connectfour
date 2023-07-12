package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.frontend.view.WriteInfoBarView;
import javafx.scene.text.Text;

public class InfoBarController implements WriteInfoBarController {

    private WriteInfoBarView infoBarView;
    private boolean inizialized=false;

    public Text turnBar, infoBar;

    public void initializeExplicit(WriteInfoBarView infoBarView) {
        this.infoBarView=infoBarView;
        this.inizialized=true;
    }

    public void changeTurnToPlayerOne(){
        infoBarView.changeTurnToPlayerOne(turnBar);
    }

    public void changeTurnToPlayerTwo(){
        infoBarView.changeTurnToPlayerTwo(turnBar);
    }

    public void changeColorFirstPlayerSuccess(){
        infoBarView.changeColorFirstPlayerSuccess(infoBar);
    }

    public void changeColorFirstPlayerFail(){
        infoBarView.changeColorFirstPlayerFail(infoBar);
    }

    public void changeSymbolFirstPlayerSuccess(){
        infoBarView.changeSymbolFirstPlayerSuccess(infoBar);
    }

    public void changeSymbolFirstPlayerFail(){
        infoBarView.changeSymbolFirstPlayerFail(infoBar);
    }

    public void changeColorSecondPlayerSuccess(){
        infoBarView.changeColorSecondPlayerSuccess(infoBar);
    }

    public void changeColorSecondPlayerFail(){
        infoBarView.changeColorSecondPlayerFail(infoBar);
    }

    public void changeSymbolSecondPlayerSuccess(){
        infoBarView.changeSymbolSecondPlayerSuccess(infoBar);
    }

    public void changeSymbolSecondPlayerFail(){
        infoBarView.changeSymbolSecondPlayerFail(infoBar);
    }
    public void changePathSuccess(){
        infoBarView.changePathSuccess(infoBar);
    }

    public void changePathFail(){
        infoBarView.changePathFail(infoBar);
    }

    public void changeLanguage(){
        infoBarView.changeLanguage(infoBar);
    }

    public void loadMatch() {
        infoBarView.loadMatch(infoBar);
    }
}

