package org.iesalandalus.programacion.tallermecanico.vista.ventana.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.VistaGrafica;
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
        VistaGrafica.getInstance().getGestorEventos().notificarEvento(Evento.INSERTAR_CLIENTE);
        getEscenario().close();
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

    public Cliente getCliente(){
        return new Cliente(tfNombre.getText(),tfDNI.getText(),tfNumeroTelefono.getText());
    }

    @FXML
    void initialize() {

    }

}
