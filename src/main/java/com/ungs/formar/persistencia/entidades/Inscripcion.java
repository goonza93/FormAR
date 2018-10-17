package com.ungs.formar.persistencia.entidades;

import java.sql.Date;

public class Inscripcion {
	private Integer inscripcionID, cliente, curso, empleado;
	private Date fecha;
	private Double nota;

	public Inscripcion(Integer inscripcionID, Integer cliente, Integer curso, Integer empleado, Date fecha,
			Double nota) {
		this.inscripcionID = inscripcionID;
		this.cliente = cliente;
		this.curso = curso;
		this.empleado = empleado;
		this.fecha = fecha;
		this.nota = nota;
	}

	public Integer getInscripcionID() {
		return inscripcionID;
	}

	public void setInscripcionID(Integer inscripcionID) {
		this.inscripcionID = inscripcionID;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public Integer getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Integer empleado) {
		this.empleado = empleado;
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