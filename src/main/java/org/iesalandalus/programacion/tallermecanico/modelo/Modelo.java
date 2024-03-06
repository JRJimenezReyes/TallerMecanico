package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.EstadoRevision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Revisiones;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Modelo {

    private Vista vista;
    private Controlador controlador;

    private Clientes clientes;
    private Vehiculos vehiculos;
    private Revisiones revisiones;

    public Modelo(Vista vista, Controlador controlador) {
        this.vista = vista;
        this.controlador = controlador;

        clientes = new Clientes();
        vehiculos = new Vehiculos();
        revisiones = new Revisiones();
    }

    public void comenzar() {
        // ...
    }

    public void terminar() {
        // ...
    }

    // Métodos insertar...
    public void insertar(Cliente cliente) throws IllegalArgumentException {
        if (!clientes.contains(cliente)) {
            clientes.insertar(cliente);
        } else {
            throw new IllegalArgumentException("Cliente ya existe.");
        }
    }

    public void insertar(Vehiculo vehiculo) throws IllegalArgumentException {
        if (!vehiculos.contains(vehiculo)) {
            vehiculos.insertar(vehiculo);
        } else {
            throw new IllegalArgumentException("Vehículo ya existe.");
        }
    }

    public void insertar(Revision revision) throws IllegalArgumentException {
        Cliente cliente = buscarCliente(revision.getCliente().getDni());
        Vehiculo vehiculo = buscarVehiculo(revision.getVehiculo().getMatricula());
        if (cliente != null && vehiculo != null) {
            revision.setCliente(cliente);
            revision.setVehiculo(vehiculo);
            revisiones.insertar(revision);
        } else {
            throw new IllegalArgumentException ("Cliente o vehículo no encontrado.");
        }
    }

    // Métodos buscar...
    public Cliente buscarCliente(String dni) {
        return clientes.buscar(dni);
    }

    public Vehiculo buscarVehiculo(String matricula) {
        return vehiculos.buscar(matricula);
    }

    public Revision buscarRevision(int id) {
        return revisiones.buscar(id);
    }

    // Método modificar...
    public void modificar(Revision revision) throws IllegalArgumentException {
        Revision revisionEncontrada = buscarRevision(revision.getNumeroRevision());
        if (revisionEncontrada != null) {
            revisionEncontrada.setHoras(revision.getHoras());
            revisionEncontrada.setPrecioMaterial(revision.getPrecioMaterial());
        } else {
            throw new IllegalArgumentException ("Revisión no encontrada.");
        }
    }


    // Métodos añadirHoras y añadirPrecioMaterial...
    public void anadirHoras(Revision revision, int horas) throws IllegalArgumentException {
        Revision revisionEncontrada = buscarRevision(revision.getNumeroRevision());
        if (revisionEncontrada != null) {
            revisionEncontrada.setHoras(revisionEncontrada.getHoras() + horas);
        } else {
            throw new IllegalArgumentException ("Revisión no encontrada.");
        }
    }

    public void anadirPrecioMaterial(Revision revision, float precio) throws IllegalArgumentException {
        Revision revisionEncontrada = buscarRevision(revision.getNumeroRevision());
        if (revisionEncontrada != null) {
            revisionEncontrada.setPrecioMaterial(revisionEncontrada.getPrecioMaterial() + precio);
        } else {
            throw new IllegalArgumentException ("Revisión no encontrada.");
        }
    }

    // Métodos cerrar...
    public void cerrar(String revision) throws IllegalArgumentException {
        int numeroRevision = Integer.parseInt(revision);
        Revision revisionEncontrada = buscarRevision(numeroRevision);
        if (revisionEncontrada != null) {
            if (revisionEncontrada.getEstado() == EstadoRevision.ABIERTA) {
                revisionEncontrada.setEstado(EstadoRevision.CERRADA);
                revisionEncontrada.setFechaFin(LocalDate.now());
            } else {
                throw new IllegalArgumentException ("La revisión ya está cerrada.");
            }
        } else {
            throw new IllegalArgumentException ("Revisión no encontrada.");
        }
    }

    // Métodos borrar...
    public void borrar(Cliente cliente) throws IllegalAccessException {
        List<Revision> revisionesCliente = buscarRevisionesPorCliente(cliente);
        for (Revision revision : revisionesCliente) {
            borrar(revision);
        }
        clientes.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) {
        List<Revision> revisionesVehiculo = buscarRevisionesPorVehiculo(vehiculo);
        for (Revision revision : revisionesVehiculo) {
            borrar(revision);
        }
        vehiculos.borrar(vehiculo);
    }

    public void borrar(Revision revision) {
        revisiones.borrar(revision);
    }

    // Métodos buscar por cliente y vehículo...
    public List<Revision> buscarRevisionesPorCliente(Cliente cliente) {
        List<Revision> revisionesCliente = new ArrayList<>();
        for (Revision revision : revisiones.get()) {
            if (revision.getCliente().equals(cliente)) {
                revisionesCliente.add(new Revision(revision));
            }
        }
        return revisionesCliente;
    }

    public List<Revision> buscarRevisionesPorVehiculo(Vehiculo vehiculo) {
        List<Revision> revisionesVehiculo = new ArrayList<>();
        for (Revision revision : revisiones.get()) {
            if (revision.getVehiculo().equals(vehiculo)) {
                revisionesVehiculo.add(new Revision(revision));
            }
        }
        return revisionesVehiculo;
    }

    public void crearRevision(String matricula, int km, LocalDate fecha, double precio) throws IllegalArgumentException {
        Cliente cliente = buscarCliente(matricula);
        Vehiculo vehiculo = buscarVehiculo(matricula);
        if (cliente != null && vehiculo != null) {
            Revision revision = new Revision(cliente, vehiculo, fecha);
            revisiones.insertar(revision);
        } else {
            throw new IllegalArgumentException("Cliente o vehículo no encontrado.");
        }
    }

    // Métodos get...
    public List<Cliente> getClientes() {
        return Collections.unmodifiableList(clientes.get());
    }

    public List<Vehiculo> getVehiculos() {
        return Collections.unmodifiableList(vehiculos.get());
    }

    public List<Revision> getRevisiones() {
        return Collections.unmodifiableList(revisiones.get());
    }

    public List<Revision> listarRevisiones() {
        return new ArrayList<>((Collection) revisiones);
    }

    public void borrarRevision(int idRevision) {
        Revision revision = buscarRevision(idRevision);
        if (revision != null) {
            revisiones.borrar(revision);
        }
    }



}



