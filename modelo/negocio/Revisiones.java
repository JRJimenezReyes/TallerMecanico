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

    private List<Revision> coleccionRevisiones;

    public Revisiones(){
        this.coleccionRevisiones = new ArrayList<>();
    }

    public List<Revision> get() {
        return this.coleccionRevisiones;
    }

    public List<Revision> get(Cliente cliente) {
        List<Revision> aux = new ArrayList<>();
        for (Revision r : coleccionRevisiones){
            if (r.getCliente().equals(cliente)){
                aux.add(r);
            }
        }
        return aux;
    }

    public List<Revision> get(Vehiculo vehiculo) {
        List<Revision> aux = new ArrayList<>();
        for (Revision r : coleccionRevisiones){
            if (r.getVehiculo().equals(vehiculo)){
                aux.add(r);
            }
        }
        return aux;
    }

    public void insertar(Revision revision) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision,"No se puede insertar una revisión nula.");
        comprobarRevision(revision.getCliente(), revision.getVehiculo(), revision.getFechaInicio());
        this.coleccionRevisiones.add(revision);
    }

    private void comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) throws TallerMecanicoExcepcion{
        for (Revision r : coleccionRevisiones){
            if (!r.estaCerrada()){
                if (r.getCliente().equals(cliente)){
                    throw new TallerMecanicoExcepcion("El cliente tiene otra revisión en curso.");
                } else if (r.getVehiculo().equals(vehiculo)){
                    throw new TallerMecanicoExcepcion("El vehículo está actualmente en revisión.");
                }
            } else {
                if (r.getCliente().equals(cliente) && !fechaRevision.isAfter(r.getFechaFin())){
                    throw new TallerMecanicoExcepcion("El cliente tiene una revisión posterior.");
                } else if (r.getVehiculo().equals(vehiculo) && !fechaRevision.isAfter(r.getFechaFin())){
                    throw new TallerMecanicoExcepcion("El vehículo tiene una revisión posterior.");
                }
            }
        }
    }

    public void anadirHoras(Revision revision, int horas) throws TallerMecanicoExcepcion{
        Revision r = getRevision(revision);
        r.anadirHoras(horas);
    }

    private Revision getRevision(Revision revision) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision,"No puedo operar sobre una revisión nula.");
        for (Revision r : coleccionRevisiones) {
            if(r.equals(revision)) {
                return r;
            }
        }
        throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
    }

    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision,"No puedo operar sobre una revisión nula.");
        Revision r = getRevision(revision);
        r.anadirPrecioMaterial(precioMaterial);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        Revision r = getRevision(revision);
        r.cerrar(fechaFin);
    }

    public Revision buscar(Revision revision) {
        Objects.requireNonNull(revision, "No se puede buscar una revisión nula.");
        for (Revision r : coleccionRevisiones) {
            if(r.equals(revision)) {
                return r;
            }
        }
        return null;
    }

    public void borrar(Revision revision) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision, "No se puede borrar una revisión nula.");
        Revision r = getRevision(revision);
        this.coleccionRevisiones.remove(r);
    }
}
