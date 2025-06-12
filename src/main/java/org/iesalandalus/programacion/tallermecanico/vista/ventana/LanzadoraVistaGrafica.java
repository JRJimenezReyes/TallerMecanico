package org.iesalandalus.programacion.tallermecanico.vista.ventana;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.controladores.VentanaPrincipal;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.utilidades.Dialogos;

import static javafx.application.Application.launch;

public class LanzadoraVistaGrafica extends Application {
    public static void comenzar(){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        VistaGrafica.getInstance().inicializar();
        VentanaPrincipal ventanaPrincipal = (VentanaPrincipal) Controladores.get("/vistas/VentanaPrincipal.fxml","Taller Mecánico", null);
        ventanaPrincipal.getEscenario().show();
        ventanaPrincipal.getEscenario().setOnCloseRequest(this::salir);
        ventanaPrincipal.centrar();
    }

    public void salir(WindowEvent e){
        Controlador ventanaPrincipal = Controladores.get("/vistas/VentanaPrincipal.fxml","Taller Mecánico", null);
        if (Dialogos.mostrarDialogoConfirmacion("Salir","¿Seguro que quieres salir?",ventanaPrincipal.getEscenario())){
            VistaGrafica.getInstance().getGestorEventos().notificarEvento(Evento.SALIR);
            ventanaPrincipal.getEscenario().close();
        }else {
            e.consume();
        }
    }
}
