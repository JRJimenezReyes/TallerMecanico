package org.iesalandalus.programacion.tallermecanico.vista.ventana.controladores;


import javafx.scene.image.Image;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.utilidades.Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.utilidades.Controladores;

public class VentanaPrincipal extends Controlador {


    @FXML
    void PincharAcercaDe(ActionEvent event) {
        AcercaDe acercaDe = (AcercaDe) Controladores.get("/vistas/AcercaDe.fxml","Acerca De...",getEscenario());
        acercaDe.getEscenario().show();
        acercaDe.centrar();


    }

    @FXML
    void PincharAyuda(ActionEvent event) {

    }

    @FXML
    void PincharEstadistica(ActionEvent event) {

    }

    @FXML
    void BBorTrabajos(ActionEvent event) {
        BorrarTrabajo borrarTrabajo = (BorrarTrabajo) Controladores.get("/vistas/BorrarTrabajo.fxml","Borrar Trabajo", getEscenario());
        borrarTrabajo.getEscenario().show();
        borrarTrabajo.centrar();

    }

    @FXML
    void bBorClientes(ActionEvent event) {
        BorrarCliente borrarCliente = (BorrarCliente) Controladores.get("/vistas/BorrarCliente.fxml", "Borrar Cliente", getEscenario());
        borrarCliente.getEscenario().show();
        borrarCliente.centrar();

    }



    @FXML
    void bBorVehiculos(ActionEvent event) {
        BorrarVehiculo borrarVehiculo = (BorrarVehiculo) Controladores.get("/vistas/BorrarVehiculo.fxml","Borrar Vehículo", getEscenario());
        borrarVehiculo.getEscenario().show();
        borrarVehiculo.centrar();
    }

    @FXML
    void bInsertClientes(ActionEvent event) {
        InsertarCliente insertarCliente = (InsertarCliente) Controladores.get("/vistas/InsertarCliente.fxml","Insertar Cliente",getEscenario());
        insertarCliente.getEscenario().show();
        insertarCliente.centrar();
    }

    @FXML
    void bInsertTrabajos(ActionEvent event) {
        InsertarTrabajo insertarTrabajo = (InsertarTrabajo) Controladores.get("/vistas/InsertarTrabajo.fxml","Insertar Trabajo", getEscenario());
        insertarTrabajo.getEscenario().show();
        insertarTrabajo.centrar();

    }

    @FXML
    void bInsertVehiculos(ActionEvent event) {
        InsertarVehiculo insertarVehiculo = (InsertarVehiculo) Controladores.get("/vistas/InsertarVehiculo.fxml","Insertar Vehiculo",getEscenario());
        insertarVehiculo.getEscenario().show();
        insertarVehiculo.centrar();

    }

    @FXML
    void bListClientes(ActionEvent event) {
        ListarCliente listarCliente = (ListarCliente) Controladores.get("/vistas/ListarCliente.fxml","Listar Clientes",getEscenario());
        listarCliente.getEscenario().show();
        listarCliente.centrar();
    }

    @FXML
    void bListTrabajos(ActionEvent event) {
        ListarTrabajo listarTrabajo = (ListarTrabajo) Controladores.get("/vistas/ListarTrabajo.fxml","Listar Trabajos", getEscenario());
        listarTrabajo.getEscenario().show();
        listarTrabajo.centrar();
    }

    @FXML
    void bListVehiculos(ActionEvent event) {
        ListarVehiculo listarVehiculo = (ListarVehiculo) Controladores.get("/vistas/ListarVehiculo.fxml","Listar Vehículos",getEscenario());
        listarVehiculo.getEscenario().show();
        listarVehiculo.centrar();
    }

    @FXML
    void initialize() {
    }
}
