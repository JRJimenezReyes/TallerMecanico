package org.iesalandalus.programacion.tallermecanico.vista.ventana.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.utilidades.Controlador;

public class ListarCliente extends Controlador {



    @FXML
    private ListView<?> lvListadoClientes;

    @FXML
    void accionSalir(ActionEvent event) {
        getEscenario().close();
    }
    @FXML
    void buscarCliente(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}
