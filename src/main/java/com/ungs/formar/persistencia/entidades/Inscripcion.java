package com.ungs.formar.persistencia.entidades;

import java.sql.Date;

public class Inscripcion {
	private Integer ID, alumno, empleado, curso;
	private Date fecha;
	private Double nota;
	
	public Inscripcion(Integer iD, Integer alumno, Integer empleado, Integer curso, Date fecha, Double nota) {
		this.ID = iD;
		this.alumno = alumno;
		this.empleado = empleado;
		this.curso = curso;
		this.fecha = fecha;
		this.nota = nota;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		this.ID = iD;
	}

	public Integer getAlumno() {
		return alumno;
	}

	public void setAlumno(Integer alumno) {
		this.alumno = alumno;
	}

	public Integer getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Integer empleado) {
		this.empleado = empleado;
	}

	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

}