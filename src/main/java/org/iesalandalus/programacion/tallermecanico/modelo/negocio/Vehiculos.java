package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.ArrayList;
import java.util.Objects;

public class Vehiculos {
    ArrayList<Vehiculo> coleccionVehiculos;

    public Vehiculos() {
        coleccionVehiculos = new ArrayList<>();
    }

    public ArrayList<Vehiculo> get() {
        return new ArrayList<>(coleccionVehiculos);
    }

    public void insertar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "No se puede insertar un vehículo nulo.");
        if (coleccionVehiculos.contains(vehiculo)) {
            throw new TallerMecanicoExcepcion("Ya existe un vehículo con esa matrícula.");
        } else {
            coleccionVehiculos.add(vehiculo);
        }
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "No se puede buscar un vehículo nulo.");
        int posicionVehiculo = coleccionVehiculos.indexOf(vehiculo);
        return posicionVehiculo == -1 ? null : coleccionVehiculos.get(posicionVehiculo);
    }

    // Crea el método `borrar` que borrará el turismo si este existe en la lista o lanzará una excepción en caso contrario.
    public void borrar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "No se puede borrar un vehículo nulo.");
        int posicionVehiculo = coleccionVehiculos.indexOf(vehiculo);
        if (posicionVehiculo == -1) {
            throw new TallerMecanicoExcepcion("No existe ningún vehículo con esa matrícula.");
        }
        coleccionVehiculos.remove(vehiculo);
    }
}
