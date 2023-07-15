package ch.supsi.connectfour.frontend.model;

import ch.supsi.connectfour.backend.utility.Preference;
import ch.supsi.connectfour.backend.utility.ReadPreference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributeView;

public class PreferenceModel implements ReadAndWritePreferenceModel{
    private boolean initialized =false;
    private Preference preference;
    private Preference preferenceToSave;
    private ObjectMapper objectMapper;
    //constructor
    public PreferenceModel() {
        this.objectMapper = new ObjectMapper();
    }
    public PreferenceModel(Preference preference) {
        this.preferenceToSave =new Preference(preference);
        this.preference=new Preference(preference);
        this.objectMapper = new ObjectMapper();
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
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(this.preferenceToSave);
        } catch (IOException e) {
            System.out.println("savePreference: JsonProcessingException " + e.getMessage());
        }
        String serializedPath = Preference.defaultPath + "/Preference.json";
        try {
            Files.write(Path.of(serializedPath), jsonString.getBytes());
        } catch (IOException e) {
            System.out.println("savePreference: IOException " + e.getMessage());
        }
    }

    //public
    public void initializeExplicit(){
        String preferenceFilePath = Preference.defaultPath + "/Preference.json";
        File preferenceFile = new File(preferenceFilePath);

        if (preferenceFile.exists()) {
            try {
                byte[] jsonData = Files.readAllBytes(preferenceFile.toPath());
                this.preference = objectMapper.readValue(jsonData, Preference.class);
                this.preferenceToSave = new Preference(preference);
            } catch (IOException e) {
                System.out.println("initializeExplicit: IOException " + e.getMessage());
                this.preference = new Preference();
                this.preferenceToSave = new Preference();
            }
        } else {
            this.preference = new Preference();
            this.preferenceToSave = new Preference();
            savePreference();
        }

        if (this.preference.getPreferedPath() == null) {
            this.changePreferedPath(Preference.defaultPath);
        }
        this.initialized = true;
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
