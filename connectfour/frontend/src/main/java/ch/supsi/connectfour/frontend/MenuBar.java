package ch.supsi.connectfour.frontend;


import ch.supsi.connectfour.backend.controller.handler.LocalizationControllerHandler;
import ch.supsi.connectfour.backend.utility.Match;
import ch.supsi.connectfour.frontend.controller.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import java.util.Objects;

public class MenuBar {
    private boolean inizialized=false;
    private LocalizationControllerHandler localizationControllerHandler;
    private WriteGameController gameController;
    private WritePersistenceController persistenceController;
    private WritePreferenceController preferenceController;
    private WriteAboutController aboutController;

    // Menu Items
    public MenuItem miNew, miOpen, miSave, miSaveAs, miQuit, miLanguage, miAbout;

    public void initializeExplicit(LocalizationControllerHandler localizationControllerHandler, WriteGameController gameController,
                                   WritePersistenceController persistenceController, WritePreferenceController preferenceController,
                                   WriteAboutController aboutController
    ) {
        this.localizationControllerHandler=localizationControllerHandler;
        this.gameController=gameController;
        this.persistenceController=persistenceController;
        this.preferenceController=preferenceController;
        this.aboutController=aboutController;
        this.inizialized=true;
    }

    @FXML
    public void newGame(ActionEvent e) {
        gameController.newGame();
        persistenceController.reset();
    }

    @FXML
    public void openGame(ActionEvent e) {
        Match match = persistenceController.load();
        if (match!=null){
            gameController.loadMatch(match);
        }
    }


    @FXML
    public void saveGame(ActionEvent e) {this.persistenceController.save();}

    @FXML
    public void saveGameAs(ActionEvent e) {
        this.persistenceController.saveAs();
    }

    @FXML
    public void quit(ActionEvent e) {
        if (persistenceController.hasSaved()){
            // Se il gioco è gia stato salvato
            Platform.exit();
        } else {
            // Se il gioco non è stato salvato
            persistenceController.wantToQuit();
        }
    }

    @FXML
    public void editLanguage(ActionEvent e){
        miLanguage = (MenuItem) e.getSource();

        // Viene capito qual'è la lingua selezionata
        String language = miLanguage.getText();

        if(Objects.equals(language, "Deutsch - CH")){
            language = "de-CH";
        } else if (Objects.equals(language, "English - UK")) {
            language = "en-UK";
        } else {
            language = "it-CH";
        }
        preferenceController.setLanguage(language);
    }

    @FXML
    public void showAbout(ActionEvent e) {
        aboutController.showAbout();
    }

    @FXML
    public void editPath(ActionEvent e) {
        preferenceController.editPath();
    }

    @FXML
    public  void editColorsPlayer(ActionEvent e) {
        preferenceController.editColorPlayerFirst();
    }
    @FXML
    public void editColorsAi(ActionEvent e) {
        preferenceController.editColorPlayerSecond();
    }

    @FXML
    public void editsymbolPlayer(ActionEvent e) {
        preferenceController.editSymbolPlayerFirst();
    }

    @FXML
    public void editsymbolAi(ActionEvent e) {
        preferenceController.editSymbolPlayerSecond();
    }
}
