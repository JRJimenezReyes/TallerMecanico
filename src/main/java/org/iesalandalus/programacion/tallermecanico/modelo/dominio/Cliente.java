package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
    private static final String ER_NOMBRE = "([A-Z][a-z]+)( [A-Z][a-z]+)*"; //([A-Z][a-z]+)( [A-Z][a-z]+)*
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
        this.dni = dni;
    }

    private boolean comprobarLetraDni(String dni) {
        int []
        while (dni.length() < 8) {

        }

        return !(dni.matches(ER_DNI));
    }
}
