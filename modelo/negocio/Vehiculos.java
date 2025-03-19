package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vehiculos {

    private final List<Vehiculo> coleccionVehiculos;

    public Vehiculos(){
        this.coleccionVehiculos = new ArrayList<>();
    }


    public List<Vehiculo> get(){
        return new ArrayList<>(this.coleccionVehiculos);
    }

    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(vehiculo, "No se puede insertar un vehículo nulo.");
        for (Vehiculo v : coleccionVehiculos){
            if (v.equals(vehiculo)){
                throw new TallerMecanicoExcepcion("Ya existe un vehículo con esa matrícula.");
            }
        }
        this.coleccionVehiculos.add(vehiculo);
    }

    public Vehiculo buscar(Vehiculo vehiculo){
        Objects.requireNonNull(vehiculo, "No se puede buscar un vehículo nulo.");
        for (Vehiculo v : coleccionVehiculos){
            if (v.equals(vehiculo)){
                return v;
            }
        }
        return null;
    }

    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(vehiculo, "No se puede borrar un vehículo nulo.");
        Vehiculo v = buscar(vehiculo);
        if (v == null){
            throw new TallerMecanicoExcepcion("No existe ningún vehículo con esa matrícula.");
        }
        this.coleccionVehiculos.remove(v);
    }
}
