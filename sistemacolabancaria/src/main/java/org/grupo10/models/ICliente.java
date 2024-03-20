package org.grupo10.models;

public interface ICliente {
    public String dni = null;
    public int identificador = 0;

    public boolean registrar();
    public boolean solicitarTurno();

}
