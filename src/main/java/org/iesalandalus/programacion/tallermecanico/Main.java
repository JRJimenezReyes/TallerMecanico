package org.iesalandalus.programacion.tallermecanico;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.cascada.IModeloCascada;
import org.iesalandalus.programacion.tallermecanico.vista.texto.VistaTexto;

public class Main {
    public static void main(String[] args) {
        IModeloCascada modeloCascada = new IModeloCascada();
        VistaTexto vistaTexto = new VistaTexto();
        Controlador controlador = new Controlador(modeloCascada, vistaTexto);
        controlador.comenzar();
    }
}
