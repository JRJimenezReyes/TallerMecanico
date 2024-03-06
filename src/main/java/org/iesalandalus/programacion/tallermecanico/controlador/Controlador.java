package org.iesalandalus.programacion.tallermecanico.controlador;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;

import java.time.LocalDate;
import java.util.List;

public class Controlador {

    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        if (vista == null) {
            throw new IllegalArgumentException("La vista no puede ser nula");
        }
        if (modelo == null) {
            throw new IllegalArgumentException("El modelo no puede ser nulo");
        }
        this.vista = vista;
        this.modelo = modelo;

        vista.setControlador(this);
    }

    public void comenzar() {
        modelo.comenzar();
        vista.comenzar();
    }

    public void terminar() {
        modelo.terminar();
        vista.terminar();
    }

    public void crearRevision(String matricula, int km, LocalDate fecha, double precio) {
        modelo.crearRevision(matricula, km, fecha, precio);
    }

    public List<Revision> listarRevisiones() {
        return modelo.listarRevisiones();
    }

    public Revision buscarRevision(String matricula) {
        return modelo.buscarRevision(matricula);
    }

    public void borrarRevision(String matricula) {
        modelo.borrarRevision(matricula);
    }

    public void modificarRevision(Revision revision) {
        modelo.modificarRevision(revision);
    }

    public void cerrarRevision(String matricula) {
        modelo.cerrarRevision(matricula);
    }
}

