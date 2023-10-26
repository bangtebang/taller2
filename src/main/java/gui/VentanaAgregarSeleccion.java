package gui;
import model.Seleccion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAgregarSeleccion extends JFrame {
    private JPanel AgregarSeleccionPanel;
    private JTextField campoPais;
    private JTextField campoId;
    private JTextField campoRanking;
    private JButton agregarButton;
    private JButton cancelarButton;
    private JTextField campoBandera;

    public VentanaAgregarSeleccion() {
        setContentPane(AgregarSeleccionPanel);
        setTitle("Agregar Seleccion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pais = campoPais.getText();
                String id = campoId.getText();
                int ranking = Integer.parseInt(campoRanking.getText());
                String bandera = campoBandera.getText();
                try {
                    controller.SeleccionController.agregarSeleccion(pais, ranking, bandera,id);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(null, "Seleccion agregada exitosamente");
                VentanaAgregarJugadores AgregarJugadores = new VentanaAgregarJugadores(new Seleccion(pais,ranking,bandera,id));
                dispose();
            }
        });
    }
}