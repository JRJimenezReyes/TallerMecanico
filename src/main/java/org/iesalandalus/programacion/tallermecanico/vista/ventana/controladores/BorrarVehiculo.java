package org.iesalandalus.programacion.tallermecanico.vista.ventana.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.utilidades.Controlador;

public class BorrarVehiculo extends Controlador {



    @FXML
    private TextField tfMatriculaBorrar;

    @FXML
    void accionAceptar(ActionEvent event) {

    }

    @FXML
    void accionCancelar(ActionEvent event) {
        getEscenario().close();
    }

    @FXML
    void initialize() {
        assert tfMatriculaBorrar != null : "fx:id=\"tfMatriculaBorrar\" was not injected: check your FXML file 'BorrarVehiculo.fxml'.";

    }

}
