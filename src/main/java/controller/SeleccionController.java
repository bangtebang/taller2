package controller;

import model.*;
import model.data.*;
import model.data.dao.*;
import org.jooq.DSLContext;
public class SeleccionController {

	public static boolean agregarSeleccion(String nombreSeleccion,int ranking, String id, String bandera) throws ClassNotFoundException {
		DSLContext query = DBGenerator.conectarBD("Fifa");
		if(!SeleccionDAO.validarExistenciaSelecciom(query,"id",id)){
			Seleccion seleccion = new Seleccion(nombreSeleccion,ranking,bandera,id);
			SeleccionDAO.agregarSeleccion(query,seleccion);
			DBConnector.closeConnection();
			return true;
		}
		else{
			return false;
		}
	}

	public static String[][] mostrarSeleccion(String id) throws ClassNotFoundException {
		DSLContext query = DBGenerator.conectarBD("Fifa");
		String[][] datosSeleccion = SeleccionDAO.obtenerSeleccion(query,id);
		DBConnector.closeConnection();
		return datosSeleccion;
	}

	public static boolean agregarJugador(String nombreJugador,String posicion, Seleccion seleccion, int numero) throws ClassNotFoundException {
		DSLContext query = DBGenerator.conectarBD("Fifa");
		if(!JugadorDAO.validarExistenciaJugador(query,"id",seleccion)){
			Jugador jugador = new Jugador(nombreJugador,numero,posicion,seleccion);
			JugadorDAO.agregarJugador(query,jugador);
			DBConnector.closeConnection();
			return true;
		}
		else{
			return false;
		}
	}
	}
