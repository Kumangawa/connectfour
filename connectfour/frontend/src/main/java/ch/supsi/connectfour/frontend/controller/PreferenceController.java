package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.backend.utility.ReadPreference;
import ch.supsi.connectfour.frontend.controller.observer.WriteObserver;
import ch.supsi.connectfour.frontend.model.ReadAndWritePreferenceModel;
import ch.supsi.connectfour.frontend.view.WritePreferenceView;

import java.io.File;
import java.util.Objects;

public class PreferenceController implements WritePreferenceController{
    private boolean inizialized=false;
    private ReadAndWritePreferenceModel preferenceModel;
    private WritePreferenceView preferenceView;
    private WriteObserver observer;

    //constructor
    public PreferenceController(ReadAndWritePreferenceModel preferenceModel, WritePreferenceView preferenceView) {
        this.preferenceModel = preferenceModel;
        this.preferenceView = preferenceView;
    }

    //static

    //private

    //public
    public void initializeExplicit(){
        this.inizialized=true;
    }
    public void initializeInfoBar(WriteObserver observer){
        this.observer=observer;
    }

    public void setLanguage(String language) {
        observer.changeLanguage();
        this.preferenceModel.setLanguage(language);
    }

    public void editPath(){
        File file=preferenceView.showDirChooser(preferenceModel.getReadPreference().getPreferedPath());
        if(file!=null){
            observer.changePathSuccess();
            preferenceModel.changePreferedPath(file.getPath());
        } else {
            observer.changePathFail();
        }
    }

    public void editSymbolPlayerSecond() {
        String newSymbol=preferenceView.showPopUpEditSymbolPlayerSecond(
                preferenceModel.getReadPreference().getSimbolPlayerSecond(),
                preferenceModel.getReadPreferenceToSave().getSimbolPlayerFirst());
        if (!Objects.equals(newSymbol, preferenceModel.getReadPreference().getSimbolPlayerSecond())){
            observer.changeSymbolSecondPlayerSuccess();
        } else {
            observer.changeSymbolSecondPlayerFail();
        }
        preferenceModel.setSimbolPlayerSecond(newSymbol);
    }

    public void editSymbolPlayerFirst() {
        String newSymbol=preferenceView.showPopUpEditSymbolPlayerFirst(
                preferenceModel.getReadPreference().getSimbolPlayerFirst(),
                preferenceModel.getReadPreferenceToSave().getSimbolPlayerSecond());
        if (!Objects.equals(newSymbol, preferenceModel.getReadPreference().getSimbolPlayerFirst())){
            observer.changeSymbolFirstPlayerSuccess();
        } else {
            observer.changeSymbolFirstPlayerFail();
        }
        preferenceModel.setSimbolPlayerFirst(newSymbol);
    }

    public void editColorPlayerSecond() {
        String color = preferenceView.showPopUpEditColor(preferenceModel.getReadPreferenceToSave().getColorPlayerSecond());
        if (!Objects.equals(color, preferenceModel.getReadPreferenceToSave().getColorPlayerSecond())){
            observer.changeColorSecondPlayerSuccess();
        } else {
            observer.changeColorSecondPlayerFail();
        }
        preferenceModel.setColorPlayerSecond(color);
    }

    public void editColorPlayerFirst() {
        String color = preferenceView.showPopUpEditColor(preferenceModel.getReadPreferenceToSave().getColorPlayerFirst());
        if (!Objects.equals(color, preferenceModel.getReadPreferenceToSave().getColorPlayerFirst())){
            observer.changeColorFirstPlayerSuccess();
        } else {
            observer.changeColorFirstPlayerFail();
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
