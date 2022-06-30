package com.gamelib.gamelib;

import com.gamelib.Data.OperationsDB.STEAMOperations;
import com.gamelib.Data.OperationsDB.dataAPITemp;
import com.gamelib.Data.Saved.UserLibrary;
import com.gamelib.controller.SceneController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        dataAPITemp.startConectionAPI();

        SceneController.sceneApp();

        loadLocalData();


    }

    public static void main(String[] args) {
        launch();
    }

    public static void loadLocalData() throws IOException {
        STEAMOperations.reloadTree();

        UserLibrary.loadUserLibrary();




    }

}