package gui;
import model.Seleccion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAgregarJugadores extends JFrame {
    private JPanel AgregarJugadoresPanel;
    private JTextField campoNumero;
    private JTextField campoNombre;
    private JTextField campoPosicion;
    private JButton cancelarButton;
    private JButton agregarButton;
    public VentanaAgregarJugadores(Seleccion seleccion) {
        setContentPane(AgregarJugadoresPanel);
        setTitle("Agregar Jugadores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numero = Integer.parseInt(campoNumero.getText());
                String nombre = campoNombre.getText();
                String posicion = campoPosicion.getText();
                try {
                    controller.SeleccionController.agregarJugador( nombre,numero, posicion,seleccion);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(null, "Jugador agregado exitosamente");
                if (numero==23){
                    VentanaMenu ventanaMenu = new VentanaMenu();
                    dispose();
                }
                else{
                    VentanaAgregarJugadores ventanaAgregarJugadores = new VentanaAgregarJugadores(seleccion);
                    dispose();
                }
            }
        });
    }
}
