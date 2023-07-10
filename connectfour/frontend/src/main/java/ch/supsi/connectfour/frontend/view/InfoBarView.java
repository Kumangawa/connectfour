package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.model.handler.LocalizationModelHandler;
import javafx.scene.text.Text;

public class InfoBarView {
    private LocalizationModelHandler localizationModelHandler;

    //constructor
    public InfoBarView(LocalizationModelHandler localizationModelHandler) {
        this.localizationModelHandler = localizationModelHandler;
    }

    public void changeTurnToPlayerOne(Text turnBar) {
        turnBar.setText(this.localizationModelHandler.localize("ui.user.message.turn.player.one"));
    }
    public void changeTurnToPlayerTwo(Text turnBar) {
        turnBar.setText(this.localizationModelHandler.localize("ui.user.message.turn.player.two"));
    }

    public void changeColorFirstPlayerSuccess(Text infoBar) {
        infoBar.setText(this.localizationModelHandler.localize("message.changed.color.player.one.success"));
    }

    public void changeColorFirstPlayerFail(Text infoBar) {
        infoBar.setText(this.localizationModelHandler.localize("message.changed.color.player.one.fail"));
    }

    public void changeSymbolFirstPlayerSuccess(Text infoBar) {
        infoBar.setText(this.localizationModelHandler.localize("message.changed.symbol.player.one.success"));
    }

    public void changeSymbolFirstPlayerFail(Text infoBar) {
        infoBar.setText(this.localizationModelHandler.localize("message.changed.symbol.player.one.fail"));
    }

    public void changeColorSecondPlayerSuccess(Text infoBar) {
        infoBar.setText(this.localizationModelHandler.localize("message.changed.color.player.two.success"));
    }

    public void changeColorSecondPlayerFail(Text infoBar) {
        infoBar.setText(this.localizationModelHandler.localize("message.changed.color.player.two.fail"));
    }

    public void changeSymbolSecondPlayerSuccess(Text infoBar) {
        infoBar.setText(this.localizationModelHandler.localize("message.changed.symbol.player.two.success"));
    }

    public void changeSymbolSecondPlayerFail(Text infoBar) {
        infoBar.setText(this.localizationModelHandler.localize("message.changed.symbol.player.two.fail"));
    }

    public void changePathSuccess(Text infoBar) {
        infoBar.setText(this.localizationModelHandler.localize("message.changed.path.success"));
    }

    public void changePathFail(Text infoBar) {
        infoBar.setText(this.localizationModelHandler.localize("message.changed.path.fail"));
    }

    public void changeLanguage(Text infoBar) {
        infoBar.setText(this.localizationModelHandler.localize("message.changed.language"));
    }

    public void loadMatch(Text infoBar) {
        infoBar.setText(this.localizationModelHandler.localize("message.changed.game"));
    }
}
