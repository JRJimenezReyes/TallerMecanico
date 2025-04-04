package org.iesalandalus.programacion.tallermecanico.modelo.cascada;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Trabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Vehiculos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModeloCascada implements org.iesalandalus.programacion.tallermecanico.modelo.Modelo {

    private Clientes clientes;
    private Vehiculos vehiculos;
    private Trabajos revisiones;

    public ModeloCascada() {
        comenzar();
    }

    @Override
    public void comenzar() {
        clientes = new Clientes();
        vehiculos = new Vehiculos();
        revisiones = new Trabajos();
    }

    @Override
    public void terminar() {
        System.out.println("El modelo ha terminado");
    }

    @Override
    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion {
        clientes.insertar(new Cliente(cliente));
    }

    @Override
    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        vehiculos.insertar(vehiculo);
    }

    @Override
    public void insertar(Trabajo revision) throws TallerMecanicoExcepcion {
        Cliente cliente = clientes.buscar(revision.getCliente());
        Vehiculo vehiculo = vehiculos.buscar(revision.getVehiculo());
        if (cliente != null && vehiculo != null) {
            revisiones.insertar(new Revision(cliente,vehiculo,revision.getFechaInicio()));
        }
    }

    @Override
    public Cliente buscar(Cliente cliente) {
        Cliente encontrado = clientes.buscar(cliente);
        return (encontrado != null) ? new Cliente(encontrado) : null;
    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo) {
        return vehiculos.buscar(vehiculo);
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        Trabajo encontrada = revisiones.buscar(trabajo); // Usar la instancia de 'revisiones'
        return (encontrada != null) ? new Revision(encontrada.getCliente(), encontrada.getVehiculo(), encontrada.getFechaInicio()) : null; // Asegúrate de usar el constructor correcto
    }

    @Override
    public boolean modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {
        return clientes.modificar(cliente, nombre, telefono);
    }

    @Override
    public Trabajo anadirHoras(Trabajo revision, int horas) throws TallerMecanicoExcepcion {
        return revisiones.anadirHoras(revision, horas);
    }

    @Override
    public Trabajo anadirPrecioMaterial(Trabajo revision, float precioMaterial) throws TallerMecanicoExcepcion {
        return revisiones.anadirPrecioMaterial(revision, precioMaterial);
    }

    @Override
    public Trabajo cerrar(Trabajo revision, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        return revisiones.cerrar(revision, fechaFin);
    }

    @Override
    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion {
        List<Trabajo> revisionesCliente = revisiones.get(cliente);
        for (Trabajo revision : revisionesCliente) {
            revisiones.borrar(revision);
        }
        clientes.borrar(cliente);
    }

    @Override
    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        List<Trabajo> revisionesVehiculo = revisiones.get(vehiculo);
        for (Trabajo revision : revisionesVehiculo) {
            revisiones.borrar(revision);
        }
        vehiculos.borrar(vehiculo);
    }

    @Override
    public void borrar(Trabajo revision) throws TallerMecanicoExcepcion {
        revisiones.borrar(revision);
    }

    @Override
    public List<Cliente> getClientes() {
        return clientes.get().stream().map(Cliente::new).collect(Collectors.toList());
    }

    @Override
    public List<Vehiculo> getVehiculos() {
        return new ArrayList<>(vehiculos.get());
    }

    @Override
    public List<Trabajo> getTrabajos() {
        return revisiones.get().stream().map(revision -> new Revision(revision.getCliente(), revision.getVehiculo(), revision.getFechaInicio())).collect(Collectors.toList());
    }

    @Override
    public List<Trabajo> getTrabajos(Cliente cliente) {
        return revisiones.get(cliente).stream().map(revision -> new Revision(revision.getCliente(), revision.getVehiculo(), revision.getFechaInicio())).collect(Collectors.toList());
    }

    @Override
    public List<Trabajo> getTrabajos(Vehiculo vehiculo) {
        return revisiones.get(vehiculo).stream().map(revision -> new Revision(revision.getCliente(), revision.getVehiculo(), revision.getFechaInicio())).collect(Collectors.toList());
    }
}
