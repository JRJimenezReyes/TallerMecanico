package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.TallerMecanicoExcepcion;

import java.time.LocalDate;

public class Mecanico extends Trabajo {
    private static final float FACTOR_HORA = 30;
    private static final float FACTOR_PRECIO_MATERIAL = 1.5f;
    private float precioMaterial ;

    public Mecanico(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente, vehiculo, fechaInicio);
    }

    public Mecanico(Mecanico mecanico) {
        super(mecanico);
        precioMaterial = mecanico.precioMaterial;
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }
    public void anadirPrecioMaterial(float precioMaterial) throws TallerMecanicoExcepcion {
            if (estaCerrado()) {
                throw new TallerMecanicoExcepcion("No se puede añadir precio del material, ya que el trabajo mecánico está cerrado.");
            }
            if (precioMaterial <= 0) {
                throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
            }
            this.precioMaterial += precioMaterial;

    }
    @Override
    public String toString() {
        String cadena;
        if (!estaCerrado()) {
            cadena = String.format("Mecánico -> %s - %s (%s - ): %d horas, %.2f € en material",
                    getCliente(), getVehiculo(),
                    getFechaInicio().format(FORMATO_FECHA), getHoras() , getPrecioMaterial());
        } else {
            cadena = String.format("Mecánico -> %s - %s (%s - %s): %d horas, %.2f € en material, %.2f € total",
                    getCliente(), getVehiculo(),
                    getFechaInicio().format(FORMATO_FECHA),
                    getFechaFin().format(FORMATO_FECHA), getHoras(),getPrecioMaterial(), getPrecio());
        }
        return cadena;
    }



    @Override
    public float getPrecioEspecifico() {
        return (getHoras() *FACTOR_HORA) + (precioMaterial * FACTOR_PRECIO_MATERIAL);
    }


}

