package model;

import model.Jugador__;
import model.Jugador;

public class Seleccion {
	private String nombre;
	private int ranking;
	private String bandera;
	private String id;
	private Jugador__ jugadores = new Jugador[0];

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getRanking() {
		return this.ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public String getBandera() {
		return this.bandera;
	}

	public void setBandera(String bandera) {
		this.bandera = bandera;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Seleccion() {
		throw new UnsupportedOperationException();
	}
}