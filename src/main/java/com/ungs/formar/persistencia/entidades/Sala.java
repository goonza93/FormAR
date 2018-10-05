package com.ungs.formar.persistencia.entidades;

public class Sala {
	private Integer salaID, numero, capacidad;
	private String nombre;

	public Sala(Integer salaID, Integer numero, Integer capacidad, String nombre) {
		this.salaID = salaID;
		this.numero = numero;
		this.capacidad = capacidad;
		this.nombre = nombre;
	}

	public Integer getSalaID() {
		return salaID;
	}

	public void setSalaID(Integer salaID) {
		this.salaID = salaID;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
		
}