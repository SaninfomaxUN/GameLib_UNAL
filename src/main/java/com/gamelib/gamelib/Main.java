package com.gamelib.gamelib;

import com.api.igdb.exceptions.RequestException;
import com.gamelib.Data.OperationsDB.STEAMOperations;
import com.gamelib.Data.OperationsDB.dataAPITemp;
import com.gamelib.Data.Saved.userLibrary;
import com.gamelib.controller.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        dataAPITemp.startConectionAPI();

        SceneController.sceneApp();

        loadLocalData();


    }

    public static void main(String[] args) throws RequestException {
        launch();
    }

    public static void loadLocalData() throws IOException, ClassNotFoundException {
        STEAMOperations.reloadTree();

        //STEAMOperations.saveTreeReloaded();

        userLibrary.loadUserLibrary();


    }

}