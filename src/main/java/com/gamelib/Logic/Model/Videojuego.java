package com.gamelib.Logic.Model;

import com.gamelib.controller.SceneController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Videojuego {
    private String name;
    private String iD;

    public Videojuego(String name) {
        this.name = name;
        File file = new File("U:\\OneDrive - Universidad Nacional de Colombia\\Semestre IV\\Estruc_Datos\\Proyecto\\Codigo_Fuente\\GameLib\\src\\main\\resources\\com\\gamelib\\Logic\\Model\\appid.txt");
        System.out.println(file.exists());

        try {
            Scanner scanner = new Scanner(file);
            String lastLine = null;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("\"" + name + "\"")) {

                    String [] lastLineArray = lastLine.split(" ");
                    lastLine = lastLineArray[lastLineArray.length -1];
                    lastLine = lastLine.substring(0, lastLine.length()-1);
                    this.iD = lastLine;
                }
                lastLine = line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getName(){
        return name;
    }

    public String getID(){
        return iD;
    }

    public String toString(){
        return "Game : " + getName() + ", ID: " + getID();
    }


}

