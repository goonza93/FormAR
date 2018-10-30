package com.ungs.formar.persistencia.entidades;

import java.sql.Date;

public class Recado {
	private Integer ID, empleado, receptor, emisor;
	private String mensaje;
	private boolean leido, archivado;
	private Date fecha;

	public Recado(Integer ID, Integer empleado, Integer receptor, Integer emisor, String mensaje, boolean leido,
			boolean archivado, Date fecha) {
		this.ID = ID;
		this.empleado = empleado;
		this.receptor = receptor;
		this.emisor = emisor;
		this.mensaje = mensaje;
		this.leido = leido;
		this.archivado = archivado;
		this.fecha = fecha;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer ID) {
		this.ID = ID;
	}

	public Integer getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Integer empleado) {
		this.empleado = empleado;
	}

	public Integer getReceptor() {
		return receptor;
	}

	public void setReceptor(Integer receptor) {
		this.receptor = receptor;
	}

	public Integer getEmisor() {
		return emisor;
	}

	public void setEmisor(Integer emisor) {
		this.emisor = emisor;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isLeido() {
		return leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}

	public boolean isArchivado() {
		return archivado;
	}

	public void setArchivado(boolean archivado) {
		this.archivado = archivado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}