package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Trabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Vehiculos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Modelo {
private Clientes clientes;
private Vehiculos vehiculos;
private Trabajos revisiones;

public Modelo (){
Comenzar();

}
public void Comenzar(){
    this.revisiones = new Trabajos();
    this.vehiculos = new Vehiculos();
    this.clientes = new Clientes();
}
public void terminar(){
    System.out.println("El modelo ha terminado");
}
public void insertar(Cliente cliente)throws TallerMecanicoExcepcion{
    this.clientes.insertar(new Cliente(cliente));
}
public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion{
    this.vehiculos.insertar(vehiculo);
}
public void insertar(Revision revision) throws TallerMecanicoExcepcion{
Cliente cliente = buscar(revision.getCliente());
Vehiculo vehiculo = buscar(revision.getVehiculo());
this.revisiones.insertar(new Revision(cliente,vehiculo,revision.getFechaInicio()));
}
public Cliente buscar(Cliente cliente){
    return (clientes.buscar(cliente) != null ? new Cliente(cliente) : null);
}
public Vehiculo buscar (Vehiculo vehiculo)  {
    return this.vehiculos.buscar(vehiculo);
}
public Revision buscar(Revision revision)  {
    return revisiones.buscar(revision) != null ? new Revision(revision) : null;
}
public Cliente modificar (Cliente cliente,String nombre,String telefono) throws TallerMecanicoExcepcion{
    return this.clientes.modificar(cliente,nombre,telefono);
}
public Revision anadirHoras (Revision revision,int horas) throws TallerMecanicoExcepcion{
    return this.revisiones.anadirHoras(revision,horas);
}
public Revision anadirPrecioMaterial(Revision revision, float precioMaterial) throws TallerMecanicoExcepcion{
    return this.revisiones.anadirPrecioMaterial(revision,precioMaterial);
}
public Revision cerrar(Revision revision, LocalDate fechaFin) throws TallerMecanicoExcepcion{
    return this.revisiones.cerrar(revision,fechaFin);
}
public void borrar (Cliente cliente) throws TallerMecanicoExcepcion{
    List<Revision> aux = this.revisiones.get(cliente);
    for (Revision revision : aux){
        this.revisiones.borrar(revision);
    }
    this.clientes.borrar(cliente);
}
public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion{
    List<Revision> aux = this.revisiones.get(vehiculo);
    for (Revision revision : aux){
        this.revisiones.borrar(revision);
    }
    this.vehiculos.borrar(vehiculo);
}
public void borrar(Revision revision)throws TallerMecanicoExcepcion{
this.revisiones.borrar(revision);
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
public List<Revision> getRevisiones(){
    List<Revision> aux = new ArrayList<>();
    for (Revision revision : this.revisiones.get()){
        aux.add(new Revision(revision));
    }
    return aux;
}
public List<Revision> getRevisiones(Cliente cliente){
    List<Revision> aux = new ArrayList<>();
    for (Revision revision : this.revisiones.get(cliente)){
        aux.add(new Revision(revision));
    }
    return aux;
}
    public List<Revision> getRevisiones(Vehiculo vehiculo){
        List<Revision> aux = new ArrayList<>();
        for (Revision revision : this.revisiones.get(vehiculo)){
            aux.add(new Revision(revision));
        }
        return aux;
    }
}

