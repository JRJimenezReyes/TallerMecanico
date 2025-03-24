package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Trabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Vehiculos;

public enum FabricaFuenteDatos {
    MEMORIA {
        @Override
        public IFuenteDatos crear() {
            return new IFuenteDatos() {
                @Override
                public IClientes crearClientes() {
                    return new Clientes();
                }

                @Override
                public IVehiculos crearVehiculos() {
                    return new Vehiculos();
                }

                @Override
                public ITrabajos crearTrabajos() {
                    return new Trabajos();
                }
            };
        }
    };


    public abstract IFuenteDatos crear();
}
