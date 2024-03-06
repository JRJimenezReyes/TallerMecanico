package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

public enum EstadoRevision {

    ABIERTA("Abierta"),
    CERRADA("Cerrada");

    private final String nombre;

    private EstadoRevision(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
