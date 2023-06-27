package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.backend.utility.ReadMatch;
import ch.supsi.connectfour.backend.utility.ReadPreference;
import ch.supsi.connectfour.frontend.model.PersistenceModel;
import ch.supsi.connectfour.backend.utility.Match;
import ch.supsi.connectfour.frontend.view.PersistenceView;
import javafx.application.Platform;

import java.io.File;

public class PersistenceController {
    private boolean inizialized=false;
    private PersistenceView persistenceView;
    private PersistenceModel persistenceModel;
    private ReadPreference readPreference;
    private ReadMatch readMatch;
    private Match match;

    //constructor
    public PersistenceController(PersistenceView persistenceView, PersistenceModel persistenceModel, ReadPreference readPreference, ReadMatch readMatch) {
        this.persistenceView = persistenceView;
        this.persistenceModel = persistenceModel;
        this.readPreference = readPreference;
        this.readMatch = readMatch;
    }

    //static

    //private
    private void updateMatch(){
        this.match=this.readMatch.readMatch();
    }

    //public
    public void initializeExplicit(){
        this.inizialized=true;
    }


    public boolean hasSaved(){
        return (this.persistenceModel.isSaved()&&this.saveIsUpToDate());
        //return this.saveIsUpToDate();
    }
    public boolean saveIsUpToDate(){
        if (this.match==null){
            return false;
        }else {
            return this.readMatch.equals(match);
        }
    }

    public void save()
    {
        if(this.persistenceModel.isSaved()){
            this.updateMatch();
            this.persistenceModel.save(this.readMatch.readMatch());
        }else {
            this.saveAs();
        }
    }
    public void saveAs(){
        String path=null;
        if(readPreference.getPathChanged()){
            path=this.persistenceView.askNameToSaveAs(readPreference.getPreferedPath());
        }else {
            path=this.persistenceView.askNameToSaveAs(PersistenceModel.pathGameSaved);
        }
        if(path==null){
            return;
        }
        this.updateMatch();
        this.persistenceModel.saveAs(this.readMatch.readMatch(), path);
    }
    public Match load(){
        Match loadedMatch=null;
        String initialDirectory;
        File file=null;
        if(readPreference.getPathChanged()){
            initialDirectory=this.readPreference.getPreferedPath();
        }else {
            initialDirectory=PersistenceModel.pathGameSaved;
        }

        if (this.hasSaved()&&this.readMatch.equals(this.match)) {
            file=this.persistenceView.selectFileToLoad(initialDirectory);
        } else {
            // Se il gioco non è stato salvato o non è up to date il salvataggio
            if(persistenceView.showPopUpOpenGame()){
                file=this.persistenceView.selectFileToLoad(initialDirectory);
            }
        }
        if(file!=null){
            loadedMatch=this.persistenceModel.load(file);
            this.match=loadedMatch;
        }

        return loadedMatch;
    }

    public void reset(){
        this.persistenceModel.reset();
        this.updateMatch();
    }
    public void wantToQuit(){
        if (hasSaved()){
            // Se il gioco è gia stato salvato almeno una volta
            Platform.exit();
        } else {
            // Se il gioco non è stato salvato
            persistenceView.showPopUpQuit();
        }
    }
}
