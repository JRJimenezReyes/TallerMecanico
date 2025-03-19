package org.iesalandalus.programacion.tallermecanico;

import org.iesalandalus.programacion.tallermecanico.Vista.Vista;
import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

public class Main {
    public static void main(String[] args) throws TallerMecanicoExcepcion {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(modelo, vista);

        vista.setControlador(controlador);
        vista.comenzar();
    }
}
