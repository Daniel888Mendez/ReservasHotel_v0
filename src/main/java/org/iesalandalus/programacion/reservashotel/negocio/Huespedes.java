package org.iesalandalus.programacion.reservashotel.negocio;

import org.iesalandalus.programacion.reservashotel.dominio.Huesped;

import javax.naming.OperationNotSupportedException;
import javax.swing.table.TableRowSorter;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class Huespedes {
    private int capacidad;
    private int tamano;
    private  Huesped[] coleccionHuespedes;

    public Huespedes(int capacidad){
        if (capacidad<=0){
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad=capacidad;
        this.coleccionHuespedes=new Huesped[capacidad];
        this.tamano=0;



    }

    public Huesped[] get() {
        return copiaProfundaHuespedes();
    }
    private Huesped[] copiaProfundaHuespedes(){
        Huesped[] copia=new Huesped[tamano];
        for (int i=0;i<tamano;i++){
            copia[i]=coleccionHuespedes[i];

        }
        return copia;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getTamano() {
        return tamano;
    }



    public void insertar(Huesped huesped) throws OperationNotSupportedException {
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede insertar un huésped nulo.");
        }

        int indice=buscarIndice(huesped);

        if (!tamanoSuperado(indice)){
            throw new OperationNotSupportedException("ERROR: Ya existe un huésped con ese dni.");
        }
        if (capacidadSuperado(indice)){
            throw new OperationNotSupportedException("ERROR: No se aceptan más huéspedes.");
        }
        coleccionHuespedes[tamano++]=new Huesped(huesped);

    }
    private int buscarIndice(Huesped huesped){

        if (huesped==null){
            throw new NullPointerException("ERROR:no se puede buscar un indice de un huesped nulo");
        }
        for (int i=0;i<tamano;i++){
            /*cuidado creo que no hace  falta el .getDNI para comparar los dnis*/
            if (coleccionHuespedes[i].equals(huesped)){
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
    private  Boolean capacidadSuperado(int indice){
        if (tamano>=capacidad && tamanoSuperado(indice)){
            return true;
        }
        return false;

    }

    public Huesped buscar(Huesped huesped)  {

        for (int i = 0; i < tamano; i++) {
            if (coleccionHuespedes[i].equals(huesped)) {
                return coleccionHuespedes[i];
            }
        }
        return null;
    }
    public void borrar(Huesped huesped) throws OperationNotSupportedException {
        if (huesped==null){
            throw new NullPointerException("ERROR: No se puede borrar un huésped nulo.");
        }
        int indice=buscarIndice(huesped);
        if (indice==-1){
            throw new OperationNotSupportedException("ERROR: No existe ningún huésped como el indicado.");
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;

    }
    private void desplazarUnaPosicionHaciaIzquierda(int indice){


        for (int i=indice;i<tamano-1;i++){
            coleccionHuespedes[i]=coleccionHuespedes[i+1];
        }
        coleccionHuespedes[tamano-1]=null;



    }

    public static void main(String[] args) {
        try{
            Huesped huesped1=new Huesped("daniel lopez perez","75720662Q","l@gmail.com","626626626",LocalDate.of(1987,12,12));
            Huesped huesped2=new Huesped("paco clavero golvan","11223344B","l@gmail.com","626626626",LocalDate.of(1987,12,12));
            Huesped huesped3=new Huesped("david martinez soria","22334455Y","l@gmail.com","626626626",LocalDate.of(1987,12,12));


            Huespedes h1=new Huespedes(3);
            h1.insertar(huesped1);
            h1.insertar(huesped2);

            System.out.println(Arrays.toString(h1.get()));
            h1.insertar(huesped3);
            System.out.println(Arrays.toString(h1.get()));
            h1.borrar(huesped1);
            System.out.println(Arrays.toString(h1.get()));




        }catch (NullPointerException | OperationNotSupportedException  | IllegalArgumentException e){
            System.out.println("-"+e.getMessage());

        }

    }




}
