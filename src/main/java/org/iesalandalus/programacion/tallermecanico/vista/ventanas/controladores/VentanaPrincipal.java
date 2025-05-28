package org.iesalandalus.programacion.tallermecanico.vista.ventanas.controladores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.vista.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.ventanas.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.vista.ventanas.utilidades.Dialogos;

public class VentanaPrincipal extends Controlador {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAnadirClientes;

    @FXML
    private Button btnAnadirRevision;

    @FXML
    private Button btnAnadirVehiculo;

    @FXML
    private Button btnBuscarClientes;

    @FXML
    private MenuItem cerrar;

    @FXML
    private VBox vbVp;

    @FXML
    private Button btnListarVehiculos;

    @FXML
    private Button btnBuscarTrabajos;

    @FXML
    private Button btnListarClientes;

    @FXML
    private Button btnListarTrabajos;
    @FXML
    private Button btnTrabajos;

    @FXML
    private MenuItem miAcercaDe;


    @FXML
    void acercaDe(ActionEvent event) {
        AcercaDe acercaDe = (AcercaDe) Controladores.get("/vistas/AcercaDe.fxml", "Acerca De", null);
        acercaDe.getEscenario().show();
        acercaDe.centrar();
    }

    @FXML
    void anadirClientes(ActionEvent event) {
        LeerCliente leerCliente = (LeerCliente) Controladores.get("/vistas/leerCliente.fxml", "Añadir Cliente", null);
        leerCliente.getEscenario().show();
        leerCliente.centrar();
    }

    @FXML
    void anadirRevision(ActionEvent event) {
        LeerTrabajo leerTrabajo = (LeerTrabajo) Controladores.get("/vistas/leerTrabajo.fxml", "Añadir Revision", null);
        leerTrabajo.getEscenario().show();
        leerTrabajo.centrar();
    }

    @FXML
    void anadirVehiculo(ActionEvent event) {
        LeerVehiculo leerVehiculo = (LeerVehiculo) Controladores.get("/vistas/leerVehiculo.fxml", "Añadir Vehículo", null);
        leerVehiculo.getEscenario().show();
        leerVehiculo.centrar();
    }

    @FXML
    void cerrarVentana(ActionEvent event) {
        Stage escenario = (Stage) vbVp.getScene().getWindow();
        if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro de que quieres salir?", escenario)) {
            escenario.close();
        }
    }

    @FXML
    void listarVehiculos(ActionEvent event) {
        ListarVehiculos listarVehiculos = (ListarVehiculos) Controladores.get("/vistas/listarVehiculos.fxml", "Lista de Vehículos", null);
        listarVehiculos.getEscenario().show();
        listarVehiculos.centrar();
    }

    @FXML
    void buscarVehiculos(ActionEvent event){
        BuscarVehiculos buscarVehiculos = (BuscarVehiculos) Controladores.get("/vistas/buscarVehiculos.fxml", "Buscar Vehículos", null);
        buscarVehiculos.getEscenario().show();
        buscarVehiculos.centrar();
    }


    @FXML
    void listarClientes(ActionEvent event) {
        ListarClientes listarClientes = (ListarClientes) Controladores.get("/vistas/listarClientes.fxml", "Lista Cliente", null);
        listarClientes.getEscenario().show();
        listarClientes.centrar();
    }


    @FXML
    void buscarClientes(ActionEvent event) {
        BuscarClientes buscarClientes = (BuscarClientes) Controladores.get("/vistas/buscarClientes.fxml", "Buscar Clientes", null);
        buscarClientes.getEscenario().show();
        buscarClientes.centrar();
    }


    @FXML
    void listarTrabajos(ActionEvent event) {
        ListarTrabajos listarTrabajos = (ListarTrabajos) Controladores.get("/vistas/listarTrabajos.fxml", "Lista de Revisiones", null);
        listarTrabajos.getEscenario().show();
        listarTrabajos.centrar();
    }

    @FXML
    void buscarTrabajos(ActionEvent event) {
        BuscarTrabajos buscarTrabajos = (BuscarTrabajos) Controladores.get("/vistas/buscarTrabajos.fxml", "Buscar Trabajos", null);
        buscarTrabajos.getEscenario().show();
        buscarTrabajos.centrar();
    }

    @FXML
    void initialize() {

    }

}
