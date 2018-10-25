package com.ungs.formar.persistencia.entidades;

import java.sql.Date;

import com.ungs.formar.persistencia.definidos.EstadoCurso;

public class Curso {
	private Integer cursoID, cupoMinimo, cupoMaximo, precio, horas;
	private String comision;
	private Date fechaInicio, fechaFin, fechaCierre;
	private Integer instructor, programa, responsable, contenido;
	private EstadoCurso estado;
	
	public Curso(Integer cursoID, Integer cupoMinimo, Integer cupoMaximo, Integer precio, Integer horas,
			Integer contenido, String comision, Date fechaInicio, Date fechaFin, Date fechaCierre,
			Integer instructor, Integer programa, EstadoCurso estado,
			Integer responsable) {
		this.cursoID = cursoID;
		this.cupoMinimo = cupoMinimo;
		this.cupoMaximo = cupoMaximo;
		this.precio = precio;
		this.horas = horas;
		this.contenido = contenido;
		this.comision = comision;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaCierre = fechaCierre;
		this.instructor = instructor;
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

	public Integer getHoras() {
		return horas;
	}

	public void setHoras(Integer horas) {
		this.horas = horas;
	}

	public Integer getContenido() {
		return contenido;
	}

	public void setContenido(Integer contenido) {
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

	public Integer getPrograma() {
		return programa;
	}

	public void setPrograma(Integer programa) {
		this.programa = programa;
	}

	public EstadoCurso getEstado() {
		return estado;
	}

	public void setEstado(EstadoCurso estado) {
		this.estado = estado;
	}

	public Integer getResponsable() {
		return responsable;
	}

	public void setResponsable(Integer responsable) {
		this.responsable = responsable;
	}

	public String getComision() {
		return comision;
	}

	public void setComision(String comision) {
		this.comision = comision;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
		
}