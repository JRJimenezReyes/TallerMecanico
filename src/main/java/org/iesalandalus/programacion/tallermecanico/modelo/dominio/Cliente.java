package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;

public class Cliente implements Comparable<Cliente> {

    private String dni;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;

    public Cliente(String dni, String nombre, String apellidos, String telefono, String email) {
        if (dni == null || dni.isEmpty()) {
            throw new IllegalArgumentException("El DNI no puede ser nulo o vacío.");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        if (apellidos == null || apellidos.isEmpty()) {
            throw new IllegalArgumentException("Los apellidos no pueden ser nulos o vacíos.");
        }
        if (telefono != null && !telefono.matches("[\\d]{9}")) {
            throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
        }
        if (email != null && !email.matches("[\\w\\.]+@[\\w\\.]+\\.[\\w]{2,4}")) {
            throw new IllegalArgumentException("El email no tiene un formato válido.");
        }
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
    }

    public Cliente(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("El cliente no puede ser nulo.");
        }
        this.dni = cliente.dni;
        this.nombre = cliente.nombre;
        this.apellidos = cliente.apellidos;
        this.telefono = cliente.telefono;
        this.email = cliente.email;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        if (apellidos == null || apellidos.isEmpty()) {
            throw new IllegalArgumentException("Los apellidos no pueden ser nulos o vacíos.");
        }
        this.apellidos = apellidos;
    }

    public void setTelefono(String telefono) {
        if (telefono != null && !telefono.matches("[\\d]{9}")) {
            throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
        }
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        if (email != null && !email.matches("[\\w\\.]+@[\\w\\.]+\\.[\\w]{2,4}")) {
            throw new IllegalArgumentException("El email no tiene un formato válido.");
        }
        this.email = email;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Cliente other = (Cliente) obj;
        return Objects.equals(dni, other.dni);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public int compareTo(Cliente other) {
        return dni.compareTo(other.dni);
    }
}
