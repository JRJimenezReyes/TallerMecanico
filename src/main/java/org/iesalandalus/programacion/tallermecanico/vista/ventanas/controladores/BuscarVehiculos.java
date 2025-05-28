package org.iesalandalus.programacion.tallermecanico.vista.ventanas.controladores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.iesalandalus.programacion.tallermecanico.vista.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.ventanas.utilidades.Dialogos;

public class BuscarVehiculos extends Controlador {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField tfMatricula; // Campo para introducir la matrícula

    @FXML
    private Label lbMatricula; // Campo donde se mostrará el resultado

    @FXML
    private VBox vbBuscarVehiculo;

    @FXML
    void buscarVehiculo(ActionEvent event) {
        String matriculaBuscada = tfMatricula.getText().trim();

        if (matriculaBuscada.isEmpty()) {
            lbMatricula.setText("Introduce una matrícula.");
            return;
        }

        String rutaXML = "ficheros/vehiculos.xml";
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaXML))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.startsWith("<vehiculo")) {
                    String matricula = extraerAtributo(linea, "matricula");
                    if (matricula.equalsIgnoreCase(matriculaBuscada)) {
                        String marca = extraerAtributo(linea, "marca");
                        String modelo = extraerAtributo(linea, "modelo");

                        lbMatricula.setText("Marca: " + marca + " | Modelo: " + modelo + " | Matrícula: " + matricula);
                        encontrado = true;
                        break;
                    }
                }
            }

            if (!encontrado) {
                lbMatricula.setText("No se encontró ningún vehículo con esa matrícula.");
            }

        } catch (Exception e) {
            lbMatricula.setText("Error al leer el archivo.");
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
    void cerrar(ActionEvent event) {
        Stage escenario = (Stage) vbBuscarVehiculo.getScene().getWindow();
        if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro que no quieres añadir ningún vehículo?", escenario)) {
            escenario.close();
        }
    }

    @FXML
    void initialize() {
        assert btnBuscar != null : "fx:id=\"btnBuscar\" was not injected: check your FXML file 'buscarVehiculos.fxml'.";
        assert btnCancelar != null : "fx:id=\"btnCancelar\" was not injected: check your FXML file 'buscarVehiculos.fxml'.";
        assert tfMatricula != null : "fx:id=\"tfMatricula\" was not injected: check your FXML file 'buscarVehiculos.fxml'.";
        assert lbMatricula != null : "fx:id=\"lbMatricula\" was not injected: check your FXML file 'buscarVehiculos.fxml'.";
        assert vbBuscarVehiculo != null : "fx:id=\"vbBuscarVehiculo\" was not injected: check your FXML file 'buscarVehiculos.fxml'.";
    }
}
