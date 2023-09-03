package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.service.LocalizationServiceHandler;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class PersistenceView implements PersistenceViewInterface {
    private Stage stage;
    private LocalizationServiceHandler localizationServiceHandler;

    //constructor
    public PersistenceView(Stage stage, LocalizationServiceHandler localizationServiceHandler) {
        this.stage = stage;
        this.localizationServiceHandler = localizationServiceHandler;
    }

    //static

    //private

    //public
    public String askNameToSaveAs(String initialPath){
        File file;
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle(".");
        fileChooser.setInitialDirectory(new File(initialPath));
        fileChooser.setInitialFileName("untitled.json");
        file=fileChooser.showSaveDialog(stage);
        try {
            return file.getAbsolutePath();
        }catch (NullPointerException e){
            return null;
        }
    }
    public File selectFileToLoad(String initialPath){
        File file=null;
        try {
            FileChooser fileChooser=new FileChooser();
            fileChooser.setTitle(".");
            fileChooser.setInitialDirectory(new File(initialPath));
            file=fileChooser.showOpenDialog(this.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return file;
    }
    public void showPopUpQuit(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle( this.localizationServiceHandler.localize("message.quit.title"));
        alert.setHeaderText( this.localizationServiceHandler.localize("message.quit.text"));

        // Aggiungi i pulsanti per la conferma o la cancellazione
        ButtonType confermaButton = new ButtonType( this.localizationServiceHandler.localize("message.quit.confirm"));
        ButtonType cancellaButton = new ButtonType( this.localizationServiceHandler.localize("message.quit.cancel"));

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

        alert.show();

        alert.resultProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == confermaButton) {

                // L'utente ha premuto il pulsante di conferma
                Platform.exit();        // la schermata si chiude
            } else {
                // L'utente ha premuto il pulsante di cancellazione o ha chiuso la finestra dell'alert
                // In questo caso, non facciamo nulla
            }
        });
    }

    public boolean showPopUpOpenGame(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle( this.localizationServiceHandler.localize("message.opengame.title"));
        alert.setHeaderText( this.localizationServiceHandler.localize("message.opengame.text"));

        // Aggiungi i pulsanti per la conferma o la cancellazione
        ButtonType confermaButton = new ButtonType( this.localizationServiceHandler.localize("message.quit.confirm"));
        ButtonType cancellaButton = new ButtonType( this.localizationServiceHandler.localize("message.quit.cancel"));

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

    public void showPopUpError(String message) {

    }

}
