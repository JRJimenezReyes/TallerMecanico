package org.iesalandalus.programacion.tallermecanico.vista;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Consola {

    private static final Scanner SC = new Scanner(System.in);

    public Consola() {
    }

    public static void mostrarCabecera(String mensaje) {
        System.out.println(mensaje);
        String linea = "";
        for (int i = 0; i < mensaje.length(); i++) {
            linea += "-";
        }
        System.out.println(linea);
    }

    public static void mostrarMenu() {
        mostrarCabecera("Taller Mecánico");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion);
        }
    }

    public static double leerReal(String mensaje) {
        mostrarMensaje(mensaje);
        return SC.nextDouble();
    }

    public static int leerEntero(String mensaje) {
        mostrarMensaje(mensaje);
        return SC.nextInt();
    }

    public static String leerCadena(String mensaje) {
        mostrarMensaje(mensaje);
        return SC.nextLine();
    }

    public static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        boolean fechaValida = false;
        do {
            try {
                mostrarMensaje(mensaje);
                fecha = LocalDate.parse(SC.nextLine());
                fechaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Fecha no válida. Introduzca una fecha con el formato dd/mm/aaaa: ");
            }
        } while (!fechaValida);
        return fecha;
    }

    public static Opcion elegirOpcion() {
        int numeroOpcion;
        Opcion opcion;
        do {
            numeroOpcion = leerEntero("Introduzca el número de la opción: ");
            opcion = Opcion.get(numeroOpcion);
            if (!Opcion.esValida(numeroOpcion)) {
                System.out.println("Opción no válida. Introduzca un número entre 0 y " + (Opcion.values().length - 1) + ": ");
            }
        } while (!Opcion.esValida(numeroOpcion));
        return opcion;
    }

    public static void mostrarMensaje(String mensaje) {
        System.out.print(mensaje);
    }

    public static void mostrarMensajeError(String mensaje) {
        System.err.println(mensaje);
    }

    public static void confirmarSalida() {
        System.out.println("¿Está seguro de que desea salir? (S/N)");
        String respuesta = SC.nextLine().toUpperCase();
        if (respuesta.equals("S")) {
            System.out.println("Saliendo del programa...");
        } else {
            System.out.println("Cancelando la salida.");
        }
    }

    public static void mostrarInformacion(String mensaje) {
        System.out.println(mensaje);
    }
}

