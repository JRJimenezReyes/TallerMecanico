package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public record Vehiculo(
        String marca,
        String modelo,
        String matricula
) {

    private static final String PATRON_MARCA = "^(Seat|Land Rover|KIA|Rolls-Royce|SsangYong)$";
    private static final String PATRON_MATRICULA = "^[0-9]{4}[B-Z]{3}$";

    public Vehiculo {
        if (marca == null) {
            throw new NullPointerException("La marca no puede ser nula.");
        }
        if (!Pattern.matches(PATRON_MARCA, marca)) {
            throw new IllegalArgumentException("La marca no tiene un formato válido.");
        }
        if (modelo == null) {
            throw new NullPointerException("El modelo no puede ser nulo.");
        }
        if (modelo.isBlank()) {
            throw new IllegalArgumentException("El modelo no puede estar en blanco.");
        }
        if (matricula == null) {
            throw new NullPointerException("La matrícula no puede ser nula.");
        }
        if (!Pattern.matches(PATRON_MATRICULA, matricula)) {
            throw new IllegalArgumentException("La matrícula no tiene un formato válido.");
        }
    }

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return  marca;
    }

    public static Vehiculo get(String matricula, List<Vehiculo> vehiculos) {
        if (matricula == null) {
            throw new NullPointerException("La matrícula no puede ser nula.");
        }
        if (!Pattern.matches(PATRON_MATRICULA, matricula)) {
            throw new IllegalArgumentException("La matrícula no tiene un formato válido.");
        }
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.matricula.equals(matricula)) {
                return vehiculo;
            }
        }
        return null;
    }

    public int compareTo(Vehiculo other) {
        int cmp = marca.compareTo(other.marca);
        if (cmp == 0) {
            cmp = modelo.compareTo(other.modelo);
        }
        if (cmp == 0) {
            return matricula.compareTo(other.matricula);
        }
        return cmp;
    }
}
