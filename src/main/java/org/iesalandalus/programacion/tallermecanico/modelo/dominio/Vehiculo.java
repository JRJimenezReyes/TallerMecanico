package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Vehiculos;

import java.util.Objects;

public record Vehiculo(String marca, String modelo, String matricula) {

    private static final String ER_MARCA = "^(?!.*\\b[A-ZÁÉÍÓÚÑ]{2}-[A-ZÁÉÍÓÚÑ]{2}\\b)(?:[A-ZÁÉÍÓÚÑ][a-záéíóúñ]*(?:[A-ZÁÉÍÓÚÑ][a-záéíóúñ]*)*|\\b[A-ZÁÉÍÓÚÑ]+\\b)(?:[\\s-](?:[A-ZÁÉÍÓÚÑ][a-záéíóúñ]*(?:[A-ZÁÉÍÓÚÑ][a-záéíóúñ]*)*|\\b[A-ZÁÉÍÓÚÑ]+\\b))*$";
    private static final String ER_MATRICULA = "^[0-9]{4}[BCDFGHJKLMNPRSTVWXYZ]{3}$";
    public static Vehiculos coleccionVehiculos;

    public Vehiculo{
        validarMarca(marca);
        validarModelo(modelo);
        validarMatricula(matricula);
    }

    private void validarMarca(String marca){
        Objects.requireNonNull(marca, "La marca no puede ser nula.");
        if(!marca.matches(ER_MARCA)){
            throw new IllegalArgumentException("La marca no tiene un formato válido.");
        }
    }

    private void validarModelo(String modelo){
        Objects.requireNonNull(modelo,"El modelo no puede ser nulo.");
        if(modelo.trim().isEmpty()){
            throw new IllegalArgumentException("El modelo no puede estar en blanco.");
        }
    }

    private void validarMatricula(String matricula){
        Objects.requireNonNull(matricula, "La matrícula no puede ser nula.");
        if(!matricula.matches(ER_MATRICULA)){
            throw new IllegalArgumentException("La matrícula no tiene un formato válido.");
        }

    }

    public static Vehiculo get(String matricula){
        Objects.requireNonNull(matricula, "La matrícula no puede ser nula.");
        if(!matricula.matches(ER_MATRICULA)){
            throw new IllegalArgumentException("La matrícula no tiene un formato válido.");
        }
        return new Vehiculo("KIA", "ceed", matricula);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) obj;
        return Objects.equals(matricula, vehiculo.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public String toString() {
        return String.format("%s %s - %s", marca, modelo, matricula);
    }
}
