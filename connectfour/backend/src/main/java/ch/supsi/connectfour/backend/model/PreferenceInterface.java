package ch.supsi.connectfour.backend.model;

import ch.supsi.connectfour.backend.utility.Player;


public interface PreferenceInterface {
    public Boolean getPathChanged();
    public String getPreferedPath();
    public String getCurrentSymbolPlayer(int numberPlayer);
    public String getNextSymbolPlayer(int numberPlayer);
    public String getCurrentColorPlayer(int numberPlayer);
    public String getNextColorPlayer(int numberPlayer);
    public String getLanguage();
    public Player[] getPlayer();
    public void setPathChanged(Boolean pathChanged);
    public void setPreferedPath(String preferedPath);
    public void setNextSymbolPlayer(int numberPlayer, String symbolPlayer);
    public void setNextColorPlayer(int numberPlayer, String colorPlayer);
    public void setLanguage(String language);
    public void setPlayer(Player[] players);
    public void switchSymbol();
}
