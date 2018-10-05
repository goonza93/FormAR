package com.ungs.formar.persistencia.entidades;

public class HorarioCursada {
	private Integer horarioCursadaID, cursoID, horarioID;

	public HorarioCursada(Integer horarioCursadaID, Integer cursoID, Integer horarioID) {
		this.horarioCursadaID = horarioCursadaID;
		this.cursoID = cursoID;
		this.horarioID = horarioID;
	}

	public Integer getHorarioCursadaID() {
		return horarioCursadaID;
	}

	public void setHorarioCursadaID(Integer horarioCursadaID) {
		this.horarioCursadaID = horarioCursadaID;
	}

	public Integer getCursoID() {
		return cursoID;
	}

	public void setCursoID(Integer cursoID) {
		this.cursoID = cursoID;
	}

	public Integer getHorarioID() {
		return horarioID;
	}

	public void setHorarioID(Integer horarioID) {
		this.horarioID = horarioID;
	}
	
}