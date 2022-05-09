package com.gamelib.Data;

import com.gamelib.Data.Connection.IGDBConnection;
import com.gamelib.Data.OperationsDB.APIOperations;
import com.gamelib.Data.OperationsDB.IGDBOperations;

public class dataTemp {


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

    public static void setAPI(APIOperations api) {API = api;
    }
}
