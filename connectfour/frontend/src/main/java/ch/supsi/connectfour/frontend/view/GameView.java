package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.service.LocalizationServiceHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameView implements GameViewInterface {
    private LocalizationServiceHandler localizationServiceHandler;

    //constructor
    public GameView(LocalizationServiceHandler localizationServiceHandler) {
        this.localizationServiceHandler = localizationServiceHandler;
    }

    //static

    //private

    //public
    public void showMove(Button clickedButton, Color colore, String simbolo) {
        // Imposta il colore del testo su Grigio
        clickedButton.setTextFill(colore);
        clickedButton.setText(simbolo);
    }
    public boolean showPopUpEnd( int ending){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if (ending == -1){
            // Draw
            alert.setTitle(this.localizationServiceHandler.localize("message.endgame.draw.title"));
            alert.setHeaderText(this.localizationServiceHandler.localize("message.endgame.draw.text"));
        } else if (ending == 1) {
            // Win Player 1
            alert.setTitle(this.localizationServiceHandler.localize("message.endgame.win.player.one.title"));
            alert.setHeaderText(this.localizationServiceHandler.localize("message.endgame.win.player.one.text"));
        } else {
            // Win Player 2
            alert.setTitle(this.localizationServiceHandler.localize("message.endgame.win.player.two.title"));
            alert.setHeaderText(this.localizationServiceHandler.localize("message.endgame.win.player.two.text"));
        }

        // Aggiungi i pulsanti per la conferma o la cancellazione
        ButtonType confermaButton = new ButtonType(this.localizationServiceHandler.localize("message.endgame.newgame"));
        ButtonType cancellaButton = new ButtonType(this.localizationServiceHandler.localize("message.endgame.exit"));

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

        alert.setTitle(this.localizationServiceHandler.localize("message.newgame.title"));
        alert.setHeaderText(this.localizationServiceHandler.localize("message.newgame.text"));

        // Aggiungi i pulsanti per la conferma o la cancellazione
        ButtonType confermaButton = new ButtonType(this.localizationServiceHandler.localize("message.quit.confirm"));
        ButtonType cancellaButton = new ButtonType(this.localizationServiceHandler.localize("message.quit.cancel"));

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
