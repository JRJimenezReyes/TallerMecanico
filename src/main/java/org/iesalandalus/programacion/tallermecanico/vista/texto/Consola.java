package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Consola {

    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";

    private Consola() {}

    static void mostrarCabecera(String mensaje) {
        System.out.printf("%n%s&n", mensaje);
        String formatoStr = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(formatoStr, 0).replace("0", "-"));
    }

    static void mostrarMenu() {
        mostrarCabecera("Gestión de un taller mecanico.");

        for (Evento opcion : Evento.values()) {
            System.out.printf("%d.- %s%n", opcion.getCodigo(), opcion);
        }
    }

     static Evento elegirOpcion() {
        Evento evento = null;
        do {
            try {
                evento = Evento.get(leerEntero("\nElige una opción: "));
            } catch (IllegalArgumentException e){
                System.out.printf("ERROR: %s%n", e.getMessage());
            }
        } while (evento == null);
        return evento;
    }

     static int leerEntero(String mensaje) {
         System.out.print(mensaje);
         return Entrada.entero();
    }

    static float leerReal(String mensaje) {
        System.out.print(mensaje);
        return Entrada.real();
    }

     static String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return Entrada.cadena();
    }

     static LocalDate leerFecha(String mensaje) {
        LocalDate fecha;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);
        mensaje = String.format("%s (%s): ", mensaje, CADENA_FORMATO_FECHA);

        try {
            fecha = LocalDate.parse(leerCadena(mensaje), formatoFecha);
        } catch (DateTimeParseException e) {
            fecha = null;
        }
        return fecha;
    }

    public static Cliente leerCliente() {
        String dni = leerCadena("Introduce el DNI del cliente: ");
        String nombre = leerCadena("Introduce el nombre del cliente: ");
        String telefono = leerCadena("Introduce el teléfono del cliente: ");
        return new Cliente(nombre,dni,telefono);
    }

    public static Cliente leerClienteDni() {
        String dni = leerCadena("Introduce el DNI del cliente: ");
        return Cliente.get(dni);
    }

    public static String leerNuevoNombre() {
        return leerCadena("Introduce el nuevo nombre: ");
    }

    public static String leerNuevoTelefono() {
        return leerCadena("Introduce el nuevo teléfono: ");
    }

    public static Vehiculo leerVehiculo() {
        String matricula = leerCadena("Introduce la matrícula del vehículo: ");
        String marca = leerCadena("Introduce la marca del vehículo: ");
        String modelo = leerCadena("Introduce el modelo del vehículo: ");
        return new Vehiculo(marca, modelo, matricula);
    }

    public static Vehiculo leerVehiculoMatricula() {
        String matricula = leerCadena("Introduce la matrícula del vehículo: ");
        return Vehiculo.get(matricula);
    }

    public static Trabajo leerRevision() {
        Cliente cliente = leerCliente();
        Vehiculo vehiculo = leerVehiculo();
        LocalDate fechaInicio = leerFecha("Introduce la fecha de inicio de la revisión (dd/MM/yyyy): ");
        return new Revision(cliente,vehiculo,fechaInicio);
    }

    public static int leerHoras() {
        return leerEntero("Introduce las horas de trabajo: ");
    }

    public static float leerPrecioMaterial() {
        return leerReal("Introduce el precio del material: ");
    }

    public static LocalDate leerFechaCierre() {
        return leerFecha("Introduce la fecha de cierre (dd/MM/yyyy): ");
    }
}

