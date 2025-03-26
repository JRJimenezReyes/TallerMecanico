package org.iesalandalus.programacion.tallermecanico.Controlador;

import org.iesalandalus.programacion.tallermecanico.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajp;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Controlador {

    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        Objects.requireNonNull(modelo, "El modelo no puede ser nulo.");
        Objects.requireNonNull(vista, "La vista no puede ser nula.");
        this.modelo = modelo;
        this.vista = vista;
        this.vista.setControlador(this);
    }

    public void comenzar() {
        modelo.Comenzar();
        vista.comenzar();
    }

    public void terminar() {
        modelo.terminar();
        vista.terminar();
    }

    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion {
        modelo.insertar(cliente);
    }

    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        modelo.insertar(vehiculo);
    }

    public void insertar(Trabajo revision) throws TallerMecanicoExcepcion {
        modelo.insertar(revision);
    }

    public Cliente buscar(Cliente cliente) {
        return modelo.buscar(cliente);
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        return modelo.buscar(vehiculo);
    }

    public Trabajo buscar(Trabajo revision) {
        return modelo.buscar(revision);
    }

    public Cliente modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {
        return modelo.modificar(cliente, nombre, telefono);
    }

    public void anadirHoras(Trabajo revision, int horas) throws TallerMecanicoExcepcion {
        modelo.anadirHoras(revision, horas);
    }

    public void anadirPrecioMaterial(Trabajo revision, float precioMaterial) throws TallerMecanicoExcepcion {
        modelo.anadirPrecioMaterial(revision, precioMaterial);
    }

    public void cerrar(Trabajo revision, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        modelo.cerrar(revision, fechaFin);
    }

    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion {
        modelo.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        modelo.borrar(vehiculo);
    }

    public void borrar(Trabajo revision) throws TallerMecanicoExcepcion {
        modelo.borrar(revision);
    }

    public List<Cliente> getClientes(){
        return modelo.getClientes();
    }

    public List<Vehiculo> getVehiculos(){
        return modelo.getVehiculos();
    }

    public List<Trabajp> getRevisiones(){
        return modelo.getTrabajos();
    }

    public List<Trabajp> getRevisiones(Cliente cliente){
        return modelo.getRevisiones(cliente);
    }

    public List<Trabajp> getRevisiones(Vehiculo vehiculo){
        return modelo.getRevisiones(vehiculo);
    }
}
