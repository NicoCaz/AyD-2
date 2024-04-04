package org.grupo10.models.colas;

import org.grupo10.models.clientes.Cliente;

import java.util.LinkedList;
import java.util.Queue;

public class ColaClientes {
    private Queue<Cliente> cola = new LinkedList<>();

    public void agregarCliente(Cliente c){
        cola.offer(c);
    }

    public void clienteLlamado()

}
