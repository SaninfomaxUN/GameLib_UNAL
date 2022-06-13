package com.gamelib.Logic.Model;

import com.gamelib.Data.Saved.userLibrary;
import com.gamelib.controller.appController;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.Serializable;


public class UIGame implements Comparable<UIGame>, Serializable {

    private VBox GameVBox;
    private Pane GamePaneCover;

    private Pane GamePanePlatform;

    private ComboBox<String> GameSettings;

    private HBox GameHBoxOptions;

    private Label GameTitle;
    //private Label GameStart;
    private Videojuego Game;

    public UIGame(Videojuego game) {
        this.Game = game;
        validateCover();
        this.GameTitle = crearTitle();
        this.GamePaneCover = crearCover();
        this.GameHBoxOptions = crearHBoxOptions();
        this.GameSettings = crearComboBoxSettings();
        this.GamePanePlatform = crearLogoPlatform();
        this.GameVBox = crearVBox();
        //this.GameStart = crearStart();
        unirComponentes();
    }




    private void validateCover(){
        if(Game.getUrlImage().contains("/.png")){
            Game.setUrlImage("https://images.igdb.com/igdb/image/upload/t_cover_big/nocover.png");
        }
    }


    //Methods to create JavaFX Components


    private Label crearTitle(){
        Label modelLabel = new Label(Game.getName());
        return modelLabel;
    }
    private Pane crearCover(){
        Pane modelPane = new Pane();
        modelPane.getStyleClass().add("PictureGame");
        modelPane.setStyle("-fx-background-image: url(" + Game.getUrlImage() + ")");
        return modelPane;
    }
    private Pane crearLogoPlatform() {
        Pane modelPane = new Pane();
        modelPane.getStyleClass().add("LogoPlatform");
        return chooseLogoPlatform(modelPane);
    }

    private ComboBox<String> crearComboBoxSettings() {
        ComboBox comboBox = new ComboBox();
        comboBox.getItems().add("Eliminar");
        comboBox.getStyleClass().add("ComboboxGame");
        return comboBox;
    }
    private HBox crearHBoxOptions() {
        HBox modelHBox = new HBox();
        modelHBox.getStyleClass().add("HBoxOptions");
        return modelHBox;
    }

    private VBox crearVBox(){
        VBox modelVBox = new VBox(10);
        modelVBox.getStyleClass().add("VBoxGame");
        modelVBox.setId(Game.getIDGameApi());
        return modelVBox;
    }
    private Label crearStart(){
        Label modelLabel = new Label();
        return modelLabel;
    }
    private void unirComponentes(){
        GameVBox.getChildren().add(GamePaneCover);
        GameHBoxOptions.getChildren().add(GamePanePlatform);
        GameHBoxOptions.getChildren().add(GameSettings);
        GameVBox.getChildren().add(GameHBoxOptions);
        addListenerVBox();
        addListenerComboBoxSettings();
        //GameVBox.getChildren().add(GameTitle);
        //GameVBox.getChildren().add(GameStart);
    }
    private void addListenerComboBoxSettings(){
        GameSettings.setOnAction((event) -> {
            //System.out.println(this.Game.getIDGameApi());
            if(GameSettings.getValue().equals("Eliminar")){
                FlowPane node = (FlowPane) this.GameVBox.getParent();
                node.getChildren().remove(this.GameVBox);
                try {
                    userLibrary.removeUIGame(this.Game);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
    private void addListenerVBox(){
        GameVBox.setOnMouseClicked((MouseEvent event) -> {
            runGame();
        });
    }

    private Pane chooseLogoPlatform(Pane modelPane){
        switch (this.Game.getPlatform()){
            case "Steam":
                String url = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Steam_icon_logo.svg/48px-Steam_icon_logo.svg.png";
                modelPane.setStyle("-fx-background-image: url(" + url + ")");
                break;
        }
        return modelPane;
    }

    private void runGame(){
        switch(Game.getPlatform()){
            case "Steam":
                appController.steam.runGame(this.Game);
                System.out.println("Ejecutando " + Game.getIDGameApi() + " en Steam...");
                break;
            default:
                System.out.println("El juego " + this.Game.getName() + " no se encuentra disponible en Steam :(");
                break;
        }
    }
    
    // SET - GET
    public VBox getGameVBox() {
        return GameVBox;
    }

    public void setGameVBox(VBox gameVBox) {
        this.GameVBox = gameVBox;
    }

    public Pane getGamePaneCover() {
        return GamePaneCover;
    }

    public void setGamePaneCover(Pane gamePaneCover) {
        this.GamePaneCover = gamePaneCover;
    }

    public Label getGameTitle() {
        return GameTitle;
    }

    public void setGameTitle(Label gameTitle) {
        this.GameTitle = gameTitle;
    }

    /*public Label getGameStart() {
        return GameStart;
    }

    public void setGameStart(Label gameStart) {
        this.GameStart = gameStart;
    }*/

    public Videojuego getGame() {
        return Game;
    }

    public void setGame(Videojuego game) {
        Game = game;
    }

    public Pane getGamePanePlatform() {
        return GamePanePlatform;
    }

    public void setGamePanePlatform(Pane gamePanePlatform) {
        GamePanePlatform = gamePanePlatform;
    }

    public ComboBox<String> getGameSettings() {
        return GameSettings;
    }

    public void setGameSettings(ComboBox<String> gameSettings) {
        GameSettings = gameSettings;
    }

    @Override
    public int compareTo(UIGame otherUIGmae) {
        return this.getGame().getName().compareTo(otherUIGmae.getGame().getName());
    }
}
