package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.HashMap;
import java.util.Map;

public enum Evento {
    INSERTAR_CLIENTE(11, "Insertar cliente."),
    BUSCAR_CLIENTE(12, "Buscar un cliente."),
    BORRAR_CLIENTE(13, "Borrar un cliente."),
    LISTAR_CLIENTES(14, "Lista los clientes."),
    MODIFICAR_CLIENTE(15, "Modifica un cliente."),
    INSERTAR_VEHICULO(21, "Inserta un vehículo."),
    BUSCAR_VEHICULO(22, "Busca un vehículo."),
    BORRAR_VEHICULO(23,"Borra un vehículo."),
    LISTAR_VEHICULOS(24, "Lista los vehículos."),
    INSERTAR_REVISION(30, "Inserta una revisión."),
    INSERTAR_MECANICO(31, "Inserta un mecanico."),
    BUSCAR_TRABAJO(32, "Busca una revisión."),
    BORRAR_TRABAJO(33, "Borra una revisión."),
    LISTAR_TRABAJOS(34, "Lista las revisiones."),
    LISTAR_TRABAJOS_CLIENTE(35, "Lista las revisiones de un cliente."),
    LISTAR_TRABAJOS_VEHICULO(36, "Lista las revisiones de un vehículo."),
    ANADIR_HORAS_TRABAJO(37,"Añade las horas de revisión."),
    ANADIR_PRECIO_MATERIAL_TRABAJO(38, "Añade el precio del material de revisión."),
    CERRAR_TRABAJO(39, "Cierra la revisión."),
    SALIR(0, "Salir.");

    private int codigo;
    private String texto;
    private static final  Map <Integer, Evento> eventos = new HashMap<>();

    static {
        for (Evento evento : Evento.values()) {
            eventos.put(evento.codigo, evento);
        }
    }

    Evento(int codigo, String texto){
        this.codigo = codigo;
        this.texto = texto;
    }

    public int getCodigo() {
        return codigo;
    }

    public static boolean esValido(int codigo) {
        return eventos.containsKey(codigo);
    }
    public static Evento get(int codigo) {
        if(!esValido(codigo)){
            throw new IllegalArgumentException("El código no es correcto.");
        }
        return eventos.get(codigo);
    }

    @Override
    public String toString() {
        return texto;
    }
}
