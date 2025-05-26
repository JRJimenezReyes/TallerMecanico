package org.iesalandalus.programacion.tallermecanico.vista.ventana.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.VistaGrafica;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.utilidades.Controlador;

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
    void accionAceptar(ActionEvent event) {
        VistaGrafica.getInstance().getGestorEventos().notificarEvento(Evento.INSERTAR_VEHICULO);
        getEscenario().close();

    }

    @FXML
    void bLimpiar(ActionEvent event) {
        tfMarca.clear();
        tfMatricula.clear();
        tfModelo.clear();
    }


    @FXML
    void accionCancelar(ActionEvent event) {
       getEscenario().close();
    }

    @FXML
    void initialize() {

    }

    public Vehiculo getVehiculo(){
        return new Vehiculo(tfMarca.getText(),tfModelo.getText(),tfMatricula.getText());
    }

}
