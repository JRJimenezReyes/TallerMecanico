package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Revisiones {
    private List<Revision> revisiones;

    public Revisiones() {
        revisiones = new ArrayList<>();
    }

    public List<Revision> get() {
        return new ArrayList<>(revisiones);
    }

    public List<Revision> get(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("No se puede obtener revisiones de un cliente nulo.");
        }
        List<Revision> resultado = new ArrayList<>();
        for (Revision revision : revisiones) {
            if (revision.getCliente().equals(cliente)) {
                resultado.add(revision);
            }
        }
        return resultado;
    }

    public List<Revision> get(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new NullPointerException("No se puede obtener revisiones de un vehículo nulo.");
        }
        List<Revision> resultado = new ArrayList<>();
        for (Revision revision : revisiones) {
            if (revision.getVehiculo().equals(vehiculo)) {
                resultado.add(revision);
            }
        }
        return resultado;
    }

    public void insertar(Revision revision) throws TallerMecanicoExcepcion {
        if (revision == null) {
            throw new NullPointerException("No se puede insertar una revisión nula.");
        }


        for (Revision revision1 : revisiones) {
            if (revision1.getCliente().equals(revision.getCliente()) && !revision1.estaCerrada()) {
                throw new TallerMecanicoExcepcion("El cliente tiene otra revisión en curso.");
            }
            if (revision1.getVehiculo().equals(revision.getVehiculo()) && !revision1.estaCerrada()) {
                throw new TallerMecanicoExcepcion("El vehículo está actualmente en revisión.");
            }
            if (revision1.getCliente().equals(revision.getCliente()) && !revision1.estaCerrada()) {
                throw new TallerMecanicoExcepcion("El cliente tiene una revisión posterior.");
            }
        }
        revisiones.add(revision);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        if (revision == null) {
            throw new NullPointerException("No puedo operar sobre una revisión nula.");
        }
        if (!revisiones.contains(revision)) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        revision.cerrar(fechaFin);
    }

    public void anadirHoras(Revision revision, int horas) throws TallerMecanicoExcepcion {
        if (revision == null) {
            throw new NullPointerException("No puedo operar sobre una revisión nula.");
        }
        if (!revisiones.contains(revision)) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        revision.anadirHoras(horas);
    }

    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws TallerMecanicoExcepcion {
        if (revision == null) {
            throw new NullPointerException("No puedo operar sobre una revisión nula.");
        }
        if (!revisiones.contains(revision)) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        revision.anadirPrecioMaterial(precioMaterial);
    }

    public void borrar(Revision revision) throws TallerMecanicoExcepcion {
        if (revision == null) {
            throw new NullPointerException("No se puede borrar una revisión nula.");
        }
        if (!revisiones.contains(revision)) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        revisiones.remove(revision);
    }

    public Revision buscar(Revision revision) {
        if (revision == null) {
            throw new NullPointerException("No se puede buscar una revisión nula.");
        }
        for (Revision revision1 : revisiones) {
            if (revision1.equals(revision)) {
                return revision1;
            }
        }
        return null;
    }
}
