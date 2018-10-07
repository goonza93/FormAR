package com.ungs.formar.persistencia.entidades;

public class Horario {
	private Integer horarioID, dia, horaInicio, horaFin, minutoInicio, minutoFin;

	public Horario(Integer horarioID, Integer dia, Integer horaInicio, Integer horaFin, Integer minutoInicio,
			Integer minutoFin) {
		this.horarioID = horarioID;
		this.dia = dia;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.minutoInicio = minutoInicio;
		this.minutoFin = minutoFin;
	}

	public Integer getHorarioID() {
		return horarioID;
	}

	public void setHorarioID(Integer horarioID) {
		this.horarioID = horarioID;
	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
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

	public Integer getMinutoInicio() {
		return minutoInicio;
	}

	public void setMinutoInicio(Integer minutoInicio) {
		this.minutoInicio = minutoInicio;
	}

	public Integer getMinutoFin() {
		return minutoFin;
	}

	public void setMinutoFin(Integer minutoFin) {
		this.minutoFin = minutoFin;
	}
	
}