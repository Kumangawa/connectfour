package ch.supsi.connectfour.frontend.model;

import ch.supsi.connectfour.backend.utility.ReadPreference;
import ch.supsi.connectfour.backend.utility.Match;
import ch.supsi.connectfour.backend.utility.Preference;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributeView;

public class PersistenceModel implements ReadAndWritePersistenceModel{
    public static final String pathGameSaved=Paths.get(Preference.defaultPath) +File.separator +"GameSaved";
    private boolean saved=false;
    private String savedPath;
    private ReadPreference preference;

    //constructor
    public PersistenceModel(ReadPreference preference) {
        this.preference = preference;
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
    //TODO: da modificare ".ser" con ".json"
    private String checkSerializeName(String string){
        return string.endsWith(".ser") ? string: string+".ser";
    }

    //public
    public boolean isSaved() {
        return saved;
    }
    public void reset(){
        this.saved=false;
        this.savedPath=null;
    }
    public <T extends Serializable> void save(T t){
        createFolderConnectFourANDGameSaved();
        ObjectOutputStream out=null;
        FileOutputStream fileOut=null;
        try {
            fileOut=new FileOutputStream(savedPath);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(t);
            out.close();
        } catch (IOException e) {
            System.out.println("save: IOException"+e.getMessage());
        }finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fileOut != null) {
                    fileOut.close();
                }
            } catch (IOException e) {
                System.out.println("Error while closing streams: " + e.getMessage());
            }
        }
    }
    public <T extends Serializable> void saveAs(T t, String absolutePath){
        createFolderConnectFourANDGameSaved();
        absolutePath=this.checkSerializeName(absolutePath);
        ObjectOutputStream out=null;
        FileOutputStream fileOut=null;
        try {
            fileOut=new FileOutputStream(absolutePath);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(t);
            savedPath=absolutePath;
            saved=true;
        } catch (IOException e) {
            System.out.println("saveAs: IOException"+e.getMessage());
        }finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fileOut != null) {
                    fileOut.close();
                }
            } catch (IOException e) {
                System.out.println("Error while closing streams: " + e.getMessage());
            }
        }
    }
    public Match load(File file){
        Match match = null;
        FileInputStream fileIn=null;
        ObjectInputStream in=null;
        try {
            fileIn=new FileInputStream(file.getAbsolutePath());
            in = new ObjectInputStream(fileIn);
            Object obj= in.readObject();
            in.close();
            fileIn.close();
            match=(Match) obj;
            this.savedPath=file.getAbsolutePath();
            this.saved=true;

        }catch (ClassCastException e){
            System.out.println("load: ClassCastException"+e.getMessage());
        } catch (IOException e){
            System.out.println("load: IOException"+e.getMessage());
        }catch (ClassNotFoundException e){
            System.out.println("load: ClassNotFoundException"+e.getMessage());
        }finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (fileIn != null) {
                    fileIn.close();
                }
            } catch (IOException e) {
                System.out.println("Error while closing streams: " + e.getMessage());
            }
        }
        return match;
    }

}
