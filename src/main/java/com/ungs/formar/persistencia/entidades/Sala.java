package com.ungs.formar.persistencia.entidades;

public class Sala {
	private Integer ID, numero, capacidad;
	private String nombre;
	private Boolean activo;
	
	public Sala(Integer iD, Integer numero, Integer capacidad, String nombre, Boolean activo) {
		this.ID = iD;
		this.numero = numero;
		this.capacidad = capacidad;
		this.nombre = nombre;
		this.activo = activo;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		this.ID = iD;
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

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
		
}