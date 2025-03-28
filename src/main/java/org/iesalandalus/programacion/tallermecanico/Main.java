package org.iesalandalus.programacion.tallermecanico;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.vista.texto.VistaTexto;
import org.iesalandalus.programacion.tallermecanico.modelo.ModeloCascada;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

public class Main {
    public static void main(String[] args) throws TallerMecanicoExcepcion {
        VistaTexto vistaTexto = new VistaTexto();
        ModeloCascada modeloCascada = new ModeloCascada(FabricaFuenteDatos.MEMORIA);
        Controlador controlador = new Controlador(modeloCascada, vistaTexto);

        vistaTexto.setControlador(controlador);
        vistaTexto.comenzar();
    }
}
