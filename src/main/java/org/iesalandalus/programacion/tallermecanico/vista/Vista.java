package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;

import java.time.LocalDate;
import java.util.List;

public interface Vista {
    static int leerHoras() {
        return Consola.leerEntero("Escriba la cantidad de horas de trabajo: ");
    }

    static float leerPrecioMaterial() {
        return Consola.leerReal("Introduce el precio del material: ");
    }

    static LocalDate leerFechaCierre() {
        return Consola.leerFecha("Introduzca la fecha de cierre de la revisión: ");
    }

    GestorEventos getGestorEventos();

    void setControlador(Controlador controlador);

    void comenzar() throws TallerMecanicoExcepcion;

    void terminar();

    Cliente leerCliente();

    Cliente leerClienteDNI();

    String leerNuevoNombre();

    String leerNuevoTelefono();

    Vehiculo leerVehiculo();

    Vehiculo leerVehiculoMatricula();

    Revision leerRevision();

    Mecanico leerMecanico();

    Trabajo leerTrabajoVehiculo();

    void notificarResultado(Evento evento, String texto, boolean exito);

    void mostrarClientes(List<Cliente> clientes);

    void mostrarVehiculos(List<Vehiculo> vehiculos);

    void mostrarTrabajos(List<Trabajo> trabajos);

    void mostrarCliente(Cliente cliente);

    void mostrarTrabajo(Trabajo trabajo);

    void mostrarVehiculo(Vehiculo vehiculo);
}
