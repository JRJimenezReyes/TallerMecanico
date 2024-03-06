package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Modelo {

    private Vista vista;
    private Controlador controlador;

    private List<Cliente> clientes;
    private List<Vehiculo> vehiculos;
    private List<Revision> revisiones;

    public Modelo(Vista vista, Controlador controlador) {
        this.vista = vista;
        this.controlador = controlador;

        clientes = new ArrayList<>();
        vehiculos = new ArrayList<>();
        revisiones = new ArrayList<>();
    }

    public void comenzar() {
        Cliente cliente1 = new Cliente("123456789A", "Juan", "Pérez", null, null);
        Vehiculo vehiculo1 = new Vehiculo("1234ABC", "Marca1", "Modelo1");
        LocalDate fechaInicio = LocalDate.now();

        Revision revision1 = new Revision(cliente1, vehiculo1, fechaInicio);

        insertar(cliente1);
        insertar(vehiculo1);
        insertar(revision1);

        System.out.println("Modelo terminado.");
    }

    public void terminar() {
        System.out.println("Modelo terminado.");
    }

    // Métodos insertar...
    public void insertar(Cliente cliente) {
        if (!clientes.contains(cliente)) {
            clientes.add(new Cliente(cliente));
            // ...
        } else {
            throw new IllegalArgumentException("Cliente ya existe.");
        }
    }

    public void insertar(Vehiculo vehiculo) {
        if (!vehiculos.contains(vehiculo)) {
            vehiculos.add(vehiculo);
            // ...
        } else {
            throw new IllegalArgumentException("Vehículo ya existe.");
        }
    }

    public void insertar(Revision revision) {
        Cliente cliente = buscarCliente(revision.getCliente().getDni());
        Vehiculo vehiculo = buscarVehiculo(revision.getVehiculo().getMatricula());
        if (cliente != null && vehiculo != null) {
            revision.setCliente(cliente);
            revision.setVehiculo(vehiculo);
            revisiones.add(new Revision(revision));
            // ...
        } else {
            throw new IllegalArgumentException ("Cliente o vehículo no encontrado.");
        }
    }

    // Métodos buscar...
    public Cliente buscarCliente(String dni) {
        for (Cliente cliente : clientes) {
            if (cliente.getDni().equals(dni)) {
                return new Cliente(cliente);
            }
        }
        return null;
    }

    public Vehiculo buscarVehiculo(String matricula) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getMatricula().equals(matricula)) {
                return vehiculo;
            }
        }
        return null;
    }

    public Revision buscarRevision(int id) {
        for (Revision revision : revisiones) {
            if (revision.getNumeroRevision() == id) {
                return new Revision(revision);
            }
        }
        return null;
    }
}

    // Método modificar...
    public void modificar(Revision revision) {
        Revision revisionEncontrada = buscarRevision(revision.getNumeroRevision());
        if (revisionEncontrada != null) {
            revisionEncontrada.setHoras(revision.getHoras());
            revisionEncontrada.setPrecioMaterial(revision.getPrecioMaterial());
            // ...
        } else {
            throw new IllegalArgumentException ("Revisión no encontrada.");
        }
    }


// Métodos añadirHoras y añadirPrecioMaterial...
    public void anadirHoras(Revision revision, int horas) {
        Revision revisionEncontrada = buscarRevision(revision.getNumeroRevision());
        if (revisionEncontrada != null) {
            revisionEncontrada.setHoras(revisionEncontrada.getHoras() + horas);
            // ...
        } else {
            throw new IllegalArgumentException ("Revisión no encontrada.");
        }
    }

    public void anadirPrecioMaterial(Revision revision, float precio) {
        Revision revisionEncontrada = buscarRevision(revision.getNumeroRevision());
        if (revisionEncontrada != null) {
            revisionEncontrada.setPrecioMaterial(revisionEncontrada.getPrecioMaterial() + precio);
            // ...
        } else {
            throw new IllegalArgumentException ("Revisión no encontrada.");
        }
    }

    // Métodos cerrar...
    public void cerrar(Revision revision) {
        Revision revisionEncontrada = buscarRevision(revision.getNumeroRevision());
        if (revisionEncontrada != null) {
            if (revisionEncontrada.getEstado() == EstadoRevision.ABIERTA) {
                revisionEncontrada.setEstado(EstadoRevision.CERRADA);
            } else {
                throw new IllegalArgumentException ("La revisión ya está cerrada.");
            }
        } else {
            throw new IllegalArgumentException ("Revisión no encontrada.");
        }
    }

    // Métodos borrar...
    public void borrar(Cliente cliente) {
        List<Revision> revisionesCliente = buscarRevisionesPorCliente(cliente);
        for (Revision revision : revisionesCliente) {
            borrar(revision);
        }
        clientes.remove(cliente);
    }

    public void borrar(Vehiculo vehiculo) {
        List<Revision> revisionesVehiculo = buscarRevisionesPorVehiculo(vehiculo);
        for (Revision revision : revisionesVehiculo) {
            borrar(revision);
        }
        vehiculos.remove(vehiculo);
    }

    public void borrar(Revision revision) {
        revisiones.remove(revision);
    }

    // Métodos buscar por cliente y vehículo...
    public List<Revision> buscarRevisionesPorCliente(Cliente cliente) {
        List<Revision> revisionesCliente = new ArrayList<>();
        for (Revision revision : revisiones) {
            if (revision.getCliente().equals(cliente)) {
                revisionesCliente.add(new Revision(revision));
            }
        }
        return revisionesCliente;
    }

    public List<Revision> buscarRevisionesPorVehiculo(Vehiculo vehiculo) {
        List<Revision> revisionesVehiculo = new ArrayList<>();
        for (Revision revision : revisiones) {
            if (revision.getVehiculo().equals(vehiculo)) {
                revisionesVehiculo.add(new Revision(revision));
            }
        }
        return revisionesVehiculo;
    }

    // Métodos get...
    public List<Cliente> getClientes() {
        List<Cliente> clientesCopia = new ArrayList<>();
        for (Cliente cliente : clientes) {
            clientesCopia.add(new Cliente(cliente));
        }
        return clientesCopia;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public List<Revision> getRevisiones() {
        List<Revision> revisionesCopia = new ArrayList<>();
        for (Revision revision : revisiones) {
            revisionesCopia.add(new Revision(revision));
        }
        return revisionesCopia;
    }
}


