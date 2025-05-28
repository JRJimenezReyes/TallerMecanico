package org.iesalandalus.programacion.tallermecanico.vista.ventanas.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.vista.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.ventanas.utilidades.Dialogos;

import java.io.*;

public class LeerCliente extends Controlador {

    @FXML
    private Button btnCerrar;

    @FXML
    private Button btnInsertar;

    @FXML
    private TextField tfDni;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfTelefono;

    @FXML
    private VBox vbLeerCliente;

    @FXML
    void cerrar(ActionEvent event) {
        Stage escenario = (Stage) vbLeerCliente.getScene().getWindow();
        if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro que no quieres añadir ningún vehículo?", escenario)) {
            escenario.close();
        }
    }

    @FXML
    void insertar(ActionEvent event) {
        String dni = tfDni.getText();
        String nombre = tfNombre.getText();
        String telefono = tfTelefono.getText();

        try {
            File archivo = new File("ficheros/clientes.xml");
            String nuevaEntrada = String.format("    <cliente dni=\"%s\" nombre=\"%s\" telefono=\"%s\"/>\n", dni, nombre, telefono);

            StringBuilder contenido = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    if (linea.trim().equals("</clientes>")) {
                        contenido.append(nuevaEntrada);
                    }
                    contenido.append(linea).append("\n");
                }
            }

            try (FileWriter writer = new FileWriter(archivo)) {
                writer.write(contenido.toString());
            }

            Stage escenario = (Stage) vbLeerCliente.getScene().getWindow();
            escenario.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
