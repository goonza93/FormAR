package com.ungs.formar.persistencia.entidades;

import java.sql.Date;

public class Interaccion {
	private Integer ID, empleadoID, interesadoID, areaID, cursoID;
	private Date fechaInteraccion;
	private String descripcion;

	public Interaccion(Integer id, Integer empleado, Integer interesado, Integer area, Integer curso, Date fecha, String desc){
		this.ID = id;
		this.empleadoID = empleado;
		this.interesadoID = interesado;
		this.areaID = area;
		this.cursoID = curso;
		this.fechaInteraccion = fecha;
		this.descripcion = desc;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getEmpleadoID() {
		return empleadoID;
	}

	public void setEmpleadoID(Integer empleadoID) {
		this.empleadoID = empleadoID;
	}

	public Integer getInteresadoID() {
		return interesadoID;
	}

	public void setInteresadoID(Integer interesadoID) {
		this.interesadoID = interesadoID;
	}

	public Integer getAreaID() {
		return areaID;
	}

	public void setAreaID(Integer areaID) {
		this.areaID = areaID;
	}

	public Integer getCursoID() {
		return cursoID;
	}

	public void setCursoID(Integer cursoID) {
		this.cursoID = cursoID;
	}

	public Date getFechaInteraccion() {
		return fechaInteraccion;
	}

	public void setFechaInteraccion(Date fechaInteraccion) {
		this.fechaInteraccion = fechaInteraccion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
