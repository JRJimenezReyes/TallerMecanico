package org.iesalandalus.programacion.tallermecanico.vista.ventanas.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.vista.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.ventanas.utilidades.Dialogos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ListarVehiculos extends Controlador {

    private ObservableList<String> vehiculos = FXCollections.observableArrayList();
    private final String XML_FILE = "ficheros/vehiculos.xml";
    @FXML
    private ListView<String> Lista;

    @FXML
    private Button btnEliminarVehiculo;

    @FXML
    private Button btnSalir;
    @FXML
    private VBox vbListarVehiculos;

    public void cargarVehiculos() {
        vehiculos.clear();
        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(XML_FILE))) {
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.startsWith("<vehiculo")) {
                    String marca = extraerAtributo(linea, "marca");
                    String modelo = extraerAtributo(linea, "modelo");
                    String matricula = extraerAtributo(linea, "matricula");
                    vehiculos.add("Marca: " + marca + " | Modelo: " + modelo + " | Matrícula: " + matricula);
                }
            }
            Lista.setItems(vehiculos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String extraerAtributo(String linea, String atributo) {
        int inicio = linea.indexOf(atributo + "=\"");
        if (inicio == -1) return "";
        inicio += atributo.length() + 2;
        int fin = linea.indexOf("\"", inicio);
        return linea.substring(inicio, fin);
    }


    @FXML
    void eliminarVehiculo(ActionEvent event) {
        String seleccionado = Lista.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            Dialogos.mostrarDialogoError("Eliminar Vehículo", "Selecciona un vehículo para eliminar.", null);
            return;
        }

        String matricula = "";
        int idx = seleccionado.indexOf("Matrícula: ");
        if (idx != -1) {
            matricula = seleccionado.substring(idx + 11).trim();
        }
        if (matricula.isEmpty()) {
            Dialogos.mostrarDialogoError("Eliminar Vehículo", "No se pudo obtener la matrícula.", null);
            return;
        }


        Stage escenario = (Stage) vbListarVehiculos.getScene().getWindow();
        if (!Dialogos.mostrarDialogoConfirmacion("Eliminar Vehículo", "¿Seguro que quieres eliminar este vehículo?", escenario)) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(XML_FILE))) {
            StringBuilder nuevoContenido = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().startsWith("<vehiculo") && linea.contains("matricula=\"" + matricula + "\"")) {
                    continue;
                }
                nuevoContenido.append(linea).append("\n");
            }
            java.nio.file.Files.write(java.nio.file.Paths.get(XML_FILE), nuevoContenido.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        cargarVehiculos();
    }


    @FXML
    void cerrar(ActionEvent event) {
        Stage escenario = (Stage) vbListarVehiculos.getScene().getWindow();
        if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro que quieres salir?", escenario)) {
            escenario.close();
        }
    }


    @FXML
    void initialize() {
        assert Lista != null : "fx:id=\"Lista\" was not injected: check your FXML file 'listarVehiculos.fxml'.";
        cargarVehiculos();
    }
}
