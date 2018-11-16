package com.ungs.formar.persistencia.entidades;

import java.sql.Date;

public class Notificacion {
	private Integer ID, empleado;
	private String contenido;
	private boolean mostrado, leido;
	private Date fechaAMostrar;
	
	public Notificacion(Integer ID, Integer empleadoID, String contenido, Date fechaANotificar){
		this.ID = ID;
		this.empleado = empleadoID;
		this.contenido = contenido;
		this.mostrado = false;
		this.leido = false;
		this.fechaAMostrar = fechaANotificar;
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

	public boolean isMostrado() {
		return mostrado;
	}

	public void setMostrado(boolean mostrado) {
		this.mostrado = mostrado;
	}

	public boolean isLeido() {
		return leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}

	public Date getFechaAMostrar() {
		return fechaAMostrar;
	}

	public void setFechaAMostrar(Date fechaAMostrar) {
		this.fechaAMostrar = fechaAMostrar;
	}

	public Integer getID() {
		return ID;
	}
	
}