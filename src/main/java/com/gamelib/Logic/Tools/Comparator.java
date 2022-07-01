package com.gamelib.Logic.Tools;

public class Comparator {

    public static String adaptString(String str){
        str = str.replace(":","");
        str = str.replace("!","");
        str = str.replace(".","");
        str = str.replace("™","");
        str = str.toUpperCase();

        return str;
    }


}
