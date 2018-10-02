package com.ungs.formar.persistencia.entidades;

public class Sala {
	private int salaID, numero, capacidad;
	private String nombre;

	public Sala(int salaID, int numero, int capacidad, String nombre) {
		this.salaID = salaID;
		this.numero = numero;
		this.capacidad = capacidad;
		this.nombre = nombre;
	}

	public int getSalaID() {
		return salaID;
	}

	public void setSalaID(int salaID) {
		this.salaID = salaID;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}