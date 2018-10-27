package com.ungs.formar.vista.util;

import com.ungs.formar.persistencia.entidades.Empleado;

public class Sesion {
	private static Empleado empleado = null;
	
	public static void setEmpleado(Empleado e) {
		empleado = e;
	}
	
	public static Empleado getEmpleado() {
		return empleado;
	}

}