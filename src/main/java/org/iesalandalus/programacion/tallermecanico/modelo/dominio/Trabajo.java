package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Trabajo {
    static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yy");
    private static final float FACTOR_DIA = 10.0f;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private Vehiculo vehiculo;
    private Cliente cliente;

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
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        Trabajo copia = null;
        if (trabajo instanceof Revision revision) {
            copia = new Revision(revision);
        } else if(trabajo instanceof Mecanico mecanico) {
            copia = new Mecanico(mecanico);
        }
        return copia;
    }

    public static Trabajo get(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo,"El vehículo no puede ser nulo.");
        return new Revision(new Cliente("Juan", "00000000X", "123456789"),vehiculo,LocalDate.now());
    }

    public Cliente getCliente () {
        return cliente;
    }

    private void setCliente (Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo () {
        return vehiculo;
    }

    private void setVehiculo (Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio () {
        return fechaInicio;
    }

    private void setFechaInicio (LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");
        if (fechaInicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin () {
        return fechaFin;
    }

    private void setFechaFin (LocalDate fechaFin) {
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nulo.");
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior.");
        }
        this.fechaFin = fechaFin;
    }

    public int getHoras () {
        return horas;
    }

    public void anadirHoras (int horas) throws TallerMecanicoExcepcion {
        if (estaCerrado()) {
            throw new TallerMecanicoExcepcion("No se puede añadir horas, ya que el trabajo está cerrado.");
        }
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }
        this.horas += horas;
    }

    public boolean estaCerrado () {
        return fechaFin != null;
    }

    public void cerrar (LocalDate fechaFin) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");
        if (estaCerrado()) {
            throw new TallerMecanicoExcepcion("El trabajo ya está cerrado.");
        }
        if (fechaFin.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        }
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        this.fechaFin = fechaFin;
        setFechaFin(fechaFin);
    }

    public float getPrecio () {
        return getPrecioEspecifico() + getPrecioFijo();
    }

    private float getPrecioFijo() {
        return (estaCerrado() ? FACTOR_DIA * getDias(): 0);
    }

    private float getDias () {
        if (!estaCerrado()) {
            return 0;
        }
        return fechaInicio.until(fechaFin).getDays();
    }

    public abstract float getPrecioEspecifico();

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (!(o instanceof Trabajo trabajo)) return false;
        return horas == trabajo.horas && Objects.equals(cliente, trabajo.cliente) && Objects.equals(vehiculo, trabajo.vehiculo) && Objects.equals(fechaInicio, trabajo.fechaInicio);
    }

    @Override
    public int hashCode () {
        return Objects.hash(cliente, vehiculo, fechaInicio);
    }
}
