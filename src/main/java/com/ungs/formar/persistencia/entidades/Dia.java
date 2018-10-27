package com.ungs.formar.persistencia.entidades;

public class Dia {
	private Integer ID;
	private String descripcion;
	
	public Dia(Integer ID, String descripcion) {
		this.ID = ID;
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return descripcion;
	}

	public Integer getDiaID() {
		return ID;
	}

	public void setID(Integer ID) {
		this.ID = ID;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}