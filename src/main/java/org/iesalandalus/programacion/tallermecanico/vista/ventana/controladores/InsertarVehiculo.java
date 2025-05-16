package org.iesalandalus.programacion.tallermecanico.vista.ventana.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.utilidades.Dialogos;

public class InsertarVehiculo extends Controlador {


    @FXML
    private Button bAceptar;

    @FXML
    private Button bCancelar;

    @FXML
    private TextField tfMarca;

    @FXML
    private TextField tfMatricula;

    @FXML
    private TextField tfModelo;

    @FXML
    void AccionAceptar(ActionEvent event) {

    }

    @FXML
    void bLimpiar(ActionEvent event) {
        tfMarca.clear();
        tfMatricula.clear();
        tfModelo.clear();
    }


    @FXML
    void AccionCancelar(ActionEvent event) {
       getEscenario().close();
    }

    @FXML
    void initialize() {

    }

}
