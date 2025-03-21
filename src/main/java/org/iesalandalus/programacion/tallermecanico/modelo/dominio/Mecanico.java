package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;

public class Mecanico extends Trabajo {
    private static final float FACTOR_HORA = 30;
    private static final float FACTOR_PRECIO_MATERIAL = 1.5F;
    private float precioMaterial;


    public Mecanico(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio){
        super(cliente,vehiculo,fechaInicio);
    }


    public Mecanico(Mecanico mecanico){
        super(mecanico.getCliente(),mecanico.getVehiculo(),mecanico.getFechaInicio());
    }




    public float anadirPrecioMaterial() {
        return precioMaterial = precioMaterial + getPrecioMaterial();
    }


    @Override
    public float getPrecioEspecifico() {
        return getHoras() * FACTOR_HORA + getPrecioMaterial() * FACTOR_PRECIO_MATERIAL;
    }




    @Override
    public String toString() {
        String fechaIF = getFechaFin().format(FORMATO_FECHA);


        if (getFechaFin() == null){


            return String.format("%s - %s (%s) - %s %s - %s: (%s - ), %s horas, %.2f € en material", getCliente().getNombre(), getCliente().getDni(), getCliente().getTelefono(), getVehiculo().marca(), getVehiculo().modelo(), getVehiculo().matricula(), fechaIF, getHoras(), getPrecioMaterial());
        }


        String fechaFF = getFechaFin().format(FORMATO_FECHA);
        return String.format("%s - %s (%s) - %s %s - %s: (%s - %s), %s horas, %.2f € en material, %.2f € total", getCliente().getNombre(), getCliente().getDni(), getCliente().getTelefono(), getVehiculo().marca(), getVehiculo().modelo(), getVehiculo().matricula(), fechaIF, fechaFF, getHoras(), getPrecioMaterial(), getPrecio());
    }




}
