package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vehiculos {

    private List<Vehiculo> vehiculos;

    public Vehiculos() {
        this.vehiculos = new ArrayList<>();
    }

    public List<Vehiculo> get() {
        return new ArrayList<>(vehiculos);
    }

    public void insertar(Vehiculo vehiculo) throws IllegalArgumentException {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo");
        if (vehiculos.contains(vehiculo)) {
            throw new IllegalArgumentException("El vehículo ya existe en la lista");
        }
        vehiculos.add(vehiculo);
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        for (Vehiculo v : vehiculos) {
            if (v.equals(vehiculo)) {
                return v;
            }
        }
        return null;
    }

    public void borrar(Vehiculo vehiculo) throws IllegalArgumentException {
        if (!vehiculos.remove(vehiculo)) {
            throw new IllegalArgumentException("El vehículo no se encuentra en la lista");
        }
    }

}