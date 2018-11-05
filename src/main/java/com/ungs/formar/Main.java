package com.ungs.formar;

import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.vista.login.ControladorLogin;
import com.ungs.formar.vista.login.VentanaIniciarSesion;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPantallaPrincipal;
import com.ungs.formar.vista.pantallasPrincipales.PantallaPrincipalAdministrativo;

public class Main {

	public static void main(String[] args) {
		
/*
		ControladorPantallaPrincipal controlador = new ControladorPantallaPrincipal(EmpleadoManager.traerEmpleado(8));
		controlador.inicializar();
		*/
		
		VentanaIniciarSesion v = new VentanaIniciarSesion();
		ControladorLogin c = new ControladorLogin(v);
		c.inicializar();
		
	}
	
}