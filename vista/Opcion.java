package org.iesalandalus.programacion.tallermecanico.vista;

import java.util.Map;
import java.util.TreeMap;

public enum Opcion {

    INSERTAR_CLIENTE(1,"Insertar Cliente"),
    BUSCAR_CLIENTE(2,"Buscar Cliente"),
    BORRAR_CLIENTE(3,"Borrar Cliente"),
    LISTAR_CLIENTES(4,"Listar Cliente"),
    MODIFICAR_CLIENTE(5,"Modificar Cliente"),
    INSERTAR_VEHICULO(6, "Insertar Vehiculo"),
    BUSCAR_VEHICULO(7, "Buscar Vehiculo"),
    BORRAR_VEHICULO(8, "Borrar Vehiculo"),
    LISTAR_VEHICULOS(9, "Listar Vehiculos"),
    INSERTAR_REVISION(10, "Buscar Revision"),
    BUSCAR_REVISION(11, "Buscar Revision"),
    BORRAR_REVISION(12, "Borrar Revision"),
    LISTAR_REVISIONES(13, "Listar Revisiones"),
    LISTAR_REVISIONES_CLIENTE(14, "Listar Revisiones por Cliente"),
    LISTAR_REVISIONES_VEHICULO(15, "Listar Revisiones por Vehiculo"),
    ANADIR_HORAS_REVISION(16, "Añadir Horas Revision"),
    ANADIR_PRECIO_MATERIAL_REVISION(17, "Añadir Precio Material Revision"),
    CERRAR_REVISION(18, "Cerrar Revision"),
    SALIR(19, "Salir");

    private int numeroOpcion;
    private String mensaje;
    private static Map<Integer, String> opciones = new TreeMap<Integer, String>(){
        private static final long serialVersionUID = 1L;
        {
            put(1, "INSERTAR_CLIENTE");
            put(2, "BUSCAR_CLIENTE");
            put(3, "BORRAR_CLIENTE");
            put(4, "LISTAR_CLIENTES");
            put(5, "MODIFICAR_CLIENTE");
            put(6, "INSERTAR_VEHICULO");
            put(7, "BUSCAR_VEHICULO");
            put(8, "BORRAR_VEHICULO");
            put(9, "LISTAR_VEHICULOS");
            put(10, "INSERTAR_REVISION");
            put(11, "BUSCAR_REVISION");
            put(12, "BORRAR_REVISION");
            put(13, "LISTAR_REVISIONES");
            put(14, "LISTAR_REVISIONES_CLIENTE");
            put(15, "LISTAR_REVISIONES_VEHICULO");
            put(16, "ANADIR_HORAS_REVISION");
            put(17, "ANADIR_PRECIO_MATERIAL_REVISION");
            put(18, "CERRAR_REVISION");
            put(19, "SALIR");
        }
    };

    private Opcion(int numeroOpcion, String mensaje) {
        this.numeroOpcion = numeroOpcion;
        this.mensaje = mensaje;
    }

    public static boolean esValida(int numeroOpcion) {
        return opciones.containsKey(numeroOpcion);
    }

    public static Opcion get(int numeroOpcion) {
        return Opcion.valueOf(opciones.get(numeroOpcion));
    }

    public String toString() {
        return this.numeroOpcion + ".- " + this.mensaje;
    }
}
