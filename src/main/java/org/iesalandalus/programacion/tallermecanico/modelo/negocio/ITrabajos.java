package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;

import java.time.LocalDate;
import java.util.List;

public interface ITrabajos {
    List<Trabajo> get();
    List<Trabajo> get(Cliente cliente);
    List<Trabajo> get(Vehiculo vehiculo);
    void insertar(Trabajo trabajo) throws TallerMecanicoExcepcion ;
    Trabajo anadirHoras(Trabajo trabajo , int horas) throws TallerMecanicoExcepcion;
    Mecanico anadirPrecioMaterial(Trabajo trabajo , float precioMaterial) throws TallerMecanicoExcepcion;
    Trabajo cerrar(Trabajo trabajo , LocalDate fechaFin) throws  TallerMecanicoExcepcion;
    Trabajo buscar(Trabajo trabajo)throws TallerMecanicoExcepcion;
    void borrar(Trabajo trabajo)throws TallerMecanicoExcepcion;

}
