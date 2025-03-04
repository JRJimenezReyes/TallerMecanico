package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class Revision {
    private Cliente cliente;
    private Vehiculo vehiculo;

    private static final float PRECIO_HORA = 30;
    private static final float PRECIO_DIA = 10;
    private static final float PRECIO_MATERIAL = 1.5f;
    static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("(dd/MM/yy)(d/M/yy)");
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private float precioMaterial;


    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
    }

    public Revision(Revision revision) {
        this.cliente = new Cliente(revision.cliente);
        this.vehiculo = new Vehiculo(revision.vehiculo.marca(), revision.vehiculo.modelo(), revision.vehiculo.matricula());
        this.fechaInicio = revision.fechaInicio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    private void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente,"El cliente no puede ser nulo.");
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo,"El vehiculo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    private void setFechaInicio(LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("la fecha de finalización no puede ser anterior a la fecha de inicio.");
        }
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        Objects.requireNonNull(fechaFin, "La fecha de finalización no puede ser nula.");
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("la fecha de finalización no puede ser anterior a la fecha de inicio.");
        }
        this.fechaFin = fechaFin;
    }

    public int getHoras() {
        return horas;
    }

    public void anadirHora(int horas) {
        if (horas <= 0) {
            throw new IllegalArgumentException("No puedes añadir igual o menos de 0 horas.");
        }
        this.horas = horas;
    }

    public static float getPrecioMaterial() {
        return PRECIO_MATERIAL;
    }

    public void anadirPrecioMaterial(float precioMaterial) {
        if (precioMaterial <= 0) {
            throw new IllegalArgumentException("No puedes añadir igual o menos de 0 euros al precio del material.");
        }
        this.precioMaterial = precioMaterial;
    }

    public boolean estaCerrado() {

    }

    public void cerrar(LocalDate fechaFin) {

    }

    public float getPrecio() {

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Revision revision = (Revision) o;
        return horas == revision.horas && Float.compare(precioMaterial, revision.precioMaterial) == 0 && Objects.equals(cliente, revision.cliente) && Objects.equals(vehiculo, revision.vehiculo) && Objects.equals(fechaInicio, revision.fechaInicio) && Objects.equals(fechaFin, revision.fechaFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, vehiculo, fechaInicio, fechaFin, horas, precioMaterial);
    }

    @Override
    public String toString() {
        return String.format("[cliente=%s, vehiculo=%s, fechaInicio=%s, fechaFin=%s, horas=%s, precioMaterial=%s]", cliente, vehiculo, fechaInicio, fechaFin, horas, precioMaterial);
    }
}
