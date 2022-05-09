package com.gamelib.gamelib;

import com.api.igdb.exceptions.RequestException;
import com.gamelib.Data.dataTemp;
import com.gamelib.controller.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        dataTemp.startConectionAPI();
        SceneController.sceneApp();


    }

    public static void main(String[] args) throws RequestException {
        launch();
    }
}