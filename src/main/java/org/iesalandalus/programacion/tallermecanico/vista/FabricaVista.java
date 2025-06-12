package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.vista.texto.VistaTexto;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.VistaGrafica;

public enum FabricaVista {
    TEXTO {@Override public Vista crear() {return new VistaTexto();}
    },
    VENTANA{
        @Override
        public Vista crear() {
            return VistaGrafica.getInstance();
        }
    };





    public abstract Vista crear();
}
