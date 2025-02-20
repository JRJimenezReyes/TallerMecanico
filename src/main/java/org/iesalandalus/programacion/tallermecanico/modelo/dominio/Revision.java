package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Revision {

    private static final float PRECIO_HORA = 30;
    private static final float PRECIO_DIA = 10;
    private static final float PRECIO_MATERIAL = 1.5F;
    static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    LocalDate fechaInicio = null;
    LocalDate fechaFin = null;
    private float precioMaterial = 0;
    private int horas = 0;

    Cliente cliente;
    Vehiculo vehiculo;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio){

        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
        horas = 0;
        precioMaterial = 0;
        fechaFin = null;
    }

    public Revision(Revision revision){
        Objects.requireNonNull(revision,"La revisión no puede ser nula.");
        this.setCliente(new Cliente(revision.cliente));
        this.setVehiculo(revision.vehiculo);
        this.setFechaInicio(revision.fechaInicio);
        this.horas = revision.horas;
        this.precioMaterial = revision.precioMaterial;
        this.fechaInicio = revision.fechaInicio;
        this.fechaFin = revision.fechaFin;
    }

    public void anadirHoras(int horas) throws TallerMecanicoExcepcion{
        if (fechaFin != null){
            throw new TallerMecanicoExcepcion("No se puede añadir horas, ya que la revisión está cerrada.");
        }

        Objects.requireNonNull(horas,"Las horas no pueden ser nulas.");
        if (horas <= 0 ){
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }

        this.horas += horas;
    }

    public void anadirPrecioMaterial(float precioMaterial) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(precioMaterial,"El precio material no puede ser nulo");
        if (fechaFin != null){
            throw new TallerMecanicoExcepcion("No se puede añadir precio del material, ya que la revisión está cerrada.");
        }
        if (precioMaterial <= 0){
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        this.precioMaterial += precioMaterial;
    }

    public void cerrar(LocalDate fechaFin) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(fechaFin,"La fecha de fin no puede ser nula.");
        if (estaCerrada()){
            throw new TallerMecanicoExcepcion("La revisión ya está cerrada.");
        }

        setFechaFin(fechaFin);
    }

    public float getPrecio(){

        return (horas * PRECIO_HORA) + (getDias() * PRECIO_DIA) +(getPrecioMaterial())*PRECIO_MATERIAL;
    }

    private float getDias(){
        if (fechaFin == null){
            return 0;
        }
        return (int) ChronoUnit.DAYS.between(getFechaInicio(),getFechaFin());
    }

    public int getHoras() {
        return horas;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio,"La fecha de inicio no puede ser nula.");
        if (fechaInicio.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }

        this.fechaInicio = fechaInicio;

    }


    public LocalDate getFechaFin() {

        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {

        if (fechaFin.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        }
        if (fechaFin.isBefore(fechaInicio)){
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }

        this.fechaFin = fechaFin;
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    public void setPrecioMaterial(float precioMaterial) {

        this.precioMaterial = precioMaterial;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    public boolean estaCerrada(){
        return(fechaFin != null);

    }

    public void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo,"El vehículo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revision revision = (Revision) o;
        return horas == revision.horas && Objects.equals(fechaInicio, revision.fechaInicio) && Objects.equals(cliente, revision.cliente) && Objects.equals(vehiculo, revision.vehiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaInicio, cliente, vehiculo);
    }

    @Override
    public String toString() {
        String fechaIF = fechaInicio.format(FORMATO_FECHA);

        if (fechaFin == null){

            return String.format("%s - %s (%s) - %s %s - %s: (%s - ), %s horas, %.2f € en material", cliente.getNombre(), cliente.getDni(), cliente.getTelefono(), vehiculo.marca(), vehiculo.modelo(), vehiculo.matricula(), fechaIF, getHoras(), getPrecioMaterial());
        }

        String fechaFF = fechaFin.format(FORMATO_FECHA);
        return String.format("%s - %s (%s) - %s %s - %s: (%s - %s), %s horas, %.2f € en material, %.2f € total", cliente.getNombre(), cliente.getDni(), cliente.getTelefono(), vehiculo.marca(), vehiculo.modelo(), vehiculo.matricula(), fechaIF, fechaFF, getHoras(), getPrecioMaterial(), getPrecio());




    }
}
