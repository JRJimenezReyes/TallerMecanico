package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class Vehiculos implements org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos {
    private final List<Vehiculo> coleccionVehiculos;

    public Vehiculos() {
        coleccionVehiculos = new ArrayList<>();
    }

    @Override
    public List<Vehiculo> get () {
        return new ArrayList<>(coleccionVehiculos);
    }

    @Override
    public void insertar (Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        if (vehiculo == null) {
            throw new NullPointerException("No se puede insertar un vehículo nulo.");
        }
        if (coleccionVehiculos.contains(vehiculo)) {
            throw new TallerMecanicoExcepcion("Ya existe un vehículo con esa matrícula.");
        }
        coleccionVehiculos.add(vehiculo);
    }

    @Override
    public Vehiculo buscar (Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new NullPointerException("No se puede buscar un vehículo nulo.");
        }
        for (Vehiculo v : coleccionVehiculos) {
            if (v.equals(vehiculo)) {
                return v;
            }
        }
        return null;
    }

    @Override
    public void borrar (Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        if (vehiculo == null) {
            throw new NullPointerException("No se puede borrar un vehículo nulo.");
        }
        Vehiculo vehiculoExistente = buscar(vehiculo);
        if (vehiculoExistente == null) {
            throw new TallerMecanicoExcepcion("No existe ningún vehículo con esa matrícula.");
        }
        coleccionVehiculos.remove(vehiculoExistente);
    }
}