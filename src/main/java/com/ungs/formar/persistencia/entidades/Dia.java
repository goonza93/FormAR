package com.ungs.formar.persistencia.entidades;

public class Dia {
	private Integer diaID;
	private String descripcion;
	
	public Dia(Integer diaID, String descripcion) {
		this.diaID = diaID;
		this.descripcion = descripcion;
	}

	public Integer getDiaID() {
		return diaID;
	}

	public void setDiaID(Integer diaID) {
		this.diaID = diaID;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}