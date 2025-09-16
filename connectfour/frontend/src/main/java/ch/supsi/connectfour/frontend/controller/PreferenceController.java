package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.frontend.model.PreferenceModelInterface;
import ch.supsi.connectfour.frontend.view.InfoBarViewInterface;
import ch.supsi.connectfour.frontend.view.PreferenceViewInterface;

import java.io.File;
import java.util.Objects;

public class PreferenceController implements PreferenceControllerInterface {
    private boolean inizialized=false;
    private PreferenceModelInterface preferenceModel;
    private PreferenceViewInterface preferenceView;
    private InfoBarViewInterface infoBarView;

    //constructor
    public PreferenceController(PreferenceModelInterface preferenceModel, PreferenceViewInterface preferenceView) {
        this.preferenceModel = preferenceModel;
        this.preferenceView = preferenceView;
    }

    //static

    //private

    //public
    public void initializeExplicit(){
        this.inizialized=true;
    }
    public void initializeInfoBar(InfoBarViewInterface infoBarView){
        this.infoBarView=infoBarView;
    }

    public void setLanguage(String language) {
        infoBarView.changeLanguage();
        this.preferenceModel.setLanguage(language);
    }

    public void editPath(){
        File file=preferenceView.showDirChooser(preferenceModel.getReadPreferenceToSave().getPreferedPath());
        if(file!=null){
            infoBarView.changePathSuccess();
            preferenceModel.changePreferedPath(file.getPath());
        } else {
            infoBarView.changePathFail();
        }
    }

    public void editSymbolPlayerSecond() {
        String newSymbol=preferenceView.showPopUpEditSymbolPlayerSecond(
                preferenceModel.getReadPreferenceToSave().getCurrentSymbolPlayer(1),
                preferenceModel.getReadPreferenceToSave().getNextSymbolPlayer(0));
        if (!Objects.equals(newSymbol, preferenceModel.getReadPreferenceToSave().getCurrentSymbolPlayer(1))){
            infoBarView.changeSymbolSecondPlayerSuccess();
        } else {
            infoBarView.changeSymbolSecondPlayerFail();
        }
        preferenceModel.setSimbolPlayerSecond(newSymbol);
    }

    public void editSymbolPlayerFirst() {
        String newSymbol=preferenceView.showPopUpEditSymbolPlayerFirst(
                preferenceModel.getReadPreferenceToSave().getCurrentSymbolPlayer(0),
                preferenceModel.getReadPreferenceToSave().getNextSymbolPlayer(1));
        if (!Objects.equals(newSymbol, preferenceModel.getReadPreferenceToSave().getCurrentSymbolPlayer(0))){
            infoBarView.changeSymbolFirstPlayerSuccess();
        } else {
            infoBarView.changeSymbolFirstPlayerFail();
        }
        preferenceModel.setSimbolPlayerFirst(newSymbol);
    }

    public void editColorPlayerSecond() {
        String color = preferenceView.showPopUpEditColor(preferenceModel.getReadPreferenceToSave().getNextColorPlayer(1));
        if (!Objects.equals(color, preferenceModel.getReadPreferenceToSave().getNextColorPlayer(1))){
            infoBarView.changeColorSecondPlayerSuccess();
        } else {
            infoBarView.changeColorSecondPlayerFail();
        }
        preferenceModel.setColorPlayerSecond(color);
    }

    public void editColorPlayerFirst() {
        String color = preferenceView.showPopUpEditColor(preferenceModel.getReadPreferenceToSave().getNextColorPlayer(0));
        if (!Objects.equals(color, preferenceModel.getReadPreferenceToSave().getNextColorPlayer(0))){
            infoBarView.changeColorFirstPlayerSuccess();
        } else {
            infoBarView.changeColorFirstPlayerFail();
        }
        preferenceModel.setColorPlayerFirst(color);
    }

}
