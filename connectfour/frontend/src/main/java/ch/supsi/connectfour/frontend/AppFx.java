package ch.supsi.connectfour.frontend;

import ch.supsi.connectfour.backend.controller.LocalizationController;
import ch.supsi.connectfour.backend.controller.handler.LocalizationControllerHandler;
import ch.supsi.connectfour.backend.model.LocalizationModel;
import ch.supsi.connectfour.backend.model.handler.LocalizationModelHandler;
import ch.supsi.connectfour.backend.utility.ReadMatch;
import ch.supsi.connectfour.backend.utility.ReadPreference;
import ch.supsi.connectfour.frontend.controller.*;
import ch.supsi.connectfour.frontend.controller.observer.Observer;
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
    private InfoBarController infoBarController;
    private InfoBarView infoBarView;

    private Observer observer;


    //backend
    /**Localization**/
    private LocalizationControllerHandler localizationControllerHandler;
    private LocalizationModelHandler localizationModelHandler;

    @Override
    public void start(Stage stage) throws Exception {

        // Serve istanziarlo subito per dare la lingua al localize
        preferenceModel=new PreferenceModel();
        preferenceModel.initializeExplicit();
        ReadPreference readPreference= preferenceModel.getReadPreference();

        localizationModelHandler = new LocalizationModel("i18n.translations", Locale.forLanguageTag(readPreference.getLanguage()));
        localizationControllerHandler = new LocalizationController(localizationModelHandler);

        preferenceView=new PreferenceView(localizationModelHandler, stage);
        preferenceController=new PreferenceController(preferenceModel,preferenceView);


        gameModel=new GameModel();
        gameView=new GameView(localizationModelHandler);
        ReadMatch readMatch= gameModel.getReadMatch();

        persistenceModel=new PersistenceModel(readPreference);
        persistenceView=new PersistenceView(stage, localizationModelHandler);
        persistenceController=new PersistenceController(persistenceView, persistenceModel, readPreference,readMatch);

        aboutView=new AboutView(stage, localizationModelHandler);
        aboutController=new AboutController(aboutView);




        /**PREFERENCE**/
        preferenceController.initializeExplicit();


        /**LocalizationInizialiation**/
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
        infoBarController = (InfoBarController) infoBarLoader.getController();

        infoBarView=new InfoBarView(localizationModelHandler);
        infoBarController.initializeExplicit(infoBarView);

        observer= new Observer(infoBarController);

        preferenceController.initializeInfoBar(observer);

        /**GAME_CONTROLLER**/
        gameController = playingGridFxmlLoader.getController();
        gameController.initializeExplicit(readPreference,  gameView,  gameModel, observer);

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
        this.menuBar.initializeExplicit(localizationControllerHandler, gameController,
                persistenceController, preferenceController, aboutController);



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
