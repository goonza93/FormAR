package com.ungs.formar.persistencia.entidades;

import java.sql.Date;

public class Recado {
	private Integer ID, empleado, receptor, emisor;
	private String contenido, titulo;
	private boolean leido, archivado;
	private Date fecha;

	public Recado(Integer ID, Integer empleado, Integer receptor, Integer emisor, String contenido, String titulo,
			boolean leido, boolean archivado, Date fecha) {
		this.ID = ID;
		this.empleado = empleado;
		this.receptor = receptor;
		this.emisor = emisor;
		this.titulo = titulo;
		this.contenido = contenido;
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