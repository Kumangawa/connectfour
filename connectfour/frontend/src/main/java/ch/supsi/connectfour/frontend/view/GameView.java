package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.model.handler.LocalizationModelHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameView {
    private LocalizationModelHandler localizationModelHandler;

    //constructor
    public GameView(LocalizationModelHandler localizationModelHandler) {
        this.localizationModelHandler = localizationModelHandler;
    }

    //static

    //private

    //public
    public void showMove(Button clickedButton, Color colore, String simbolo) {
        // Imposta il colore del testo su Grigio
        clickedButton.setTextFill(colore);
        clickedButton.setText(simbolo);
        clickedButton.setDisable(true);
    }
    public boolean showPopUpEnd( int ending){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if (ending == -1){
            // Draw
            alert.setTitle(this.localizationModelHandler.localize("message.endgame.draw.title"));
            alert.setHeaderText(this.localizationModelHandler.localize("message.endgame.draw.text"));
        } else if (ending == 1) {
            // Win Player
            alert.setTitle(this.localizationModelHandler.localize("message.endgame.win.player.one.title"));
            alert.setHeaderText(this.localizationModelHandler.localize("message.endgame.win.player.one.text"));
        } else {
            // Win AI
            alert.setTitle(this.localizationModelHandler.localize("message.endgame.win.player.two.title"));
            alert.setHeaderText(this.localizationModelHandler.localize("message.endgame.win.player.two.text"));
        }


        // Aggiungi i pulsanti per la conferma o la cancellazione
        ButtonType confermaButton = new ButtonType(this.localizationModelHandler.localize("message.endgame.newgame"));
        ButtonType cancellaButton = new ButtonType(this.localizationModelHandler.localize("message.endgame.exit"));

        alert.getButtonTypes().setAll(confermaButton, cancellaButton);

        // Imposta la gestione dell'evento di chiusura della finestra del popup
        alert.setOnHiding(event -> {
            if (alert.getResult() == null) {
                alert.setResult(cancellaButton);
            }
        });

        // Imposta la gestione della chiusura dell'alert quando l'utente preme X o Alt+F4
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setOnCloseRequest(event -> {
            event.consume();
            alert.setResult(cancellaButton);
            alert.hide();
        });

        ButtonType button=alert.showAndWait().get();
        if(button.equals(confermaButton)){
            return true;
        }else {
            return false;
        }
    }


    public boolean showPopUpNewGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle(this.localizationModelHandler.localize("message.newgame.title"));
        alert.setHeaderText(this.localizationModelHandler.localize("message.newgame.text"));



        // Aggiungi i pulsanti per la conferma o la cancellazione
        ButtonType confermaButton = new ButtonType(this.localizationModelHandler.localize("message.quit.confirm"));
        ButtonType cancellaButton = new ButtonType(this.localizationModelHandler.localize("message.quit.cancel"));

        alert.getButtonTypes().setAll(confermaButton, cancellaButton);

        // Imposta la gestione dell'evento di chiusura della finestra del popup
        alert.setOnHiding(event -> {
            if (alert.getResult() == null) {
                alert.setResult(cancellaButton);
            }
        });

        // Imposta la gestione della chiusura dell'alert quando l'utente preme X o Alt+F4
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setOnCloseRequest(event -> {
            event.consume();
            alert.setResult(cancellaButton);
            alert.hide();
        });

        ButtonType button=alert.showAndWait().get();
        if(button.equals(confermaButton)){
            return true;
        }else {
            return false;
        }
    }
}
