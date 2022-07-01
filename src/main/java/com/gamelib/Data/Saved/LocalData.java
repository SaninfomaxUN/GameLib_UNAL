package com.gamelib.Data.Saved;

import com.gamelib.Data.OperationsDB.STEAMOperations;
import com.gamelib.Logic.Model.Videojuego;
import com.gamelib.Logic.Tools.Comparator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
public class LocalData implements Serializable{

    public static void loadSteamTree() throws IOException {
        String apiJsonStr;
        /*try{
            apiJsonStr = new Scanner(new URL("http://api.steampowered.com/ISteamApps/GetAppList/v0002/").openStream(), "UTF-8").useDelimiter("\\A").next();
        }catch (UnknownHostException e){

        }*/
        Path filePath = Path.of(STEAMOperations.nameFileData);
        apiJsonStr = Files.readString(filePath);

        JsonObject gsonObjAppList = JsonParser.parseString(/*content*/apiJsonStr).getAsJsonObject();
        JsonObject gsonObjApps = gsonObjAppList.get("applist").getAsJsonObject();
        JsonArray gsonObjArrayApps = gsonObjApps.get("apps").getAsJsonArray();

        for (JsonElement gsonElementGame : gsonObjArrayApps) {
            JsonObject gsonGame = gsonElementGame.getAsJsonObject();
            String name = gsonGame.get("name").getAsString();
            String appid = gsonGame.get("appid").getAsString();
            insertInSteamMapQuadratic(new Videojuego(Comparator.adaptString(name) , "Steam", appid));//-----Insert in Steam Map Quadratic
        }
    }

    public static void insertInSteamMapQuadratic(Videojuego game){
        STEAMOperations.insertSteamMapQGame(game);
    }
}
