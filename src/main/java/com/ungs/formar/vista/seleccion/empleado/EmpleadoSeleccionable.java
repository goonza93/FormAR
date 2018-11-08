package com.ungs.formar.vista.seleccion.empleado;

import java.util.List;

import com.ungs.formar.persistencia.entidades.Empleado;

public interface EmpleadoSeleccionable {

	public void setEmpleado(List<Empleado> empleado);
	
	public void mostrar();
	
}