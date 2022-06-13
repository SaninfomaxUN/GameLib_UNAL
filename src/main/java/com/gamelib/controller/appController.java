package com.gamelib.controller;

import com.gamelib.Data.OperationsDB.STEAMOperations;
import com.gamelib.Logic.Model.UIGame;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class appController implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListenerAddGame();
    }

    public static SimpleBooleanProperty addGame = new SimpleBooleanProperty(false);

    public static STEAMOperations steam = new STEAMOperations();
    public static UIGame newUIGame;

    private void addListenerAddGame(){
        addGame.addListener(
                (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                    if(newValue){
                        addUIGame();
                        addGame.set(false);
                    }
                }
        );
    }

    @FXML
    private FlowPane FlowPaneGames;

    public void addUIGame(){
        FlowPaneGames.getChildren().add(newUIGame.getGameVBox());
    }

    public void removeUIGame(){

        FlowPaneGames.getChildren().add(newUIGame.getGameVBox()
        );
    }
    @FXML
    private void openMenuAddGame(ActionEvent actionEvent) throws IOException {
        SceneController.sceneMenuAddGame();
    }



}
