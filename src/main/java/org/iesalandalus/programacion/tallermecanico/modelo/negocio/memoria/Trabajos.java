package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Trabajos implements ITrabajos {

    List<Trabajo> coleccionTrabajos;

    public Trabajos() {
        coleccionTrabajos = new ArrayList<>();
    }

    @Override
    public List<Trabajo> get() {

        return new ArrayList<>(coleccionTrabajos);
    }

    @Override
    public List<Trabajo> get(Cliente cliente) {
        List<Trabajo> trabajosCliente = new ArrayList<>();

        for (Trabajo trabajo1 : coleccionTrabajos) {
            if (trabajo1.getCliente().getDni().equals(cliente.getDni())) {
                trabajosCliente.add(trabajo1);
            }
        }
        return trabajosCliente;

    }

    @Override
    public List<Trabajo> get(Vehiculo vehiculo) {
        List<Trabajo> trabajosVehiculo = new ArrayList<>();
        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getVehiculo().matricula().equals(vehiculo.matricula())) {
                trabajosVehiculo.add(trabajo);
            }
        }
        return trabajosVehiculo;
    }

    @Override
    public void insertar(Trabajo trabajo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "No se puede insertar un trabajo nulo.");


        comprobarTrabajo(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio());

        coleccionTrabajos.add(trabajo);
    }

    private void comprobarTrabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        Objects.requireNonNull(fechaRevision, "La fecha de revisión no puede ser nula.");

        for (Trabajo trabajoExistente : coleccionTrabajos) {

            if (!trabajoExistente.estaCerrado()) {
                if (trabajoExistente.getCliente().equals(cliente)) {
                    throw new TallerMecanicoExcepcion("El cliente tiene otro trabajo en curso.");
                }
                if (trabajoExistente.getVehiculo().equals(vehiculo)) {
                    throw new TallerMecanicoExcepcion("El vehículo está actualmente en el taller.");
                }
            }

            if (trabajoExistente.estaCerrado()) {
                if (trabajoExistente.getCliente().equals(cliente) && !fechaRevision.isAfter(trabajoExistente.getFechaFin())) {
                    throw new TallerMecanicoExcepcion("El cliente tiene otro trabajo posterior.");
                }
                if (trabajoExistente.getVehiculo().equals(vehiculo) && !fechaRevision.isAfter(trabajoExistente.getFechaFin())) {
                    throw new TallerMecanicoExcepcion("El vehículo tiene otro trabajo posterior.");
                }
            }
        }

    }

    private Trabajo getTrabajo(Trabajo trabajo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "La revisión no puede ser nula.");


        for (Trabajo trabajo1 : coleccionTrabajos) {
            if (trabajo1.equals(trabajo)) {
                return trabajo;
            }

        }
        throw new TallerMecanicoExcepcion("No existe ningún trabajo igual.");
    }

    @Override
    public Trabajo anadirHoras(Trabajo trabajo, int horas) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "No puedo añadir horas a un trabajo nulo.");
        Trabajo trabajo1;
        trabajo1 = getTrabajoAbierto(trabajo.getVehiculo());
        if (trabajo1 instanceof Revision revision){
            revision.anadirHoras(horas);

        } else if (trabajo1 instanceof Mecanico mecanico){
            mecanico.anadirHoras(horas);
        }


        return trabajo1;
    }

    @Override
    public Trabajo anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws TallerMecanicoExcepcion {
        Trabajo trabajo1;
        Objects.requireNonNull(trabajo, "No puedo añadir precio del material a un trabajo nulo.");
        trabajo1 = getTrabajoAbierto(trabajo.getVehiculo());
        if (trabajo1 instanceof Revision){
            throw new TallerMecanicoExcepcion("No se puede añadir precio al material para este tipo de trabajos.");

        } else if (trabajo1 instanceof Mecanico mecanico){
            mecanico.anadirPrecioMaterial(precioMaterial);
        }

        return  trabajo1;
    }

    @Override
    public Trabajo cerrar(Trabajo trabajo, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "No puedo cerrar un trabajo nulo.");
        if (!coleccionTrabajos.contains(trabajo)) {
            throw new TallerMecanicoExcepcion("No existe ningún trabajo abierto para dicho vehículo.");
        }

        getTrabajoAbierto(trabajo.getVehiculo()).cerrar(fechaFin);
        return trabajo;
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "No se puede buscar un trabajo nulo.");
        if (coleccionTrabajos.contains(trabajo)) {
            return trabajo;
        }

        return null;
    }

    @Override
    public void borrar(Trabajo trabajo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "No se puede borrar un trabajo nulo.");
        if (coleccionTrabajos.contains(trabajo)) {
            coleccionTrabajos.remove(trabajo);
        } else {
            throw new TallerMecanicoExcepcion("No existe ningún trabajo igual.");
        }
    }

    private Trabajo getTrabajoAbierto(Vehiculo vehiculo) throws TallerMecanicoExcepcion{
       Trabajo trabajoAux = null;
        Iterator<Trabajo> iterator = coleccionTrabajos.iterator();
        boolean encontrado = false;
        while(iterator.hasNext() && !encontrado){
            trabajoAux = iterator.next();
            if (trabajoAux.getVehiculo().equals(vehiculo) && !trabajoAux.estaCerrado()) {
                encontrado = true;
            }
        }
        if (trabajoAux == null){
            throw new TallerMecanicoExcepcion("No existe ningún trabajo abierto para dicho vehículo.");
        }
        return trabajoAux;
    }
}
