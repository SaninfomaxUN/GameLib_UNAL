package com.gamelib.Data.Saved;

import com.gamelib.Logic.Model.UIGame;
import com.gamelib.Logic.Model.Videojuego;
import com.gamelib.Logic.Structures.AvlTree;
import com.gamelib.Logic.Structures.DynamicArray;
import com.gamelib.controller.appController;

import java.io.*;

public class userLibrary implements Serializable{


    private static AvlTree<Videojuego> gamesLibraryTree = new AvlTree<>(Videojuego.class);
    private static DynamicArray<Videojuego> arrayInOrden = new DynamicArray<>(Videojuego.class);

    public static void addUIGame(Videojuego game) throws IOException {
        gamesLibraryTree.insert(game);
        saveTree();
    }
    public static boolean constainsUIGame(Videojuego game) throws IOException {
        return gamesLibraryTree.contains(game);
    }

    public static void removeUIGame(Videojuego game) throws IOException {
        gamesLibraryTree.remove(game);
        saveTree();
    }

    public static void listAllGames(){
        arrayInOrden = gamesLibraryTree.getList();
    }
    public static void readTree(){
        if(!arrayInOrden.isEmpty()){
            //Object a = arrayInOrden.getArray()[0];
            //Videojuego v = (Videojuego) a;
            for (Videojuego readUIGame:arrayInOrden.getArray()){
                if(readUIGame==null){
                   break;
                }
                appController.newUIGame = new UIGame(readUIGame);
                appController.addGame.set(true);
            }
        }
    }

    public static void saveTree() throws IOException {
        String fileName= "dataSaved.txt";
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(gamesLibraryTree);
        oos.close();
    }

    public static void loadTree() throws IOException, ClassNotFoundException {
        String fileName= "dataSaved.txt";
        FileInputStream fin = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fin);
        gamesLibraryTree = (AvlTree<Videojuego>) ois.readObject();
        ois.close();
    }

    public static void loadUserLibrary() throws IOException, ClassNotFoundException {
        loadTree();
        listAllGames();
        readTree();
    }
}
