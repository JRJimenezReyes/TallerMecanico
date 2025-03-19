package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Revisiones {
    ArrayList<Revision> coleccionRevisiones;

    public Revisiones() {
        coleccionRevisiones = new ArrayList<>();
    }

    public ArrayList<Revision> get() {
        return new ArrayList<>(coleccionRevisiones);
    }

    public ArrayList<Revision> get(Cliente cliente) {
        ArrayList<Revision> revisionesCliente = new ArrayList<>();
        for(Revision revision : coleccionRevisiones) {
            if (revision.getCliente().equals(cliente)) {
                revisionesCliente.add(revision);
            }
        }
        return revisionesCliente;
    }

    public ArrayList<Revision> get(Vehiculo vehiculo) {
        ArrayList<Revision> revisionesVehiculo = new ArrayList<>();
        for(Revision revision : coleccionRevisiones) {
            if (revision.getVehiculo().equals(vehiculo)) {
                revisionesVehiculo.add(revision);
            }
        }
        return revisionesVehiculo;
    }

    public void insertar(Revision revision) {
        Objects.requireNonNull(revision, "La revisión no puede ser nula.");
        for (Revision revision1 : coleccionRevisiones) {
            if (revision1.equals(revision)) {
                coleccionRevisiones.add(revision);
            }
        }
    }

    public Revision cerrar(Revision revision, LocalDate fechaFin) {
        Objects.requireNonNull(revision, "La revisión no puede ser nula");
        Objects.requireNonNull(fechaFin, "La fecha de finalización no puede ser nula");
        if (revision.estaCerrada() || fechaFin != null) {
            for (Revision revision1 : coleccionRevisiones) {
                if (revision1.equals(revision)) {
                    revision.cerrar(fechaFin);
                }
            }
        } else {
            throw new IllegalArgumentException("La revisión no se puede cerrar.");
        }
        return revision;
    }

    public Revision buscar(Revision revision) {
        
    }

    public void borrar(Revision revision) {

    }

    //Métodos privados:
    private void comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) {

    }

    private Revision getRevision(Revision revision {
    }


    //Métodos públicos adicionales con parámetros:
    public Revision anadirHoras(Revision revision, int horas) {
    }

    public Revision anadirPrecioMaterial(Revision revision, float precioMaterial) {
    }
}
