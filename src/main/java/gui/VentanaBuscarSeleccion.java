package gui;
import javax.swing.*;
public class VentanaBuscarSeleccion extends JFrame{
    private JPanel BuscarSeleccionPanel;
    private JTextField campoId;
    private JTextField campoPais;
    private JButton buscarButton;
    private JButton cancelarButton;

    public VentanaBuscarSeleccion(){
        setContentPane(BuscarSeleccionPanel);
        setTitle("Buscar Seleccion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}