package com.ungs.formar.persistencia.entidades;

import java.sql.Date;

public class Programa {
	private int programaID, area;
	private String nombre, descripcion;
	private Date fecha_aprobacion;

	public Programa(int programaID, int area, String nombre, String descripcion, Date fecha_aprobacion) {
		this.programaID = programaID;
		this.area = area;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha_aprobacion = fecha_aprobacion;
	}

	public int getProgramaID() {
		return programaID;
	}

	public void setProgramaID(int programaID) {
		this.programaID = programaID;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
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

	public Date getFecha_aprobacion() {
		return fecha_aprobacion;
	}

	public void setFecha_aprobacion(Date fecha_aprobacion) {
		this.fecha_aprobacion = fecha_aprobacion;
	}
	
}