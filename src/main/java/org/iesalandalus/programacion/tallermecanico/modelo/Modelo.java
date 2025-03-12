package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Revisiones;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Modelo {

    private Clientes clientes;
    private Vehiculos vehiculos;
    private Revisiones revisiones;


    public Modelo(){
        comenzar();
    }

    public void comenzar(){
        clientes = new Clientes();
        vehiculos = new Vehiculos();
        revisiones = new Revisiones();
    }

    public void terminar(){
        System.out.println("El modelo ha terminado.");
    }

    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(cliente,"El cliente no puede ser nulo.");
        Cliente cliente1 = new Cliente(cliente);
        clientes.insertar(cliente1);
    }

    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(vehiculo,"El vehiculo no puede ser nulo.");
        vehiculos.insertar(vehiculo);
    }

    public void insertar(Revision revision) throws TallerMecanicoExcepcion{
       Objects.requireNonNull(revision,"La revisión no puede ser nula.");
        Revision revision1 = new Revision(clientes.buscar(revision.getCliente()),vehiculos.buscar(revision.getVehiculo()),revision.getFechaInicio());
        revisiones.insertar(revision1);
    }

    public Cliente buscar (Cliente cliente){

        return new Cliente(clientes.buscar(cliente));
    }

    public Vehiculo buscar(Vehiculo vehiculo){
        return vehiculos.buscar(vehiculo);
    }

    public Revision buscar(Revision revision){
        return new Revision(revisiones.buscar(revision));
    }

    public Cliente modificar(Cliente cliente, String nombre,String telefono)throws TallerMecanicoExcepcion{
        Objects.requireNonNull(cliente,"El cliente no puede ser nulo.");
        Objects.requireNonNull(nombre,"El nombre no puede ser nulo.");
        Objects.requireNonNull(telefono,"El teléfono no puede ser nulo.");
        clientes.modificar(cliente,nombre,telefono);
        return new Cliente(cliente);
    }

    public Revision anadirHoras(Revision revision, int horas) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision,"La revisión no puede ser nula.");

        revisiones.anadirHoras(revision, horas);
        return new Revision(revision);
    }

    public Revision anadirPrecioMaterial(Revision revision, float precioMaterial) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision,"La revisión no puede ser nula.");

        revisiones.anadirPrecioMaterial(revision,precioMaterial);
        return new Revision(revision);
    }

    public Revision cerrar(Revision revision, LocalDate fechaFin) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision,"La revisión no puede ser nula.");
        Objects.requireNonNull(fechaFin,"La fecha no puede ser nula.");

        revisiones.cerrar(revision,fechaFin);
        return new Revision(revision);
    }

    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(cliente,"El cliente no pude ser nulo.");
        for (Revision revisionCliente : revisiones.get(cliente)){
            vehiculos.borrar(revisionCliente.getVehiculo());
            revisiones.borrar(revisionCliente);
        }
        clientes.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(vehiculo,"El vehiculo no puede ser nulo.");
        for (Revision revision : revisiones.get(vehiculo)){
            revisiones.borrar(revision);
        }
        vehiculos.borrar(vehiculo);
    }

    public void borrar(Revision revision) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision,"La revisión no puede ser nula.");

        revisiones.borrar(revision);
    }

    public List<Cliente> getClientes() {
         List<Cliente> nuevosClientes = new ArrayList<>();
            for (Cliente cliente: clientes.get()){

                nuevosClientes.add(new Cliente(cliente));
            }
            return nuevosClientes;
    }

    public List<Vehiculo> getVehiculos() {

        return new ArrayList<>(vehiculos.get());
    }

    public List<Revision> getRevisiones() {
        List<Revision> nuevasRevisiones = new ArrayList<>();

        for (Revision revision : revisiones.get()){
            nuevasRevisiones.add(new Revision(revision));
        }
        return nuevasRevisiones;
    }

    public List<Revision> getRevisiones(Cliente cliente){

        List<Revision> nuevasRevisiones = new ArrayList<>();
        for (Revision revision : revisiones.get(cliente)){
            nuevasRevisiones.add(new Revision(revision));
        }
        return nuevasRevisiones;
    }

    public List<Revision> getRevisiones(Vehiculo vehiculo){
        List<Revision> nuevasRevisiones = new ArrayList<>();
        for (Revision revision : revisiones.get(vehiculo)){
            nuevasRevisiones.add(new Revision(revision));
        }
        return nuevasRevisiones;
    }
}
