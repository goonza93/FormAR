package com.ungs.formar.persistencia.interfacesOBD;

import java.util.List;

import com.ungs.formar.persistencia.entidades.Empleado;

public interface EmpleadoODB {
	
	public void insert (Empleado empleado);
	public void edit (Empleado empleado);
	public void delete (Empleado empleado);
	public List<Empleado> select();
	public Empleado selectByNombre(String nombre);
	public Empleado selectByID(Integer id);
	public List<Empleado> selectByRol(Integer rolID);
	
}