package ch.supsi.connectfour.frontend.model.handler;

import ch.supsi.connectfour.backend.utility.Handler;

import java.io.*;

public interface PersistanceHandler extends Handler {
    public static <T extends Serializable>  void writeFile(String path, String fileName, T t) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path+"\\"+fileName+".ser"));
        out.writeObject(t);
        out.close();
    }
    public static Object readFile(String path, String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(path+"\\"+fileName+".ser"));
        Object r=(Object) in.readObject();
        in.close();
        return r;
    }
    public <T extends Serializable>  void saveAs(String path, String fileName, T t)throws IOException;
    public <T extends Serializable>  void save(T t)throws IOException;
    public Object loadFile(String path, String fileName)throws IOException, ClassNotFoundException;

}
