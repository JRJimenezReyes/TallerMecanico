package org.iesalandalus.programacion.tallermecanico.Vista;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.util.HashMap;
import java.util.Map;

public enum Opcion {

    INSERTAR_CLIENTE(1,"Insertar un cliente."),
    BUSCAR_CLIENTE(2,"Buscar un cliente."),
    BORRAR_CLIENTE(3,"Borrar un cliente."),
    LISTAR_CLIENTES(4,"Listar clientes."),
    MODIFICAR_CLIENTE(5,"Modificar un cliente."),
    INSERTAR_VEHICULO(6,"Insertar un vehiculo."),
    BUSCAR_VEHICULO(7,"Buscar un vehiculo."),
    BORRAR_VEHICULO(8,"Borrar un vehiculo."),
    LISTAR_VEHICULOS(9,"Listar vehículos."),
    INSERTAR_REVISION(10,"Insertar una revisión."),
    BUSCAR_REVISION(11, "Buscar una revisión."),
    BORRAR_REVISION(12,"Borrar una revisión."),
    LISTAR_REVISIONES(13,"Listar revisiones."),
    LISTAR_REVISIONES_CLIENTE(14,"Listar revisiones de un cliente."),
    LISTAR_REVISIONES_VEHICULO(15,"Listar revisiones de un vehiculo."),
    ANADIR_HORAS_REVISION(16,"Añadir horas a una revisión."),
    ANADIR_PRECIO_MATERIAL_REVISION(17,"Añadir precio de material a una revisión."),
    CERRAR_REVISION(18,"Cerrar una revisión."),
    SALIR(0,"Salir.");


    private static final Map<Integer, Opcion> opciones = new HashMap<>();
    private String mensaje;
    private int numeroOpcion;

    private Opcion(int numeroOpcion, String mensaje) {
        this.mensaje = mensaje;
        this.numeroOpcion = numeroOpcion;
    }
    //Ahora mismo solo tenemos el mapa creado hay que inicializaro y rellenarlo con las opciones asi:

    static {
        //esto abre un bloque static

        for (Opcion opcion : values()){
            //Hacemos un bucle que recorre todas las opciones del enumerado colocando values() recorre el enum
            opciones.put(opcion.numeroOpcion,opcion);
            //con esto le digo que por cada opcion coja el número de opcion asociado y lo coloque

        }
    }

    public static boolean esValida(int numeroOpcion){
        return opciones.containsKey(numeroOpcion);
        //Para un mapa el constainskey toma como si ese número que funciona como CLAVE existe.
    }

    public static Opcion get(int numeroOpcion){
        if (esValida(numeroOpcion)) {
            return opciones.get(numeroOpcion);
        } else throw new IllegalArgumentException("El número de opción no es valido.");
    }

    @Override
    public String toString() {
        return String.format("%s: %s",numeroOpcion, mensaje);
    }

}
