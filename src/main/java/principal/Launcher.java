package principal;

import gui.*;
import model.data.DBGenerator;

public class Launcher {
    public static void main(String[] args) throws ClassNotFoundException {
        DBGenerator.iniciarBD("Fifa");
        new VentanaMenu();
    }
}