package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.service.LocalizationServiceHandler;
import javafx.scene.text.Text;

public class InfoBarView implements InfoBarViewInterface {

    private boolean inizialized=false;

    public Text infoBar;

    private LocalizationServiceHandler localizationServiceHandler;
    public void initializeExplicit(LocalizationServiceHandler localizationServiceHandler) {
        this.localizationServiceHandler = localizationServiceHandler;
        this.inizialized=true;
    }

    public void changeTurnToPlayerOne() {
        infoBar.setText(this.localizationServiceHandler.localize("ui.user.message.turn.player.one"));
    }
    public void changeTurnToPlayerTwo() {
        infoBar.setText(this.localizationServiceHandler.localize("ui.user.message.turn.player.two"));
    }

    public void changeColorFirstPlayerSuccess() {
        infoBar.setText(this.localizationServiceHandler.localize("message.changed.color.player.one.success"));
    }

    public void changeColorFirstPlayerFail() {
        infoBar.setText(this.localizationServiceHandler.localize("message.changed.color.player.one.fail"));
    }

    public void changeSymbolFirstPlayerSuccess() {
        infoBar.setText(this.localizationServiceHandler.localize("message.changed.symbol.player.one.success"));
    }

    public void changeSymbolFirstPlayerFail() {
        infoBar.setText(this.localizationServiceHandler.localize("message.changed.symbol.player.one.fail"));
    }

    public void changeColorSecondPlayerSuccess() {
        infoBar.setText(this.localizationServiceHandler.localize("message.changed.color.player.two.success"));
    }

    public void changeColorSecondPlayerFail() {
        infoBar.setText(this.localizationServiceHandler.localize("message.changed.color.player.two.fail"));
    }

    public void changeSymbolSecondPlayerSuccess() {
        infoBar.setText(this.localizationServiceHandler.localize("message.changed.symbol.player.two.success"));
    }

    public void changeSymbolSecondPlayerFail() {
        infoBar.setText(this.localizationServiceHandler.localize("message.changed.symbol.player.two.fail"));
    }

    public void changePathSuccess() {
        infoBar.setText(this.localizationServiceHandler.localize("message.changed.path.success"));
    }

    public void changePathFail() {
        infoBar.setText(this.localizationServiceHandler.localize("message.changed.path.fail"));
    }

    public void changeLanguage() {
        infoBar.setText(this.localizationServiceHandler.localize("message.changed.language"));
    }

    public void loadMatch() {
        infoBar.setText(this.localizationServiceHandler.localize("message.changed.game"));
    }

    public void endGameDraw(){
        infoBar.setText(this.localizationServiceHandler.localize("message.endgame.draw.title"));
    }

    public void endGameWin(int end){
        if (end == 1){
            infoBar.setText(this.localizationServiceHandler.localize("message.endgame.win.player.one.title"));
        } else {
            infoBar.setText(this.localizationServiceHandler.localize("message.endgame.win.player.two.title"));
        }

    }
}
