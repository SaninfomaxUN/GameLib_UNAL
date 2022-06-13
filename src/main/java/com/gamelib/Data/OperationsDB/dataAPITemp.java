package com.gamelib.Data.OperationsDB;

import com.gamelib.Data.Connection.IGDBConnection;

public class dataAPITemp {
    public static IGDBConnection IGDBConnector;
    private static APIOperations API;

    public static void startConectionAPI(){
        IGDBConnector = new IGDBConnection();
        IGDBConnector.authenticate();
        API = new IGDBOperations();
    }


    public static APIOperations getAPI() {
        return API;
    }

    public static void setAPI(APIOperations api) {

    }
}
