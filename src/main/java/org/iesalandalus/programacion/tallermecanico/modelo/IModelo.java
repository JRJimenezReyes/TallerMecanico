package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.time.LocalDate;

public interface IModelo {
    void comenzar();
    void terminar();
    void insertar(Cliente cliente);
    void insertar(Vehiculo vehiculo);
    void insertar(Trabajo trabajo);
    Cliente buscar(Cliente cliente);
    Vehiculo buscar(Vehiculo vehiculo);
    Trabajo buscar(Trabajo trabajo);
    Cliente modificar(Cliente cliente, String nombre, String telefono);
    Trabajo anadirHoras(Trabajo trabajo, int horas);
    Trabajo anadirPrecioMaterial(float precioMaterial, Trabajo trabajo);
    Trabajo cerrar(LocalDate fechafin, Trabajo trabajo);
    void borrar(Cliente cliente);
    void borrar(Vehiculo vehiculo);
    void borrar(Trabajo trabajo);


}
