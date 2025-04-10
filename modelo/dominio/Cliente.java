package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;

public class Cliente {

    private static final String ER_NOMBRE = "^([A-ZÁÉÍÓÚÑ][a-záéíóúñ]+(?: [A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)*)$";
    private static final String ER_DNI = "^\\d{8}[TRWAGMYFPDXBNJZSQVHLCKE]$";
    private static final String ER_TELEFONO = "^\\d{9}$";
    private String nombre;
    private String dni;
    private String telefono;

    public Cliente(String nombre, String dni, String telefono) {
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }

    public Cliente(Cliente cliente) {
        Objects.requireNonNull(cliente,"No es posible copiar un cliente nulo.");
        this.nombre = cliente.nombre;
        this.dni = cliente.dni;
        this.telefono = cliente.telefono;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre){
        Objects.requireNonNull(nombre,"El nombre no puede ser nulo.");
        if(!nombre.matches(ER_NOMBRE)) {
            throw new IllegalArgumentException("El nombre no tiene un formato válido.");
        }
        this.nombre = nombre;
    }

    public String getDni() {
        return this.dni;
    }

    private void setDni(String dni){
        Objects.requireNonNull(dni, "El DNI no puede ser nulo.");
        if(!dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("El DNI no tiene un formato válido.");
        }
        if(!comprobarLetraDni(dni)) throw new IllegalArgumentException("La letra del DNI no es correcta.");
        this.dni = dni;
    }

    private static boolean comprobarLetraDni(String dni) {
        String seq = "TRWAGMYFPDXBNJZSQVHLCKE";
        String numero = dni.substring(0, 8);
        int numDNI = Integer.parseInt(numero);
        char letraDNI = dni.charAt(8);
        char letraCalculada = seq.charAt(numDNI % 23);
        return letraDNI == letraCalculada;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        Objects.requireNonNull(telefono, "El teléfono no puede ser nulo.");
        if(!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
        }
        this.telefono = telefono;
    }

    public static Cliente get(String dni){
        Objects.requireNonNull(dni, "El DNI no puede ser nulo.");
        if(!dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("El DNI no tiene un formato válido.");
        }
        if(!comprobarLetraDni(dni)) throw new IllegalArgumentException("La letra del DNI no es correcta.");
        return new Cliente("Desconocido",dni,"123456789");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        return Objects.equals(dni, other.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    public String toString() {
        return String.format("%s - %s (%s)",this.nombre, this.dni, this.telefono);
    }

}
