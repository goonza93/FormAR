package com.ungs.formar.persistencia.entidades;

import java.sql.Date;

public class Programa {
	private Integer programaID, areaID;
	private String nombre, descripcion;
	private Date fechaAprobacion;
	
	public Programa(Integer programaID, Integer areaID, String nombre, String descripcion, Date fechaAprobacion) {
		this.programaID = programaID;
		this.areaID = areaID;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaAprobacion = fechaAprobacion;
	}

	public Integer getProgramaID() {
		return programaID;
	}

	public void setProgramaID(Integer programaID) {
		this.programaID = programaID;
	}

	public Integer getAreaID() {
		return areaID;
	}

	public void setAreaID(Integer areaID) {
		this.areaID = areaID;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaAprobacion() {
		return fechaAprobacion;
	}

	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}
	
}