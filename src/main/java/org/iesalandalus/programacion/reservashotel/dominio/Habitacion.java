package org.iesalandalus.programacion.reservashotel.dominio;

import java.util.Objects;
import java.util.regex.Pattern;

public class Habitacion {
    public static double MIN_PRECIO_HABITACION=40;
    public static double MAX_PRECIO_HABITACION=150;
    public static int MIN_NUMERO_PUERTA=0;
    public static int MAX_NUMERO_PUERTA=15;
    public static int MIN_NUMERO_PLANTA=1;
    public static int MAX_NUMERO_PLANTA=3;



    private String identificador;
    private int planta;
    private int puerta;
    private double precio;
    private TipoHabitacion tipoHabitacion;
    private String descripcion;


    public Habitacion(int planta,int puerta,double precio){
        setPlanta(planta);
        setPuerta(puerta);
        setPrecio(precio);
        setIdentificador();
        //Para inicializarlo
        tipoHabitacion=TipoHabitacion.DOBLE;



    }
    public Habitacion(int planta,int puerta,double precio,TipoHabitacion tipoHabitacion){
        setPlanta(planta);
        setPuerta(puerta);
        setPrecio(precio);
        setTipoHabitacion(tipoHabitacion);
        //No forma parte del constructor, pero a partir de los parametros calcula el identificador de la habitacion creada.
        setIdentificador();




    }
    //Constructor Copia;accede a los metodos set de habitacion para crear una nueva instancia con esos valores.
    //no copia la habitacion sino los valores se los asigna a otra inistancia.
    public Habitacion(Habitacion habitacion){
        if (habitacion==null){
            throw new NullPointerException("ERROR: No es posible copiar una habitaci�n nula.");
        }
        setPlanta(habitacion.getPlanta());
        setPuerta(habitacion.getPuerta());
        setPrecio(habitacion.getPrecio());
        setTipoHabitacion(habitacion.getTipoHabitacion());
        setIdentificador();



    }


    public String getIdentificador() {
        return identificador;
    }
    //Calcula el identificador apartir de los enteros y se queda con el valor para pasarlo a una cadena.
    private void setIdentificador(){
        this.identificador= String.valueOf(planta)+String.valueOf(puerta);

    }

    private void setIdentificador(String identificador) {

        if (!Pattern.matches("[1-3][1-9][1-5]" , identificador)){
            throw new IllegalArgumentException("ERROR:El identificador es incorrecto");
        }else {
            this.identificador=identificador;
        }
    }

    public int getPlanta() {
        return planta;
    }

    private void setPlanta(int planta) {
       if (planta<MIN_NUMERO_PLANTA||planta>MAX_NUMERO_PLANTA){
            throw new IllegalArgumentException("ERROR: No se puede establecer como planta de una habitaci�n un valor menor que 1 ni mayor que 3.");
        }else{
           this.planta = planta;
       }

    }

    public int getPuerta() {
        return puerta;
    }

    private void setPuerta(int puerta) {
        if (puerta<MIN_NUMERO_PUERTA || puerta>=MAX_NUMERO_PUERTA){
            throw new IllegalArgumentException("ERROR: No se puede establecer como puerta de una habitaci�n un valor menor que 0 ni mayor que 15.");
        }
        this.puerta = puerta;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio<MIN_PRECIO_HABITACION||precio>MAX_PRECIO_HABITACION){
            throw new IllegalArgumentException("ERROR: No se puede establecer como precio de una habitaci�n un valor menor que 40.0 ni mayor que 150.0.");
        }else {
            this.precio = precio;
        }
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion==null){
            throw new NullPointerException("ERROR: No se puede establecer un tipo de habitaci�n nula.");
        }
        this.tipoHabitacion = tipoHabitacion;

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = getTipoHabitacion().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habitacion that = (Habitacion) o;
        return Objects.equals(identificador, that.identificador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador);
    }

    @Override
    public String toString() {

        return String.format("identificador=%s (%s-%s), precio habitaci�n=%s, tipo habitaci�n=%s",identificador,planta,puerta,precio,tipoHabitacion);
    }

    public static void main(String[] args) {
        try {
            Habitacion h1=new Habitacion(2,15,50,TipoHabitacion.SUITE);
            System.out.println(h1);

        }catch (IllegalArgumentException e){
            System.out.println("-"+ e.getMessage());
        }

    }

}
