package com.ungs.formar.persistencia.entidades;

public class Tarea {
	private Integer ID, empleado;
	private String contenido, titulo;
	private boolean pendiente;
	
	public Tarea(Integer ID, Integer empleadoID, String contenido, String titulo){
		this.ID = ID;
		this.empleado = empleadoID;
		this.contenido = contenido;
		this.titulo = titulo;
		this.pendiente = true;
	}

	public Integer getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Integer empleado) {
		this.empleado = empleado;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public boolean isPendiente() {
		return pendiente;
	}

	public void setPendiente(boolean pendiente) {
		this.pendiente = pendiente;
	}

	public Integer getID() {
		return ID;
	}

}
