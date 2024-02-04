package org.iesalandalus.programacion.reservashotel.vista;

public enum Opcion {
    SALIR("Salir"),
    INSERTAR_HUESPED("Insertar Huesped"),
    BUSCAR_HUESPED("Buscar Huesped"),
    BORRAR_HUESPED("Borrar Huesped"),
    MOSTRAR_HUESPEDES("Mostrar huespedes"),
    INSERTAR_HABITACION("Insertar habitacion"),
    BUSCAR_HABITACION("Buscar Habitacion"),
    BORRAR_HABITACION("Borrar Habitacion"),
    MOSTRAR_HABITACIONES("Mostrar Habitaciones"),
    INSERTAR_RESERVA("Insertar reservas"),
    ANULAR_RESERVA("Anular reservas"),
    MOSTRAR_RESERVAS("Mostrar reservas"),
    CONSULTAR_DISPONIBILIDAD("Consultar disoinibilidad");

    private final String mensajeAMostrar;

    private Opcion(String mensajeAMostrar) {
        this.mensajeAMostrar=mensajeAMostrar;

    }

    @Override
    public String toString() {
        return (ordinal()+1)+".-"+mensajeAMostrar;
    }

    public static void main(String[] args) {
        System.out.println(INSERTAR_HABITACION);
    }


}
