package org.iesalandalus.programacion.tallermecanico.dominio;

import org.iesalandalus.programacion.utilidades.Entrada;

import java.util.Objects;

public class Cliente {
    private final static String ER_NOMBRE = "^[A-Z][a-z]+([A-Z][a-z]+)*$";
    private final static String ER_DNI = "^[0-9]{8}{A-Z}$";
    private final static String ER_TELEFONO = "^[0-9]{9}$";
    private String nombre;
    private String dni;
    private String telefono;

    public Cliente (String nombre, String dni, String telefono) {
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }

    public Cliente(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("El nombre no puede ser nulo.");
        }
        if (nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar en blanco.");
        }
    }

    public String getNombre() {
        if (nombre == null) {
            throw new NullPointerException("El nombre no puede ser nulo.");
        } else if (nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar en blanco.");
        }
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = Objects.requireNonNull(nombre, "Nombre no puede ser nulo.");
    }

    public String getDni() {
        if (dni == null) {
            throw new NullPointerException("El nombre no puede ser nulo.");
        }
        return dni;
    }

    private void setDni(String dni) {
        this.dni = Objects.requireNonNull(dni, "Nombre no puede ser nulo.");
    }

    private Cliente comprobarLetraDni(String dni) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        return this;
    }

    public String getTelefono() {
        if (telefono == null) {
            throw new NullPointerException("El nombre no puede ser nulo.");
        }
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = Objects.requireNonNull(telefono, "Nombre no puede ser nulo.");
    }

    public static boolean get(String dni) {

    }


}