package org.iesalandalus.programacion.tallermecanico.vista;

public enum FabricaVista {
    TEXTO {
        @Override
        public Vista crear () {
            return crear();
        }
    };

    public abstract Vista crear();
}
