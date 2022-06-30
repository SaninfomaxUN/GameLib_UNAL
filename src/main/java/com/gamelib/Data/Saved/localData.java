package com.gamelib.Data.Saved;

import com.gamelib.Data.OperationsDB.STEAMOperations;
import com.gamelib.Logic.Model.Videojuego;
import com.gamelib.Logic.Structures.AvlTree;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.*;
public class localData implements Serializable{

    /*public static void saveTree(AvlTree<Videojuego> treeToSave, String nameDataSaved) throws IOException {
        String fileName= nameDataSaved + ".txt";
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(treeToSave);
        oos.close();
    }*/

    /*public static AvlTree<Videojuego> loadTree(String nameDataSaved) throws IOException, ClassNotFoundException {
        String fileName= nameDataSaved + ".txt";
        FileInputStream fin = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fin);
        new AvlTree<>(Videojuego.class);
        AvlTree<Videojuego> treeToRead;
        treeToRead = (AvlTree<Videojuego>) ois.readObject();
        ois.close();
        return treeToRead;
    }*/
    public static void loadSteamTree(String nameDataSaved) throws IOException, ClassNotFoundException {
        String apiJsonStr = null;
        /*try{
            apiJsonStr = new Scanner(new URL("http://api.steampowered.com/ISteamApps/GetAppList/v0002/").openStream(), "UTF-8").useDelimiter("\\A").next();
        }catch (UnknownHostException e){

        }*/
        Path filePath = Path.of("SteamAPI" + ".json");
        apiJsonStr = Files.readString(filePath);

        JsonObject gsonObjAppList = JsonParser.parseString(/*content*/apiJsonStr).getAsJsonObject();
        JsonObject gsonObjApps = gsonObjAppList.get("applist").getAsJsonObject();
        JsonArray gsonObjArrayApps = gsonObjApps.get("apps").getAsJsonArray();

        for (JsonElement gsonElementGame : gsonObjArrayApps) {
            JsonObject gsonGame = gsonElementGame.getAsJsonObject();
            String name = gsonGame.get("name").getAsString();
            String appid = gsonGame.get("appid").getAsString();
            insertInSteamTree(new Videojuego(name, "Steam", appid));//-----Insert in Steam Tree
        }
    }

    public static void insertInSteamTree(Videojuego game){
        STEAMOperations.insertSteamGame(game);
    }

}
