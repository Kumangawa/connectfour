package ch.supsi.connectfour.frontend;

import ch.supsi.connectfour.backend.controller.LocalizationController;
import ch.supsi.connectfour.backend.controller.LocalizationControllerHandler;
import ch.supsi.connectfour.backend.model.LocalizationModel;
import ch.supsi.connectfour.backend.model.LocalizationModelHandler;
import ch.supsi.connectfour.backend.service.LocalizationService;
import ch.supsi.connectfour.backend.service.LocalizationServiceHandler;
import ch.supsi.connectfour.backend.model.PreferenceInterface;
import ch.supsi.connectfour.backend.model.MatchInterface;
import ch.supsi.connectfour.frontend.controller.*;
import ch.supsi.connectfour.frontend.model.GameModel;
import ch.supsi.connectfour.frontend.model.PersistenceModel;
import ch.supsi.connectfour.frontend.model.PreferenceModel;
import ch.supsi.connectfour.frontend.view.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Locale;


public class AppFx extends Application {

    /**PREFERENCE**/
    private PreferenceController preferenceController;
    private PreferenceModel preferenceModel;
    private PreferenceView preferenceView;

    /**GAME_CONTROLLER**/
    private GameController gameController;
    private GameModel gameModel;
    private GameView gameView;

    /**PERSISTENCE**/
    private PersistenceController persistenceController;
    private PersistenceModel persistenceModel;
    private PersistenceView persistenceView;

    /**ABOUT**/
    private AboutController aboutController;
    private AboutView aboutView;

    /**MENU BAR**/
    private MenuBar menuBar;

    /**INFO BAR**/
    private InfoBarView infoBarView;


    //backend
    /**Localization**/
    private LocalizationControllerHandler localizationControllerHandler;
    private LocalizationModelHandler localizationModelHandler;
    private LocalizationServiceHandler localizationServiceHandler;

    @Override
    public void start(Stage stage) throws Exception {

        // Serve istanziarlo subito per dare la lingua al localize
        preferenceModel=new PreferenceModel();
        preferenceModel.initializeExplicit();
        PreferenceInterface readPreference= preferenceModel.getReadPreferenceToSave();

        localizationServiceHandler = new LocalizationService("i18n.translations", Locale.forLanguageTag(readPreference.getLanguage()));
        localizationModelHandler = new LocalizationModel(localizationServiceHandler);
        localizationControllerHandler = new LocalizationController(localizationModelHandler);

        preferenceView=new PreferenceView(localizationServiceHandler, stage);
        preferenceController=new PreferenceController(preferenceModel,preferenceView);


        gameModel=new GameModel();
        gameView=new GameView(localizationServiceHandler);
        MatchInterface readMatch= gameModel.getReadMatch();

        persistenceModel=new PersistenceModel(readPreference);
        persistenceView=new PersistenceView(stage, localizationServiceHandler);
        persistenceController=new PersistenceController(persistenceView, persistenceModel, readPreference,readMatch);

        aboutView=new AboutView(stage, localizationServiceHandler);
        aboutController=new AboutController(aboutView);




        /**PREFERENCE**/
        preferenceController.initializeExplicit();


        /**LocalizationInizialiation**/
        localizationServiceHandler.initializeExplicit();
        localizationModelHandler.initializeExplicit();
        localizationControllerHandler.initializeExplicit();


        /** GRID**/
        URL gridFxmlUrl = getClass().getResource("/ch/supsi/connectfour/frontend/grid.fxml");
        if (gridFxmlUrl == null) {
            // resource file not found
            return;
        }

        FXMLLoader playingGridFxmlLoader = new FXMLLoader(gridFxmlUrl, localizationControllerHandler.getResourceBundle());
        Parent gridPane = playingGridFxmlLoader.load();

        /**INFO BAR**/
        // get the layout URL based on the fxml resource file
        URL infoBarFxmlUrl = getClass().getResource("/ch/supsi/connectfour/frontend/infobar.fxml");
        if (infoBarFxmlUrl == null) {
            // resource file not found
            return;
        }

        FXMLLoader infoBarLoader = new FXMLLoader(infoBarFxmlUrl, localizationControllerHandler.getResourceBundle());
        Parent infoBar = infoBarLoader.load();
        infoBarView = (InfoBarView) infoBarLoader.getController();

        infoBarView.initializeExplicit(localizationServiceHandler);

        preferenceController.initializeInfoBar(infoBarView);

        /**GAME_CONTROLLER**/
        gameController = playingGridFxmlLoader.getController();
        gameController.initializeExplicit(readPreference,  gameView,  gameModel, infoBarView);

        /**PERSISTENCE**/


        /**ABOUT**/
        aboutController.initializeExplicit();


        /**MENU BAR**/
        URL menuBarFxmlUrl = getClass().getResource("/ch/supsi/connectfour/frontend/menubar.fxml");
        if (menuBarFxmlUrl == null) {
            // resource file not found
            return;
        }

        FXMLLoader menuBarLoader = new FXMLLoader(menuBarFxmlUrl, localizationControllerHandler.getResourceBundle());
        Parent menuBar = menuBarLoader.load();
        this.menuBar = menuBarLoader.getController();
        this.menuBar.initializeExplicit(gameController, persistenceController, preferenceController, aboutController);



        /**BORDER PANE**/
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(gridPane);
        borderPane.setBottom(infoBar);

        // create a new scene with the border pane
        Scene scene = new Scene(borderPane, 690, 719);  // 600, 629     340, 369

        // put the scene onto the stage
        stage.setTitle("Connect Four");
        stage.setResizable(false);
        stage.setScene(scene);

        // now open the curtains (i.e., make the stage visibile)
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
