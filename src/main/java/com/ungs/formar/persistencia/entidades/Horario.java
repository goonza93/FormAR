package com.ungs.formar.persistencia.entidades;

public class Horario {
	private Integer horarioID, diaID, horaInicio, horaFin;

	public Horario(Integer horarioID, Integer diaID, Integer horaInicio, Integer horaFin) {
		this.horarioID = horarioID;
		this.diaID = diaID;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}

	public Integer getHorarioID() {
		return horarioID;
	}

	public void setHorarioID(Integer horarioID) {
		this.horarioID = horarioID;
	}

	public Integer getDiaID() {
		return diaID;
	}

	public void setDiaID(Integer diaID) {
		this.diaID = diaID;
	}

	public Integer getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Integer horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Integer getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Integer horaFin) {
		this.horaFin = horaFin;
	}
	
}