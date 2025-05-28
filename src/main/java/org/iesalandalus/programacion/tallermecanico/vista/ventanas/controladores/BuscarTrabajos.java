package org.iesalandalus.programacion.tallermecanico.vista.ventanas.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.vista.ventanas.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.ventanas.utilidades.Dialogos;

import java.io.BufferedReader;
import java.io.FileReader;

public class BuscarTrabajos extends Controlador {

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Label lbDni;

    @FXML
    private TextField tfdni;

    @FXML
    private VBox vbBuscarTrabajos;


    @FXML
    void buscarTrabajos(ActionEvent event) {
        String dniBuscado = tfdni.getText().trim();

        if (dniBuscado.isEmpty()) {
            lbDni.setText("Introduce un dni.");
            return;
        }

        String rutaXML = "ficheros/trabajos.xml";
        StringBuilder resultados = new StringBuilder();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaXML))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.startsWith("<trabajo")) {
                    String cliente = extraerAtributo(linea, "cliente");
                    if (cliente.equalsIgnoreCase(dniBuscado)) {
                        String fechaFin = extraerAtributo(linea, "fechaFin");
                        String fechaInicio = extraerAtributo(linea, "fechaInicio");
                        String precioMaterial = extraerAtributo(linea, "precioMaterial");
                        String horas = extraerAtributo(linea, "horas");
                        String tipo = extraerAtributo(linea, "tipo");
                        String vehiculo = extraerAtributo(linea, "vehiculo");
                        resultados.append("Cliente: ").append(cliente)
                                .append(" | Fecha Fin: ").append(fechaFin)
                                .append(" | Fecha Inicio: ").append(fechaInicio)
                                .append(" | Precio Material: ").append(precioMaterial)
                                .append(" | Horas: ").append(horas)
                                .append(" | Tipo: ").append(tipo)
                                .append(" | Vehiculo: ").append(vehiculo)
                                .append("\n");
                        encontrado = true;
                    }
                }
            }

            if (encontrado) {
                lbDni.setText(resultados.toString());
            } else {
                lbDni.setText("No se encontró ningún trabajo de ese cliente.");
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
        Stage escenario = (Stage) vbBuscarTrabajos.getScene().getWindow();
        if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro que no quieres buscar ningún cliente?", escenario)) {
            escenario.close();
        }
    }

}
