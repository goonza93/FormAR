package com.ungs.formar.persistencia.entidades;

import java.sql.Date;

import com.ungs.formar.persistencia.definidos.TipoNotificacion;

public class Notificacion {
	private Integer ID, empleado;
	private String contenido;
	private boolean mostrado, leido;
	private Date fechaAMostrar;
	private TipoNotificacion tipo;
	
	public Notificacion(Integer ID, TipoNotificacion tipo, Integer empleadoID, String contenido, boolean mostrado, boolean leido, Date fechaANotificar){
		this.ID = ID;
		this.tipo = tipo;
		this.empleado = empleadoID;
		this.contenido = contenido;
		this.mostrado = mostrado;
		this.leido = leido;
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

	public TipoNotificacion getTipo() {
		return tipo;
	}

	public void setTipo(TipoNotificacion tipo) {
		this.tipo = tipo;
	}
	
}