package com.gamelib.Controller;

import com.api.igdb.exceptions.RequestException;
import com.gamelib.Connection.IGDBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws RequestException {
        welcomeText.setText("Revisa la consola!");
        IGDBConnection conector= new IGDBConnection();
        conector.authenticate();
        conector.testConnection();
    }
}