package gui;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaMenu extends JFrame {
    private JPanel MenuPanel;
    private JButton agregarSeleccionButton;
    private JButton buscarSeleccionButton;
    private JButton salirButton;

    public VentanaMenu() {
        setContentPane(MenuPanel);
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        agregarSeleccionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaAgregarSeleccion ventanaAgregarSeleccion = new VentanaAgregarSeleccion();
                dispose();
            }
        });
        buscarSeleccionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaBuscarSeleccion ventanaBuscarSeleccion = new VentanaBuscarSeleccion();
                dispose();
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}