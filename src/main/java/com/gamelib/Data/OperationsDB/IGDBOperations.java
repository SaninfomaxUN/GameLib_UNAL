package com.gamelib.Data.OperationsDB;

import com.api.igdb.apicalypse.APICalypse;
import com.api.igdb.exceptions.RequestException;
import com.api.igdb.request.IGDBWrapper;
import com.api.igdb.request.ProtoRequestKt;
import com.api.igdb.utils.ImageBuilderKt;
import com.api.igdb.utils.ImageSize;
import com.api.igdb.utils.ImageType;
import com.gamelib.Logic.Model.Videojuego;
import com.gamelib.Logic.Structures.Queue;
import proto.Game;

import java.util.List;

public class IGDBOperations implements APIOperations{


    //-----------------------BUSQUEDA -----------------------------------------------------
    public Queue<Videojuego> buscarJuegosBase(String nameGame) throws RequestException {
        APICalypse apicalypse = new APICalypse().fields("name").search(nameGame)/*.where("category = 0 & version_parent = null")*/.limit(500);
        Game[] arrayResultados = convertirLista(ProtoRequestKt.games(IGDBWrapper.INSTANCE, apicalypse));
        Queue<Videojuego> filaResultados = extraerConsulta(arrayResultados);
        return filaResultados;
    }

    @Override
    public Videojuego buscarJuegoById(String idGame, String campos) throws RequestException {
        APICalypse apicalypse = new APICalypse().fields(campos).where("id =" + idGame).limit(5);
        Game[] arrayResultados = convertirLista(ProtoRequestKt.games(IGDBWrapper.INSTANCE, apicalypse));
        return new Videojuego(arrayResultados[0]);
    }

    @Override
    public Videojuego buscarJuegoByName(String nameGame) throws RequestException {
        return null;
    }

    @Override
    public void runGame(Videojuego gameToRun) {}

    public Game[] convertirLista(List<Game> listToConvert){
        Game[] arrayGames = new Game[listToConvert.size()];
        listToConvert.toArray(arrayGames);
        return arrayGames;
    }
    public Queue<Videojuego> extraerConsulta(Game[] listToExtract){
        Queue<Videojuego> filaResultados = new Queue<>();

        for (Game juegoExtr:listToExtract){
            filaResultados.enqueue(new Videojuego(juegoExtr));
        }

      return filaResultados;
    }


    public String getUrlCover(Videojuego videojuego) throws RequestException {
        videojuego = buscarJuegoById(String.valueOf(videojuego.getIDGameApi()),"cover.*");

        String image_id = videojuego.getGameApi().getCover().getImageId();
        String urlCover = ImageBuilderKt.imageBuilder(image_id, ImageSize.COVER_BIG, ImageType.PNG);

        return urlCover;
    }

}
