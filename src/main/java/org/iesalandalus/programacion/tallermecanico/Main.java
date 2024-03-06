package org.iesalandalus.programacion.tallermecanico;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;

public class Main {

    public static void main(String[] args) {
        Vista vista = new VistaImp();
        Modelo modelo = new Modelo(vista);
        Controlador controlador = new Controlador(vista, modelo);

        controlador.comenzar();
    }
}
