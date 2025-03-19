package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;

public record Vehiculo(String marca, String modelo, String matricula) {

    private static final String ER_MARCA = "^(?!^\\s+$)([A-Z][A-Za-z]*|[A-Z][a-z]*(?:[-\\s]?[a-z]+)?(?:[-\\s][a-zA-Z][a-z]*)*|[A-Z]+)$";
    private static final String ER_MATRICULA = "^(?![AEIOQU])[0-9]{4}(?![AEIOQUÑ])(?!CH|LL)[BCDFGHJKLMNPRSTVWXYZ]{3}$";

    public Vehiculo{
        validarMarca(marca);
        validarModelo(modelo);
        validarMatricula(matricula);
    }

    private void validarMarca(String marca){
        Objects.requireNonNull(marca,"La marca no puede ser nula.");
        if (!marca.matches(ER_MARCA)){
            throw new IllegalArgumentException("La marca no tiene un formato válido.");
        }
    }

    private void validarModelo(String modelo){
        Objects.requireNonNull(modelo, "El modelo no puede ser nulo.");
        if (modelo.trim().isBlank()){
            throw new IllegalArgumentException("El modelo no puede estar en blanco.");
        }
    }

    private void validarMatricula(String matricula){
        Objects.requireNonNull(matricula, "La matrícula no puede ser nula.");
        if (!matricula.matches(ER_MATRICULA)){
            throw new IllegalArgumentException("La matrícula no tiene un formato válido.");
        }
    }

    public static Vehiculo get(String matricula){
        Objects.requireNonNull(matricula, "La matrícula no puede ser nula.");
        if (!matricula.matches(ER_MATRICULA)){
            throw new IllegalArgumentException("La matrícula no tiene un formato válido.");
        }
        return new Vehiculo("Desconocido","Desconocido", matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vehiculo other = (Vehiculo) obj;
        return Objects.equals(matricula, other.matricula);
    }

    public String toString(){
        return String.format("%s %s - %s", this.marca, this.modelo, this.matricula);
    }
}
