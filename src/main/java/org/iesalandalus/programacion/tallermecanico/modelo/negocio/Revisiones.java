package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class Revisiones {
    private final List<Revision> coleccionRevisiones;

    public Revisiones() {
        coleccionRevisiones = new ArrayList<>();
    }

    public ArrayList<Revision> get() {
        return new ArrayList<>(coleccionRevisiones);
    }

    public List<Revision> get(Cliente cliente) {
        return new ArrayList<>(coleccionRevisiones);
    }

    public List<Revision> get(Vehiculo vehiculo) {
        return new ArrayList<>(coleccionRevisiones);
    }

    public void insertar(Revision revision) throws TallerMecanicoExcepcion {
        if (revision == null) {
            throw new NullPointerException("No se puede insertar un vehículo nulo.");
        }
        if (coleccionRevisiones.contains(revision)) {
            throw new TallerMecanicoExcepcion("Ya existe un vehículo con esa matrícula.");
        }
        coleccionRevisiones.add(revision);
    }


}
