package ch.supsi.connectfour.backend.utility;

public interface ReadAndWritePreference extends ReadPreference {
    public void setPathChanged(Boolean pathChanged);
    public void setPreferedPath(String preferedPath);
    public void setSimbolPlayerFirst(String simbolPlayer);
    public void setSimbolPlayerSecond(String simbolAI);
    public void setColorPlayerFirst(String colorPlayer);
    public void setColorPlayerSecond(String colorAI);
    public void setLanguage(String language);
}
