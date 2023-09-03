package ch.supsi.connectfour.frontend.model;

import ch.supsi.connectfour.backend.model.Preference;
import ch.supsi.connectfour.backend.model.PreferenceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PreferenceModel implements PreferenceModelInterface {
    private boolean initialized =false;
    private PreferenceInterface preferenceToSave;
    private ObjectMapper objectMapper;
    //constructor
    public PreferenceModel() {
        this.objectMapper = new ObjectMapper();
    }
    public PreferenceModel(Preference preference) {
        this.preferenceToSave =new Preference(preference);
        this.objectMapper = new ObjectMapper();
    }
    //static
    private synchronized static void createFolderConnectFourANDGameSaved(){
        try {
            Path defaultPath = Paths.get(Preference.defaultPath);
            Files.createDirectories(defaultPath);

            Path gameSavedPath = defaultPath.resolve("GameSaved");
            Files.createDirectories(gameSavedPath);

            // Opzionale: Nascondi la directory
            File defaultPathFile = defaultPath.toFile();
            if (defaultPathFile.isHidden()) {
                defaultPathFile.setReadable(true);
                defaultPathFile.setExecutable(true);
            }
        } catch (IOException e) {
            System.out.println("createFolderConnectFourANDGameSaved: " + e.getMessage());
        }
    }

    //private
    private void savePreference(){
        createFolderConnectFourANDGameSaved();
        String combinedJson = "";
        try {
            // Aggiungi l'array "player" direttamente all'oggetto "preference"
            this.preferenceToSave.setPlayer(this.preferenceToSave.getPlayer());

            // Serializza l'intero oggetto "preference"
            combinedJson = objectMapper.writeValueAsString(this.preferenceToSave);
        } catch (IOException e) {
            System.out.println("savePreference: JsonProcessingException " + e.getMessage());
        }
        String serializedPath = Preference.defaultPath + "/Preference.json";
        try {
            Files.write(Path.of(serializedPath), combinedJson.getBytes());
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
                this.preferenceToSave = objectMapper.readValue(jsonData, Preference.class);

                this.preferenceToSave.switchSymbol();
            } catch (IOException e) {
                System.out.println("initializeExplicit: IOException " + e.getMessage());
                this.preferenceToSave = new Preference();
            }
        } else {
            this.preferenceToSave = new Preference();
            savePreference();
        }

        if (this.preferenceToSave.getPreferedPath() == null) {
            this.changePreferedPath(Preference.defaultPath);
        }
        this.initialized = true;
    }
    public boolean isInitialized() {
        return initialized;
    }
    public void changePreferedPath(String preferedPath){
        this.preferenceToSave.setPathChanged(true);
        this.preferenceToSave.setPreferedPath(preferedPath);
        this.savePreference();
    }
    public void setSimbolPlayerFirst(String simbolPlayer) {
        this.preferenceToSave.setNextSymbolPlayer(0,simbolPlayer);
        this.savePreference();
    }
    public void setSimbolPlayerSecond(String simbolPlayer) {
        this.preferenceToSave.setNextSymbolPlayer(1,simbolPlayer);
        this.savePreference();
    }
    public void setColorPlayerFirst(String colorPlayer) {
        this.preferenceToSave.setNextColorPlayer(0,colorPlayer);
        this.savePreference();
    }
    public void setColorPlayerSecond(String colorPlayer) {
        this.preferenceToSave.setNextColorPlayer(1,colorPlayer);
        this.savePreference();
    }
    public void setLanguage(String language) {
        this.preferenceToSave.setLanguage(language);
        this.savePreference();
    }
    public PreferenceInterface getReadPreferenceToSave(){return preferenceToSave;}
}
