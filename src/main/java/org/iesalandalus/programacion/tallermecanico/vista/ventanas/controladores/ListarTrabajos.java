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

public class ListarTrabajos extends Controlador {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> Lista;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnEliminarTrabajos;

    @FXML
    private VBox vbListarTrabajos;
    private ObservableList<String> trabajos = FXCollections.observableArrayList();
    private final String XML_FILE = "ficheros/trabajos.xml";

    public void cargarTrabajos() {
        trabajos.clear();
        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(XML_FILE))) {
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.startsWith("<trabajo")) {
                    String cliente = extraerAtributo(linea, "cliente");
                    String fechaFin = extraerAtributo(linea, "fechaFin");
                    String fechaInicio = extraerAtributo(linea, "fechaInicio");
                    String precioMaterial = extraerAtributo(linea, "precioMaterial");
                    String horas = extraerAtributo(linea, "horas");
                    String tipo = extraerAtributo(linea, "tipo");
                    String vehiculo = extraerAtributo(linea, "vehiculo");
                    trabajos.add("Cliente: " + cliente + " | Fecha Fin: " + fechaFin + " | Fecha Inicio: " + fechaInicio + " | Precio Material: " + precioMaterial + " | Horas: " + horas + " | Tipo: " + tipo + " | Vehiculo: " + vehiculo);
                }
            }
            Lista.setItems(trabajos);
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
    void eliminarTrabajos(ActionEvent event) {
        String seleccionado = Lista.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            Dialogos.mostrarDialogoError("Eliminar Trabajo", "Selecciona un trabajo para eliminar.", null);
            return;
        }

        String cliente = "";
        String vehiculo = "";
        int idxCliente = seleccionado.indexOf("Cliente: ");
        int idxVehiculo = seleccionado.indexOf("Vehiculo: ");
        if (idxCliente != -1) {
            int fin = seleccionado.indexOf(" |", idxCliente);
            if (fin != -1) {
                cliente = seleccionado.substring(idxCliente + 9, fin).trim();
            }
        }
        if (idxVehiculo != -1) {
            vehiculo = seleccionado.substring(idxVehiculo + 10).trim();
        }
        if (cliente.isEmpty() || vehiculo.isEmpty()) {
            Dialogos.mostrarDialogoError("Eliminar Trabajo", "No se pudo obtener los datos del trabajo.", null);
            return;
        }

        Stage escenario = (Stage) vbListarTrabajos.getScene().getWindow();
        if (!Dialogos.mostrarDialogoConfirmacion("Eliminar Trabajo", "¿Seguro que quieres eliminar este trabajo?", escenario)) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(XML_FILE))) {
            StringBuilder nuevoContenido = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().startsWith("<trabajo") &&
                        linea.contains("cliente=\"" + cliente + "\"") &&
                        linea.contains("vehiculo=\"" + vehiculo + "\"")) {
                    continue;
                }
                nuevoContenido.append(linea).append("\n");
            }
            java.nio.file.Files.write(java.nio.file.Paths.get(XML_FILE), nuevoContenido.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        cargarTrabajos();
    }

    @FXML
    void cerrar(ActionEvent event) {
        Stage escenario = (Stage) vbListarTrabajos.getScene().getWindow();
        if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro que no quieres seguir viendo los trabajos?", escenario)) {
            escenario.close();
        }
    }

    @FXML
    void initialize() {
        assert Lista != null : "fx:id=\"Lista\" was not injected: check your FXML file 'listarTrabajos.fxml'.";
        assert btnSalir != null : "fx:id=\"btnSalir\" was not injected: check your FXML file 'listarTrabajos.fxml'.";
        assert vbListarTrabajos != null : "fx:id=\"vbListarTrabajos\" was not injected: check your FXML file 'listarTrabajos.fxml'.";
        cargarTrabajos();

    }

}
