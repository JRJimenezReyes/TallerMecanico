package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Vehiculos {

    private List<Vehiculo> vehiculos;

    public Vehiculos() {
        this.vehiculos = new ArrayList<>();
    }

    public List<Vehiculo> get() {
        return Collections.unmodifiableList(vehiculos);
    }

    public void insertar(Vehiculo vehiculo) throws IllegalArgumentException {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo");
        if (vehiculos.contains(vehiculo)) {
            throw new IllegalArgumentException("El vehículo ya existe en la lista");
        }
        vehiculos.add(vehiculo);
    }

    public Vehiculo buscar(String matricula) {
        Objects.requireNonNull(matricula, "La matrícula no puede ser nula");
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getMatricula().equals(matricula)) {
                return vehiculo;
            }
        }
        return null;
    }

    public void borrar(Vehiculo vehiculo) throws IllegalArgumentException {
        if (!vehiculos.remove(vehiculo)) {
            throw new IllegalArgumentException("El vehículo no se encuentra en la lista");
        }
    }

    public int getNumeroVehiculos() {
        return vehiculos.size();
    }

    public List<Vehiculo> buscarPorMarca(String marca) {
        if (marca == null || marca.trim().isEmpty()) {
            return Collections.emptyList();
        }
        List<Vehiculo> vehiculosEncontrados = new ArrayList<>();
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getMarca().toLowerCase().contains(marca.toLowerCase())) {
                vehiculosEncontrados.add(vehiculo);
            }
        }
        return Collections.unmodifiableList(vehiculosEncontrados);
    }

    public boolean contains(Vehiculo vehiculo) {
        for (Vehiculo v : vehiculos) {
            if (v.equals(vehiculo)) {
                return true;
            }
        }
        return false;
    }

}