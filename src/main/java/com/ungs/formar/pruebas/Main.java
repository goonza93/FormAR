package com.ungs.formar.pruebas;

import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.email.ControladorCambiarEmail;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPantallaPrincipal;

public class Main {

	public static void main(String[] args) {
		Empleado empleado = EmpleadoManager.traerEmpleado(1);
		//new ControladorCambiarEmail(new ControladorPantallaPrincipal(empleado));
	}
	
}