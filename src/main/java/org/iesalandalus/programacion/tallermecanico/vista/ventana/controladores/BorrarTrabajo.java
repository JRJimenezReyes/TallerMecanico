package org.iesalandalus.programacion.tallermecanico.vista.ventana.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.utilidades.Controlador;

public class BorrarTrabajo extends Controlador {



    @FXML
    private DatePicker dpFechaInicio;

    @FXML
    private TextField tfDni;

    @FXML
    private TextField tfMatricula;

    @FXML
    void accionAceptar(ActionEvent event) {

    }

    @FXML
    void accionCancelar(ActionEvent event) {
        getEscenario().close();
    }

    @FXML
    void bLimpiar(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert dpFechaInicio != null : "fx:id=\"dpFechaInicio\" was not injected: check your FXML file 'BorrarTrabajo.fxml'.";
        assert tfDni != null : "fx:id=\"tfDni\" was not injected: check your FXML file 'BorrarTrabajo.fxml'.";
        assert tfMatricula != null : "fx:id=\"tfMatricula\" was not injected: check your FXML file 'BorrarTrabajo.fxml'.";

    }

}
