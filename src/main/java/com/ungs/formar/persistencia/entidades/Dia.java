package com.ungs.formar.persistencia.entidades;

public class Dia {

	private int diaID;
	private String dia;
	
	public Dia(int diaID, String dia) {
		this.diaID = diaID;
		this.dia = dia;
	}

	public int getDiaID() {
		return diaID;
	}

	public void setDiaID(int diaID) {
		this.diaID = diaID;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	
	
}