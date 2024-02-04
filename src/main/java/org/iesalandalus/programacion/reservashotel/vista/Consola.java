package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.MainApp;
import org.iesalandalus.programacion.reservashotel.dominio.*;
import org.iesalandalus.programacion.reservashotel.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.negocio.Reservas;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import javax.swing.text.html.HTMLDocument;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;



public class Consola {
    private Consola() {

    }
    public static void mostrarMenu(){
        System.out.println("!!!!!!!!!!!! MENU !!!!!!!!!!!!");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion+".");
        }
    }

    public static Opcion elegirOpcion(){

        int opcionUsuario=0;

        do {
            System.out.println("Eligir Opcion:");
            opcionUsuario=Entrada.entero();
            if (opcionUsuario<1||opcionUsuario>13){
                System.out.println("NUMERO INCORRECTO-->Introduce un numero correcto(1-13).");
            }

        }while (opcionUsuario<0||opcionUsuario>13);


        Opcion[] opciones=Opcion.values();
        System.out.println(opciones[opcionUsuario-1]);
        return opciones[opcionUsuario-1];


    }
    public static Huesped leerHuespedes()  {
        //Cuidado con la propagacion de excepciones, creo que solo hay que propagar, y no tratarla como he echo.

        String telefono;
        String correo;
        String nombre;
        String dni;

        System.out.println("!!!!!DATOS CLIENTE!!!!!");

        System.out.print("NOMBRE:");
        nombre = Entrada.cadena();

        System.out.print("DNI:");
        dni = Entrada.cadena();

        System.out.print("CORREO:");
        correo = Entrada.cadena();

        System.out.print("TELEFONO:");
        telefono = Entrada.cadena();

        System.out.print("FECHA NACIMIENTO (dd-MM-yyyy):");
        String nacimientoCad = Entrada.cadena();
        LocalDate fecNacimiento=LocalDate.parse(nacimientoCad,DateTimeFormatter.ofPattern("dd-MM-yyyy"));





        System.out.println("Creado correctamente");


        return new Huesped(nombre,dni,correo,telefono,fecNacimiento);

    }
    public static Huesped getHuespedPorDni(){
        //Propagar las excepciones

        String nombre="Sujeto De Pruebas";
        String dni;
        String telefono="666666666";
        String correo="correoFalso@gmail.com";
        LocalDate fechaNac=LocalDate.of(2000,01,01);

        System.out.println("Introduce el dni del cliente que quiere buscar");
        dni=Entrada.cadena();

        return new Huesped(nombre,dni,correo,telefono,fechaNac);

    }
    public static LocalDate leerFecha(String mensaje){
        String formatoFecha = "\\d{2}-\\d{2}-\\d{4}";
        String fecha;
        System.out.println(" ");
        System.out.println("!!!!!!RESERVA DIAS!!!!!!");

        do {
            System.out.println(mensaje);
            fecha=Entrada.cadena();
            if (!Pattern.matches(formatoFecha,fecha)){
                System.out.println("La fecha tiene que tener este formato-->dd-MM-yyyy");
            }
        }while (!Pattern.matches(formatoFecha,fecha));

        LocalDate fecNacimiento=LocalDate.parse(fecha,DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return fecNacimiento;


        //return LocalDate.parse(fecha);
    }


    public static Habitacion leerHabitacion(){
        int planta;
        int puerta;
        double precio;

        System.out.println(" ");
        System.out.println("!!!!!DATOS HABITACION!!!!!!");

        System.out.print("Introduce la planta:");
        planta=Entrada.entero();

        System.out.print("Introduce la puerta:");
        puerta=Entrada.entero();
        System.out.print("Introduce el precio:");
        precio=Entrada.realDoble();

        return new Habitacion(planta,puerta,precio,leerTipoHabitacion());
    }
    public static Habitacion leerHabitacionPorIdentificador(){
        int planta;
        int puerta;
        double precio=50.00;


        System.out.print("Introduce la planta:");
        planta=Entrada.entero();
        System.out.print("Introduce la puerta:");
        puerta=Entrada.entero();

        return new Habitacion(planta,puerta,precio,leerTipoHabitacion());
    }
    public static TipoHabitacion leerTipoHabitacion(){

        System.out.println(" ");
        System.out.println("!!!!!!!!!!TIPOS DE HABITACIONES!!!!!!!!!!!");
        for (TipoHabitacion tipos : TipoHabitacion.values()) {
            System.out.println((tipos.ordinal()+1) +"-."+tipos);
        }

        int opcion;
        System.out.print("Elige un tipo de Habitacion(1-4)-->");
        opcion=Entrada.entero();

        TipoHabitacion[] tipos=TipoHabitacion.values();
        return tipos[opcion-1];

    }

    public static Regimen leerRegimen(){
        System.out.println(" ");
        System.out.println("!!!!!!!!!!TIPOS DE REGIMEN!!!!!!!!!!!");
        for (Regimen tipos : Regimen.values()) {
            System.out.println((tipos.ordinal()+1) +"-."+tipos);
        }

        int opcion;
        System.out.print("Elige un tipo de Regimen(1-4)-->");
        opcion=Entrada.entero();

        Regimen[] tipos=Regimen.values();
        return tipos[opcion-1];
    }
    //cuando creo reserva,no termina de crearme un nuevo huesped ni habitación.
    //tengo que colocar un new en algun lado;mirarlo.
    public static Reserva leerReserva() {

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!!!!!!!!!CREANDO RESERVA!!!!!!!!!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(" ");


        System.out.println("Numero de pesonas alojadas:");
        int numPerson = Entrada.entero();
        //tengo que tener un huesped ya creado, a la hora de crear la reserva le asignare un huesped ya creado mediante algun tipo de busqueda con dni o algo.

        Huesped huesped=getHuespedPorDni();


        return new Reserva(huesped, leerHabitacionPorIdentificador(), leerRegimen(), leerFecha("Entrada"), leerFecha("Salida"), numPerson);


    }

}
