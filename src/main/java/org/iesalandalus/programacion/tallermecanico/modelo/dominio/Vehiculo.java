package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;

import java.util.Objects;

public record Vehiculo(String marca, String modelo, String matricula) {
    private static final String ER_MARCA =  "^([A-Z]+[a-z]*)([A-Z]*[a-z]*)*( [A-Z]*[a-z]*)*(-[A-Z][a-z-]*)*";
    private static final String ER_MATRICULA = "^\\d{4}[^\\W\\dAEIOUa-z_]{3}";

    public Vehiculo {
        validarMarca(marca);
        validarModelo(modelo);
        validarMatricula(matricula);
    }

    private String validarMarca(String marca) {
        Objects.requireNonNull(marca, "La marca no puede ser nula.");
        if (!(marca.matches(ER_MARCA))) {
            throw new IllegalArgumentException("La marca no tiene un formato válido.");
        }
        return marca;
    }

    private String validarMatricula(String matricula) {
        Objects.requireNonNull(matricula, "La matrícula no puede ser nula.");
        if (!(matricula.matches(ER_MATRICULA))) {
            throw new IllegalArgumentException("La matrícula no tiene un formato válido.");
        }
        return matricula;
    }

    private String validarModelo(String modelo) {
        Objects.requireNonNull(modelo, "El modelo no puede ser nulo.");
        if (modelo.isBlank()) {
            throw new IllegalArgumentException("El modelo no puede estar en blanco.");
        }
        return modelo;
    }

    public static Vehiculo get(String matricula) {
        return new Vehiculo("Volkswagen", "Golf", matricula);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return Objects.equals(matricula, vehiculo.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public String toString() {
        return String.format("%s %s - %s",marca , modelo, matricula);
    }
}
