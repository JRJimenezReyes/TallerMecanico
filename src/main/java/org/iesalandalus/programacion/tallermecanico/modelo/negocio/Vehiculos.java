package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class Vehiculos {
    private final List<Vehiculo> coleccionVehiculo;

    public Vehiculos() {
        coleccionVehiculo = new ArrayList<>();
    }

    public List<Vehiculo> get() {
        return new ArrayList<>(coleccionVehiculo);
    }

    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        if (vehiculo == null) {
            throw new NullPointerException("No se puede insertar un vehículo nulo.");
        }
        if (coleccionVehiculo.contains(vehiculo)) {
            throw new TallerMecanicoExcepcion("Ya existe un vehículo con esa matrícula.");
        }
        coleccionVehiculo.add(vehiculo);
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new NullPointerException("No se puede buscar un vehículo nulo.");
        }
        for (Vehiculo v : coleccionVehiculo) {
            if (v.equals(vehiculo)) {
                return v;
            }
        }
        return null;
    }

    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        if (vehiculo == null) {
            throw new NullPointerException("No se puede borrar un vehículo nulo.");
        }
        Vehiculo vehiculoExistente = buscar(vehiculo);
        if (vehiculoExistente == null) {
            throw new TallerMecanicoExcepcion("No existe ningún vehículo con esa matrícula.");
        }
        coleccionVehiculo.remove(vehiculoExistente);
    }
}