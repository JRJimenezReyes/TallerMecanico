package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public interface IClientes {
    List<Cliente>  get();
    void insertar (Cliente cliente);
    Cliente modificar(Cliente cliente, String nombre, String telefono);
    Cliente buscar(Cliente cliente);
    void borrar(Cliente cliente);

}
