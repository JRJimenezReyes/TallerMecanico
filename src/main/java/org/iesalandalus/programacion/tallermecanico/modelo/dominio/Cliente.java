package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;

public class Cliente {

    private String nombre;
    private String dni;
    private String telefono;

    private static final String ER_NOMBRE = "^[A-ZÁÉÍÓÚ][a-záéíóú]+( [A-ZÁÉÍÓÚ][a-záéíóú]+)*$";
    private static final String ER_DNI = "^[0-9]{8}[A-Za-z]$";
    private static final String ER_TELEFONO = "^[0-9]{9}$";

    public Cliente(String nombre, String dni, String telefono) {
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }

    public Cliente(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("No es posible copiar un cliente nulo.");
        }
        this.nombre = cliente.nombre;
        this.dni = cliente.dni;
        this.telefono = cliente.telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        Objects.requireNonNull(nombre, "No es posible copiar un cliente nulo.");
        if (!nombre.matches(ER_NOMBRE)) {
            throw new IllegalArgumentException("El nombre no tiene un formato válido.");
        }
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        Objects.requireNonNull(dni,"El DNI no puede ser nulo.");
        if (!dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("El DNI no tiene un formato válido.");
        }
        if (!comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("La letra del DNI no es correcta.");
        }
        this.dni = dni;
    }

    public static boolean comprobarLetraDni(String dni) {
        String letrasDni = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numero = Integer.parseInt(dni.substring(0, 8));
        char letra = dni.charAt(8);
        return letra == letrasDni.charAt(numero % 23);
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        Objects.requireNonNull(telefono,"El teléfono no puede ser nulo.");
        if (!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
        }
        this.telefono = telefono;
    }


    public static Cliente get(String dni) {
        return new Cliente("Patricio Estrella", dni, "111222333");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente cliente)) return false;
        return Objects.equals(dni, cliente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", nombre, dni, telefono);
    }
}
