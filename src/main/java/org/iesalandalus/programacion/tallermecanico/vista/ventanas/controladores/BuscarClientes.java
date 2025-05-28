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

public class BuscarClientes extends Controlador {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Label lbDni;

    @FXML
    private TextField tfdni;

    @FXML
    private VBox vbBuscarCliente;

    @FXML
    void buscarCliente(ActionEvent event) {
        String dniBuscado = tfdni.getText().trim();

        if (dniBuscado.isEmpty()) {
            lbDni.setText("Introduce una dni.");
            return;
        }

        String rutaXML = "ficheros/clientes.xml";
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaXML))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.startsWith("<cliente")) {
                    String dni = extraerAtributo(linea, "dni");
                    if (dni.equalsIgnoreCase(dniBuscado)) {
                        String nombre = extraerAtributo(linea, "nombre");
                        String telefono = extraerAtributo(linea, "telefono");

                        lbDni.setText("DNI: " + dni + " | Nombre: " + nombre + " | Telefono: " + telefono);
                        encontrado = true;
                        break;
                    }
                }
            }

            if (!encontrado) {
                lbDni.setText("No se encontró ningún cliente con ese DNI.");
            }

        } catch (Exception e) {
            lbDni.setText("Error al leer el archivo.");
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
        Stage escenario = (Stage) vbBuscarCliente.getScene().getWindow();
        if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro que no quieres buscar ningún cliente?", escenario)) {
            escenario.close();
        }
    }

    @FXML
    void initialize() {
        assert btnBuscar != null : "fx:id=\"btnBuscar\" was not injected: check your FXML file 'buscarClientes.fxml'.";
        assert btnCancelar != null : "fx:id=\"btnCancelar\" was not injected: check your FXML file 'buscarClientes.fxml'.";
        assert lbDni != null : "fx:id=\"lbDni\" was not injected: check your FXML file 'buscarClientes.fxml'.";
        assert tfdni != null : "fx:id=\"tfdni\" was not injected: check your FXML file 'buscarClientes.fxml'.";
        assert vbBuscarCliente != null : "fx:id=\"vbBuscarCliente\" was not injected: check your FXML file 'buscarClientes.fxml'.";

    }

}
