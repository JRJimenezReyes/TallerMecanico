package org.iesalandalus.programacion.tallermecanico.vista.ventana;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.controladores.InsertarCliente;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.controladores.InsertarVehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.controladores.VentanaPrincipal;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.vista.ventana.utilidades.Dialogos;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class VistaGrafica implements Vista {
    private GestorEventos gestorEventos = new GestorEventos(Evento.values());
    private InsertarVehiculo insertarVehiculo;
    private VentanaPrincipal ventanaPrincipal;
    private InsertarCliente insertarCliente;

    private VistaGrafica() {
    }

    public void inicializar(){
        ventanaPrincipal = (VentanaPrincipal) Controladores.get("/vistas/VentanaPrincipal.fxml", "Taller Mecánico", null);
    }

    private static VistaGrafica instancia;

    public static VistaGrafica getInstance() {
        if (instancia == null) {
            return instancia = new VistaGrafica();
        }
        return instancia;
    }

    @Override
    public int leerHoras() {
        return 0;
    }

    @Override
    public float leerPrecioMaterial() {
        return 0;
    }

    @Override
    public LocalDate leerFechaCierre() {
        return null;
    }

    @Override
    public GestorEventos getGestorEventos() {
        return gestorEventos;
    }

    @Override
    public void comenzar() {
        LanzadoraVistaGrafica.comenzar();

    }

    @Override
    public void terminar() {

    }

    @Override
    public Cliente leerCliente() {
        insertarCliente = (InsertarCliente) Controladores.get("/vistas/InsertarCliente.fxml","Insertar Cliente", ventanaPrincipal.getEscenario());
        return insertarCliente.getCliente();
    }

    @Override
    public Cliente leerClienteDNI() {
        return null;
    }

    @Override
    public String leerNuevoNombre() {
        return "";
    }

    @Override
    public String leerNuevoTelefono() {
        return "";
    }

    @Override
    public Vehiculo leerVehiculo() {
        insertarVehiculo= (InsertarVehiculo) Controladores.get("/vistas/InsertarVehiculo.fxml","Insertar Vehículo", ventanaPrincipal.getEscenario());
        return insertarVehiculo.getVehiculo();
    }

    @Override
    public Vehiculo leerVehiculoMatricula() {
        return null;
    }

    @Override
    public Revision leerRevision() {
        return null;
    }

    @Override
    public Mecanico leerMecanico() {
        return null;
    }

    @Override
    public Trabajo leerTrabajoVehiculo() {
        return null;
    }

    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito) {
        
        if (exito) {
            Dialogos.mostrarDialogoInformacion(evento.toString(), texto, ventanaPrincipal.getEscenario());
        } else {
            Dialogos.mostrarDialogoError(evento.toString(), texto, ventanaPrincipal.getEscenario());
        }
    }

    @Override
    public void mostrarClientes(List<Cliente> clientes) {

    }

    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos) {

    }

    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos) {

    }

    @Override
    public void mostrarCliente(Cliente cliente) {

    }

    @Override
    public void mostrarTrabajo(Trabajo trabajo) {

    }

    @Override
    public void mostrarVehiculo(Vehiculo vehiculo) {

    }

    @Override
    public LocalDate leerMes() {
        return null;
    }

    @Override
    public void mostrarEstadisticas(Map<TipoTrabajo, Integer> estadistica) {

    }


}
