package ch.supsi.connectfour.frontend.model;

import ch.supsi.connectfour.backend.utility.Preference;
import ch.supsi.connectfour.backend.utility.ReadPreference;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributeView;

public class PreferenceModel {
    private boolean initialized =false;
    private Preference preference;
    private Preference preferenceToSave;
    //constructor
    public PreferenceModel() {
    }
    public PreferenceModel(Preference preference) {
        this.preferenceToSave =new Preference(preference);
        this.preference=new Preference(preference);
    }
    //static
    private synchronized static void createFolderTicTacToeANDGameSaved(){
        try {
            Files.createDirectories(Paths.get(Preference.defaultPath));
            Files.createDirectories(Path.of(Paths.get(Preference.defaultPath) + "\\GameSaved"));
            // Set the "hidden" attribute
            DosFileAttributeView dosView = Files.getFileAttributeView(Path.of(Preference.defaultPath), DosFileAttributeView.class);
            dosView.setHidden(true);
        }catch (IOException e) {System.out.println("createFolderConnectFourANDGameSaved "+e.getMessage());}
    }

    //private
    private void savePreference(){
        createFolderTicTacToeANDGameSaved();
        ObjectOutputStream out=null;
        FileOutputStream fileOut=null;
        try {
            fileOut=new FileOutputStream(Preference.defaultPath+"\\"+ "Preference.ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(this.preferenceToSave);
        } catch (IOException e) {System.out.println("createFolderConnectFourANDGameSaved "+e.getMessage());}
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fileOut != null) {
                    fileOut.close();
                }
            } catch (IOException e) {
                System.out.println("Error while closing streams: " + e.getMessage());
            }
        }
    }

    //public
    public void initializeExplicit(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(Preference.defaultPath+"\\"+ "Preference.ser"));
            this.preference=(Preference) in.readObject();
            this.preferenceToSave=new Preference(preference);
            in.close();
        }catch (FileNotFoundException e) {
            this.preference=new Preference();
            this.preferenceToSave=new Preference();
            this.savePreference();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if(this.preference.getPreferedPath()==null){
            this.changePreferedPath(Preference.defaultPath);
        }
        this.initialized =true;
    }
    public boolean isInitialized() {
        return initialized;
    }

    public void changePreferedPath(String preferedPath){
        this.preference.setPathChanged(true);
        this.preference.setPreferedPath(preferedPath);
        this.preferenceToSave.setPreferedPath(preferedPath);
        this.savePreference();
    }
    public void setSimbolPlayerFirst(String simbolPlayer) {
        this.preferenceToSave.setSimbolPlayerFirst(simbolPlayer);
        this.savePreference();
    }
    public void setSimbolPlayerSecond(String simbolPlayer) {
        this.preferenceToSave.setSimbolPlayerSecond(simbolPlayer);
        this.savePreference();
    }

    public void setColorPlayerFirst(String colorPlayer) {
        this.preferenceToSave.setColorPlayerFirst(colorPlayer);
        this.savePreference();
    }
    public void setColorPlayerSecond(String colorPlayer) {
        this.preferenceToSave.setColorPlayerSecond(colorPlayer);
        this.savePreference();
    }
    public void setLanguage(String language) {
        this.preferenceToSave.setLanguage(language);
        this.savePreference();
    }
    public ReadPreference getReadPreference(){return preference;}
    public ReadPreference getReadPreferenceToSave(){return preferenceToSave;}

}
