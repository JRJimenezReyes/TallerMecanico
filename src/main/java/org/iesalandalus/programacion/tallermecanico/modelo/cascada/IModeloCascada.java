package org.iesalandalus.programacion.tallermecanico.modelo.cascada;

import org.iesalandalus.programacion.tallermecanico.modelo.IModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class IModeloCascada implements org.iesalandalus.programacion.tallermecanico.modelo.Modelo {
    private IClientes clientes;
    private IVehiculos vehiculos;
    private ITrabajos trabajos;

    public IModeloCascada (FabricaFuenteDatos fabricaFuenteDatos) {
        Objects.requireNonNull(fabricaFuenteDatos,"La factoría de la fuente de datos no puede ser nula.");
        IFuenteDatos fuenteDatos = fabricaFuenteDatos.crear();
        clientes = fuenteDatos.crearClientes();
        vehiculos = fuenteDatos.crearVehiculos();
        trabajos = fuenteDatos.crearTrabajos();
    }

    @Override
    public void comenzar () {

    }

    @Override
    public void terminar () {
        System.out.println("Modelo terminado.");
    }

    @Override
    public void insertar (Cliente cliente) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(cliente,"El cliente no puede ser nulo.");
        clientes.insertar(new Cliente(cliente));
    }

    @Override
    public void insertar (Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(vehiculo,"El vehículo no puede ser nulo.");
        vehiculos.insertar(vehiculo);
    }

    @Override
    public void insertar (Trabajo trabajo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        Cliente cliente = buscar(trabajo.getCliente());
        Vehiculo vehiculo = buscar(trabajo.getVehiculo());
        if (cliente != null && vehiculo != null) {
            if (trabajo instanceof Revision) {
                trabajos.insertar(new Revision((Revision) trabajo));
            } else if (trabajo instanceof Mecanico) {
                trabajos.insertar(new Mecanico((Mecanico) trabajo));
            }
        }
    }

    @Override
    public Cliente buscar (Cliente cliente) {
        Cliente clienteEncontrado = clientes.buscar(cliente);
        return clienteEncontrado != null ? new Cliente(clienteEncontrado) : null;
    }

    @Override
    public Vehiculo buscar (Vehiculo vehiculo) {
        return vehiculos.buscar(vehiculo);
    }

    @Override
    public Trabajo buscar (Trabajo trabajo) {
        Trabajo trabajoEncontrado = trabajos.buscar(trabajo);
        return (trabajoEncontrado != null) ? Trabajo.copiar(trabajoEncontrado) : null;
    }

    @Override
    public void modificar (Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {
        clientes.modificar(cliente, nombre, telefono);
    }

    @Override
    public void anadirHoras (Trabajo trabajo, int horas) throws TallerMecanicoExcepcion {
        trabajos.anadirHoras(trabajo, horas);
    }

    @Override
    public void anadirPrecioMaterial (Trabajo trabajo, float precioMaterial) throws TallerMecanicoExcepcion {
        trabajos.anadirPrecioMaterial(trabajo, precioMaterial);
    }

    @Override
    public void cerrar (Trabajo trabajo, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        trabajos.cerrar(trabajo, fechaFin);
    }

    @Override
    public void borrar (Cliente cliente) throws TallerMecanicoExcepcion {
        List<Trabajo> trabajoClientes = trabajos.get(cliente);
        for (Trabajo trabajo : trabajoClientes) {
            trabajos.borrar(trabajo);
        }
        clientes.borrar(cliente);
    }

    @Override
    public void borrar (Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        List<Trabajo> trabajoVehiculos = trabajos.get(vehiculo);
        for (Trabajo trabajo : trabajoVehiculos) {
            trabajos.borrar(trabajo);
        }
        vehiculos.borrar(vehiculo);
    }

    @Override
    public void borrar (Trabajo trabajo) throws TallerMecanicoExcepcion {
        trabajos.borrar(trabajo);
    }

    @Override
    public List<Cliente> getClientes () {
        List<Cliente> listaClientes = clientes.get();
        return listaClientes.stream().map(Cliente::new).toList();
    }

    @Override
    public List<Vehiculo> getVehiculos () {
        return vehiculos.get();
    }

    @Override
    public List<Trabajo> getTrabajos () {
        return trabajos.get().stream().map(trabajo -> {
            if (trabajo instanceof Revision) {
                return new Revision((Revision) trabajo);
            } else {
                return new Mecanico((Mecanico) trabajo);
            }
        }).toList();
    }

    @Override
    public List<Trabajo> getTrabajos (Cliente cliente) {
        return trabajos.get(cliente).stream().map(Trabajo::copiar).toList();
    }

    @Override
    public List<Trabajo> getTrabajos (Vehiculo vehiculo) {
        return trabajos.get(vehiculo).stream().map(Trabajo::copiar).toList();
    }
}
