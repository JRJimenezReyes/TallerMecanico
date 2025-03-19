package org.iesalandalus.programacion.tallermecanico.Vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Consola {
    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";
    private Consola(){}

    public static void mostrarCabecera(String mensaje){
        System.out.println(mensaje);
        System.out.println("-".repeat(mensaje.length()).concat(""));
        //Esto lo que hace es poner una cadena y despues el comanodo repetir por lo larga que sea la cadena del mensaje.

    }

    public static void mostrarMenu(){
        mostrarCabecera("Gestor de Taller de Reparación de Vehículos.");
        mostrarCabecera("Menú de opciones: ");
        for (Opcion opcion : Opcion.values()){
            System.out.println(opcion);
        }
    }

    public static Opcion elegirOpcion(){
        Opcion opcion = null;
        do {
            try {
                opcion = Opcion.get(leerEntero("Introduzca el número de opción:"));
            } catch (IllegalArgumentException e) {
                System.out.printf("ERROR: %s%n", e.getMessage());
            }

        } while (opcion == null);


        return opcion;
    }

    private static float leerReal(String mensaje){
        System.out.println(mensaje);
        return Float.parseFloat(Entrada.cadena());

    }

    private static int leerEntero(String mensaje){
        System.out.println(mensaje);
        return Integer.parseInt(Entrada.cadena());

    }

    private static String leerCadena(String mensaje){
        System.out.println(mensaje);
        return Entrada.cadena();
    }

    private static LocalDate leerFecha(String mensaje){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);
        return LocalDate.parse(leerCadena(mensaje),formatter);
    }

    public static Cliente leerCliente(){
        Cliente cliente = new Cliente(leerClienteDNI());
        cliente.setNombre(leerNuevoNombre());
        cliente.setTelefono(leerNuevoTelefono());
        return cliente;
        
    }

    public static Cliente leerClienteDNI(){
        System.out.print("Escriba el DNI del cliente: ");
        String dniCliente = Entrada.cadena();
        return Cliente.get(dniCliente);
    }

    public static String leerNuevoNombre(){
        return leerCadena("Introduzca un nombre para el cliente: ");
    }

    public static String leerNuevoTelefono(){
        return leerCadena("Introduzca un nuevo teléfono para el cliente.");
    }

    public static Vehiculo leerVehiculo(){
    String marca = leerCadena("Introduzca el modelo del vehiculo: ");
    String modelo = leerCadena("Introduzca el modelo del vehiculo: ");
    String matricula = leerCadena("Introduzca la matricula del vehiculo: ");
    return new Vehiculo(marca,modelo,matricula);

    }

    public static Vehiculo leerVehiculoMatricula(){
        return Vehiculo.get(leerCadena("Introduzca la matrícula del vehiculo: "));
    }

    public static Revision leerRevision(){
        return new Revision(leerClienteDNI(),leerVehiculoMatricula(),leerFecha("Introduzca la fecha de la revisión: "));
    }

    public static int leerHoras(){
        return leerEntero("Escriba la cantidad de horas de trabajo: ");
    }

    public static float leerPrecioMaterial(){
        return leerReal("Introduce el precio del material: ");
    }

    public static LocalDate leerFechaCierre(){
        return leerFecha("Introduzca la fecha de cierre de la revisión: ");
    }



}
