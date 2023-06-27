package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.backend.controller.LocalizationController;
import ch.supsi.connectfour.backend.controller.handler.LocalizationControllerHandler;
import ch.supsi.connectfour.backend.utility.ReadPreference;
import ch.supsi.connectfour.frontend.model.PreferenceModel;
import ch.supsi.connectfour.backend.utility.Preference;
import ch.supsi.connectfour.frontend.view.PreferenceView;
import javafx.stage.Stage;

import java.io.File;

public class PreferenceController {
    private boolean inizialized=false;
    private PreferenceModel preferenceModel;
    private PreferenceView preferenceView;

    //constructor
    public PreferenceController(PreferenceModel preferenceModel, PreferenceView preferenceView) {
        this.preferenceModel = preferenceModel;
        this.preferenceView = preferenceView;
    }

    //static

    //private

    //public
    public void initializeExplicit(){
        this.inizialized=true;
    }

    public void setLanguage(String language) {
        this.preferenceModel.setLanguage(language);
    }

    public void editPath(){
        File file=preferenceView.showDirChooser(preferenceModel.getReadPreference().getPreferedPath());
        if(file!=null){
            preferenceModel.changePreferedPath(file.getPath());
        }
    }

    public void editSymbolAi() {
        String newSymbol=preferenceView.showPopUpEditSymbolAI(
                preferenceModel.getReadPreference().getSimbolAI(),
                preferenceModel.getReadPreferenceToSave().getSimbolPlayer());
        preferenceModel.setSimbolAI(newSymbol);
    }

    public void editSymbolPlayer() {
        String newSymbol=preferenceView.showPopUpEditSymbolPlayer(
                preferenceModel.getReadPreference().getSimbolPlayer(),
                preferenceModel.getReadPreferenceToSave().getSimbolAI());
        preferenceModel.setSimbolPlayer(newSymbol);
    }

    public void editColorAi() {
        String color = preferenceView.showPopUpEditColor(preferenceModel.getReadPreferenceToSave().getColorAI());
        preferenceModel.setColorAI(color);
    }

    public void editColorPlayer() {
        String color = preferenceView.showPopUpEditColor(preferenceModel.getReadPreferenceToSave().getColorPlayer());
        preferenceModel.setColorPlayer(color);
    }

    public ReadPreference getReadPreference() {
        return this.preferenceModel.getReadPreference();
    }
    public ReadPreference getReadPreferenceToSave() {
        return this.preferenceModel.getReadPreferenceToSave();
    }
}
