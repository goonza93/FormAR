package com.ungs.formar.persistencia.entidades;

import java.sql.Date;

public class Programa {
	private Integer programaID, areaID, horas;
	private String nombre, descripcion, codigo;
	private Date fechaAprobacion;
	
	public Programa(Integer programaID, Integer areaID, Integer horas, String nombre, String descripcion, Date fechaAprobacion, String codigo) {
		this.programaID = programaID;
		this.areaID = areaID;
		this.horas = horas;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaAprobacion = fechaAprobacion;
		this.codigo = codigo;
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

	public Integer getHoras() {
		return horas;
	}

	public void setHoras(Integer horas) {
		this.horas = horas;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
}