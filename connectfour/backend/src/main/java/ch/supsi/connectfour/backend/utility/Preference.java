package ch.supsi.connectfour.backend.utility;

import java.io.File;
import java.io.Serializable;


public class Preference implements Serializable, ReadAndWritePreference {
    public static final String defaultPath=(String) System.getProperties().get("user.home")+File.separator+"connectfour";
    //fields
    private Boolean pathChanged;
    private String preferedPath;
    private String simbolPlayer;       // Utilizzato durante il game
    private String simbolAI;           // Utilizzato durante il game
    private String colorPlayer;         // Utilizzato durante il game
    private String colorAI;             // Utilizzato durante il game
    private String language;             // Da salvare nel file
    public Preference() {
        this.pathChanged=false;
        this.preferedPath=defaultPath;
        this.simbolPlayer = "X";
        this.simbolAI = "O";
        this.colorPlayer = "black";
        this.colorAI = "red";

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
        this.simbolPlayer = preference.simbolPlayer;
        this.simbolAI = preference.simbolAI;
        this.colorPlayer = preference.colorPlayer;
        this.colorAI = preference.colorAI;
        this.language=preference.language;
        this.preferedPath=preference.preferedPath;
    }

    public Boolean getPathChanged() {
        return pathChanged;
    }

    public String getPreferedPath() {
        return preferedPath;
    }

    public String getSimbolPlayer() {
        return simbolPlayer;
    }

    public String getSimbolAI() {
        return simbolAI;
    }

    public String getColorPlayer() {
        return colorPlayer;
    }

    public String getColorAI() {
        return colorAI;
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

    public void setSimbolPlayer(String simbolPlayer) {
        this.simbolPlayer = simbolPlayer;
    }

    public void setSimbolAI(String simbolAI) {
        this.simbolAI = simbolAI;
    }

    public void setColorPlayer(String colorPlayer) {
        this.colorPlayer = colorPlayer;
    }

    public void setColorAI(String colorAI) {
        this.colorAI = colorAI;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
