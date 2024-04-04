package org.grupo10.models;

public class Turno implements ITurno{

    public static int cantidadDeTurnos=0;
    private int numeroTurno;
    private String dni;
    public Turno(String dni){
        Turno.cantidadDeTurnos++;
        this.numeroTurno=Turno.cantidadDeTurnos;
        this.dni=dni;
    }
    public int getNumero(){
        return this.numeroTurno;
    }


}
