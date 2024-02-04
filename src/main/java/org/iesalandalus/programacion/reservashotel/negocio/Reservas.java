package org.iesalandalus.programacion.reservashotel.negocio;

import org.iesalandalus.programacion.reservashotel.dominio.*;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Arrays;

public class Reservas {
    private int capacidad;
    private int tamano;
    private Reserva[] coleccionReservas;

    public Reservas(int capacidad){
        if (capacidad<=0){
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad=capacidad;
        this.coleccionReservas =new Reserva[capacidad];
        this.tamano=0;

    }
    public Reserva[] get(){
        return copiaProfundaReservas();
    }
    private Reserva[] copiaProfundaReservas(){
        Reserva[] copia=new Reserva[tamano];
        for (int i=0;i<tamano;i++){
            copia[i]=new Reserva(coleccionReservas[i]) ;
        }
        return copia;
    }
    public int getTamano(){
        return tamano;
    }
    public int getCapacidad(){
        return capacidad;
    }

    public void  insertar(Reserva reserva) throws OperationNotSupportedException {
        if (reserva==null){
            throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
        }
        int indice=buscarIndice(reserva);
        if (!tamanoSuperado(indice)){
            throw new OperationNotSupportedException("ERROR: Ya existe una reserva igual.");
        }
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más reservas.");
        }

        coleccionReservas[tamano++]=new Reserva(reserva);

    }
    private int buscarIndice(Reserva reserva){
        for (int i=0;i<tamano;i++){
            if (coleccionReservas[i].equals(reserva)){
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
    public  Reserva buscar(Reserva reserva){
        for (int i=0;i<tamano;i++){
            if (coleccionReservas[i].equals(reserva)){
                return coleccionReservas[i];
            }
        }
        return null;
    }
    public  void  borrar(Reserva reserva) throws OperationNotSupportedException {
        if (reserva==null){
            throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
        }
        int indice=buscarIndice(reserva);
        if (indice==-1){
            throw new OperationNotSupportedException("ERROR: No existe ninguna reserva como la indicada.");
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
        this.tamano--;

    }
    private  void desplazarUnaPosicionHaciaIzquierda(int indice){
        for (int i=indice;i<tamano-1;i++){
            coleccionReservas[i]= coleccionReservas[i+1];
        }
        coleccionReservas[tamano-1]=null;

    }
    public  Reserva[] getReservas(Huesped huesped)  {
        if (huesped==null){
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un huesped nulo.");
        }

        /*Diferenes problemas.He tenido que crear una variable encontradas, para saber antes de crear una nueva array
        el tamano maximo de dicha array para que no saliesen espacios en null a la derecha.
        el siguiente problema venia cuando borraba la primera reserva de la array, aparecia en nulo esa posicion , asin que e tenido que forzar
        creando una nueva variable colocacionOrdenada dandole valor 0 que la primera asignacion de la coincidencia(equal) este en el indice0 e incremento.
        Los nulos por la derecha desaparacen y por la izquierda tambien.
         */
        int encontradas=0;

        for (int i=0;i<tamano;i++){
            if (coleccionReservas[i].getHuesped().getDni().equals(huesped.getDni())){
                encontradas++;
            }
        }
        Reserva[] reservasHuesped=new Reserva[encontradas];
        int colocacionOrdenadaReservas=0;
        for (int i=0;i<tamano;i++){
            if (coleccionReservas[i].getHuesped().getDni().equals(huesped.getDni())){
                reservasHuesped[colocacionOrdenadaReservas++]=coleccionReservas[i];
            }
        }
        if (encontradas>0){
            return reservasHuesped;
        }else return null;

    }


    public Reserva[] getReservas(TipoHabitacion tipoHabitacion) throws OperationNotSupportedException {
        if (tipoHabitacion==null){
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un tipo de habitación nula.");
        }
        int encontradas=0;

        for (int i=0;i<tamano;i++){
            if (coleccionReservas[i].getHabitacion().getTipoHabitacion().equals(tipoHabitacion)){
                encontradas++;
            }
        }
        Reserva[] reservasHuesped=new Reserva[encontradas];
        int colocacionOrdenadaReservas=0;
        for (int i=0;i<tamano;i++){
            if (coleccionReservas[i].getHabitacion().getTipoHabitacion().equals(tipoHabitacion)){
                reservasHuesped[colocacionOrdenadaReservas++]=coleccionReservas[i];
            }
        }
        if (encontradas>0){
            return reservasHuesped;
        }else return null;
    }

    public Reserva[] getReservasFuturas(Habitacion habitacion){
        if (habitacion==null){
            throw new NullPointerException("ERROR: No se pueden buscar reservas de una habitación nula.");
        }
        int encontradas=0;

        for (int i=0;i<tamano;i++){
            if (coleccionReservas[i].getHabitacion().getTipoHabitacion().equals(habitacion.getTipoHabitacion())){
                encontradas++;
            }
        }
        Reserva[] reservasHuesped=new Reserva[encontradas];
        int colocacionOrdenadaReservas=0;
        for (int i=0;i<tamano;i++){
            if (coleccionReservas[i].getHabitacion().equals(habitacion)){
                reservasHuesped[colocacionOrdenadaReservas++]=coleccionReservas[i];
            }
        }
        if (encontradas>0){
            return reservasHuesped;
        }else return null;
    }


    public static void main(String[] args) {
        try{
            Huesped huesped1=new Huesped("11111daniel lopez perez","75720662Q","l@gmail.com","626626626", LocalDate.of(1987,12,12));
            Huesped huesped2=new Huesped("22222paco clavero golvan","11223344B","l@gmail.com","626626626",LocalDate.of(1987,12,12));
            Huesped huesped3=new Huesped("33333david martinez soria","22334455Y","l@gmail.com","626626626",LocalDate.of(1987,12,12));

            Habitacion habitacion1=new Habitacion(2,1,40, TipoHabitacion.SIMPLE);
            Habitacion habitacion2=new Habitacion(2,2,45, TipoHabitacion.DOBLE);
            Habitacion habitacion3=new Habitacion(2,3,55, TipoHabitacion.TRIPLE);
            Habitacion habitacion4=new Habitacion(1,1,45, TipoHabitacion.SIMPLE);
            Habitacion habitacion5=new Habitacion(1,2,40, TipoHabitacion.DOBLE);
            Habitacion habitacion6=new Habitacion(1,3,50, TipoHabitacion.TRIPLE);

            Reserva reserva1=new Reserva(huesped3,habitacion2, Regimen.MEDIA_PENSION,LocalDate.of(2024,1,26),LocalDate.of(2024,1,27),2 );
            Reserva reserva2=new Reserva(huesped3,habitacion4, Regimen.MEDIA_PENSION,LocalDate.of(2024,1,26),LocalDate.of(2024,1,27),1 );
            Reserva reserva3=new Reserva(huesped3,habitacion1, Regimen.MEDIA_PENSION,LocalDate.of(2024,1,26),LocalDate.of(2024,1,27),1 );

            Reservas reservasLista1=new Reservas(5);
            reservasLista1.insertar(reserva1);
            reservasLista1.insertar(reserva2);
            reservasLista1.insertar(reserva3);
            System.out.println(Arrays.toString(reservasLista1.get()));

            System.out.println(Arrays.toString(reservasLista1.getReservas(huesped3)));











        }catch (NullPointerException   |OperationNotSupportedException| IllegalArgumentException e){
            System.out.println("-"+e.getMessage());

        }

    }


}
