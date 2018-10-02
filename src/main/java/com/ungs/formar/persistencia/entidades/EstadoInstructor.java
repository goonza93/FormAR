package com.ungs.formar.persistencia.entidades;

public class EstadoInstructor {
	
	private int estadoInstructorID;
	private String estado;
	
	public EstadoInstructor(int estadoInstructorID, String estado) {
		this.estadoInstructorID = estadoInstructorID;
		this.estado = estado;
	}

	public int getEstadoInstructorID() {
		return estadoInstructorID;
	}

	public void setEstadoInstructorID(int estadoInstructorID) {
		this.estadoInstructorID = estadoInstructorID;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	
}