package com.ungs.formar.persistencia.entidades;

public class EstadoCurso {
	private Integer estadoID;
	private String descripcion;
	
	public EstadoCurso(Integer estadoID, String descripcion) {
		this.estadoID = estadoID;
		this.descripcion = descripcion;
	}

	public Integer getEstadoID() {
		return estadoID;
	}

	public void setEstadoID(Integer estadoID) {
		this.estadoID = estadoID;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}