package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Revisiones {
    Revision revision;

    List<Revision> coleccionRevisiones;

    public Revisiones(){
        coleccionRevisiones = new ArrayList<>();
    }

    public List<Revision> get() {

        return new ArrayList<>(coleccionRevisiones);
    }

    public List<Revision> get(Cliente cliente){
        List<Revision>revisionesCliente = new ArrayList<>();

        for (Revision revision : coleccionRevisiones){
            if (revision.getCliente().getDni().equals(cliente.getDni())){
                revisionesCliente.add(revision);
            }
        }
        return revisionesCliente;

    }

    public List<Revision> get(Vehiculo vehiculo){
        List<Revision>revisionesVehiculo = new ArrayList<>();
        for (Revision revision : coleccionRevisiones){
            if (revision.getVehiculo().matricula().equals(vehiculo.matricula())){
             revisionesVehiculo.add(revision);
            }
        }
        return revisionesVehiculo;
    }

    public void insertar(Revision revision) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision, "No se puede insertar una revisión nula.");


        comprobarRevision(revision.getCliente(), revision.getVehiculo(), revision.getFechaInicio());

        coleccionRevisiones.add(revision);
    }


    private void comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        Objects.requireNonNull(fechaRevision, "La fecha de revisión no puede ser nula.");

        for (Revision revisionExistente : coleccionRevisiones) {

            if (!revisionExistente.estaCerrada()){
                if (revisionExistente.getCliente().equals(cliente)){
                    throw new TallerMecanicoExcepcion("El cliente tiene otra revisión en curso.");
                }
                if (revisionExistente.getVehiculo().equals(vehiculo)){
                    throw new TallerMecanicoExcepcion("El vehículo está actualmente en revisión.");
                }
            }

            if (revisionExistente.estaCerrada()){
                if (revisionExistente.getCliente().equals(cliente) && !fechaRevision.isAfter(revisionExistente.getFechaFin())){
                    throw new TallerMecanicoExcepcion("El cliente tiene una revisión posterior.");
                }
                if (revisionExistente.getVehiculo().equals(vehiculo) && !fechaRevision.isAfter(revisionExistente.getFechaFin())){
                    throw new TallerMecanicoExcepcion("El vehículo tiene una revisión posterior.");
                }
            }
        }

    }



    private Revision getRevision(Revision revision) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision, "La revisión no puede ser nula.");


        for (Revision revision1 : coleccionRevisiones){
            if (revision1.equals(revision)){
                return revision1;
            }

        }
        throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
    }

    public Revision anadirHoras(Revision revision, int horas) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision,"No puedo operar sobre una revisión nula.");
        Objects.requireNonNull(horas,"Las horas no pueden ser nulas");

        getRevision(revision).anadirHoras(horas);

        return revision;
    }

    public Revision anadirPrecioMaterial(Revision revision, float precioMaterial) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision,"La revisión no puede ser nula");
        Objects.requireNonNull(precioMaterial,"El precio de material no puede ser nulo.");
        getRevision(revision).setPrecioMaterial(precioMaterial);
        return revision;
    }

    public Revision cerrar( Revision revision, LocalDate fechaFin)throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision,"No puedo operar sobre una revisión nula.");
        if (!coleccionRevisiones.contains(revision)){
            throw  new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        revision.cerrar(fechaFin);
        return revision;
    }

    public Revision buscar(Revision revision){
        Objects.requireNonNull(revision,"No se puede buscar una revisión nula.");
        if (coleccionRevisiones.contains(revision)){
            return revision;
        }

        return null;
    }

    public void borrar(Revision revision) throws TallerMecanicoExcepcion{
       Objects.requireNonNull(revision,"No se puede borrar una revisión nula.");
        if (coleccionRevisiones.contains(revision)){
            coleccionRevisiones.remove(revision);
        } else {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
    }

}

