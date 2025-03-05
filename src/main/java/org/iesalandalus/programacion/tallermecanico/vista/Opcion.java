package org.iesalandalus.programacion.tallermecanico.vista;

import java.util.HashMap;
import java.util.Map;

public enum Opcion {
    INSERTAR_CLIENTE(11, "Insertar cliente"),
    BUSCAR_CLIENTE(12, "Buscar cliente"),
    BORRAR_CLIENTE(13, "Borrar cliente"),
    LISTAR_CLIENTES(14, "Listar clientes"),
    MODIFICAR_CLIENTE(15, "Modificar cliente"),
    INSERTAR_VEHICULO(21, "Insertar vehículo"),
    BUSCAR_VEHICULO(22, "Buscar vehículo"),
    BORRAR_VEHICULO(23, "Borrar vehículo"),
    LISTAR_VEHICULOS(24, "Listar vehículos"),
    INSERTAR_REVISION(31, "Insertar revisión"),
    BUSCAR_REVISION(32, "Buscar revisión"),
    LISTAR_REVISIONES(33, "Listar revisiones"),
    LISTAR_REVISIONES_CLIENTE(34, "Listar revisiones de un cliente"),
    LISTAR_REVISIONES_VEHICULO(35, "Listar revisiones de un vehículo"),
    ANADIR_HORAS_REVISION(36, "Añadir horas a revisión"),
    ANADIR_PRECIO_MATERIAL_REVISION(37, "Añadir precio de material a revisión"),
    SALIR(0, "Salir");

    private final int numeroOpcion;
    private final String mensaje;
    private static final Map<Integer, Opcion> opciones = new HashMap<>();
    static {
        for (Opcion op : Opcion.values()) {
            opciones.put(op.numeroOpcion, op);
        }
    }

    Opcion(int numeroOpcion, String mensaje) {
        this.numeroOpcion = numeroOpcion;
        this.mensaje = mensaje;
    }

    public static boolean esValida(int numeroOpcion) {
        return opciones.containsKey(numeroOpcion);
    }

    public static Opcion get(int numeroOpcion) {
        if(!esValida(numeroOpcion)) {
            throw new IllegalArgumentException("Opción no válida.");
        }
        return opciones.get(numeroOpcion);
    }

    @Override
    public String toString () {
        return String.format("%s - %s", numeroOpcion, mensaje);
    }
}
