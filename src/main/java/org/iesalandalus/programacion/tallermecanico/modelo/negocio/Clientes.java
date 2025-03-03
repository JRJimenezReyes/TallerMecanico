package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import java.util.ArrayList;
import java.util.Objects;

public class Clientes {
    ArrayList<Cliente> coleccionClientes;


    public Clientes() {
        coleccionClientes = new ArrayList<>();
    }

    public ArrayList<Cliente> get() {
        return new ArrayList<>(coleccionClientes);
    }

    public void insertar(Cliente cliente) {
        Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");
        if (coleccionClientes.contains(cliente)) {
            throw new TallerMecanicoExcepcion("Ya existe un cliente con ese DNI.");
        }
        coleccionClientes.add(cliente);
    }

    public Cliente modificar(Cliente cliente, String nombre, String telefono) {
        Objects.requireNonNull(cliente, "No se puede modificar un cliente nulo.");
        if (!(coleccionClientes.contains(cliente))) {
            throw new TallerMecanicoExcepcion("No existe ningún cliente con ese DNI.");
        }

        if (nombre != null && !(nombre.isBlank())) {
            cliente.setNombre(nombre);
        }
        if (telefono != null && !(telefono.isBlank())) {
            cliente.setTelefono(telefono);
        }
        return cliente;
    }

    public Cliente buscar(Cliente cliente) {
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");
        //Si el cliente no está en la lista (indexof == -1) devuelve null y si esta (el cliente) lo devuelve.
        return coleccionClientes.indexOf(cliente) == -1 ? null : coleccionClientes.get(coleccionClientes.indexOf(cliente));
    }

    public void borrar(Cliente cliente) {
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");
        //Si el cliente no está en la lista (indexof == -1) lanza una excepcion y si esta lo borra.
        if (!(coleccionClientes.contains(cliente))) {
            throw new TallerMecanicoExcepcion("No existe ningún cliente con ese DNI.");
        } else {
            coleccionClientes.remove(cliente);
        }
    }
}
