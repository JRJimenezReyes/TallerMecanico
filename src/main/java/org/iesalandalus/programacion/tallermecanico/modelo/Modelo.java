package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Trabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Vehiculos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Modelo {

    private Clientes clientes;
    private Vehiculos vehiculos;
    private Trabajos revisiones;

    public Modelo() {
        comenzar();
    }

    public void comenzar() {
        clientes = new Clientes();
        vehiculos = new Vehiculos();
        revisiones = new Trabajos();
    }

    public void terminar() {
        System.out.println("El modelo ha terminado");
    }

    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion {
        clientes.insertar(new Cliente(cliente));
    }

    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        vehiculos.insertar(vehiculo);
    }

    public void insertar(Trabajo revision) throws TallerMecanicoExcepcion {
        Cliente cliente = clientes.buscar(revision.getCliente());
        Vehiculo vehiculo = vehiculos.buscar(revision.getVehiculo());
        if (cliente != null && vehiculo != null) {
            revisiones.insertar(new Revision(cliente,vehiculo,revision.getFechaInicio()));
        }
    }

    public Cliente buscar(Cliente cliente) {
        Cliente encontrado = clientes.buscar(cliente);
        return (encontrado != null) ? new Cliente(encontrado) : null;
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        return vehiculos.buscar(vehiculo);
    }

    public Trabajo buscar(Trabajo revision) {
        Revision encontrada = revisiones.buscar(revision);
        return (encontrada != null) ? new Revision(encontrada) : null;
    }

    public boolean modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {
        return clientes.modificar(cliente, nombre, telefono);
    }

    public Trabajo anadirHoras(Trabajo revision, int horas) throws TallerMecanicoExcepcion {
        return revisiones.anadirHoras(revision, horas);
    }

    public Trabajo anadirPrecioMaterial(Trabajo revision, float precioMaterial) throws TallerMecanicoExcepcion {
        return revisiones.anadirPrecioMaterial(revision, precioMaterial);
    }

    public Trabajo cerrar(Trabajo revision, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        return revisiones.cerrar(revision, fechaFin);
    }

    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion {
        List<Trabajo> revisionesCliente = revisiones.get(cliente);
        for (Trabajo revision : revisionesCliente) {
            revisiones.borrar(revision);
        }
        clientes.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        List<Trabajo> revisionesVehiculo = revisiones.get(vehiculo);
        for (Trabajo revision : revisionesVehiculo) {
            revisiones.borrar(revision);
        }
        vehiculos.borrar(vehiculo);
    }

    public void borrar(Trabajo revision) throws TallerMecanicoExcepcion {
        revisiones.borrar(revision);
    }

    public List<Cliente> getClientes() {
        return clientes.get().stream().map(Cliente::new).collect(Collectors.toList());
    }

    public List<Vehiculo> getVehiculos() {
        return new ArrayList<>(vehiculos.get());
    }

   public List<Trabajo> getRevisiones() {
        return revisiones.get().stream().map(Revision::new).collect(Collectors.toList());
    }

    public List<Trabajo> getRevisiones(Cliente cliente) {
        return revisiones.get(cliente).stream().map(Revision::new).collect(Collectors.toList());
    }

    public List<Trabajo> getRevisiones(Vehiculo vehiculo) {
        return revisiones.get(vehiculo).stream().map(Revision::new).collect(Collectors.toList());
    }
}
