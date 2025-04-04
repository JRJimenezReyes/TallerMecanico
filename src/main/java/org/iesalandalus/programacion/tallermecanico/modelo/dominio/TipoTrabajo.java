package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

public enum TipoTrabajo {
    MECANICO("Mecánico"),
    REVISION("Revisión");

    private String nombre;

    TipoTrabajo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static TipoTrabajo get(Trabajo trabajo) {
        if (trabajo == null) {
            throw new IllegalArgumentException("El trabajo no puede ser nulo.");
        }
        if (trabajo instanceof Mecanico) {
            return MECANICO;
        } else if (trabajo instanceof Revision) {
            return REVISION;
        } else {
            throw new IllegalArgumentException("Tipo de trabajo desconocido.");
        }
    }

}

