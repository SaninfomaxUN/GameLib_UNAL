package com.gamelib.Data.OperationsDB;

import com.api.igdb.apicalypse.APICalypse;
import com.api.igdb.exceptions.RequestException;
import com.api.igdb.request.IGDBWrapper;
import com.api.igdb.request.ProtoRequestKt;
import com.gamelib.Logic.Structures.Queue;
import proto.Game;

import java.util.List;

public class IGDBOperations implements APIOperations{


    //-----------------------BUSQUEDA -----------------------------------------------------
    public Queue<Game> buscarJuegoBase(String nameGame) throws RequestException {
        APICalypse apicalypse = new APICalypse().fields("name").search(nameGame).where("category = 0 & version_parent = null").limit(200);
        Game[] arrayResultados = convertirLista(ProtoRequestKt.games(IGDBWrapper.INSTANCE, apicalypse));
        Queue<Game> filaResultados = extraerConsulta(arrayResultados);

        return filaResultados;
    }

    @Override
    public Game buscarJuegoById(int idGame) throws RequestException {
        APICalypse apicalypse = new APICalypse().fields("*").where("id =" + idGame).limit(5);
        Game[] arrayResultados = convertirLista(ProtoRequestKt.games(IGDBWrapper.INSTANCE, apicalypse));
        return arrayResultados[0];
    }

    @Override
    public void runGame( String nameGame) {}

    public Game[] convertirLista(List<Game> listToConvert){
        Game[] arrayGames = new Game[listToConvert.size()];
        listToConvert.toArray(arrayGames);
        return arrayGames;
    }
    public Queue<Game> extraerConsulta(Game[] listToExtract){
        Queue<Game> filaResultados = new Queue<>();
       for (Game juegoExtr:listToExtract){
           filaResultados.enqueue(juegoExtr);
       }
      return filaResultados;
    }



    /*public Queue<String[]> extraerConsulta(String consulta){
        consulta = consulta.substring(1,consulta.length()-1);
        String[] arrayJuego = consulta.split(",");
        Queue<String[]> filaResultados = new Queue<>();
        for (String juego:arrayJuego){
            String[] arrayJuegoExtr = juego.split("\n");
            int index = arrayJuegoExtr[0].indexOf("id: ");
            arrayJuegoExtr[0] = arrayJuegoExtr[0].substring(index+4);
            index = arrayJuegoExtr[1].indexOf("name: ");
            arrayJuegoExtr[1] = arrayJuegoExtr[1].substring(index+7,arrayJuegoExtr[1].length()-1);
            filaResultados.enqueue(arrayJuegoExtr);
        }
        System.out.println(consulta);
      return null;
    }*/


}
