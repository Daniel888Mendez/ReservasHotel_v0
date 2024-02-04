package org.iesalandalus.programacion.reservashotel.negocio;


import org.iesalandalus.programacion.reservashotel.dominio.*;

import javax.naming.OperationNotSupportedException;
import javax.xml.transform.sax.SAXSource;
import java.time.LocalDate;
import java.util.Arrays;

public class Habitaciones {
    private int capacidad;
    private int tamano;
    private Habitacion[] coleccionHabitaciones;

    public  Habitaciones(int capacidad){
        if (capacidad<=0){
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad=capacidad;
        this.coleccionHabitaciones=new Habitacion[capacidad];
        this.tamano=0;

    }
    public Habitacion[] get(){
        return copiaProfundaHabitaciones();
    }
    private Habitacion[] copiaProfundaHabitaciones(){
        Habitacion[] copia=new Habitacion[tamano];
        for (int i=0;i<tamano;i++){
            //Para evitar el problema de aliasis he tenido que colocar un new Habitaciones.
            copia[i]= new Habitacion (coleccionHabitaciones[i]);
        }
        return copia;
    }
    public int getTamano(){
        return tamano;
    }
    public int getCapacidad(){
        return capacidad;
    }
    public void  insertar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion==null){
            throw new NullPointerException("ERROR: No se puede insertar una habitación nula.");
        }
        int indice=buscarIndice(habitacion);
        if (!tamanoSuperado(indice)){
            throw new OperationNotSupportedException("ERROR: Ya existe una habitación con ese identificador.");
        }
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más habitaciones.");
        }
        coleccionHabitaciones[tamano++]=new Habitacion(habitacion);


    }
    private int buscarIndice(Habitacion habitacion){
        for (int i=0;i<tamano;i++){
            if (coleccionHabitaciones[i].equals(habitacion)){
                return i;
            }
        }
        return -1;
    }
    private  Boolean tamanoSuperado(int indice){
        if (indice<0){
            return true;
        }
        return false;
    }
    private  Boolean capacidadSuperada(int indice){
        if (tamanoSuperado(indice)==true && tamano>=capacidad){
            return true;
        }
        return false;
    }
    public  Habitacion buscar(Habitacion habitacion){
        for (int i=0;i<tamano;i++){
            if (coleccionHabitaciones[i].equals(habitacion)){
                //Para controlar la alisis he tenido que colocar un new Habitacion.
                return new Habitacion(coleccionHabitaciones[i]);
            }
        }
        return null;
    }
    public  void  borrar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion==null){
            throw new NullPointerException("ERROR: No se puede borrar una habitación nula.");
        }
        int indice=buscarIndice(habitacion);
        if (indice==-1){
            throw new OperationNotSupportedException("ERROR: No existe ninguna habitación como la indicada.");
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
        this.tamano--;

    }
    private  void desplazarUnaPosicionHaciaIzquierda(int indice){
        for (int i=indice;i<tamano-1;i++){
            coleccionHabitaciones[i]=coleccionHabitaciones[i+1];
        }
        coleccionHabitaciones[tamano-1]=null;

    }

    public static void main(String[] args) {
        try{
            Huesped huesped1=new Huesped("daniel lopez perez","75720662Q","l@gmail.com","626626626", LocalDate.of(1987,12,12));
            Huesped huesped2=new Huesped("paco clavero golvan","11223344B","l@gmail.com","626626626",LocalDate.of(1987,12,12));
            Huesped huesped3=new Huesped("david martinez soria","22334455Y","l@gmail.com","626626626",LocalDate.of(1987,12,12));

            Habitacion habitacion1=new Habitacion(2,1,50, TipoHabitacion.SIMPLE);
            Habitacion habitacion2=new Habitacion(2,2,50, TipoHabitacion.DOBLE);
            Habitacion habitacion3=new Habitacion(2,3,50, TipoHabitacion.TRIPLE);
            Habitacion habitacion4=new Habitacion(1,1,50, TipoHabitacion.SIMPLE);
            Habitacion habitacion5=new Habitacion(1,2,50, TipoHabitacion.DOBLE);
            Habitacion habitacion6=new Habitacion(1,3,50, TipoHabitacion.TRIPLE);

            Reserva reserva1=new Reserva(huesped1,habitacion2, Regimen.MEDIA_PENSION,LocalDate.of(2024,1,22),LocalDate.of(2024,1,25),2 );
            Reserva reserva2=new Reserva(huesped2,habitacion4, Regimen.MEDIA_PENSION,LocalDate.of(2024,1,22),LocalDate.of(2024,1,25),1 );
            Reserva reserva3=new Reserva(huesped3,habitacion1, Regimen.MEDIA_PENSION,LocalDate.of(2024,1,22),LocalDate.of(2024,1,25),1 );

            Reservas reservasLista1=new Reservas(5);
            reservasLista1.insertar(reserva1);
            reservasLista1.insertar(reserva2);
            reservasLista1.insertar(reserva3);
            System.out.println(Arrays.toString(reservasLista1.get()));

            System.out.println(reservasLista1.getReservas(huesped1));







        }catch (NullPointerException   |OperationNotSupportedException| IllegalArgumentException e){
            System.out.println("-"+e.getMessage());

        }

    }




}
