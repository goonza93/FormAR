package com.ungs.formar.persistencia.entidades;

import java.sql.Date;

public class Examen {
	private Integer ID, alumno, curso, nota;
	private String descripcion;
	private Date fecha;

	public Examen(Integer ID, Integer alumno, Integer curso, Integer nota, String descripcion, Date fecha) {
		this.ID = ID;
		this.alumno = alumno;
		this.curso = curso;
		this.nota = nota;
		this.descripcion = descripcion;
		this.fecha = fecha;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer id) {
		this.ID = id;
	}

	public Integer getAlumno() {
		return alumno;
	}

	public void setAlumno(Integer alumno) {
		this.alumno = alumno;
	}

	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}