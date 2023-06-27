package ch.supsi.connectfour.backend.utility;

public interface ReadPreference {

    public Boolean getPathChanged();
    public String getPreferedPath();
    public String getSimbolPlayer();
    public String getSimbolAI();
    public String getColorPlayer();
    public String getColorAI();
    public String getLanguage();

}
