package com.ungs.formar.persistencia.entidades;

public class Area {
	private Integer ID;
	private String nombre, descripcion;
	private Boolean activo;
	
	public Area(Integer iD, String nombre, String descripcion, Boolean activo) {
		this.ID = iD;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.activo = activo;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		this.ID = iD;
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

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
}