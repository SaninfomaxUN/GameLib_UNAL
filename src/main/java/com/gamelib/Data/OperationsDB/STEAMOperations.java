package com.gamelib.Data.OperationsDB;

import com.api.igdb.exceptions.RequestException;
import com.gamelib.Logic.Model.Videojuego;
import com.gamelib.Logic.Structures.AvlTree;
import com.gamelib.Logic.Structures.DynamicArray;
import com.gamelib.Logic.Structures.Queue;
import proto.Game;

import java.io.IOException;

public class STEAMOperations implements APIOperations {


    private int numberOfGames;
    //private Videojuego [] listOfGames;

    /*public STEAMOperations(){
        this.numberOfGames = 0;
        this.listOfGames = new Videojuego[100];

    }*/

    @Override
    public Queue<Game> buscarJuegoBase(String nameGame) throws RequestException {
        return null;
    }

    @Override
    public Game buscarJuegoById(int idGame) throws RequestException {
        return null;
    }

    /*@Override
    public void runGame(String nameGame) {}
    @Override
    public void runGame(String nameGame) {
        Videojuego run = null;
        for (int i = 0;i< listOfGames.length;i++){
            if (listOfGames[i].getName().equalsIgnoreCase(nameGame)){
                run = listOfGames[i];
                break;
            }
        }
        String id = run.getID();
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \"C:\\Users\" && start Steam://run/" + id);
            builder.redirectErrorStream(false);
            Process p = builder.start();
        } catch(IOException e){
            e.printStackTrace();
        }
    }


    public void addGame(String game){
        Videojuego newGame = new Videojuego(game);
        listOfGames[numberOfGames] = newGame;
        numberOfGames++;
    }

    public void deleteGame(int index){
        Videojuego []  temp = new Videojuego[100];
        Videojuego temp2 = null;
        for (int i = 0; i < numberOfGames; i++){
            if (i < index){
                temp[i] = listOfGames[i];
            }else if (i == index){
                temp2 = listOfGames[i];
                temp[i] = listOfGames[i+1];
            }
        }

        System.out.println("Game deleted: " + temp2);
        listOfGames = temp;
        numberOfGames--;
        showGames();
    }

    public void showGames(){
        for (int i = 0; i < numberOfGames; i++){
            String y = String.valueOf(i+1);
            System.out.println(y + ". " + listOfGames[i]);
        }
    }*/


    //---------------------------------------------------
    public STEAMOperations(){
        this.numberOfGames = 0;
        this.listOfGames = new AvlTree();
        this.listArray = new DynamicArray<>();

    }

    public void runGame(String nameGame) {
        //showGames();
        //String listFinal [] = (String[]) listArray.getArray();
        //String id = listFinal[index];

        String id = ((Videojuego)(listOfGames.get(new Videojuego(nameGame,false)).element)).getID();

        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \"C:\\Users\" && start Steam://run/" + id);
            builder.redirectErrorStream(false);
            Process p = builder.start();

            p.destroy();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void addGame(String game){
        Videojuego newGame = new Videojuego(game, true);
        listOfGames.insert(newGame);
        numberOfGames++;
    }
    public void deleteGame(String game){
        listOfGames.remove(game);

        numberOfGames--;

    }
    /*public void showGames(){
        listOfGames.inOrden();
        listArray = listOfGames.getList();
    }*/
    private AvlTree listOfGames;
    private DynamicArray listArray;
    private Videojuego lastGame;

}
