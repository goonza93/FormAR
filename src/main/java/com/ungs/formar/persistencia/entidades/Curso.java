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


	
}