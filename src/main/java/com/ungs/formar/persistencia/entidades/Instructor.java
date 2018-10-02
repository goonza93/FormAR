package com.ungs.formar.persistencia.entidades;

public class Instructor {
	
	private int instructorID;
	private int empleado;
	private int estadoInstructor;
	
	public Instructor(int instructorID, int empleado, int estadoInstructor) {
		this.instructorID = instructorID;
		this.empleado = empleado;
		this.estadoInstructor = estadoInstructor;
	}

	public int getInstructorID() {
		return instructorID;
	}

	public void setInstructorID(int instructorID) {
		this.instructorID = instructorID;
	}

	public int getEmpleado() {
		return empleado;
	}

	public void setEmpleado(int empleado) {
		this.empleado = empleado;
	}

	public int getEstadoInstructor() {
		return estadoInstructor;
	}

	public void setEstadoInstructor(int estadoInstructor) {
		this.estadoInstructor = estadoInstructor;
	}

	
	
}