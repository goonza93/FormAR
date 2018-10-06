package com.ungs.formar.persistencia.entidades;

public class HorarioCursada {
	private Integer horarioID, curso, horario, sala;

	public HorarioCursada(Integer horarioID, Integer curso, Integer horario, Integer sala) {
		this.horarioID = horarioID;
		this.curso = curso;
		this.horario = horario;
		this.sala = sala;
	}

	public Integer getHorarioID() {
		return horarioID;
	}

	public void setHorarioID(Integer horarioID) {
		this.horarioID = horarioID;
	}

	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public Integer getHorario() {
		return horario;
	}

	public void setHorario(Integer horario) {
		this.horario = horario;
	}

	public Integer getSala() {
		return sala;
	}

	public void setSala(Integer sala) {
		this.sala = sala;
	}

}