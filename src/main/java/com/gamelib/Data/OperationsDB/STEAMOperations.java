package com.gamelib.Data.OperationsDB;

import com.gamelib.Data.Saved.LocalData;
import com.gamelib.Logic.Model.Videojuego;
import com.gamelib.Logic.Structures.AvlTree;
import com.gamelib.Logic.Structures.Queue;

import java.io.IOException;
import java.io.Serializable;

public class STEAMOperations implements APIOperations, Serializable {

    private static AvlTree<Videojuego> steamTreeGames = new AvlTree(Videojuego.class);
    private Videojuego GameSelected;
    public static final String nameFileData = "SteamAPI.json";

    //---------------------------------------------------

    @Override
    public Queue<Videojuego> buscarJuegosBase(String nameGame) {
        GameSelected = new Videojuego(nameGame, "Steam");
        Queue<Videojuego> filaResultados = new Queue<>();
        filaResultados.enqueue(steamTreeGames.get(GameSelected));
        return filaResultados;
    }

    @Override
    public Videojuego buscarJuegoById(String idGame, String campos) {

        return null;
    }

    @Override
    public Videojuego buscarJuegoByName(String nameGame) {
        GameSelected = new Videojuego(nameGame);
        Videojuego coincidencia = steamTreeGames.get(GameSelected);
        if(coincidencia == null){
            return GameSelected;
        }
        return coincidencia;
    }

    public void runGame(Videojuego gameToRun) {
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "cd \"C:\\Users\" && start Steam://run/" + /*searchIdInFileSteam(nameGame)*/ gameToRun.getIDPlatform());
            builder.redirectErrorStream(true);
            Process p = builder.start();
            //p.destroy();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void reloadTree() throws IOException {
        LocalData.loadSteamTree();
        System.out.println("Arbol STEAM correctamente Cargado!");
    }

    public void showGames(){
        //steamTreeGames.inOrden();
    }

    //-------SET / GET / INSERT -----------------
   public static AvlTree<Videojuego> getSteamTreeGames() {
       return steamTreeGames;
   }

    public static void setSteamTreeGames(AvlTree<Videojuego> steamTreeGames) {
        STEAMOperations.steamTreeGames = steamTreeGames;
    }

    public static void insertSteamGame(Videojuego game){
       steamTreeGames.insert(game);
    }

}
