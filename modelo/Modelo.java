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



public class Modelo {

    private Clientes clientes;
    private Vehiculos vehiculos;
    private Revisiones revisiones;

    public Modelo() {
        comenzar();
    }

    public void comenzar() {
        this.clientes = new Clientes();
        this.vehiculos = new Vehiculos();
        this.revisiones = new Revisiones();
    }

    public void terminar() {
        System.out.println("El modelo ha terminado.");
    }

    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion {
        this.clientes.insertar(new Cliente(cliente));
    }

    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        this.vehiculos.insertar(vehiculo);
    }

    public void insertar(Revision revision) throws TallerMecanicoExcepcion {
        Cliente c = buscar(revision.getCliente());
        Vehiculo v = buscar(revision.getVehiculo());
        this.revisiones.insertar(new Revision(c, v, revision.getFechaInicio()));
    }

    public Cliente buscar(Cliente cliente) {
        return this.clientes.buscar(cliente);
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        return this.vehiculos.buscar(vehiculo);
    }

    public Revision buscar(Revision revision) {
        return this.revisiones.buscar(revision);
    }

    public boolean modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {
        return this.clientes.modificar(cliente, nombre, telefono);
    }

    public void anadirHoras(Revision revision, int horas) throws TallerMecanicoExcepcion {
        this.revisiones.anadirHoras(revision, horas);
    }

    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws TallerMecanicoExcepcion {
        this.revisiones.anadirPrecioMaterial(revision, precioMaterial);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        this.revisiones.cerrar(revision, fechaFin);
    }

    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion {
        List<Revision> aux = this.revisiones.get(cliente);
        for (Revision r : aux) {
            this.revisiones.borrar(r);
        }
        this.clientes.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        List<Revision> aux = this.revisiones.get(vehiculo);
        for (Revision r : aux) {
            this.revisiones.borrar(r);
        }
        this.vehiculos.borrar(vehiculo);
    }

    public void borrar(Revision revision) throws TallerMecanicoExcepcion {
        this.revisiones.borrar(revision);
    }

    public List<Cliente> getClientes(){
        List<Cliente> aux = new ArrayList<>();
        for (Cliente c : this.clientes.get()) {
            aux.add(new Cliente(c));
        }
        return aux;
    }

    public List<Vehiculo> getVehiculos(){
        return this.vehiculos.get();
    }

    public List<Revision> getRevisiones(){
        List<Revision> aux = new ArrayList<>();
        for (Revision r : this.revisiones.get()) {
            aux.add(new Revision(r));
        }
        return aux;
    }

    public List<Revision> getRevisiones(Cliente cliente){
        List<Revision> aux = new ArrayList<>();
        for (Revision r : this.revisiones.get(cliente)) {
            aux.add(new Revision(r));
        }
        return aux;
    }

    public List<Revision> getRevisiones(Vehiculo vehiculo){
        List<Revision> aux = new ArrayList<>();
        for (Revision r : this.revisiones.get(vehiculo)) {
            aux.add(new Revision(r));
        }
        return aux;
    }
}
