package org.grupo10.models;

import java.util.ArrayList;
import java.util.List;

public class GestionAtencion {
    private static GestionAtencion instance;

    private List<ITurno> turnosPendientes;
    private List<ITurno> turnosAtendidos;
    private List<IBox> boxes;

    private GestionAtencion(int cantidadBoxes) {
        turnosPendientes = new ArrayList<>();
        turnosAtendidos = new ArrayList<>();
        boxes = new ArrayList<>();

        for (int i = 0; i < cantidadBoxes; i++) {
            IBox box = new Box(i + 1);
            boxes.add(box);
        }
    }

    public static GestionAtencion getInstance(int cantidadBoxes) {
        if (instance == null) {
            instance = new GestionAtencion(cantidadBoxes);
        }
        return instance;
    }
    public static GestionAtencion getInstance() {
        return instance;
    }

    public void agregarTurno(ITurno turno) {
        this.turnosPendientes.add(turno);
    }
    public List<ITurno> getTurnosPendientes(){
        return this.turnosPendientes;
    }

    public void terminarTurno(ITurno turno) {
        this.turnosAtendidos.add(turno);
    }

    public void atenderTurno(ITurno turno) {
        this.turnosPendientes.remove(turno);
        // Lógica de atender turno
        // Llamar a los box para que lo atiendan
    }

    public void comenzarAtencion() {
        // Aquí se invoca a todos los métodos de las otras clases para que se inicialice la atención
    }
}
