package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.time.LocalDate;
import java.util.List;

public interface Modelo extends IModelo {
    @Override
    void comenzar ();

    @Override
    void terminar ();

    @Override
    void insertar (Cliente cliente) throws TallerMecanicoExcepcion;

    @Override
    void insertar (Vehiculo vehiculo) throws TallerMecanicoExcepcion;

    @Override
    void insertar (Trabajo trabajo) throws TallerMecanicoExcepcion;

    @Override
    Cliente buscar (Cliente cliente);

    @Override
    Vehiculo buscar (Vehiculo vehiculo);

    @Override
    Trabajo buscar (Trabajo trabajo);

    @Override
    void modificar (Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion;

    @Override
    void anadirHoras (Trabajo trabajo, int horas) throws TallerMecanicoExcepcion;

    @Override
    void anadirPrecioMaterial (Trabajo trabajo, float precioMaterial) throws TallerMecanicoExcepcion;

    @Override
    void cerrar (Trabajo trabajo, LocalDate fechaFin) throws TallerMecanicoExcepcion;

    @Override
    void borrar (Cliente cliente) throws TallerMecanicoExcepcion;

    @Override
    void borrar (Vehiculo vehiculo) throws TallerMecanicoExcepcion;

    @Override
    void borrar (Trabajo trabajo) throws TallerMecanicoExcepcion;

    @Override
    List<Cliente> getClientes ();

    @Override
    List<Vehiculo> getVehiculos ();

    @Override
    List<Trabajo> getTrabajos ();

    @Override
    List<Trabajo> getTrabajos (Cliente cliente);

    @Override
    List<Trabajo> getTrabajos (Vehiculo vehiculo);
}
