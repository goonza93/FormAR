package com.ungs.formar.persistencia.entidades;

public class Usuario {
	private Integer usuarioID;
	private String nombre, password;
	
	public Usuario(Integer usuarioID, String nombre, String password) {
		this.usuarioID = usuarioID;
		this.nombre = nombre;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Usuario [usuarioID=" + usuarioID + ", nombre=" + nombre + ", password=" + password + "]";
	}

	public Integer getUsuarioID() {
		return usuarioID;
	}

	public void setUsuarioID(Integer usuarioID) {
		this.usuarioID = usuarioID;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}