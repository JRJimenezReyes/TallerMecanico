package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes {

    private List<Cliente> clientes;

    public Clientes() {
        this.clientes = new ArrayList<>();
    }

    public List<Cliente> get() {
        return new ArrayList<>(clientes);
    }

    public boolean insertar(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo");
        if (!clientes.contains(cliente)) {
            clientes.add(cliente);
            return true;
        }
        return false;
    }

    public boolean modificar(Cliente cliente, String nombre, String telefono) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo");
        if (clientes.contains(cliente)) {
            if ((nombre != null && !nombre.trim().isEmpty()) || (telefono != null && !telefono.trim().isEmpty())) {
                cliente.setNombre(nombre);
                cliente.setTelefono(telefono);
                return true;
            }
        }
        return false;
    }

    public Cliente buscar(Cliente cliente) {
        for (Cliente c : clientes) {
            if (c.equals(cliente)) {
                return c;
            }
        }
        return null;
    }

    public void borrar(Cliente cliente) throws IllegalAccessException {
        if (!clientes.remove(cliente)) {
            throw new IllegalAccessException("El cliente no se encuentra en la lista: " + cliente);
        }
    }
}




