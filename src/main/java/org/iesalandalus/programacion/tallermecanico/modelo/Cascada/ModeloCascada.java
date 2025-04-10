package org.iesalandalus.programacion.tallermecanico.modelo.Cascada;

import org.iesalandalus.programacion.tallermecanico.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Trabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Vehiculos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModeloCascada implements Modelo {
    private IClientes clientes;
    private IVehiculos vehiculos;
    private ITrabajos trabajos;
    public ModeloCascada (FabricaFuenteDatos fabricaFuenteDatos){
        Objects.requireNonNull(fabricaFuenteDatos,"la fabrica de datos no puede ser nula");
        IFuenteDatos fuenteDatos =fabricaFuenteDatos.crear();
        clientes = fuenteDatos.crearClientes();
        vehiculos = fuenteDatos.crearVehiculos();
        trabajos = fuenteDatos.crearTrabajos();
    }

    @Override
    public void comenzar() {
        this.clientes = new Clientes();
        this.trabajos = new Trabajos();
        this.vehiculos = new Vehiculos();

    }

    @Override
    public void terminar() {
        System.out.println("El modelo ha terminado");
    }

    @Override
    public void insertar(Cliente cliente) {
        this.clientes.insertar(new Cliente(cliente));
    }

    @Override
    public void insertar(Vehiculo vehiculo) {
        this.vehiculos.insertar(vehiculo);
    }

    @Override
    public void insertar(Trabajo trabajo) {
        Cliente cliente = buscar(trabajo.getCliente());
        Vehiculo vehiculo = buscar(trabajo.getVehiculo());
        Trabajo resultado = null;
        if (trabajo instanceof Mecanico) {
          resultado = new Mecanico(cliente,vehiculo,trabajo.getFechaInicio());
        } else {
            resultado = new Revision(cliente,vehiculo,trabajo.getFechaInicio());
        }
        trabajos.insertar(resultado)
        ;
    }

    @Override
    public Cliente buscar(Cliente cliente) {
        return (clientes.buscar(cliente) != null ? new Cliente(cliente) : null);
    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo) {
        return this.vehiculos.buscar(vehiculo);
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        return trabajos.buscar(trabajo) != null ? new Trabajo(trabajo) {
            @Override
            public float getPrecioEspecifico() {
                return 0;
            }
        } : null;
    }

    @Override
    public Cliente modificar(Cliente cliente, String nombre, String telefono) {
        return this.clientes.modificar(cliente,nombre,telefono);
    }

    @Override
    public Trabajo anadirHoras(Trabajo trabajo, int horas) {
        return this.trabajos.anadirHoras(trabajo,horas);
    }

    @Override
    public Trabajo anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) {
        return this.trabajos.anadirPrecioMaterial(trabajo,precioMaterial);
    }

    @Override
    public Trabajo cerrar(Trabajo trabajo, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        return this.trabajos.cerrar(trabajo,fechaFin);
    }



    @Override
    public void borrar(Cliente cliente) {
        List<Trabajo> aux = this.trabajos.get(cliente);
        for (Trabajo revision : aux){
            this.trabajos.borrar(revision);
        }
        this.clientes.borrar(cliente);
    }

    @Override
    public void borrar(Vehiculo vehiculo) {
        List<Trabajo> aux = this.trabajos.get(vehiculo);
        for (Trabajo revision : aux){
            this.trabajos.borrar(revision);
        }
        this.vehiculos.borrar(vehiculo);

    }

    @Override
    public void borrar(Trabajo trabajo) {

        this.trabajos.borrar(trabajo);

    }
    public List<Cliente> getClientes()  {
        List<Cliente> aux = new ArrayList<>();
        for (Cliente cliente : this.clientes.get()){
            aux.add(new Cliente(cliente));
        }
        return aux;
    }
    public List<Vehiculo> getVehiculos(){
        return this.vehiculos.get();

    }


    @Override
    public List<Trabajo> getTrabajos() {
        List<Trabajo> trabajosOriginales = trabajos.get();
        List<Trabajo> copiaTrabajos = new ArrayList<>();

        for (Trabajo trabajo : trabajosOriginales) {
            if (trabajo instanceof Mecanico) {
                copiaTrabajos.add(new Mecanico(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio()));
            } else if (trabajo instanceof Revision) {
                copiaTrabajos.add(new Revision(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio()));
            }
        }

        return copiaTrabajos;
    }

    @Override
    public List<Trabajo> getTrabajos(Cliente cliente) {
        List<Trabajo> trabajosOriginales = trabajos.get(cliente);
        List<Trabajo> copiaTrabajos = new ArrayList<>();

        for (Trabajo trabajo : trabajosOriginales) {
            if (trabajo instanceof Mecanico) {
                copiaTrabajos.add(new Mecanico(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio()));
            } else if (trabajo instanceof Revision) {
                copiaTrabajos.add(new Revision(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio()));
            }
        }

        return copiaTrabajos;
    }

    @Override
    public List<Trabajo> getTrabajos(Vehiculo vehiculo) {
        List<Trabajo> trabajosOriginales = trabajos.get(vehiculo);
        List<Trabajo> copiaTrabajos = new ArrayList<>();

        for (Trabajo trabajo : trabajosOriginales) {
            if (trabajo instanceof Mecanico) {
                copiaTrabajos.add(new Mecanico(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio()));
            } else if (trabajo instanceof Revision) {
                copiaTrabajos.add(new Revision(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio()));
            }
        }

        return copiaTrabajos;
    }




}
