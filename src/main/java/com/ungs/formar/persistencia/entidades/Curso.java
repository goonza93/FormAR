package com.ungs.formar.persistencia.entidades;

import java.sql.Date;

public class Curso {
	private Integer cursoID, cupoMinimo, cupoMaximo, clases, horas;
	private String nombre, contenido;
	private Date fechaInicio, fechaFin;
	private Integer instructor, sala, programa, estado, responsable;

	public Curso(Integer cursoID, Integer cupoMinimo, Integer cupoMaximo, Integer clases, Integer horas, String nombre,
			String contenido, Date fechaInicio, Date fechaFin, Integer instructor, Integer sala, Integer programa,
			Integer estado, Integer responsable) {
		this.cursoID = cursoID;
		this.cupoMinimo = cupoMinimo;
		this.cupoMaximo = cupoMaximo;
		this.clases = clases;
		this.horas = horas;
		this.nombre = nombre;
		this.contenido = contenido;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.instructor = instructor;
		this.sala = sala;
		this.programa = programa;
		this.estado = estado;
		this.responsable = responsable;
	}

	public Integer getCursoID() {
		return cursoID;
	}

	public void setCursoID(Integer cursoID) {
		this.cursoID = cursoID;
	}

	public Integer getCupoMinimo() {
		return cupoMinimo;
	}

	public void setCupoMinimo(Integer cupoMinimo) {
		this.cupoMinimo = cupoMinimo;
	}

	public Integer getCupoMaximo() {
		return cupoMaximo;
	}

	public void setCupoMaximo(Integer cupoMaximo) {
		this.cupoMaximo = cupoMaximo;
	}

	public Integer getClases() {
		return clases;
	}

	public void setClases(Integer clases) {
		this.clases = clases;
	}

	public Integer getHoras() {
		return horas;
	}

	public void setHoras(Integer horas) {
		this.horas = horas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getInstructor() {
		return instructor;
	}

	public void setInstructor(Integer instructor) {
		this.instructor = instructor;
	}

	public Integer getSala() {
		return sala;
	}

	public void setSala(Integer sala) {
		this.sala = sala;
	}

	public Integer getPrograma() {
		return programa;
	}

	public void setPrograma(Integer programa) {
		this.programa = programa;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getResponsable() {
		return responsable;
	}

	public void setResponsable(Integer responsable) {
		this.responsable = responsable;
	}

}