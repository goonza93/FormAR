package com.ungs.formar.pruebas.ventanainterna;

import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.vista.util.Sesion;

public class Main {

	public static void main(String[] args) {
		Sesion.setEmpleado(EmpleadoManager.traerEmpleado(5));
		new ControladorPrincipal();		
	}

}