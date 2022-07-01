package com.gamelib.Data.Saved;

import com.gamelib.Logic.Model.UIGame;
import com.gamelib.Logic.Model.Videojuego;
import com.gamelib.Logic.Structures.AvlTree;
import com.gamelib.Logic.Structures.DynamicArray;
import com.gamelib.controller.appController;
import com.gamelib.gamelib.Main;
import com.google.gson.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class UserLibrary implements Serializable{


    private static AvlTree<Videojuego> gamesUserLibraryTree = new AvlTree<>(Videojuego.class);
    private static DynamicArray<Videojuego> arrayInOrden = new DynamicArray<>(Videojuego.class);

    public static void addUIGame(Videojuego game) throws IOException {
        gamesUserLibraryTree.insert(game);
        saveUserDataJson();
    }
    public static boolean constainsUIGame(Videojuego game) {
        return gamesUserLibraryTree.contains(game);
    }

    public static void removeUIGame(Videojuego game) throws IOException {
        gamesUserLibraryTree.remove(game);
        saveUserDataJson();
    }

    public static void listAllGames(){
        arrayInOrden = gamesUserLibraryTree.getList();
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
        return gamesUserLibraryTree.getList();
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
        gamesUserLibraryTree.insert(game);
    }


    public static void loadUserLibrary() throws IOException {
        loadUserDataJson();
        listAllGames();
        readTree();
    }
}
