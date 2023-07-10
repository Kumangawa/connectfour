package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.backend.utility.ReadPreference;
import ch.supsi.connectfour.frontend.model.PreferenceModel;
import ch.supsi.connectfour.frontend.view.PreferenceView;

import java.io.File;
import java.util.Objects;

public class PreferenceController {
    private boolean inizialized=false;
    private PreferenceModel preferenceModel;
    private PreferenceView preferenceView;
    private InfoBarController infoBar;

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
    public void initializeInfoBar(InfoBarController infoBar){
        this.infoBar=infoBar;
    }

    public void setLanguage(String language) {
        infoBar.changeLanguage();
        this.preferenceModel.setLanguage(language);
    }

    public void editPath(){
        File file=preferenceView.showDirChooser(preferenceModel.getReadPreference().getPreferedPath());
        if(file!=null){
            infoBar.changePathSuccess();
            preferenceModel.changePreferedPath(file.getPath());
        } else {
            infoBar.changePathFail();
        }
    }

    public void editSymbolPlayerSecond() {
        String newSymbol=preferenceView.showPopUpEditSymbolPlayerSecond(
                preferenceModel.getReadPreference().getSimbolPlayerSecond(),
                preferenceModel.getReadPreferenceToSave().getSimbolPlayerFirst());
        if (!Objects.equals(newSymbol, preferenceModel.getReadPreference().getSimbolPlayerSecond())){
            infoBar.changeSymbolSecondPlayerSuccess();
        } else {
            infoBar.changeSymbolSecondPlayerFail();
        }
        preferenceModel.setSimbolPlayerSecond(newSymbol);
    }

    public void editSymbolPlayerFirst() {
        String newSymbol=preferenceView.showPopUpEditSymbolPlayerFirst(
                preferenceModel.getReadPreference().getSimbolPlayerFirst(),
                preferenceModel.getReadPreferenceToSave().getSimbolPlayerSecond());
        if (!Objects.equals(newSymbol, preferenceModel.getReadPreference().getSimbolPlayerFirst())){
            infoBar.changeSymbolFirstPlayerSuccess();
        } else {
            infoBar.changeSymbolFirstPlayerFail();
        }
        preferenceModel.setSimbolPlayerFirst(newSymbol);
    }

    public void editColorPlayerSecond() {
        String color = preferenceView.showPopUpEditColor(preferenceModel.getReadPreferenceToSave().getColorPlayerSecond());
        if (!Objects.equals(color, preferenceModel.getReadPreferenceToSave().getColorPlayerSecond())){
            infoBar.changeColorSecondPlayerSuccess();
        } else {
            infoBar.changeColorSecondPlayerFail();
        }
        preferenceModel.setColorPlayerSecond(color);
    }

    public void editColorPlayerFirst() {
        String color = preferenceView.showPopUpEditColor(preferenceModel.getReadPreferenceToSave().getColorPlayerFirst());
        if (!Objects.equals(color, preferenceModel.getReadPreferenceToSave().getColorPlayerFirst())){
            infoBar.changeColorFirstPlayerSuccess();
        } else {
            infoBar.changeColorFirstPlayerFail();
        }
        preferenceModel.setColorPlayerFirst(color);
    }

    public ReadPreference getReadPreference() {
        return this.preferenceModel.getReadPreference();
    }
    public ReadPreference getReadPreferenceToSave() {
        return this.preferenceModel.getReadPreferenceToSave();
    }
}
