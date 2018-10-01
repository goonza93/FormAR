package com.ungs.formar.persistencia.entidades;

import java.util.Date;

public class Empleado {
	
	private int empleadoID;
	private String DNI;
	private String nombre;
	private String apellido;
	private String telefono;
	private String email;
	private Date fechaIngreso;
	private Date fechaEgreso;
	
	
	public Empleado(int empleadoID, String DNI, String nombre, String apellido, String telefono,
			String email, Date fechaIngreso, Date fechaEgreso) {
		this.empleadoID = empleadoID;
		this.DNI = DNI;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.fechaIngreso = fechaIngreso;
		this.fechaEgreso = fechaEgreso;
	}


	public int getEmpleadoID() {
		return empleadoID;
	}


	public void setEmpleadoID(int empleadoID) {
		this.empleadoID = empleadoID;
	}


	public String getDNI() {
		return DNI;
	}


	public void setDNI(String dNI) {
		DNI = dNI;
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

	

	
}