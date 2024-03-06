package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static org.iesalandalus.programacion.tallermecanico.vista.Consola.elegirOpcion;
import static org.iesalandalus.programacion.tallermecanico.vista.Consola.mostrarMenu;

public class Vista {

    private Controlador controlador;

    public Vista() {
    }

    public void setControlador(Controlador controlador) {
        if (controlador != null) {
            this.controlador = controlador;
        }
    }

    public void comenzar() {
        Opcion opcion;
        do {
            mostrarMenu();
            opcion = elegirOpcion();
            ejecutar(opcion);
        } while (opcion != Opcion.SALIR);
    }

    public void terminar() {
        System.out.println("¡Hasta pronto!");
    }

    private void ejecutar(Opcion opcion) {
        try {
            switch (opcion) {
                case NUEVA_REVISION:
                    nuevaRevision();
                    break;
                case LISTAR_REVISION:
                    listarRevisiones();
                    break;
                case BUSCAR_REVISION:
                    buscarRevision();
                    break;
                case BORRAR_REVISION:
                    borrarRevision();
                    break;
                case MODIFICAR_REVISION:
                    modificarRevision();
                    break;
                case CERRAR_REVISION:
                    cerrarRevision();
                    break;
                case SALIR_SIN_GUARDAR:
                    salirSinGuardar();
                    break;
                case SALIR:
                    salir();
                    break;
            }
        } catch (Exception e) {
            Consola.mostrarMensajeError(e.getMessage());
        }
    }

    private void nuevaRevision() {
        String matricula = Consola.leerCadena("Introduzca la matrícula del vehículo: ");
        int km = Consola.leerEntero("Introduzca el kilometraje del vehículo: ");
        LocalDate fecha = Consola.leerFecha("Introduzca la fecha de la revisión: ");
        double precio = Consola.leerReal("Introduzca el precio de la revisión: ");

        try {
            controlador.crearRevision(matricula, km, fecha, precio);
            Consola.mostrarInformacion("Revisión creada correctamente.");
        } catch (Exception e) {
            Consola.mostrarMensajeError(e.getMessage());
        }
    }

    private void listarRevisiones() {
        try {
            List<Revision> revisiones = controlador.listarRevisiones();
            if (revisiones.isEmpty()) {
                Consola.mostrarInformacion("No hay revisiones disponibles.");
            } else {
                for (Revision revision : revisiones) {
                    System.out.println(revision);
                }
            }
        } catch (Exception e) {
            Consola.mostrarMensajeError(e.getMessage());
        }
    }

    private void buscarRevision() {
        String matricula = Consola.leerCadena("Introduzca la matrícula del vehículo: ");

        try {
            Revision revision = controlador.buscarRevision(matricula);
            if (revision == null) {
                Consola.mostrarInformacion("No se ha encontrado ninguna revisión para la matrícula " + matricula);
            } else {
                System.out.println(revision);
            }
        } catch (Exception e) {
            Consola.mostrarMensajeError(e.getMessage());
        }
    }

    private void borrarRevision() {
        String matricula = Consola.leerCadena("Introduzca la matrícula del vehículo: ");

        try {
            controlador.borrarRevision(matricula);
            Consola.mostrarInformacion("Revisión eliminada correctamente.");
        } catch (Exception e) {
            Consola.mostrarMensajeError(e.getMessage());
        }
    }

    private void modificarRevision() {
        String matricula = Consola.leerCadena("Introduzca la matrícula del vehículo: ");

        try {
            Revision revision = controlador.buscarRevision(matricula);
            if (revision == null) {
                Consola.mostrarInformacion("No se ha encontrado ninguna revisión para la matrícula " + matricula);
            } else {
                int km = Consola.leerEntero("Introduzca el nuevo kilometraje del vehículo: ");
                LocalDate fecha = Consola.leerFecha("Introduzca la nueva fecha de la revisión: ");
                double precio = Consola.leerReal("Introduzca el nuevo precio de la revisión: ");

                revision.setKm(km);
                revision.setFecha(fecha);
                revision.setPrecio(precio);

                controlador.modificarRevision(revision);
                Consola.mostrarInformacion("Revisión modificada correctamente.");
            }
        } catch (Exception e) {
            Consola.mostrarMensajeError(e.getMessage());
        }
    }

    private void cerrarRevision() {
        String matricula = Consola.leerCadena("Introduzca la matrícula del vehículo: ");

        try {
            Revision revision = controlador.buscarRevision(matricula);
            if (revision == null) {
                Consola.mostrarInformacion("No se ha encontrado ninguna revisión para la matrícula " + matricula);
            } else {
                revision.setCerrada(true);
                controlador.modificarRevision(revision);
                Consola.mostrarInformacion("Revisión cerrada correctamente.");
            }
        } catch (Exception e) {
            Consola.mostrarMensajeError(e.getMessage());
        }
    }

    private void salirSinGuardar() {
        Consola.mostrarMensaje("¿Está seguro de que desea salir sin guardar? (S/N)");
        String respuesta = Consola.leerCadena().toUpperCase();
        if (respuesta.equals("S")) {
            System.exit(0);
        }
    }

    private void salir() {
        Consola.mostrarMensaje("¿Está seguro de que desea salir? (S/N)");
        String respuesta = Consola.leerCadena().toUpperCase();
        if (respuesta.equals("S")) {
            System.exit(0);
        }
    }
}

