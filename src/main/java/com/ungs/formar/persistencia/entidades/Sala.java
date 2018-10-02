package com.ungs.formar.persistencia.entidades;

public class Sala {
	private int salaID;
	private int numero;
	private String nombre;
	private int capacidad;
	
	public Sala(int salaID, int numero, String nombre, int capacidad) {
		this.salaID = salaID;
		this.numero = numero;
		this.nombre = nombre;
		this.capacidad = capacidad;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	
}