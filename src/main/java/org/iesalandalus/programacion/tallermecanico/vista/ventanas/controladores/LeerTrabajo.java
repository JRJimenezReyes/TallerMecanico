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

public class LeerTrabajo extends Controlador {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCerrar;

    @FXML
    private TextField tfDNI;

    @FXML
    private TextField tfFFin;

    @FXML
    private TextField tfFInicio;

    @FXML
    private TextField tfHoras;

    @FXML
    private TextField tfMatricula;

    @FXML
    private TextField tfPMaterial;

    @FXML
    private TextField tfTipo;

    @FXML
    private VBox vbLeerRevision;

    @FXML
    void insertar(ActionEvent event) {
        String dni = tfDNI.getText();
        String matricula = tfMatricula.getText();
        String fechaInicio = tfFInicio.getText();
        String fechaFin = tfFFin.getText();
        String precioMaterial = tfPMaterial.getText();
        String tipo = tfTipo.getText();
        String horas = tfHoras.getText();

        try {
            File archivo = new File("ficheros/trabajos.xml");
            String nuevaEntrada = String.format("    <trabajo cliente=\"%s\" fechaFin=\"%s\" fechaInicio=\"%s\" precioMaterial=\"%s\" horas=\"%s\" tipo=\"%s\" vehiculo=\"%s\"/>\n",dni, fechaFin, fechaInicio, precioMaterial, horas, tipo, matricula );

            StringBuilder contenido = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    if (linea.trim().equals("</trabajos>")) {
                        contenido.append(nuevaEntrada);
                    }
                    contenido.append(linea).append("\n");
                }
            }

            try (FileWriter writer = new FileWriter(archivo)) {
                writer.write(contenido.toString());
            }

            Stage escenario = (Stage) vbLeerRevision.getScene().getWindow();
            escenario.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cerrar(ActionEvent event) {
        Stage escenario = (Stage) vbLeerRevision.getScene().getWindow();
        if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro que no quieres añadir ningún trabajo?", escenario)) {
            escenario.close();
        }
    }
    @FXML
    void initialize() {
        assert btnAceptar != null : "fx:id=\"btnAceptar\" was not injected: check your FXML file 'leerTrabajo.fxml'.";
        assert btnCerrar != null : "fx:id=\"btnCerrar\" was not injected: check your FXML file 'leerTrabajo.fxml'.";
        assert tfDNI != null : "fx:id=\"tfDNI\" was not injected: check your FXML file 'leerTrabajo.fxml'.";
        assert tfFFin != null : "fx:id=\"tfFFin\" was not injected: check your FXML file 'leerTrabajo.fxml'.";
        assert tfFInicio != null : "fx:id=\"tfFInicio\" was not injected: check your FXML file 'leerTrabajo.fxml'.";
        assert tfHoras != null : "fx:id=\"tfHoras\" was not injected: check your FXML file 'leerTrabajo.fxml'.";
        assert tfMatricula != null : "fx:id=\"tfMatricula\" was not injected: check your FXML file 'leerTrabajo.fxml'.";
        assert tfPMaterial != null : "fx:id=\"tfPMaterial\" was not injected: check your FXML file 'leerTrabajo.fxml'.";
        assert tfTipo != null : "fx:id=\"tfTipo\" was not injected: check your FXML file 'leerTrabajo.fxml'.";
        assert vbLeerRevision != null : "fx:id=\"vbLeerRevision\" was not injected: check your FXML file 'leerTrabajo.fxml'.";

    }

}
