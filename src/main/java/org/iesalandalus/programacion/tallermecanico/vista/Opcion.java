package org.iesalandalus.programacion.tallermecanico.vista;

import java.util.HashMap;
import java.util.Map;

public enum Opcion {

    SALIR("Salir", 0),
    NUEVA_REVISION("Nueva revisión", 1),
    LISTAR_REVISION("Listar revisiones", 2),
    BUSCAR_REVISION("Buscar revisión", 3),
    BORRAR_REVISION("Borrar revisión", 4),
    MODIFICAR_REVISION("Modificar revisión", 5),
    CERRAR_REVISION("Cerrar revisión", 6),
    SALIR_SIN_GUARDAR("Salir sin guardar", 7);

    private final String texto;
    private final int numero;

    private static final Map<Integer, Opcion> OPCIONES_POR_NUMERO = new HashMap<>();

    static {
        for (Opcion opcion : values()) {
            OPCIONES_POR_NUMERO.put(opcion.numero, opcion);
        }
    }

    private Opcion(String texto, int numero) {
        this.texto = texto;
        this.numero = numero;
    }

    public String getTexto() {
        return texto;
    }

    public int getNumero() {
        return numero;
    }

    public static boolean esValida(int numero) {
        return OPCIONES_POR_NUMERO.containsKey(numero);
    }

    public static Opcion get(int numero) throws IllegalArgumentException {
        if (!esValida(numero)) {
            throw new IllegalArgumentException("La opción " + numero + " no es válida");
        }
        return OPCIONES_POR_NUMERO.get(numero);
    }

    @Override
    public String toString() {
        return numero + " - " + texto;
    }
}

