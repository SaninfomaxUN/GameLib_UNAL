package com.gamelib.Data.OperationsDB;

import com.gamelib.Data.Saved.LocalData;
import com.gamelib.Logic.Model.Videojuego;
import com.gamelib.Logic.Structures.AvlTree;
import com.gamelib.Logic.Structures.HashMapQuadratic;
import com.gamelib.Logic.Structures.Queue;
import com.gamelib.Logic.Tools.Comparator;

import java.io.IOException;
import java.io.Serializable;

public class STEAMOperations implements APIOperations, Serializable {
    private static HashMapQuadratic<String, Videojuego> steamHashMapQGames = new HashMapQuadratic<>();
    private Videojuego GameSelected;
    public static final String nameFileData = "SteamAPI.json";

    //---------------------------------------------------

    @Override
    public Queue<Videojuego> buscarJuegosBase(String nameGame) {
        return null;
    }

    @Override
    public Videojuego buscarJuegoById(String idGame, String campos) {

        return null;
    }

    @Override
    public Videojuego buscarJuegoByName(String nameGame) {
        GameSelected = new Videojuego(nameGame);
        Videojuego coincidencia = steamHashMapQGames.getValue(Comparator.adaptString(GameSelected.getName()));
        if(coincidencia == null){
            return GameSelected;
        }
        return coincidencia;
    }

    public void runGame(Videojuego gameToRun) {
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "cd \"C:\\Users\" && start Steam://run/" + gameToRun.getIDPlatform());
            builder.redirectErrorStream(true);
            Process p = builder.start();
            //p.destroy();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void reloadTree() throws IOException {
        LocalData.loadSteamTree();
        System.out.println("Hash Map Quadratic STEAM correctamente Cargado ✅!");
    }


    //-------SET / GET / INSERT -----------------
    public static void insertSteamMapQGame(Videojuego game){
        steamHashMapQGames.add(game.getName(), game);
    }
}
