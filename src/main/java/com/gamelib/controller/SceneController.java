package com.gamelib.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private static Stage stage;
    private static Scene scene;
    private static Parent root;

    private static Stage stageSecondary;
    private static Scene sceneSecondary;
    private static Parent rootSecondary;


    public static void crearScene(String nameFile, boolean maximizar) throws IOException {
        if(stage == null){
            stage = new Stage();
        }
        root = FXMLLoader.load(SceneController.class.getResource(nameFile+".fxml"));
        stage.setTitle("GameLib");
        //stage.getIcons().add(details.getIconAPP());
        if(scene == null){
            scene = new Scene(root);
        }else{
            scene.setRoot(root);
        }
        //scene.getStylesheets().add(SceneController.class.getResource("/com/gamelib/styles/base.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(maximizar);
        //stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, SceneController::confirmarSalir);
    }

    //APP--------------
    public static void sceneApp() throws IOException {
        crearScene("app2",false);
    }


}

