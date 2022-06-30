package com.gamelib.controller;

import com.api.igdb.exceptions.RequestException;
import com.gamelib.Data.OperationsDB.IGDBOperations;
import com.gamelib.Data.OperationsDB.STEAMOperations;
import com.gamelib.Data.OperationsDB.dataAPITemp;
import com.gamelib.Data.Saved.UserLibrary;
import com.gamelib.Logic.Model.UIGame;
import com.gamelib.Logic.Model.Videojuego;
import com.gamelib.Logic.Structures.Queue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import org.apache.commons.lang3.SerializationUtils;

public class addGameController implements Initializable, Serializable {
    @FXML
    private TextField TextField_busqueda;
    @FXML
    private ComboBox<String> ComboBox_Resultados;
    @FXML
    private Label label_ComboBoxResultados;
    private static Queue<Videojuego> filaResultados;
    private static Videojuego juegoSeleccionado;

    private static STEAMOperations steam = new STEAMOperations();
    @FXML
    private Button btn_Save;
    @FXML
    private Label label_ComboBoxPlataforma;
    @FXML
    private ComboBox<String> ComboBox_Plataforma;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        habilitarCamposSeleccJuego(false);
        habilitarCamposSeleccPlataforma(false);
        habilitarCamposGuardarJuego(false);
        iniciarListenerComboBoxJuego();
        iniciarListenerComboBoxPlataforma();

    }

    @FXML
    private void buscarJuegosAPI() throws RequestException {
        filaResultados = dataAPITemp.getAPI().buscarJuegosBase(TextField_busqueda.getText());
        completarComboboxResultados(filaResultados);
        habilitarCamposSeleccJuego(true);
    }

    private void buscarJuegoAPIbyId(String idGame) throws RequestException {
        juegoSeleccionado = dataAPITemp.getAPI().buscarJuegoById(idGame,"*");
    }

    private void iniciarListenerComboBoxJuego() {
        ComboBox_Resultados.setOnAction((event) -> {
            habilitarCamposSeleccPlataforma(true);
            completarComboboxPlataforma();
        });
    }

    private void completarComboboxResultados(Queue<Videojuego> filaResultados) {
        ComboBox_Resultados.getItems().clear();
        Queue<Videojuego> fila = SerializationUtils.clone(filaResultados);
        while(!fila.isEmpty()){
            ComboBox_Resultados.getItems().add(fila.dequeue().getName());
        }
    }

    private void iniciarListenerComboBoxPlataforma() {
        ComboBox_Plataforma.setOnAction((event) -> {
            habilitarCamposGuardarJuego(true);
        });
    }
    private void completarComboboxPlataforma() {
        ComboBox_Plataforma.getItems().clear();
        ComboBox_Plataforma.getItems().add("Steam");
    }
    private void habilitarCamposSeleccJuego(boolean habilitar){
        label_ComboBoxResultados.setVisible(habilitar);
        ComboBox_Resultados.setVisible(habilitar);
    }

    private void habilitarCamposSeleccPlataforma(boolean habilitar){
        label_ComboBoxPlataforma.setVisible(habilitar);
        ComboBox_Plataforma.setVisible(habilitar);
    }

    private void habilitarCamposGuardarJuego(boolean habilitar){
        btn_Save.setVisible(habilitar);
    }

    @FXML
    private void addGameToLibrary() throws IOException {
        String nombreSelecc = ComboBox_Resultados.getSelectionModel().getSelectedItem();
        if(comprobateRepeatGame(nombreSelecc)){
            return;
        }

        Queue<Videojuego> fila = SerializationUtils.clone(filaResultados);
        while(!fila.isEmpty()){
            Videojuego juegoExtr = fila.dequeue();
            if (juegoExtr.getName().equalsIgnoreCase(nombreSelecc)){
                try {
                    buscarJuegoAPIbyId(juegoExtr.getIDPlatform());
                    createUIGame();
                    break;
                } catch (RequestException | IOException e) {}
                return;
            }
        }

        SceneController.cerrarVentanaSecundaria();
    }
    private boolean comprobateRepeatGame(String nombreSelecc) throws IOException {
        return UserLibrary.constainsUIGame(new Videojuego(nombreSelecc));
    }
    private void createUIGame() throws RequestException, IOException {
        addPlatformData();
        saveNewUIGame(juegoSeleccionado);
        UIGame newUIGame = new UIGame(juegoSeleccionado);
        moveGameToApp(newUIGame);

    }

    private void addPlatformData() throws RequestException {
        String urlCover = ((IGDBOperations)dataAPITemp.getAPI()).getUrlCover(juegoSeleccionado);
        juegoSeleccionado.setUrlImage(urlCover);
        juegoSeleccionado.setPlatform(ComboBox_Plataforma.getValue());
        juegoSeleccionado.setIDPlatform(searchIDByPlatform());

    }
    private void moveGameToApp(UIGame newUIGame){
        appController.newUIGame = newUIGame;
        appController.addGame.set(true);
    }
    private void saveNewUIGame(Videojuego newUIGame) throws IOException {
        UserLibrary.addUIGame(newUIGame);
    }

    private String searchIDByPlatform(){
        String idPlatform = juegoSeleccionado.getIDGameApi();
        switch (juegoSeleccionado.getPlatform()){
            case "Steam":
                Videojuego v = steam.buscarJuegoByName(juegoSeleccionado.getName());
                if(v.getIDPlatform()==null){
                    juegoNulo();
                }else{
                    idPlatform = v.getIDPlatform();
                }

                break;
        }

        return idPlatform;
    }

    private void juegoNulo(){
        juegoSeleccionado.setPlatform("API");
    }

}