package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

public enum TipoTrabajo {
    MECANICO(""),
    REVISION("");

    private String nombre;

    private TipoTrabajo(String nombre) {
        this.nombre = nombre;
    }

    public static TipoTrabajo get(Trabajo trabajo) {
        TipoTrabajo tipoTrabajo = null;
        if (trabajo instanceof Revision) {

        } else if (trabajo instanceof Mecanico) {

        }
        return tipoTrabajo;
    }

    @Override
    public String toString() {
        return String.format("[nombre=%s]", nombre);
    }
}