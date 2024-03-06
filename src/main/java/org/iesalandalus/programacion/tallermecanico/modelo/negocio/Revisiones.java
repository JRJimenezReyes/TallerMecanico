package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

public class Revisiones {

    private List<Revision> revisiones;

    public Revisiones() {
        this.revisiones = new ArrayList<>();
    }

    public Revisiones(List<Revision> revisiones) {
        this.revisiones = new ArrayList<>(revisiones);
    }

    public int getNumeroRevisiones() {
        return revisiones.size();
    }

    public List<Revision> get() {
        return new ArrayList<>(revisiones);
    }

    public List<Revision> get(Cliente cliente) {
        return revisiones.stream().filter(r -> r.getCliente().equals(cliente)).collect(Collectors.toList());
    }

    public List<Revision> get(Vehiculo vehiculo) {
        return revisiones.stream().filter(r -> r.getVehiculo().equals(vehiculo)).collect(Collectors.toList());
    }

    public void insertar(Revision revision) throws IllegalArgumentException {
        Objects.requireNonNull(revision, "La revisión no puede ser nula");
        if (revisiones.contains(revision)) {
            throw new IllegalArgumentException("La revisión ya existe en la lista");
        }
        if (!comprobarRevision(revision)) {
            throw new IllegalArgumentException("No se puede crear la revisión: fechas o cliente/vehículo ya en revisión");
        }
        revisiones.add(revision);
    }

    private boolean comprobarRevision(Revision revision) {
        for (Revision r : revisiones) {
            if ((r.getCliente() != null && r.getCliente().equals(revision.getCliente())) ||
                    (r.getVehiculo() != null && r.getVehiculo().equals(revision.getVehiculo()))) {
                if (r.getFechaFin() == null || r.getFechaFin().isAfter(revision.getFechaInicio())) {
                    return false;
                }
            }
        }
        return true;
    }

    private Revision getRevision(Revision revision) throws IllegalArgumentException {
        Objects.requireNonNull(revision, "La revisión no puede ser nula");
        if (!revisiones.contains(revision)) {
            throw new IllegalArgumentException("La revisión no se encuentra en la lista");
        }
        return revisiones.get(revisiones.indexOf(revision));
    }

    public void anadirHoras(Revision revision, int horas) throws IllegalArgumentException {
        Revision revisionEncontrada = getRevision(revision);
        revisionEncontrada.setHoras(revisionEncontrada.getHoras() + horas);
    }

    public void anadirPrecioMaterial(Revision revision, float precio) throws IllegalArgumentException {
        Revision revisionEncontrada = getRevision(revision);
        revisionEncontrada.setPrecioMaterial(revisionEncontrada.getPrecioMaterial() + precio);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) throws IllegalArgumentException {
        Revision revisionEncontrada = getRevision(revision);
        if (fechaFin.isBefore(revisionEncontrada.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
        revisionEncontrada.setFechaFin(fechaFin);
    }

    public Revision buscar(Revision revision) {
        for (Revision r : revisiones) {
            if (r.equals(revision)) {
                return r;
            }
        }
        return null;
    }

    public void borrar(Revision revision) throws IllegalArgumentException {
        if (!revisiones.remove(revision)) {
            throw new IllegalArgumentException("La revisión no se encuentra en la lista");
        }
    }

}
