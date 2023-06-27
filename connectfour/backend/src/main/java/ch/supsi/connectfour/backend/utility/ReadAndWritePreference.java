package ch.supsi.connectfour.backend.utility;

public interface ReadAndWritePreference extends ReadPreference {
    public void setPathChanged(Boolean pathChanged);
    public void setPreferedPath(String preferedPath);
    public void setSimbolPlayer(String simbolPlayer);
    public void setSimbolAI(String simbolAI);
    public void setColorPlayer(String colorPlayer);
    public void setColorAI(String colorAI);
    public void setLanguage(String language);
}
