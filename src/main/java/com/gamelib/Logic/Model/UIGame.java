package com.gamelib.Logic.Model;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class UIGame {

    private VBox GameVBox;
    private Pane GamePane;
    private Label GameTitle;
    private Label GameStart;
    private Videojuego Game;

    public UIGame(Videojuego game) {
        
        this.Game = game;
        this.GameVBox = crearVBox();
        this.GamePane = crearPane();
        this.GameTitle = crearTitle();
        this.GameStart = crearStart();
    }



    //Methods to create JavaFX Components
    public VBox crearVBox(){
        VBox modelVBox = new VBox(10);
        return modelVBox;
    }
    public Pane crearPane(){
        Pane modelPane = new Pane();
        return modelPane;
    }

    public Label crearTitle(){
        Label modelLabel = new Label();
        return modelLabel;
    }

    public Label crearStart(){
        Label modelLabel = new Label();
        return modelLabel;
    }

    
    
    
    // SET - GET
    public VBox getGameVBox() {
        return GameVBox;
    }

    public void setGameVBox(VBox gameVBox) {
        this.GameVBox = gameVBox;
    }

    public Pane getGamePane() {
        return GamePane;
    }

    public void setGamePane(Pane gamePane) {
        this.GamePane = gamePane;
    }

    public Label getGameTitle() {
        return GameTitle;
    }

    public void setGameTitle(Label gameTitle) {
        this.GameTitle = gameTitle;
    }

    public Label getGameStart() {
        return GameStart;
    }

    public void setGameStart(Label gameStart) {
        this.GameStart = gameStart;
    }
}
