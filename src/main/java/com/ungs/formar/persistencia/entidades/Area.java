package com.ungs.formar.persistencia.entidades;

public class Area {
	private Integer areaID;
	private String nombre, descripcion;
	
	public Area(Integer areaID, String nombre, String descripcion) {
		this.areaID = areaID;
		this.nombre = nombre;
		this.descripcion = descripcion;
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
	
}