package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.backend.utility.ReadPreference;
import ch.supsi.connectfour.frontend.model.PreferenceModel;
import ch.supsi.connectfour.frontend.view.PreferenceView;

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

    public void editSymbolPlayerSecond() {
        String newSymbol=preferenceView.showPopUpEditSymbolPlayerSecond(
                preferenceModel.getReadPreference().getSimbolPlayerSecond(),
                preferenceModel.getReadPreferenceToSave().getSimbolPlayerFirst());
        preferenceModel.setSimbolPlayerSecond(newSymbol);
    }

    public void editSymbolPlayerFirst() {
        String newSymbol=preferenceView.showPopUpEditSymbolPlayerFirst(
                preferenceModel.getReadPreference().getSimbolPlayerFirst(),
                preferenceModel.getReadPreferenceToSave().getSimbolPlayerSecond());
        preferenceModel.setSimbolPlayerFirst(newSymbol);
    }

    public void editColorPlayerSecond() {
        String color = preferenceView.showPopUpEditColor(preferenceModel.getReadPreferenceToSave().getColorPlayerSecond());
        preferenceModel.setColorPlayerSecond(color);
    }

    public void editColorPlayerFirst() {
        String color = preferenceView.showPopUpEditColor(preferenceModel.getReadPreferenceToSave().getColorPlayerFirst());
        preferenceModel.setColorPlayerFirst(color);
    }

    public ReadPreference getReadPreference() {
        return this.preferenceModel.getReadPreference();
    }
    public ReadPreference getReadPreferenceToSave() {
        return this.preferenceModel.getReadPreferenceToSave();
    }
}
