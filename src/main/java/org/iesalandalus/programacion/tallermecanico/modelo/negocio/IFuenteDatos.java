package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

public interface IFuenteDatos {
    IClientes crearClientes();
    IVehiculos crearVehiculos();
    ITrabajos crearTrabajos();

}
