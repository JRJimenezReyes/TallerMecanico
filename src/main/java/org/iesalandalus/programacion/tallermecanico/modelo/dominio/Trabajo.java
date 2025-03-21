package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.TallerMecanicoExcepcion;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Objects;

public abstract class Trabajo {
    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final float FACTOR_DIA = 10;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;

    protected Trabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
        fechaFin = null;
        horas = 0;
    }

    protected Trabajo(Trabajo trabajo) {
        Objects.requireNonNull(trabajo,"El trabajo no puede ser nulo.");
        cliente = new Cliente(trabajo.cliente);
        vehiculo = trabajo.vehiculo;
        fechaInicio = trabajo.fechaInicio;
        fechaFin = trabajo.fechaFin;
        horas = trabajo.horas;
    }

    public static Trabajo copiar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo,"El trabajo no puede ser nulo.");
        Trabajo resultado = null;
        if (trabajo instanceof Revision revision){
            resultado = new Revision(revision);
        }
        if (trabajo instanceof Mecanico mecanico){
            resultado = new Mecanico(mecanico);

        }

        return resultado;
    }

    public static Trabajo get(Vehiculo vehiculo) {
        return new Revision(Cliente.get("77166622N"), vehiculo,LocalDate.now());
    }

    public Cliente getCliente() {

        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {

        return this.vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {

        return this.fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");
        if (fechaInicio.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura." +
                    "");
        }

        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        Objects.requireNonNull(fechaFin, "La fecha de inicio no puede ser futura.");
        this.fechaFin = fechaFin;
    }

    public int getHoras() {

        return this.horas;
    }

    public void anadirHoras(int horas) throws TallerMecanicoExcepcion {
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }
        if (fechaFin != null) {
            throw new TallerMecanicoExcepcion("No se puede añadir horas, ya que el trabajo está cerrado.");
        }
        this.horas += horas;
    }

    public boolean estaCerrado() {

        return this.fechaFin != null;
    }

    public void cerrar(LocalDate fechaFin) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");
        if (fechaFin.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        }
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        if (estaCerrado()) {
            throw new TallerMecanicoExcepcion("El trabajo ya está cerrado.");
        }

        this.fechaFin = fechaFin;
    }

    private float getPrecioFijo() {
        return getDias() * 10;
    }

    public float getPrecio() {
        return getPrecioEspecifico() + getPrecioFijo();
    }

    public float getDias() {
        if (fechaFin == null) {
            return 0;
        }
        return fechaInicio.until(fechaFin, ChronoUnit.DAYS);
    }

    public abstract float getPrecioEspecifico();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trabajo trabajo)) return false;
        return Objects.equals(cliente, trabajo.cliente) && Objects.equals(vehiculo, trabajo.vehiculo) && Objects.equals(fechaInicio, trabajo.fechaInicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, vehiculo, fechaInicio);
    }
}

