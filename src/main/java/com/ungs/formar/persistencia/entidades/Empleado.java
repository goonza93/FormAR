package com.ungs.formar.persistencia.entidades;

import java.sql.Date;
import com.ungs.formar.persistencia.definidos.Rol;

public class Empleado {
	private Integer ID;
	private String DNI, nombre, apellido, telefono, email, usuario, password;
	private Date fechaIngreso, fechaEgreso;
	private Rol rol;
	private Boolean activo;
	
	public Empleado(Integer ID, String DNI, String nombre, String apellido, String telefono, String email,
			String usuario, String password, Date fechaIngreso, Date fechaEgreso, Rol rol, Boolean activo) {
		this.ID = ID;
		this.DNI = DNI;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.usuario = usuario;
		this.password = password;
		this.fechaIngreso = fechaIngreso;
		this.fechaEgreso = fechaEgreso;
		this.rol = rol;
		this.activo = activo;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer ID) {
		this.ID = ID;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dni) {
		this.DNI = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
}