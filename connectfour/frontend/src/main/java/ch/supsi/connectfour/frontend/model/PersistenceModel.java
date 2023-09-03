package ch.supsi.connectfour.frontend.model;

import ch.supsi.connectfour.backend.model.PreferenceInterface;
import ch.supsi.connectfour.backend.model.Match;
import ch.supsi.connectfour.backend.model.Preference;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributeView;

public class PersistenceModel implements PersistenceModelInterface {
    public static final String pathGameSaved=Paths.get(Preference.defaultPath) +File.separator +"GameSaved";
    private boolean saved=false;
    private String savedPath;
    private PreferenceInterface preference;
    private ObjectMapper objectMapper;
    private final String extension = ".json";

    //constructor
    public PersistenceModel(PreferenceInterface preference) {
        this.preference = preference;
        this.objectMapper = new ObjectMapper();
    }

    //static
    private synchronized static void createFolderConnectFourANDGameSaved(){
        try {
            Files.createDirectories(Path.of(pathGameSaved));
            // Set the "hidden" attribute
            DosFileAttributeView dosView = Files.getFileAttributeView(Path.of(Preference.defaultPath), DosFileAttributeView.class);
            dosView.setHidden(true);
        } catch (IOException e) {System.out.println("createFolderConnectFourANDGameSaved "+e.getMessage());}
    }

    //private
    private String checkJsonExtension(String string){
        return string.endsWith(extension) ? string: string+extension;
    }

    //public
    public boolean isSaved() {
        return saved;
    }
    public void reset(){
        this.saved=false;
        this.savedPath=null;
    }
    public <T> void save(T t){
        createFolderConnectFourANDGameSaved();
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(t);
        } catch (IOException e) {
            System.out.println("save: JsonProcessingException"+e.getMessage());
        }

        String serializedPath = savedPath;
        if (serializedPath == null) {
            serializedPath = pathGameSaved + File.separator + System.currentTimeMillis() + extension;
        }

        try {
            Files.write(Path.of(serializedPath), jsonString.getBytes());
            savedPath = serializedPath;
            saved = true;
        } catch (IOException e) {
            System.out.println("save: IOException " + e.getMessage());
        }
    }
    public <T> void saveAs(T t, String absolutePath){
        createFolderConnectFourANDGameSaved();
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            System.out.println("saveAs: JsonProcessingException " + e.getMessage());
            return;
        }

        String serializedPath = checkJsonExtension(absolutePath);
        try {
            Files.write(Path.of(serializedPath), jsonString.getBytes());
            savedPath = serializedPath;
            saved = true;
        } catch (IOException e) {
            System.out.println("saveAs: IOException " + e.getMessage());
        }
    }
    public Match load(File file){
        Match match = null;
        try {
            byte[] jsonData = Files.readAllBytes(file.toPath());
            match = objectMapper.readValue(jsonData, Match.class);
            savedPath = file.getAbsolutePath();
            saved = true;
        } catch (IOException e) {
            System.out.println("load: IOException " + e.getMessage());
        }
        return match;
    }

}
