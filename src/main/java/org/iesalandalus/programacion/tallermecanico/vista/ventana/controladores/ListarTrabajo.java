package org.iesalandalus.programacion.tallermecanico.vista.ventana.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.utilidades.Controlador;

public class ListarTrabajo extends Controlador {

    @FXML
    void buscarTrabajo(ActionEvent event) {

    }



    @FXML
    private ListView<?> lvListadoClientes;

    @FXML
    void accionSalir(ActionEvent event) {
        getEscenario().close();
    }

    @FXML
    void finalizarTrabajo(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert lvListadoClientes != null : "fx:id=\"lvListadoClientes\" was not injected: check your FXML file 'ListarTrabajo.fxml'.";

    }

}
