package com.ungs.formar.persistencia.entidades;

import java.util.Date;

public class Horario {

	private int horarioID;
	private Date horaInicio;
	private Date horaFin;
	private int dia;
	
	public Horario(int horarioID, Date horaInicio, Date horaFin, int dia) {
		this.horarioID = horarioID;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.dia = dia;
	}

	public int getHorarioID() {
		return horarioID;
	}

	public void setHorarioID(int horarioID) {
		this.horarioID = horarioID;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	
	
}