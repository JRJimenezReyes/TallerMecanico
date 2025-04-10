package org.iesalandalus.programacion.tallermecanico.vista;

import java.util.Map;
import java.util.TreeMap;

public enum Opcion {
    INSERTAR_CLIENTE(11,"Insertar Cliente"),
    BUSCAR_CLIENTE(12,"Buscar Cliente"),
    BORRAR_CLIENTE(13,"Borrar Cliente"),
    LISTAR_CLIENTES(14,"Listar Cliente"),
    MODIFICAR_CLIENTE(15,"Modificar Cliente"),
    INSERTAR_VEHICULO(21, "Insertar Vehiculo"),
    BUSCAR_VEHICULO(22, "Buscar Vehiculo"),
    BORRAR_VEHICULO(23, "Borrar Vehiculo"),
    LISTAR_VEHICULOS(24, "Listar Vehiculos"),
    INSERTAR_REVISION(31, "Buscar Revision"),
    BUSCAR_REVISION(32, "Buscar Revision"),
    BORRAR_REVISION(33, "Borrar Revision"),
    LISTAR_REVISIONES(34, "Listar Revisiones"),
    LISTAR_REVISIONES_CLIENTE(35, "Listar Revisiones por Cliente"),
    LISTAR_REVISIONES_VEHICULO(36, "Listar Revisiones por Vehiculo"),
    ANADIR_HORAS_REVISION(37, "Añadir Horas Revision"),
    ANADIR_PRECIO_MATERIAL_REVISION(38, "Añadir Precio Material Revision"),
    CERRAR_REVISION(39, "Cerrar Revision"),
    SALIR(0, "Salir");

    private final int numeroOpcion;
    private final String mensaje;
    private final static Map<Integer, String> opciones = new TreeMap<Integer, String>(){
        private static final long serialVersionUID = 1L;
        {
            put(11, "INSERTAR_CLIENTE");
            put(12, "BUSCAR_CLIENTE");
            put(13, "BORRAR_CLIENTE");
            put(14, "LISTAR_CLIENTES");
            put(15, "MODIFICAR_CLIENTE");
            put(21, "INSERTAR_VEHICULO");
            put(22, "BUSCAR_VEHICULO");
            put(23, "BORRAR_VEHICULO");
            put(24, "LISTAR_VEHICULOS");
            put(31, "INSERTAR_REVISION");
            put(32, "BUSCAR_REVISION");
            put(33, "BORRAR_REVISION");
            put(34, "LISTAR_REVISIONES");
            put(35, "LISTAR_REVISIONES_CLIENTE");
            put(36, "LISTAR_REVISIONES_VEHICULO");
            put(37, "ANADIR_HORAS_REVISION");
            put(38, "ANADIR_PRECIO_MATERIAL_REVISION");
            put(39, "CERRAR_REVISION");
            put(0, "SALIR");
        }
    };

    private Opcion(int numeroOpcion , String mensaje) {
        this.numeroOpcion = numeroOpcion;
        this.mensaje = mensaje;
    }
    public static boolean esValida (int numeroOpcion){
        return opciones.containsKey(numeroOpcion);

    }
    public static Opcion get(int numeroOpcion){
        return valueOf(opciones.get(numeroOpcion));
    }

    @Override
    public String toString() {
        return this.numeroOpcion + ".-" + this.mensaje;
    }
}

