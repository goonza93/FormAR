package com.ungs.formar.persistencia.entidades;

import java.sql.Date;

public class Pago {
	private Integer ID, alumno, cursada, empleado, monto, mes;
	private Boolean pagoEnTermino, pagoCompleto;
	private Date fecha;

	public Pago(Integer ID, Integer alumno, Integer cursada, Integer empleado, Integer monto, Integer mes,
			Boolean pagoEnTermino, Boolean pagoCompleto, Date fecha) {
		this.ID = ID;
		this.alumno = alumno;
		this.cursada = cursada;
		this.empleado = empleado;
		this.monto = monto;
		this.mes = mes;
		this.pagoEnTermino = pagoEnTermino;
		this.pagoCompleto = pagoCompleto;
		this.fecha = fecha;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer ID) {
		this.ID = ID;
	}

	public Integer getAlumno() {
		return alumno;
	}

	public void setAlumno(Integer alumno) {
		this.alumno = alumno;
	}

	public Integer getCursada() {
		return cursada;
	}

	public void setCursada(Integer cursada) {
		this.cursada = cursada;
	}

	public Integer getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Integer empleado) {
		this.empleado = empleado;
	}

	public Integer getMonto() {
		return monto;
	}

	public void setMonto(Integer monto) {
		this.monto = monto;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Boolean isPagoEnTermino() {
		return pagoEnTermino;
	}

	public void setPagoEnTermino(Boolean pagoEnTermino) {
		this.pagoEnTermino = pagoEnTermino;
	}

	public Boolean isPagoCompleto() {
		return pagoCompleto;
	}

	public void setPagoCompleto(Boolean pagoCompleto) {
		this.pagoCompleto = pagoCompleto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}