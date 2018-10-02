package com.ungs.formar.persistencia.entidades;

import java.sql.Date;

public class Curso {
	private int cursoID, cupoMinimo, cupoMaximo, cantidadClases, instructor, sala, programa;
	private String nombre, contenido;
	private Date fecha_inicio, fecha_fin;
	
	public Curso(int cursoID, int cupoMinimo, int cupoMaximo, int cantidadClases, int instructor, int sala,
			int programa, String nombre, String contenido, Date fecha_inicio, Date fecha_fin) {
		this.cursoID = cursoID;
		this.cupoMinimo = cupoMinimo;
		this.cupoMaximo = cupoMaximo;
		this.cantidadClases = cantidadClases;
		this.instructor = instructor;
		this.sala = sala;
		this.programa = programa;
		this.nombre = nombre;
		this.contenido = contenido;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
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

	public int getCantidadClases() {
		return cantidadClases;
	}

	public void setCantidadClases(int cantidadClases) {
		this.cantidadClases = cantidadClases;
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

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	
}