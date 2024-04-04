package org.grupo10.models;

import java.util.Date;

public interface ITurno {
     int numero = 0;
     public static int cantidadTurnos=0;
     Date tiempoAsignacion = null;
     Date tiempoAtencion = null;
     int box = 0;


     int getNumero();
}
