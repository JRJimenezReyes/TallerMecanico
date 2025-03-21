package org.iesalandalus.programacion.tallermecanico.vista;
import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.List;
import java.util.Objects;

public class Vista {
    private Controlador controlador;

    public void setControlador(Controlador controlador){
        Objects.requireNonNull(controlador);
        this.controlador = controlador;
    }

    public void comenzar() throws TallerMecanicoExcepcion {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        } while (opcion != Opcion.SALIR);
            controlador.terminar();
    }

    public void terminar(){
        System.out.println("Me doy el piro, vampiro!");
    }

    private void ejecutar(Opcion opcion) throws TallerMecanicoExcepcion {

        switch(opcion){
            case INSERTAR_CLIENTE -> insertarCliente();
            case INSERTAR_VEHICULO -> insertarVehiculo();
            case INSERTAR_REVISION -> insertarRevision();
            case BUSCAR_CLIENTE -> buscarCliente();
            case BUSCAR_VEHICULO -> buscarVehiculo();
            case BUSCAR_REVISION -> buscarRevision();
            case MODIFICAR_CLIENTE -> modificarCliente();
            case ANADIR_HORAS_REVISION -> anadirHoras();
            case ANADIR_PRECIO_MATERIAL_REVISION -> anadirPrecioMaterial();
            case BORRAR_CLIENTE -> borrarCliente();
            case BORRAR_REVISION -> borrarRevision();
            case CERRAR_REVISION -> cerrarRevision();
            case BORRAR_VEHICULO -> borrarVehiculo();
            case LISTAR_CLIENTES -> listarClientes();
            case LISTAR_REVISIONES -> listarRevisones();
            case LISTAR_REVISIONES_CLIENTE -> listarRevisionesClientes();
            case LISTAR_REVISIONES_VEHICULO -> listarRevisionesVehiculos();
            case LISTAR_VEHICULOS -> listarVehiculos();
            case SALIR -> salir();

        }
    }

    private void insertarCliente() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Insertar Cliente");
        controlador.insertar(Consola.leerCliente());
        System.out.println("Cliente insertado correctamente.");

    }

    private void insertarVehiculo() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Insertar vehiculo");
        controlador.insertar(Consola.leerVehiculo());
    }

    private void insertarRevision() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Insertar Revisión");
        controlador.insertar(Consola.leerRevision());
    }
    private void buscarCliente(){
        Consola.mostrarCabecera("Buscar cliente");
        controlador.buscar(Consola.leerCliente());
    }

    private void buscarVehiculo(){
        Consola.mostrarCabecera("Buscar vehiculo");
        controlador.buscar(Consola.leerVehiculo());
    }

    private void buscarRevision(){
        Consola.mostrarCabecera("Buscar revisión");
        controlador.buscar(Consola.leerRevision());
    }

    private void modificarCliente() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Modificar cliente");
        controlador.modificar(Consola.leerCliente(),Consola.leerNuevoNombre(),Consola.leerNuevoTelefono());
    }

    private void anadirHoras() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Añadir Horas de Taller");
        controlador.anadirHoras(Consola.leerRevision(),Consola.leerHoras());
    }

    private void anadirPrecioMaterial() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Añadir el precio del material");
        controlador.anadirPrecioMaterial(Consola.leerRevision(),Consola.leerPrecioMaterial());
    }

    private void cerrarRevision() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Cerrar una revisión");
        controlador.cerrar(Consola.leerRevision(),Consola.leerFechaCierre());
    }

    private void borrarCliente() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Borrar un cliente");
        controlador.borrar(Consola.leerCliente());
    }

    private void borrarVehiculo() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Borrar un vehiculo");
        controlador.borrar(Consola.leerVehiculo());
    }

    private void borrarRevision() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Borrar una revisión");
        controlador.borrar(Consola.leerRevision());
    }

    private void listarClientes(){
        Consola.mostrarCabecera("Listado de clientes");
        List<Cliente> clientes = controlador.getClientes();
        if (!clientes.isEmpty()){
            for (Cliente cliente : clientes){
                System.out.println(cliente);
            }

        }else {
            System.out.println("La lista esta vacía.");
        }
    }

    private void listarVehiculos(){
        Consola.mostrarCabecera("Listado de vehículos");
        List<Vehiculo> vehiculos = controlador.getVehiculos();
        if (!vehiculos.isEmpty()){
            for (Vehiculo vehiculo : vehiculos){
                System.out.println(vehiculo);
            }

        }else {
            System.out.println("La lista esta vacía.");
        }
    }

    private void listarRevisones(){
        Consola.mostrarCabecera("Listado de revisiones");
        List<Revision> revisiones = controlador.getRevisiones();
        if (!revisiones.isEmpty()){
            for (Revision revision : revisiones){
                System.out.println(revision);
            }

        }else {
            System.out.println("La lista esta vacía.");
        }
    }

    private void listarRevisionesClientes(){
        Consola.mostrarCabecera("Listado de revisiones por cliente");
        List<Revision> revisonesCliente = controlador.getRevisiones();
        if (!revisonesCliente.isEmpty()){
            for (Revision revision : revisonesCliente){
                System.out.println(revision);
            }

        }else {
            System.out.println("La lista esta vacía.");
        }
    }

    private void listarRevisionesVehiculos(){
        Consola.mostrarCabecera("Listado de revisiones por vehículos");
        List<Revision> revisonesCliente = controlador.getRevisiones();
        if (!revisonesCliente.isEmpty()){
            for (Revision revision : revisonesCliente){
                System.out.println(revision);
            }

        }else {
            System.out.println("La lista esta vacía.");
        }
    }

    private void salir(){
        Consola.mostrarCabecera("Salir");

    }



}
