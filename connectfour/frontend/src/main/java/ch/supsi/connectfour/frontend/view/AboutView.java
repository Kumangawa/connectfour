package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.service.LocalizationServiceHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AboutView implements AboutViewInterface {
    private Stage stage;

    private LocalizationServiceHandler localizationServiceHandler;

    //constructor
    public AboutView(Stage stage, LocalizationServiceHandler localizationServiceHandler) {
        this.stage = stage;
        this.localizationServiceHandler = localizationServiceHandler;
    }
    //static

    //private

    //public
    public void showAbout(){
        Stage popupStage = new Stage();
        popupStage.setTitle(this.localizationServiceHandler.localize("message.about.title"));
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(this.stage);

        // Create a label for the message
        Label messageLabel = new Label("ConnectFour 1.0 \n authors: \n Alex Petralli\n Samuele Saporito");

        Button closeButton = new Button(this.localizationServiceHandler.localize("message.about.close"));
        closeButton.setOnAction(event -> popupStage.close());

        VBox popupRoot = new VBox(messageLabel, closeButton);
        popupRoot.setSpacing(10);
        popupRoot.setPadding(new Insets(10));

        popupStage.setScene(new Scene(popupRoot, 150, 150));
        popupStage.resizableProperty().set(false);
        popupStage.setFullScreen(false);
        popupStage.showAndWait();
    }
}
