package org.grupo10.models.clientes;

public abstract class Cliente {
     protected int identificador = 0;
     protected String dni;
     abstract boolean registrar();
}
