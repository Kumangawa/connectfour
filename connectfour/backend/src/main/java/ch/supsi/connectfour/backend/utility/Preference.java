package ch.supsi.connectfour.backend.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.File;


public class Preference implements ReadAndWritePreference {
    public static final String defaultPath=(String) System.getProperties().get("user.home")+File.separator+"connectfour";
    //fields
    private Boolean pathChanged;
    private String preferedPath;
    private String simbolPlayerFirst;       // Utilizzato durante il game
    private String simbolPlayerSecond;      // Utilizzato durante il game
    private String colorPlayerFirst;        // Utilizzato durante il game
    private String colorPlayerSecond;       // Utilizzato durante il game
    private String language;                // Da salvare nel file
    public Preference() {
        this.pathChanged=false;
        this.preferedPath=defaultPath;
        this.simbolPlayerFirst = "X";
        this.simbolPlayerSecond = "O";
        this.colorPlayerFirst = "black";
        this.colorPlayerSecond = "red";

        String language= System.getProperty("user.language");
        if(language.equals("en")){
            language="en-UK";
        }else if (language.equals("de")){
            language="de-CH";
        } else if (language.equals("it")) {
            language="it-CH";
        }else {
            language="en-UK";
        }
        this.language=language;
    }
    public Preference(Preference preference){
        this.pathChanged=preference.pathChanged;
        this.simbolPlayerFirst = preference.simbolPlayerFirst;
        this.simbolPlayerSecond = preference.simbolPlayerSecond;
        this.colorPlayerFirst = preference.colorPlayerFirst;
        this.colorPlayerSecond = preference.colorPlayerSecond;
        this.language=preference.language;
        this.preferedPath=preference.preferedPath;
    }

    public Boolean getPathChanged() {
        return pathChanged;
    }

    public String getPreferedPath() {
        return preferedPath;
    }

    public String getSimbolPlayerFirst() {
        return simbolPlayerFirst;
    }

    public String getSimbolPlayerSecond() {
        return simbolPlayerSecond;
    }

    public String getColorPlayerFirst() {
        return colorPlayerFirst;
    }

    public String getColorPlayerSecond() {
        return colorPlayerSecond;
    }

    public String getLanguage() {
        return language;
    }

    public void setPathChanged(Boolean pathChanged) {
        this.pathChanged = pathChanged;
    }

    public void setPreferedPath(String preferedPath) {
        this.preferedPath = preferedPath;
    }

    public void setSimbolPlayerFirst(String simbolPlayer) {
        this.simbolPlayerFirst = simbolPlayer;
    }

    public void setSimbolPlayerSecond(String simbolAI) {
        this.simbolPlayerSecond = simbolAI;
    }

    public void setColorPlayerFirst(String colorPlayer) {
        this.colorPlayerFirst = colorPlayer;
    }

    public void setColorPlayerSecond(String colorAI) {
        this.colorPlayerSecond = colorAI;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    // Metodo per serializzare in JSON
    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper.writeValueAsString(this);
    }

    // Metodo per deserializzare da JSON
    public static Preference fromJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Preference.class);
    }
}
