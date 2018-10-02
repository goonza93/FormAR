package com.ungs.formar.persistencia.entidades;

import java.util.Date;

public class Programa {
	
	private int programaID;
	private String nombreMateria;
	private Date fechaAprobacion;
	private String descripcion;
	private int areaDeInteres;
	
	public Programa(int programaID, String nombreMateria, Date fechaAprobacion, String descripcion, int areaDeInteres) {
		this.programaID = programaID;
		this.nombreMateria = nombreMateria;
		this.fechaAprobacion = fechaAprobacion;
		this.descripcion = descripcion;
		this.areaDeInteres = areaDeInteres;
	}

	public int getProgramaID() {
		return programaID;
	}

	public void setProgramaID(int programaID) {
		this.programaID = programaID;
	}

	public String getNombreMateria() {
		return nombreMateria;
	}

	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}

	public Date getFechaAprobacion() {
		return fechaAprobacion;
	}

	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getAreaDeInteres() {
		return areaDeInteres;
	}

	public void setAreaDeInteres(int areaDeInteres) {
		this.areaDeInteres = areaDeInteres;
	}


	
}