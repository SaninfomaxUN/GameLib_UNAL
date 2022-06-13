package com.gamelib.Data.OperationsDB;


import com.api.igdb.exceptions.RequestException;
import com.gamelib.Logic.Model.Videojuego;
import com.gamelib.Logic.Structures.Queue;

public interface APIOperations {

    //Metodos/Operaciones a realizar con las API
    Queue<Videojuego> buscarJuegosBase(String nameGame) throws RequestException;

    Videojuego buscarJuegoById(String idGame, String campos) throws RequestException;

    Videojuego buscarJuegoByName(String nameGame) throws RequestException;

    void runGame(Videojuego gameToRun);
}