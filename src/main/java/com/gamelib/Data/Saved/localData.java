package com.gamelib.Data.Saved;

import com.gamelib.Logic.Model.Videojuego;
import com.gamelib.Logic.Structures.AvlTree;

import java.io.*;

public class localData implements Serializable{

    public static void saveTree(AvlTree<Videojuego> treeToSave, String nameDataSaved) throws IOException {
        String fileName= nameDataSaved + ".txt";
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(treeToSave);
        oos.close();
    }

    public static AvlTree<Videojuego> loadTree(String nameDataSaved) throws IOException, ClassNotFoundException {
        String fileName= nameDataSaved + ".txt";
        FileInputStream fin = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fin);
        new AvlTree<>(Videojuego.class);
        AvlTree<Videojuego> treeToRead;
        treeToRead = (AvlTree<Videojuego>) ois.readObject();
        ois.close();
        return treeToRead;
    }
}
