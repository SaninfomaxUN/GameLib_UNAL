package com.gamelib.Data.Saved;

import com.gamelib.Logic.Model.UIGame;
import com.gamelib.Logic.Model.Videojuego;
import com.gamelib.Logic.Structures.AvlTree;
import com.gamelib.Logic.Structures.DynamicArray;
import com.gamelib.controller.appController;
import com.google.gson.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class UserLibrary implements Serializable{


    private static AvlTree<Videojuego> gamesLibraryTree = new AvlTree<>(Videojuego.class);
    private static DynamicArray<Videojuego> arrayInOrden = new DynamicArray<>(Videojuego.class);

    public static void addUIGame(Videojuego game) throws IOException {
        gamesLibraryTree.insert(game);
        saveUserDataJson();
    }
    public static boolean constainsUIGame(Videojuego game) {
        return gamesLibraryTree.contains(game);
    }

    public static void removeUIGame(Videojuego game) throws IOException {
        gamesLibraryTree.remove(game);
        saveUserDataJson();
    }

    public static void listAllGames(){
        arrayInOrden = gamesLibraryTree.getList();
    }
    public static void readTree(){
        if(!arrayInOrden.isEmpty()){
            for (Videojuego readUIGame:arrayInOrden.getArray()){
                if(readUIGame==null){
                   break;
                }
                appController.newUIGame = new UIGame(readUIGame);
                appController.addGame.set(true);
            }
        }
    }


    public static void saveUserDataJson() throws IOException {
        DynamicArray<Videojuego> dataUsergames = saveFromTree(); //----Save from Tree

        Gson gson = new Gson();
        FileWriter writeFile = new FileWriter("dataSaved.json");
        gson.toJson(dataUsergames.getArray(),writeFile);
        writeFile.flush();
        writeFile.close();
    }

    public static DynamicArray<Videojuego> saveFromTree(){
        return gamesLibraryTree.getList();
    }



    public static void loadUserDataJson() throws IOException {
        Path filePath = Path.of("dataSaved.json");
        String gamesJsonStr = Files.readString(filePath);

        Gson gson = new Gson();
        Videojuego[] userVideoGames = gson.fromJson(gamesJsonStr,
                Videojuego[].class);

        for (Videojuego game : userVideoGames) {
            if(game!=null){
                loadInTree(game); //------------Save in Tree
            }

        }
    }
    public static void loadInTree(Videojuego game){
        gamesLibraryTree.insert(game);
    }


    public static void loadUserLibrary() throws IOException {
        loadUserDataJson();
        listAllGames();
        readTree();
    }
}
