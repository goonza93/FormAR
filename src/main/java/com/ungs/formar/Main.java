package com.ungs.formar;

import com.ungs.formar.vista.controladores.ControladorPantallaPrincipal;
import com.ungs.formar.vista.login.ControladorLogin;
import com.ungs.formar.vista.login.VentanaIniciarSesion;
import com.ungs.formar.vista.ventanas.PantallaPrincipal;

public class Main {

	public static void main(String[] args) {
		PantallaPrincipal ventanaPantallaPrincipal = new PantallaPrincipal();
		ControladorPantallaPrincipal controlador = new ControladorPantallaPrincipal(ventanaPantallaPrincipal);
		controlador.inicializar();
		
		/*
		VentanaIniciarSesion v = new VentanaIniciarSesion();
		ControladorLogin c = new ControladorLogin(v);
		c.inicializar();*/
	}
	
}