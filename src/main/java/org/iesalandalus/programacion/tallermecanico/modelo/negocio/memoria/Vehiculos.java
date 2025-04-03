package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vehiculos implements org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos {
    Vehiculos coleccionVehiculos;

    List <Vehiculo> listaVehiculos;

    public Vehiculos(){
        listaVehiculos = new ArrayList<>();
    }

    @Override
    public List <Vehiculo> get(){
        List <Vehiculo> nuevaListaVehiculos;
        nuevaListaVehiculos = listaVehiculos;
        return nuevaListaVehiculos;
    }

    @Override
    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(vehiculo, "No se puede insertar un vehículo nulo.");
        if (!listaVehiculos.contains(vehiculo)){
            listaVehiculos.add(vehiculo);
        } else {
            throw new TallerMecanicoExcepcion("Ya existe un vehículo con esa matrícula.");
        }
    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo){

        Objects.requireNonNull(vehiculo,"No se puede buscar un vehículo nulo.");
        int indexVehiculo;
        if (listaVehiculos.contains(vehiculo)){
            indexVehiculo = listaVehiculos.indexOf(vehiculo);
            return listaVehiculos.get(indexVehiculo);
        }
            return null;
    }

    @Override
    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(vehiculo,"No se puede borrar un vehículo nulo.");
        if (listaVehiculos.contains(vehiculo)){
            listaVehiculos.remove(vehiculo);
        } else {
            throw new TallerMecanicoExcepcion("No existe ningún vehículo con esa matrícula.");
        }

    }

}
