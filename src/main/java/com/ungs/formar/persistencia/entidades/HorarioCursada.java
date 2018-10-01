package com.ungs.formar.persistencia.entidades;

public class HorarioCursada {

	private int horarioCursadaID;
	private int curso;
	private int horario;
	
	public HorarioCursada(int horarioCursadaID, int curso, int horario) {
		this.horarioCursadaID = horarioCursadaID;
		this.curso = curso;
		this.horario = horario;
	}

	public int getHorarioCursadaID() {
		return horarioCursadaID;
	}

	public void setHorarioCursadaID(int horarioCursadaID) {
		this.horarioCursadaID = horarioCursadaID;
	}

	public int getCurso() {
		return curso;
	}

	public void setCurso(int curso) {
		this.curso = curso;
	}

	public int getHorario() {
		return horario;
	}

	public void setHorario(int horario) {
		this.horario = horario;
	}

	
	
}