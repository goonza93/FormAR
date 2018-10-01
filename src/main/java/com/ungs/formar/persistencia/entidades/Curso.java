package com.ungs.formar.persistencia.entidades;

import java.util.Date;

public class Curso {
	
	private int cursoID;
	private int cupoMinimo;
	private int cupoMaximo;
	private int vacantes;
	private Date fechaInicio;
	private Date fechaFin;
	private String contenidoEspecifico;
	private int cantidadTotalClases;
	private int horasTotalClases;
	private int instructor;
	private int sala;
	private int programa;
	private int estado;
	private int responsable;
	
	public Curso(int cursoID, int cupoMinimo, int cupoMaximo, int vacantes, Date fechaInicio,
			Date fechaFin, String contenidoEspecifico, int cantidadTotalClases, int horasTotalClases,
			int instructor, int sala, int programa, int estado, int responsable) {
		this.cursoID = cursoID;
		this.cupoMinimo = cupoMinimo;
		this.cupoMaximo = cupoMaximo;
		this.vacantes = vacantes;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.contenidoEspecifico = contenidoEspecifico;
		this.cantidadTotalClases = cantidadTotalClases;
		this.horasTotalClases = horasTotalClases;
		this.instructor = instructor;
		this.sala = sala;
		this.programa = programa;
		this.estado = estado;
		this.responsable = responsable;
		
	}

	public int getCursoID() {
		return cursoID;
	}

	public void setCursoID(int cursoID) {
		this.cursoID = cursoID;
	}

	public int getCupoMinimo() {
		return cupoMinimo;
	}

	public void setCupoMinimo(int cupoMinimo) {
		this.cupoMinimo = cupoMinimo;
	}

	public int getCupoMaximo() {
		return cupoMaximo;
	}

	public void setCupoMaximo(int cupoMaximo) {
		this.cupoMaximo = cupoMaximo;
	}

	public int getVacantes() {
		return vacantes;
	}

	public void setVacantes(int vacantes) {
		this.vacantes = vacantes;
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

	public String getContenidoEspecifico() {
		return contenidoEspecifico;
	}

	public void setContenidoEspecifico(String contenidoEspecifico) {
		this.contenidoEspecifico = contenidoEspecifico;
	}

	public int getCantidadTotalClases() {
		return cantidadTotalClases;
	}

	public void setCantidadTotalClases(int cantidadTotalClases) {
		this.cantidadTotalClases = cantidadTotalClases;
	}

	public int getHorasTotalClases() {
		return horasTotalClases;
	}

	public void setHorasTotalClases(int horasTotalClases) {
		this.horasTotalClases = horasTotalClases;
	}

	public int getInstructor() {
		return instructor;
	}

	public void setInstructor(int instructor) {
		this.instructor = instructor;
	}

	public int getSala() {
		return sala;
	}

	public void setSala(int sala) {
		this.sala = sala;
	}

	public int getPrograma() {
		return programa;
	}

	public void setPrograma(int programa) {
		this.programa = programa;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getResponsable() {
		return responsable;
	}

	public void setResponsable(int responsable) {
		this.responsable = responsable;
	}
	
}