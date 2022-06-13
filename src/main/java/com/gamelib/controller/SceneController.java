package com.gamelib.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
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
        stage.getIcons().add(new Image(SceneController.class.getResource("logo.png").toExternalForm()));
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
    public static void crearSceneSecondary(String nameFile, boolean maximizar) throws IOException {
        stageSecondary = new Stage();
        rootSecondary = FXMLLoader.load(SceneController.class.getResource(nameFile+".fxml"));
        stageSecondary.setTitle("GameLib");
        stageSecondary.getIcons().add(new Image(SceneController.class.getResource("logo.png").toExternalForm()));
        sceneSecondary = new Scene(rootSecondary);
        //scene.getStylesheets().add(SceneController.class.getResource("/com/gamelib/styles/base.css").toExternalForm());
        stageSecondary.setScene(sceneSecondary);
        stageSecondary.setMaximized(maximizar);
        stageSecondary.initModality(Modality.APPLICATION_MODAL);
        stageSecondary.setResizable(false);
        stageSecondary.show();
        //stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, SceneController::confirmarSalir);
    }


    //APP--------------
    public static void sceneApp() throws IOException {
        crearScene("app",true);
    }

    public static void sceneMenuAddGame() throws IOException {
        crearSceneSecondary("addGame",false);
    }
    public static void sceneLoad() throws IOException {
        crearSceneSecondary("load",false);
    }


    public static void cerrarVentanaPrincipal() throws IOException {
        stage.close();
    }

    public static void cerrarVentanaSecundaria() {
        stageSecondary.close();
    }

}

