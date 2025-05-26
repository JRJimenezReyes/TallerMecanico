package org.iesalandalus.programacion.tallermecanico.vista.ventana.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.utilidades.Controladores;

public class ListarVehiculo extends Controlador {

    @FXML
    private ListView<?> lvListadoVehiculos;

    @FXML
    void accionSalir(ActionEvent event) {
        getEscenario().close();

    }
    @FXML
    void buscarVehiculo(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}
