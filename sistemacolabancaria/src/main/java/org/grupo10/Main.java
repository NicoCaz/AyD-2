package org.grupo10;

import org.grupo10.models.GestionAtencion;
import org.grupo10.vistas.Totem;
import org.grupo10.vistas.TurnosLlamados;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        GestionAtencion gestionAtencionSingleton= GestionAtencion.getInstance(5);


        Totem totem= new Totem(gestionAtencionSingleton);
        TurnosLlamados turnosLlamados=new TurnosLlamados(gestionAtencionSingleton);

        totem.setVisible(true);
        turnosLlamados.setVisible(true);
    }
}