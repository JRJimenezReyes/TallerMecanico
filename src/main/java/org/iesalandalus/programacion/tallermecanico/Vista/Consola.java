package org.iesalandalus.programacion.tallermecanico.Vista;

import com.sun.jdi.Value;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Consola {
    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";
    private Consola(){}

    public static void mostrarCebecera(String mensaje){
        System.out.println(mensaje);
        System.out.println("-".repeat(mensaje.length()));
        //Esto lo que hace es poner una cadena y despues el comanodo repetir por lo larga que sea la cadena del mensaje.

    }

    public static void mostrarMenu(){
        mostrarCebecera("Menú de opciones: ");
        mostrarCebecera("Gestor de Taller de Reparación de Vehículos.");
        for (Opcion opcion : Opcion.values()){
            System.out.println(opcion);
        }
    }

    public static Opcion elegirOpcion(){
        int opcion = -1;
        do {

            System.out.print("Introduzca el número de la opción válida: ");
            opcion = Entrada.entero();
        } while (!Opcion.esValida(opcion));

        return Opcion.get(opcion);
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
        System.out.println(mensaje);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);
        return LocalDate.parse(Entrada.cadena(),formatter);
    }

    public static Cliente leerCliente(){
        Cliente cliente = new Cliente(leerClienteDNI());
        cliente.setNombre(leerNuevoNombre());
        cliente.setTelefono(leerNuevoTalefono());
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

    public static String leerNuevoTalefono(){
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
        return new Revision(leerCliente(),leerVehiculo(),leerFecha("Introduzca la fecha de la revisión: "));
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
