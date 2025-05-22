package org.iesalandalus.programacion.tallermecanico;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.FabricaModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.vista.FabricaVista;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

public class Main {
    public static void main(String[] args){
        FabricaModelo fabricaModelo = FabricaModelo.CASCADA;

        FabricaFuenteDatos fabricaFuenteDatos = FabricaFuenteDatos.MEMORIA;
        Controlador controlador = new Controlador(fabricaModelo,fabricaFuenteDatos,procesarArgumentosVista(args));
            controlador.comenzar();

    }

    private static FabricaVista procesarArgumentosVista(String[] args){
        FabricaVista fabricaVista = FabricaVista.VENTANA;
        for (String argumento : args){
            if (argumento.equalsIgnoreCase("-vventana")){
                fabricaVista = FabricaVista.VENTANA;
            }
            if (argumento.equalsIgnoreCase("-vtexto")){
                fabricaVista = FabricaVista.TEXTO;
            }

        }
        return fabricaVista;
    }
}
