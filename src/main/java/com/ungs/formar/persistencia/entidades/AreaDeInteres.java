package com.ungs.formar.persistencia.entidades;

public class AreaDeInteres {
	
	private int areaID;
	private String area;
	private String descripcion;
	
	public AreaDeInteres(int areaID, String area, String descripcion) {
		this.areaID = areaID;
		this.area = area;
		this.descripcion = descripcion;
	}

	public int getAreaID() {
		return areaID;
	}

	public void setAreaID(int areaID) {
		this.areaID = areaID;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	
}