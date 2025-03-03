package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Vehiculos {
    ArrayList<Vehiculo> listaVehiculos;

    public Vehiculos() {
        listaVehiculos = new ArrayList<>();
    }

    public ArrayList<Vehiculo> get() {
        return new ArrayList<>(listaVehiculos);
    }

    public void insertar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "No se puede insertar un vehículo nulo.");
        if (listaVehiculos.contains(vehiculo)) {
            throw new TallerMecanicoExcepcion("Ya existe un vehículo con esa matrícula.");
        } else {
            listaVehiculos.add(vehiculo);
        }
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "No se puede buscar un vehículo nulo.");
        int posicionVehiculo = listaVehiculos.indexOf(vehiculo);
        return posicionVehiculo == -1 ? null : listaVehiculos.get(posicionVehiculo);
    }

    // Crea el método `borrar` que borrará el turismo si este existe en la lista o lanzará una excepción en caso contrario.
    public void borrar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "No se puede borrar un vehículo nulo.");
        int posicionVehiculo = listaVehiculos.indexOf(vehiculo);
        if (posicionVehiculo == -1) {
            throw new TallerMecanicoExcepcion("No existe ningún vehículo con esa matrícula.");
        }
        listaVehiculos.remove(vehiculo);
    }
}
