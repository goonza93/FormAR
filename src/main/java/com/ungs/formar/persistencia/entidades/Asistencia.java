package com.ungs.formar.persistencia.entidades;

import java.sql.Date;

public class Asistencia {
	private Integer ID, curso, alumno;
	private Date fecha;
	private Boolean presente;

	public Asistencia(Integer ID, Integer curso, Integer alumno, Date fecha, Boolean presente) {
		this.ID = ID;
		this.curso = curso;
		this.alumno = alumno;
		this.fecha = fecha;
		this.presente = presente;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer ID) {
		this.ID = ID;
	}

	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public Integer getAlumno() {
		return alumno;
	}

	public void setAlumno(Integer alumno) {
		this.alumno = alumno;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Boolean isPresente() {
		return presente;
	}

	public void setPresente(Boolean presente) {
		this.presente = presente;
	}

}