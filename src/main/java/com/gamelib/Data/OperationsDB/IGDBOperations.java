package com.gamelib.Data.OperationsDB;

import com.api.igdb.apicalypse.APICalypse;
import com.api.igdb.exceptions.RequestException;
import com.api.igdb.request.IGDBWrapper;
import com.api.igdb.request.ProtoRequestKt;
import com.gamelib.Logic.Structures.DynamicArray;
import com.gamelib.Logic.Structures.LinkedListSimple;
import com.gamelib.Logic.Structures.Queue;
import com.gamelib.Logic.Structures.Stack;
import org.apache.commons.lang3.SerializationUtils;
import proto.Game;

import java.util.List;

public class IGDBOperations implements APIOperations{


    //-----------------------BUSQUEDA -----------------------------------------------------
    public Queue<Game> buscarJuegoBase(String nameGame) throws RequestException {
        APICalypse apicalypse = new APICalypse().fields("name").search(nameGame)/*.where("category = 0 & version_parent = null")*/.limit(500);
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

        //long startTime = System.nanoTime();
        for (Game juegoExtr:listToExtract){
            filaResultados.enqueue(juegoExtr);
        }
        //long endTime = System.nanoTime() - startTime;
       // System.out.println("Tiempo de ejecución de Insertar - Cola: "+endTime);

        //-----------Pruebas----------------------------------
        /*long startTime = System.nanoTime();
        Stack<Game> pilaPrueba = new Stack<>();
        for (Game juegoExtr:listToExtract){
            pilaPrueba.push(juegoExtr);
        }
        long endTime = System.nanoTime() - startTime;
        System.out.println("Tiempo de ejecución de Push - Stack: "+endTime);*/

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
