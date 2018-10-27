package com.ungs.formar.persistencia.entidades;

public class HorarioCursada {
	private Integer ID, curso, horario, sala;
	private Boolean activo;
	
	public HorarioCursada(Integer iD, Integer curso, Integer horario, Integer sala, Boolean activo) {
		this.ID = iD;
		this.curso = curso;
		this.horario = horario;
		this.sala = sala;
		this.activo = activo;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		this.ID = iD;
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

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
}