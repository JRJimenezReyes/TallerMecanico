package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
    private static final String ER_NOMBRE = "^([A-ZÁÉÍÓÚÜ][a-záéíóú]+)( [A-ZÁÉÍÓÚÜ][a-záéíóú]+)*"; //([A-ZÁÉÍÓÚÜ][a-záéíóú]+)( [A-ZÁÉÍÓÚÜ][a-záéíóú]+)*
    private static final String ER_DNI = "^[0-9]{8}[^\\W\\d_[a-z][IÑOU]]"; //^[0-9]{8}[^\W\d_[a-z][IÑOU]] validacion del dni
    private static final String ER_TELEFONO = "^\\d{9}";


    private String nombre;
    private String dni;
    private String telefono;

    public Cliente(String nombre, String dni, String telefono) {
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }

    public Cliente(Cliente cliente) {
        nombre = cliente.nombre;
        dni = cliente.nombre;
        telefono = cliente.telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        if (!(nombre.matches(ER_NOMBRE))) {
            throw new TallerMecanicoExcepcion("El nombre no es válido.");
        }
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    private void setDni(String dni) {
        Objects.requireNonNull(dni, "El dni no puede ser nulo.");
        if (!(dni.matches(ER_DNI))) {
            throw new TallerMecanicoExcepcion("El dni no e válido.");
        }
        if (comprobarLetraDni(dni) == false) {
            throw new TallerMecanicoExcepcion("El dni no es válido.");
        }
        this.dni = dni;
    }



    private boolean comprobarLetraDni(String dni) {
        boolean dniCorrecto = false;
        char[] letraDni =  {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        String numerosDniCadena;
        numerosDniCadena = dni.substring(0,(dni.length()-2));
        Integer numerosDniEnteros = Integer.valueOf(numerosDniCadena);

        if (letraDni[numerosDniEnteros % 23] == dni.charAt(dni.length()-1)) {
            dniCorrecto = true;
        }

        return dniCorrecto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        Objects.requireNonNull(telefono, "El teléfono no puede ser nulo.");
        if (!(telefono.matches(ER_TELEFONO))) {
            throw new TallerMecanicoExcepcion("El teléfono no es válido");
        }
    }

    public static Cliente get(String dni) {
        return new Cliente("dario", dni, "950132332");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(dni, cliente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return String.format("[nombre=%s, dni=%s, telefono=%s]", nombre, dni, telefono);
    }
}