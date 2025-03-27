package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.cascada.IModeloCascada;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;

public enum FabricaModelo {
    CASCADA {
        @Override
        public IModelo crear (FabricaFuenteDatos fabricaFuenteDatos) {
            return new IModeloCascada(fabricaFuenteDatos) {
            };
        }
    };

    public abstract IModelo crear (FabricaFuenteDatos fabricaFuenteDatos);
}
