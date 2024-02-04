package org.iesalandalus.programacion.reservashotel;


import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.negocio.Reservas;
import org.iesalandalus.programacion.reservashotel.vista.Consola;
import org.iesalandalus.programacion.reservashotel.vista.Opcion;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class MainApp {
    public static int CAPACIDAD=10;
    private static Huespedes huespedes=new Huespedes(CAPACIDAD);
    private static Reservas reservas=new Reservas(CAPACIDAD);
    private static Habitaciones habitaciones=new Habitaciones(CAPACIDAD);


    //esto tiene que estar en public


    public static void main(String[] args) {
        /*
        Opcion opcion=null;
        do {
            try {

                Consola.mostrarMenu();
                ejecutarOpcion(opcion=Consola.elegirOpcion());
            }catch (NullPointerException|IllegalArgumentException |OperationNotSupportedException e){
                System.out.println("-"+e.getMessage());
            }

        }while (opcion==Opcion.SALIR);

         */





            Opcion opcion=null;

            do {
                try {

                    Consola.mostrarMenu();
                    ejecutarOpcion(opcion=Consola.elegirOpcion());
                }catch (NullPointerException |OperationNotSupportedException  | DateTimeParseException | IllegalArgumentException e){
                    System.out.println("-"+e.getMessage());
                }

            }while (opcion!=Opcion.SALIR);







    }


    private static void ejecutarOpcion(Opcion opcion) throws OperationNotSupportedException {

        switch (opcion){
            case SALIR -> {
                System.out.println("SALIENDO DEL PROGRAMA");
            }
            case INSERTAR_HUESPED -> insertarHuesped();
            case BUSCAR_HUESPED -> buscarHuesped();
            case BORRAR_HUESPED -> borrarHuesped();
            case MOSTRAR_HUESPEDES -> mostrarHuespedes();
            case INSERTAR_HABITACION -> insertarHabitacion();
            case BUSCAR_HABITACION -> buscarHabitacion();
            case BORRAR_HABITACION -> borrarHabitacion();
            case MOSTRAR_HABITACIONES -> mostrarHabitaciones();
            case INSERTAR_RESERVA -> insertarReservas();
            case ANULAR_RESERVA -> anularReserva();
            case MOSTRAR_RESERVAS -> mostrarReservas();
            case CONSULTAR_DISPONIBILIDAD -> consultarDisponibilidad(null,null,null);


        }

    }
    private static void insertarHuesped() throws OperationNotSupportedException {
        huespedes.insertar(Consola.leerHuespedes());
        System.out.println("huesped creado");

    }
    private static void buscarHuesped(){
        Huesped huesped=new Huesped(huespedes.buscar(Consola.getHuespedPorDni()));
        if (huesped==null){
            System.out.println("Huesped no Encontrado");
        }else {
            System.out.println(huesped);
        }

    }
    private static void borrarHuesped() throws OperationNotSupportedException {
        huespedes.borrar(Consola.getHuespedPorDni());
        System.out.println("Huesped BORRADO");
    }

    private static void mostrarHuespedes(){
        System.out.println(Arrays.toString(huespedes.get()));

    }

    private static void insertarHabitacion() throws OperationNotSupportedException {
        habitaciones.insertar(Consola.leerHabitacion());
        System.out.println("Habitacion insertada");
    }

    private static void buscarHabitacion(){

        Habitacion habitacion=new Habitacion(habitaciones.buscar(Consola.leerHabitacionPorIdentificador()));
        if (habitacion==null){
            System.out.println("Huesped no Encontrado");
        }else {
            System.out.println(habitacion);
        }

    }

    private static void borrarHabitacion() throws OperationNotSupportedException {

        habitaciones.borrar(Consola.leerHabitacionPorIdentificador());
        System.out.println("Habitacion borrada");

    }

    private static void mostrarHabitaciones(){
        /*Nueva forma para mostrarlos en diferentes lineas.
        System.out.println(Arrays.toString(habitaciones.get()));
         */

        Habitacion[] habitacionesArray=habitaciones.get();
        System.out.println(" ");
        System.out.println("Resultrados de la busqueda");

        for (Habitacion habitacion:habitacionesArray){
            System.out.println("-->"+habitacion);
        }
        System.out.println(" ");

    }

    private static void insertarReservas() throws OperationNotSupportedException {

        reservas.insertar(Consola.leerReserva());





    }
    private static void listarReservas(Huesped huesped){
        System.out.println(Arrays.toString(reservas.getReservas(Consola.getHuespedPorDni())));


    }
    private static void listarReservas(TipoHabitacion tipoHabitacion) throws OperationNotSupportedException {
        System.out.println(Arrays.toString(reservas.getReservas(Consola.leerTipoHabitacion())));

    }
    private static Reserva[] getReservasAnulables(Reserva[] reservasAAnular){
        reservasAAnular=reservas.getReservas(Consola.getHuespedPorDni());

        return reservasAAnular;
    }
    private static void anularReserva(){
        System.out.println("En desaroyo");

    }
    private static void mostrarReservas(){
        System.out.println(Arrays.toString(reservas.get()));
    }

    private static Habitacion consultarDisponibilidad(TipoHabitacion tipoHabitacion, LocalDate fechaInicioReserva, LocalDate fechaFinReserva){
        System.out.println("En desarroyo");
        return null;
    }

}
