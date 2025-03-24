package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Trabajos implements ITrabajos {
    private List<Trabajo> coleccionRevisiones;
    public Trabajos(){

        coleccionRevisiones = new ArrayList<>();
    }
    public List<Trabajo> get(){
        return coleccionRevisiones;

    }
    public List<Trabajo> get(Cliente cliente) {
        List<Trabajo> aux = new ArrayList<>();
        for (Trabajo trabajo : coleccionRevisiones){
            if (trabajo.getCliente().equals(cliente)){
                aux.add(trabajo);
            }
        }
        return aux;
    }

    public List<Trabajo> get(Vehiculo vehiculo) {
        List<Trabajo> aux = new ArrayList<>();
        for (Trabajo trabajo : coleccionRevisiones){
            if (trabajo.getVehiculo().equals(vehiculo)){
                aux.add(trabajo);
            }
        }
        return aux;
    }

    @Override
    public void insertar(Trabajo trabajo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo,"No se puede insertar un trabajo nulo.");
        comprobarTrabajo(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio());
        this.coleccionRevisiones.add(trabajo);

    }

    @Override
    public Trabajo anadirHoras(Trabajo trabajo, int horas) throws TallerMecanicoExcepcion {
        getTrabajoAbierto(trabajo).anadirHoras(horas);
        return getTrabajoAbierto(trabajo);
    }

    @Override
    public Mecanico anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "No puedo añadir precio del material a un trabajo nulo.");

        Trabajo trabajoAbierto = getTrabajoAbierto(trabajo);

        if (trabajoAbierto instanceof Mecanico) {
            ((Mecanico) trabajoAbierto).anadirPrecioMaterial(precioMaterial);
            return (Mecanico) trabajoAbierto;
        } else {
            throw new TallerMecanicoExcepcion("No se puede añadir precio al material para este tipo de trabajos.");
        }
    }


    @Override
    public Trabajo cerrar(Trabajo trabajo, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo,"No puedo cerrar un trabajo nulo.");
        getTrabajoAbierto(trabajo).getVehiculo();
        return getTrabajoAbierto(trabajo);
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) throws TallerMecanicoExcepcion {

        Objects.requireNonNull(trabajo,"No se puede buscar un trabajo nulo.");
        Trabajo revision1 = null;
        if (coleccionRevisiones.contains(trabajo)){
            revision1 = trabajo;

        }
        return revision1;

    }

    @Override
    public void borrar(Trabajo trabajo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "No se puede borrar un trabajo nulo.");
        if (!coleccionRevisiones.contains(trabajo)) {
            throw new TallerMecanicoExcepcion("No existe ningún trabajo igual.");
        }
        coleccionRevisiones.remove(trabajo);

    }


    private void comprobarTrabajo (Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) throws TallerMecanicoExcepcion{
       for (Trabajo trabajo : coleccionRevisiones){
           if (!trabajo.estaCerrado()) {
               if (trabajo.getCliente().equals(cliente)) {
                   throw new TallerMecanicoExcepcion("El cliente tiene otro trabajo en curso.");
               } else if (trabajo.getVehiculo().equals(vehiculo)) {
                   throw new TallerMecanicoExcepcion("El vehículo está actualmente en el taller.");

               }
           } else  {
               if (trabajo.getCliente().equals(cliente) && !fechaRevision.isAfter(trabajo.getFechaFin())){
                   throw new TallerMecanicoExcepcion("El cliente tiene otro trabajo posterior.");
               } else if (trabajo.getVehiculo().equals(vehiculo) && !fechaRevision.isAfter(trabajo.getFechaFin())){
                   throw new TallerMecanicoExcepcion("El vehículo tiene otro trabajo posterior.");
               }
           }
       }


    }

    private Trabajo getTrabajoAbierto (Trabajo trabajo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "No puedo añadir horas a un trabajo nulo.");
        for (Trabajo trabajo1 : coleccionRevisiones){
            if (trabajo1.equals(trabajo1)){
                return trabajo1;
            }
        }
        throw new TallerMecanicoExcepcion("No existe ningún trabajo abierto para dicho vehículo.");
    }




}





