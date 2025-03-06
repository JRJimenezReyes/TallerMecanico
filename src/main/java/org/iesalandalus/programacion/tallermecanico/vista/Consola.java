package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.utilidades.Entrada;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {

    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";
    private Consola() {}

    public static void mostrarCabecera(String mensaje) {
        System.out.println(mensaje);
        System.out.println("-----------------");
    }


    public static void mostrarMenu() {
        mostrarCabecera("Gestión de Revisiones - Menú Principal");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion);
        }
    }

    public static Opcion elegirOpcion() {
        int numeroOpcion = leerEntero("Elige una opción: ");
        if (Opcion.esValida(numeroOpcion)) {
            return Opcion.get(numeroOpcion);
        } else {
            System.out.println("Opción no válida, intentalo de nuevo.");
            return elegirOpcion();
        }
    }

    private static float leerReal (String mensaje) {
        System.out.println(mensaje);
        return Entrada.real();
    }

    private static int leerEntero(String mensaje) {
        System.out.println(mensaje);
        return Entrada.entero();
    }

    private static String leerCadena (String mensaje) {
        System.out.print(mensaje);
        return Entrada.cadena();
    }

    private static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);
        do {
            try {
                System.out.println(mensaje + " (" + CADENA_FORMATO_FECHA + "):");
                String fechaString = Entrada.cadena();
                fecha = LocalDate.parse(fechaString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha no válido. Inténtalo de nuevo.");
            }
        } while (fecha == null);
        return fecha;
    }


    public static Cliente leerCliente() {
        String nombre = leerCadena("Introduce el nombre del cliente: ");
        String dni = leerCadena("Introduce el DNI del cliente: ");
        String telefono = leerCadena("Introduce el teléfono del cliente: ");
        return new Cliente(nombre, dni, telefono);
    }

    public static Cliente leerClienteDni() {
        String dni = leerCadena("Introduce el DNI del cliente: ");
        return new Cliente("Desconocido", dni, "Desconocido");
    }

    public static String leerNuevoNombre() {
        return leerCadena("Introduce el nuevo nombre del cliente: ");
    }

    public static String leerNuevoTelefono() {
        return leerCadena("Introduce el nuevo teléfono del cliente: ");
    }

    public static Vehiculo leerVehiculo() {
        String marca = leerCadena("Introduce la marca del vehículo: ");
        String modelo = leerCadena("Introduce el modelo del vehículo: ");
        String matricula = leerCadena("Introduce la matrícula del vehículo: ");
        return new Vehiculo(marca, modelo, matricula);
    }

    public static Vehiculo leerVehiculoMatricula() {
        String matricula = leerCadena("Introduce la matrícula del vehículo: ");
        return new Vehiculo("Desconocida", "Desconocido", matricula);
    }

    public static Revision leerRevision() {
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate fecha = leerFecha("Introduce la fecha de la revisión (dd/MM/yyyy): ");
        int horas = leerHoras();
        float precioMaterial = leerPrecioMaterial();
        return new Revision(cliente, vehiculo, fecha);
    }

    public static int leerHoras() {
        return leerEntero("Introduce la cantidad de horas de la revisión: ");
    }

    public static float leerPrecioMaterial() {
        return leerReal("Introduce el precio del material: ");
    }

    public static LocalDate leerFechaCierre() {
        LocalDate fecha = leerFecha("Introduce la fecha de cierre (" + CADENA_FORMATO_FECHA + "): ");
        System.out.println("Fecha ingresada: " + fecha.format(DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA)));
        return fecha;
    }

}
