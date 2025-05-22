package org.iesalandalus.programacion.tallermecanico.vista.ventana.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.utilidades.Controlador;

public class InsertarTrabajo extends Controlador {


    @FXML
    private TextField tfDni;
    @FXML
    private ChoiceBox<String> cbTipo;

    @FXML
    private DatePicker dpFechaInicio;

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
        tfDni.clear();
        tfMatricula.clear();
    }


    @FXML
    void initialize() {
        ObservableList<String> cajaEleccion = FXCollections.observableArrayList("Mecánico", "Revisión");
        cbTipo.setItems(cajaEleccion);

    }

}
