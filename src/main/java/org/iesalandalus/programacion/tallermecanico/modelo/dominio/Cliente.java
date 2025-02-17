package org.iesalandalus.programacion.tallermecanico.modelo.dominio;


import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;

import java.util.Objects;

public class Cliente {
    private static final String ER_NOMBRE = "[A-Z][a-záéíóú]+( [A-Z][a-záéíóú]+)*";
    private static final String ER_DNI = "\\d{8}[A-Z]";
    private static final String ER_TELEFONO = "\\d{9}";
    private String nombre;
    private String dni;
    private String telefono;
    public Clientes coleccionClientes;
    public Revision cliente;

    public Cliente(String nombre, String dni, String telefono){
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }

    public Cliente(Cliente cliente){
        this(cliente.nombre, cliente.dni, cliente.telefono);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(!nombre.matches(ER_NOMBRE)){
            throw new IllegalArgumentException("El nombre debe estar en el formato correcto.");
        }
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo.");
    }

    public String getDni() {
        return dni;
    }

    private void setDni(String dni) {
        if(!dni.matches(ER_DNI)){
            throw new IllegalArgumentException("El dni debe estar en el formato correcto.");
        }
        this.dni = Objects.requireNonNull(dni, "El dni no puede ser nulo.");
    }

    private boolean comprobarLetraDni(String dni){
        if (dni == null || !dni.matches(ER_DNI)) {
            return false;
        }
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numeroDni = Integer.parseInt(dni.substring(0, 8));
        char letraCalculada = letras.charAt(numeroDni % 23);
        char letraDni = dni.charAt(8);
        return letraCalculada == letraDni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if(!telefono.matches(ER_TELEFONO)){
            throw new IllegalArgumentException("El telefono debe estar en el formato correcto.");
        }
        this.telefono = Objects.requireNonNull(telefono, "El telefono no puede ser nulo.");
    }

    //Crea nuevo cliente
    public static Cliente get(String dni){
        return new Cliente("Adrian", dni, "684251101");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente1 = (Cliente) o;
        return Objects.equals(nombre, cliente1.nombre) && Objects.equals(dni, cliente1.dni) && Objects.equals(telefono, cliente1.telefono) && Objects.equals(coleccionClientes, cliente1.coleccionClientes) && Objects.equals(cliente, cliente1.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, dni, telefono, coleccionClientes, cliente);
    }

    @Override
    public String toString() {
        return String.format("[nombre=%s, dni=%s, telefono=%s, coleccionClientes=%s, cliente=%s]", nombre, dni, telefono, coleccionClientes, cliente);
    }
}



























