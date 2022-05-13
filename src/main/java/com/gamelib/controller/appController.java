package com.gamelib.controller;

import com.api.igdb.exceptions.RequestException;
import com.gamelib.Data.OperationsDB.STEAMOperations;
import com.gamelib.Data.dataTemp;
import com.gamelib.Logic.Structures.DynamicArray;
import com.gamelib.Logic.Structures.LinkedListSimple;
import com.gamelib.Logic.Structures.Queue;
import com.gamelib.Logic.Structures.Stack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import proto.Game;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import org.apache.commons.lang3.SerializationUtils;

public class appController implements Initializable, Serializable {
    @FXML
    private TextField TextField_busqueda;
    @FXML
    private Button btnBuscar;
    @FXML
    private ComboBox<String> ComboBox_Resultados;
    @FXML
    private Label label_ComboBox;
    @FXML
    private Label label_Metadatos;
    private static Queue<Game> filaResultados;
    private static Game juegoSeleccionado;
    @FXML
    private HBox Hbox_Steam;
    @FXML
    private Label label_Steam;
    @FXML
    private Button btn_Steam;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        habilitarCamposSeleccJuego(false);
        habilitarCamposAbrirJuego(false);
        iniciarListenerComboBox();

    }

    private void iniciarListenerComboBox() {
        ComboBox_Resultados.setOnAction((event) -> {
            String nombreSelecc = ComboBox_Resultados.getSelectionModel().getSelectedItem();
            Queue<Game> fila = SerializationUtils.clone(filaResultados);
            while(!fila.isEmpty()){
                Game juegoExtr = fila.dequeue();
                if (juegoExtr.getName().equalsIgnoreCase(nombreSelecc)){
                    try {
                        buscarJuegoAPIbyId((int) juegoExtr.getId());

                    } catch (RequestException e) {}
                    return;
                }
            }

        });
    }

    @FXML
    private void buscarJuegosAPI() throws RequestException {
        filaResultados = dataTemp.getAPI().buscarJuegoBase(TextField_busqueda.getText());
        completarCombobox(filaResultados);
        habilitarCamposSeleccJuego(true);
    }

    private void buscarJuegoAPIbyId(int idGame) throws RequestException {
        juegoSeleccionado = dataTemp.getAPI().buscarJuegoById(idGame);

        imprimirJuego();
        label_Steam.setText("Desea abrir " + juegoSeleccionado.getName() + " en STEAM?");
        habilitarCamposAbrirJuego(true);
    }

    private void imprimirJuego() {
        for (int i = 0;i<2;i++){
            for (int j = 0;j<70;j++){
                System.out.print("-");
            }
            System.out.println();
        }
        System.out.println("METADATOS DISPONIBLES PARA: ----- " + juegoSeleccionado.getName() + " -----");
        System.out.println();
        System.out.println(juegoSeleccionado);
        habilitarCamposSeleccJuego(true);
        for (int i = 0;i<2;i++){
            for (int j = 0;j<70;j++){
                System.out.print("-");
            }
            System.out.println();
        }
        System.out.println();
    }


    private void completarCombobox(Queue<Game> filaResultados) {
        ComboBox_Resultados.getItems().clear();
        //long startTime = System.nanoTime();
        Queue<Game> fila = SerializationUtils.clone(filaResultados);
            //Stack<Game> pilaPrueba = new Stack<>();
        while(!fila.isEmpty()){
            ComboBox_Resultados.getItems().add(fila.dequeue().getName());
                //pilaPrueba.push(juego);
        }
        //long endTime = System.nanoTime() - startTime;
        // System.out.println("Tiempo de ejecución de Eliminar - Cola: "+endTime);

        //-----------Pruebas----------------------------------
        /*long startTime = System.nanoTime();
        while(!pilaPrueba.isEmpty()){
            pilaPrueba.pop();
        }
        long endTime = System.nanoTime() - startTime;

        System.out.println("Tiempo de ejecución de Pop - Stack: "+endTime);*/

    }

    private void habilitarCamposSeleccJuego(boolean habilitar){
        label_ComboBox.setVisible(habilitar);
        ComboBox_Resultados.setVisible(habilitar);
    }

    private void habilitarCamposAbrirJuego(boolean habilitar){
        label_Metadatos.setVisible(habilitar);
        Hbox_Steam.setVisible(habilitar);
    }

    @FXML
    private void runGameBySteam(ActionEvent actionEvent) {
        STEAMOperations steam = new STEAMOperations();
        steam.addGame(juegoSeleccionado.getName());
        //steam.showGames();
        //steam.deleteGame(1); //
        steam.runGame(juegoSeleccionado.getName());
        System.out.println("Ejecutando " + juegoSeleccionado.getName() + " en Steam..." );
    }
}