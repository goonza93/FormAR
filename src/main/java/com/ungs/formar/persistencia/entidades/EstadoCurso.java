package com.ungs.formar.persistencia.entidades;

public class EstadoCurso {
	
	private int estadoID;
	private String estado;
	
	public EstadoCurso(int estadoID, String estado) {
		this.estadoID = estadoID;
		this.estado = estado;
	}

	public int getEstadoID() {
		return estadoID;
	}

	public void setEstadoID(int estadoID) {
		this.estadoID = estadoID;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


	
}