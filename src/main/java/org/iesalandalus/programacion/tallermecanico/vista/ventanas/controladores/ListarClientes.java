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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.vista.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.ventanas.utilidades.Dialogos;

public class ListarClientes extends Controlador {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> Lista;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnEliminarCliente;

    @FXML
    private VBox vbListarClientes;
    private ObservableList<String> clientes = FXCollections.observableArrayList();
    private final String XML_FILE = "ficheros/clientes.xml";

    public void cargarClientes() {
        clientes.clear();
        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(XML_FILE))) {
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.startsWith("<cliente")) {
                    String dni = extraerAtributo(linea, "dni");
                    String nombre = extraerAtributo(linea, "nombre");
                    String telefono = extraerAtributo(linea, "telefono");
                    clientes.add("Dni: " + dni + " | nombre: " + nombre + " | telefono: " + telefono);
                }
            }
            Lista.setItems(clientes);
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
    void eliminarCliente(ActionEvent event) {
        String seleccionado = Lista.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            Dialogos.mostrarDialogoError("Eliminar Cliente", "Selecciona un cliente para eliminar.", null);
            return;
        }

        String dni = "";
        int idx = seleccionado.indexOf("Dni: ");
        if (idx != -1) {
            int fin = seleccionado.indexOf(" |", idx);
            if (fin != -1) {
                dni = seleccionado.substring(idx + 5, fin).trim();
            } else {
                dni = seleccionado.substring(idx + 5).trim();
            }
        }
        if (dni.isEmpty()) {
            Dialogos.mostrarDialogoError("Eliminar Cliente", "No se pudo obtener el DNI.", null);
            return;
        }

        Stage escenario = (Stage) vbListarClientes.getScene().getWindow();
        if (!Dialogos.mostrarDialogoConfirmacion("Eliminar Cliente", "¿Seguro que quieres eliminar este cliente?", escenario)) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(XML_FILE))) {
            StringBuilder nuevoContenido = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().startsWith("<cliente") && linea.contains("dni=\"" + dni + "\"")) {
                    continue;
                }
                nuevoContenido.append(linea).append("\n");
            }
            java.nio.file.Files.write(java.nio.file.Paths.get(XML_FILE), nuevoContenido.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        cargarClientes();
    }


    @FXML
    void cerrar(ActionEvent event) {
        Stage escenario = (Stage) vbListarClientes.getScene().getWindow();
        if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro que no quieres continuar?", escenario)) {
            escenario.close();
        }
    }
    @FXML
    void initialize() {
        assert Lista != null : "fx:id=\"Lista\" was not injected: check your FXML file 'listarClientes.fxml'.";
        assert btnSalir != null : "fx:id=\"btnSalir\" was not injected: check your FXML file 'listarClientes.fxml'.";
        assert vbListarClientes != null : "fx:id=\"vbListarClientes\" was not injected: check your FXML file 'listarClientes.fxml'.";
        cargarClientes();
    }

}
