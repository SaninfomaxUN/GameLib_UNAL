package com.gamelib.Logic.Model;

import com.api.igdb.exceptions.RequestException;
import com.gamelib.Data.OperationsDB.dataAPITemp;
import proto.Game;

import java.io.Serializable;

public class Videojuego implements Comparable<Videojuego>, Serializable {
    public String Name;
    public String IDPlatform;
    public String Platform;
    private transient Game GameApi;

    public String IDGameApi;
    public String urlImage;


    public Videojuego(Game gameApi) {
        this(gameApi.getName(), "API", Long.toString(gameApi.getId()));
        this.GameApi = gameApi;
        this.IDGameApi = String.valueOf(gameApi.getId());
    }
    public Videojuego(String name, String platform, String iDPlatform) {
        this(name,platform);
        this.IDPlatform = iDPlatform;
    }
    public Videojuego(String name, String platform) {
        this(name);
        this.Platform = platform;
    }
    public Videojuego(String name) {
        this.Name = name;
    }



//----------------- GET/SET ---------------
    public String getName() {
        return Name;
    }
    public void setIDPlatform(String IDPlatform) {
        this.IDPlatform = IDPlatform;
    }
    public void setPlatform(String platform) {
        Platform = platform;
    }
    public void setGameApi(Game gameApi) {
        GameApi = gameApi;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setIDGameApi(String IDGameApi) {
        this.IDGameApi = IDGameApi;
    }


    public String getIDPlatform() {
        return IDPlatform;
    }
    public String getPlatform() {
        return Platform;
    }
    public String getIDGameApi() {
        return IDGameApi;
    }

    public Game getGameApi() throws RequestException {
        if(GameApi==null){
            GameApi = dataAPITemp.getAPI().buscarJuegoById(IDGameApi,"*").getGameApi();
        }
        return GameApi;
    }

    public String getUrlImage() {
        return urlImage;
    }




    public String toString(){
        return "Game : " + getName() + ", ID: " + getIDPlatform();
    }

    @Override
    public int compareTo(Videojuego videojuego) {
        String x = this.getName();
        x = x.replace(":","");
        x = x.replace("!","");
        x = x.replace(".","");
        x = x.replace("™","");
        x = x.toUpperCase();
        String y = videojuego.getName();
        y = y.replace(":","");
        y = y.replace("!","");
        y = y.replace(".","");
        y = y.replace("™","");
        y = y.toUpperCase();
        return x.compareTo(y);
    }
}

