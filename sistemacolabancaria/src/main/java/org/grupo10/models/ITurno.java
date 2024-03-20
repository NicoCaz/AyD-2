package org.grupo10.models;

import java.util.Date;

public interface ITurno {
    public int numero = 0;
    public Date tiempoAsignacion = null;
    public Date tiempoAtencion = null;
    public int box = 0;
    public boolean asignarTurno();

}
