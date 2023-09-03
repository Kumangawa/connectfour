package ch.supsi.connectfour.backend.model;

import ch.supsi.connectfour.backend.utility.Player;

import java.io.*;


public class Preference implements PreferenceInterface {
    public static final String defaultPath=(String) System.getProperties().get("user.home")+File.separator+"connectfour";
    //fields
    private Boolean pathChanged;
    private String preferedPath;
    private Player[] players;
    private String language;
    public Preference() {
        this.pathChanged=false;
        this.preferedPath=defaultPath;
        this.players = new Player[2];
        players[0] = new Player("X", "black");
        players[1] = new Player("O", "red");

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
        this.preferedPath=preference.preferedPath;
        this.players = new Player[2];
        for (int i = 0; i<=players.length; i++){
            players[i] = new Player(preference.players[i].getNextSymbol(), preference.players[i].getNextColor());
        }

        this.language=preference.language;

    }

    public Boolean getPathChanged() {
        return pathChanged;
    }

    public String getPreferedPath() {
        return preferedPath;
    }

    public String getCurrentSymbolPlayer(int numberPlayer) {
        return players[numberPlayer].getCurrentSymbol();
    }

    public String getNextSymbolPlayer(int numberPlayer) {
        return players[numberPlayer].getNextSymbol();
    }

    public String getCurrentColorPlayer(int numberPlayer) {
        return players[numberPlayer].getCurrentColor();
    }

    public String getNextColorPlayer(int numberPlayer) {
        return players[numberPlayer].getNextColor();
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

    public void setNextSymbolPlayer(int numberPlayer, String symbolPlayer) {
        this.players[numberPlayer].setNextSymbol(symbolPlayer);
    }

    public void setNextColorPlayer(int numberPlayer, String colorPlayer) {
        this.players[numberPlayer].setNextColor(colorPlayer);
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    public Player[] getPlayer(){
        return players;
    }

    public void setPlayer(Player[] players){
        this.players = players;
    }

    public void switchSymbol(){
        this.players[0].setCurrentSymbol(this.players[0].getNextSymbol());
        this.players[0].setCurrentColor(this.players[0].getNextColor());

        this.players[1].setCurrentSymbol(this.players[1].getNextSymbol());
        this.players[1].setCurrentColor(this.players[1].getNextColor());
    }

}
