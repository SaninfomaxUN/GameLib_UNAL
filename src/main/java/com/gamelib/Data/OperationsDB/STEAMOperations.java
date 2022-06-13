package com.gamelib.Data.OperationsDB;

import com.gamelib.Data.Saved.localData;
import com.gamelib.Logic.Model.Videojuego;
import com.gamelib.Logic.Structures.AvlTree;
import com.gamelib.Logic.Structures.Queue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class STEAMOperations implements APIOperations, Serializable {

    private static AvlTree<Videojuego> steamTreeGames = new AvlTree(Videojuego.class);
    private Videojuego GameSelected;
    private static final String nameFileData = "SteamDATA";

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

    private String searchIdInFileSteam(String name){
        try {
            Scanner scanner = new Scanner(readFile());
            String lastLine = null;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = line.replace("™", "");
                if (line.contains("\"" + name + "\"")) {
                    String [] lastLineArray = lastLine.split(" ");
                    lastLine = lastLineArray[lastLineArray.length -1];
                    lastLine = lastLine.substring(0, lastLine.length()-1);
                    return(lastLine);
                }
                lastLine = line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
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

    public static void reloadTree() throws IOException, ClassNotFoundException {
        steamTreeGames = localData.loadTree(nameFileData);
        System.out.println("Arbol correctamente Cargado!");
    }

    public static void saveTreeReloaded() throws IOException {
        steamTreeGames = new AvlTree<>(Videojuego.class);
        try {
            Scanner scanner = new Scanner(readFile());
            String idLastLine = "PRUEBA 1";

            while (scanner.hasNextLine()) {
                String title = scanner.nextLine();
                title = title.replace("™", "");
                title = title.replace(":", "");
                if(title.contains("\"name\"")){
                    String [] idLineArray = idLastLine.split(" ");
                    idLastLine = idLineArray[idLineArray.length -1];
                    idLastLine = idLastLine.substring(0, idLastLine.length()-1);

                    String [] lineArrayName = title.split("\"");
                    title = lineArrayName [lineArrayName.length -1];
                    steamTreeGames.insert(new Videojuego(title.trim(),"Steam",idLastLine));
                }
                idLastLine = title;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        localData.saveTree(steamTreeGames,nameFileData);

    }


    public void showGames(){
        //steamTreeGames.inOrden();
    }


    private static File readFile(){
        File steamFile = new File("U:\\OneDrive - Universidad Nacional de Colombia\\Semestre IV\\Estruc_Datos\\Proyecto\\Codigo_Fuente\\GameLib\\src\\main\\resources\\com\\gamelib\\Logic\\Model\\appid.txt");
        //System.out.println(steamFile.exists());
        return steamFile;
    }


}
