package org.iesalandalus.programacion.tallermecanico.vista.ventanas.controladores;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.vista.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.ventanas.utilidades.Dialogos;



public class LeerVehiculo extends Controlador {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCerrar;

    @FXML
    private TextField tfMarca;

    @FXML
    private TextField tfMatricula;

    @FXML
    private TextField tfModelo;

    @FXML
    private VBox vbLeerVehiculo;




    @FXML
    void cerrar(ActionEvent event) {
        Stage escenario = (Stage) vbLeerVehiculo.getScene().getWindow();
        if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro que no quieres añadir ningún vehículo?", escenario)) {
            escenario.close();
        }
    }

    @FXML
    void insertar(ActionEvent event) {
        String marca = tfMarca.getText();
        String modelo = tfModelo.getText();
        String matricula = tfMatricula.getText();

        try {
            File archivo = new File("ficheros/vehiculos.xml");
            String nuevaEntrada = String.format("    <vehiculo marca=\"%s\" matricula=\"%s\" modelo=\"%s\"/>\n", marca, matricula, modelo);

            // Leer contenido actual
            StringBuilder contenido = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    if (linea.trim().equals("</vehiculos>")) {
                        contenido.append(nuevaEntrada); // Añadir el nuevo <vehiculo> antes del cierre
                    }
                    contenido.append(linea).append("\n");
                }
            }

            // Sobrescribir el archivo con la nueva versión
            try (FileWriter writer = new FileWriter(archivo)) {
                writer.write(contenido.toString());
            }

            Stage escenario = (Stage) vbLeerVehiculo.getScene().getWindow();
            escenario.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert btnAceptar != null : "fx:id=\"btnAceptar\" was not injected: check your FXML file 'leerVehiculo.fxml'.";
        assert btnCerrar != null : "fx:id=\"btnCerrar\" was not injected: check your FXML file 'leerVehiculo.fxml'.";
        assert tfMarca != null : "fx:id=\"tfMarca\" was not injected: check your FXML file 'leerVehiculo.fxml'.";
        assert tfMatricula != null : "fx:id=\"tfMatricula\" was not injected: check your FXML file 'leerVehiculo.fxml'.";
        assert tfModelo != null : "fx:id=\"tfModelo\" was not injected: check your FXML file 'leerVehiculo.fxml'.";
        assert vbLeerVehiculo != null : "fx:id=\"vbLeerVehiculo\" was not injected: check your FXML file 'leerVehiculo.fxml'.";

    }

}
