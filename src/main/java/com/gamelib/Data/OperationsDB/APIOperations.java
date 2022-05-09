package com.gamelib.Data.OperationsDB;


import com.api.igdb.exceptions.RequestException;
import com.gamelib.Logic.Structures.Queue;
import proto.Game;

public interface APIOperations {

    //Metodos/Operaciones a realizar con las API
    Queue<Game> buscarJuegoBase(String nameGame) throws RequestException;

    Game buscarJuegoById(int idGame) throws RequestException;

    void runGame(String nameGame);
}