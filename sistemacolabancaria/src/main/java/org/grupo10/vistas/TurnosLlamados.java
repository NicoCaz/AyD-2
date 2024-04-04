package org.grupo10.vistas;

import org.grupo10.models.GestionAtencion;
import org.grupo10.models.ITurno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TurnosLlamados extends JFrame {
    private GestionAtencion gestionAtencion;

    public TurnosLlamados(GestionAtencion gestionAtencion) {
        this.gestionAtencion = gestionAtencion;

        setTitle("Turnos Llamados");
        setSize(800, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        initUI();
    }

    private void initUI() {
        // Obtener datos de la lista de turnos pendientes
        List<ITurno> turnosPendientes = gestionAtencion.getTurnosPendientes();

        // Crear los datos para la tabla
        String[] columnNames = {"Turno", "BOX"};
        Object[][] data = new Object[turnosPendientes.size()][2];

        for (int i = 0; i < turnosPendientes.size(); i++) {
            ITurno turno = turnosPendientes.get(i);
            data[i][0] = turno.getNumero();
            data[i][1] = "5";//turno.getBoxAsignado();
        }

        // Crear el modelo de tabla con los datos
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        // Agregar la tabla a un panel con scroll
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Configurar la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        GestionAtencion gestionAtencion = GestionAtencion.getInstance(5); // Ejemplo con 5 boxes
        SwingUtilities.invokeLater(() -> new TurnosLlamados(gestionAtencion));
    }
}
