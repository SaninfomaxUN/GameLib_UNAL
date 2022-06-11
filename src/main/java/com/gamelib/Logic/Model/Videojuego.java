package com.gamelib.Logic.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Videojuego implements Comparable<Videojuego>  {
    private String Name;
    private String ID;
    private String Platform;


    public Videojuego(String name, boolean findID, String platform) {
        this(name, findID);
        this.Platform = platform;


    }
    public Videojuego(String name, boolean findID) {
        this.Name = name;
        if(findID){
            this.ID = getID(name);
        }

    }

    public String getID(String name){
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
                    return(lastLine);
                }
                lastLine = line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String getName(){
        return Name;
    }

    public String getID(){
        return ID;
    }

    public String toString(){
        return "Game : " + getName() + ", ID: " + getID();
    }

    @Override
    public int compareTo(Videojuego o) {
        return this.getName().compareTo(o.getName());
    }
}

