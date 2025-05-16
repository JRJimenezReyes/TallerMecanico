package org.iesalandalus.programacion.tallermecanico.vista.ventana.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.utilidades.Controlador;

public class InsertarCliente extends Controlador {



    @FXML
    private TextField tfDNI;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfNumeroTelefono;

    @FXML
    void AccionAceptar(ActionEvent event) {

    }

    @FXML
    void AccionCancelar(ActionEvent event) {
        getEscenario().close();
    }

    @FXML
    void bLimpiar(ActionEvent event) {
        tfDNI.clear();
        tfNombre.clear();
        tfNumeroTelefono.clear();
    }

    @FXML
    void initialize() {

    }

}
