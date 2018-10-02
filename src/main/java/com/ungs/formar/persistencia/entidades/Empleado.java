package com.ungs.formar.persistencia.entidades;

import java.sql.Date;

public class Empleado {
	private int empleadoID;
	private String legajo, nombre, apellido, telefono, email;
	private Date fecha_ingreso, fecha_egreso;
	
	public Empleado(int empleadoID, String legajo, String nombre, String apellido, String telefono, String email,
			Date fecha_ingreso, Date fecha_egreso) {
		this.empleadoID = empleadoID;
		this.legajo = legajo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.fecha_ingreso = fecha_ingreso;
		this.fecha_egreso = fecha_egreso;
	}

	public int getEmpleadoID() {
		return empleadoID;
	}

	public void setEmpleadoID(int empleadoID) {
		this.empleadoID = empleadoID;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
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

	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public Date getFecha_egreso() {
		return fecha_egreso;
	}

	public void setFecha_egreso(Date fecha_egreso) {
		this.fecha_egreso = fecha_egreso;
	}
	
}