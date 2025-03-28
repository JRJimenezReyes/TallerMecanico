package org.iesalandalus.programacion.tallermecanico.controlador;

import org.iesalandalus.programacion.tallermecanico.modelo.ModeloCascada;

import org.iesalandalus.programacion.tallermecanico.vista.texto.VistaTexto;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;



import java.util.Objects;

public class Controlador implements IControlador {
    private ModeloCascada modelo;
    private VistaTexto vistaTexto;
    public Controlador(ModeloCascada modeloCascada, VistaTexto vistaTexto){
        Objects.requireNonNull(modeloCascada,"El modelo no puede ser nulo.");
        Objects.requireNonNull(vistaTexto,"La vista no puede ser nula.");
        this.vistaTexto = vistaTexto;
        this.modelo = modeloCascada;
        this.vistaTexto.setControlador(this);
    }

    @Override
    public void comenzar() throws TallerMecanicoExcepcion {
        modelo.comenzar();
        vistaTexto.comenzar();
    }

    @Override
    public void actualizar(Evento evento){

    }

    @Override
    public void terminar(){
        vistaTexto.terminar();
        modelo.terminar();
    }


}
