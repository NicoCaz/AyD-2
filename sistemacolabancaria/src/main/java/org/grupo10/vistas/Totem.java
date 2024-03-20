package org.grupo10.vistas;

import org.grupo10.models.GestionAtencion;
import org.grupo10.models.ITurno;
import org.grupo10.models.Turno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Totem extends JFrame {
    private JLabel displayLabel;
    private StringBuilder inputBuffer;
    private GestionAtencion gestionAtencion;

    public Totem(GestionAtencion gestionAtencion) {
        this.gestionAtencion = gestionAtencion;
        this.inputBuffer = new StringBuilder();

        setupFrame();

        JPanel mainPanel = createMainPanel();
        setContentPane(mainPanel);
    }

    private void setupFrame() {
        setTitle("Numeric Keypad");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 400);
        setLocationRelativeTo(null);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(createDisplayPanel(), BorderLayout.NORTH);
        mainPanel.add(createKeypadPanel(), BorderLayout.CENTER);
        mainPanel.add(createSideButtonPanel(), BorderLayout.SOUTH);
        return mainPanel;
    }

    private JPanel createDisplayPanel() {
        JPanel displayPanel = new JPanel(new BorderLayout(5, 5));
        displayPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        displayPanel.setPreferredSize(new Dimension(200, 60));

        displayLabel = new JLabel("", SwingConstants.RIGHT);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        displayLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        displayPanel.add(displayLabel, BorderLayout.CENTER);

        return displayPanel;
    }

    private JPanel createKeypadPanel() {
        JPanel keypadPanel = new JPanel(new GridLayout(4, 3, 5, 5)); // Grid layout para botones numéricos

        JButton[] numButtons = new JButton[10];
        for (int i = 1; i <= 9; i++) {
            numButtons[i] = createNumericButton(String.valueOf(i));
            keypadPanel.add(numButtons[i]);
        }
        numButtons[0] = createNumericButton("0");
        keypadPanel.add(numButtons[0]);

        // Botón de borrar un dígito
        JButton backButton = new JButton("<--");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new BackButtonListener());
        keypadPanel.add(backButton);

        return keypadPanel;
    }

    private JButton createNumericButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(new NumericButtonListener());
        return button;
    }

    private JPanel createSideButtonPanel() {
        JPanel sideButtonPanel = new JPanel(new GridLayout(1, 2, 5, 5));

        JButton acceptButton = createSideButton("Aceptar", Color.GREEN, new AcceptButtonListener());
        JButton cancelButton = createSideButton("Cancelar", Color.RED, new CancelButtonListener());

        sideButtonPanel.add(acceptButton);
        sideButtonPanel.add(cancelButton);

        return sideButtonPanel;
    }

    private JButton createSideButton(String label, Color bgColor, ActionListener listener) {
        JButton button = new JButton(label);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.addActionListener(listener);
        return button;
    }

    private class NumericButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            inputBuffer.append(button.getText());
            displayLabel.setText(inputBuffer.toString());
        }
    }

    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (inputBuffer.length() > 0) {
                inputBuffer.deleteCharAt(inputBuffer.length() - 1);
                displayLabel.setText(inputBuffer.toString());
            }
        }
    }

    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            inputBuffer.setLength(0);
            displayLabel.setText("");
        }
    }

    private class AcceptButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            inputBuffer.setLength(0);
            displayLabel.setText("");

            String inputValue = displayLabel.getText();
            boolean isValid = true; // Cambia esta condición según tus criterios de validación

            if (isValid) {
                ITurno nuevoTurno = new Turno(inputValue);
                gestionAtencion.agregarTurno(nuevoTurno);
                showTicketDialog("Su número de ticket es: " + Turno.cantidadDeTurnos);
            } else {
                showErrorDialog("DNI incorrecto");
            }
        }
    }

    private void showErrorDialog(String message) {
        JDialog errorDialog = new JDialog(this, "Error", true);
        JLabel errorLabel = new JLabel(message, SwingConstants.CENTER);
        errorLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton okButton = new JButton("Aceptar");
        okButton.addActionListener(event -> errorDialog.dispose());

        JPanel errorPanel = new JPanel(new BorderLayout());
        errorPanel.add(errorLabel, BorderLayout.CENTER);
        errorPanel.add(okButton, BorderLayout.SOUTH);

        errorDialog.setContentPane(errorPanel);
        errorDialog.pack();
        errorDialog.setLocationRelativeTo(this);
        errorDialog.setVisible(true);
    }

    private void showTicketDialog(String message) {
        JDialog ticketDialog = new JDialog(this, "Ticket", true);
        JLabel ticketLabel = new JLabel(message, SwingConstants.CENTER);
        ticketLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel ticketPanel = new JPanel(new BorderLayout());
        ticketPanel.add(ticketLabel, BorderLayout.CENTER);

        ticketDialog.setContentPane(ticketPanel);
        ticketDialog.pack();
        ticketDialog.setLocationRelativeTo(this);
        ticketDialog.setVisible(true);

        // Cerrar el diálogo después de 5 segundos
        Timer timer = new Timer(5000, event -> ticketDialog.dispose());
        timer.setRepeats(false);
        timer.start();
    }

    public static void main(String[] args) {
        GestionAtencion gestionAtencion = GestionAtencion.getInstance(5); // Cambia el número
    }
}