package ch.supsi.connectfour.backend.utility;

public interface ReadPreference {

    public Boolean getPathChanged();
    public String getPreferedPath();
    public String getSimbolPlayerFirst();
    public String getSimbolPlayerSecond();
    public String getColorPlayerFirst();
    public String getColorPlayerSecond();
    public String getLanguage();

}
