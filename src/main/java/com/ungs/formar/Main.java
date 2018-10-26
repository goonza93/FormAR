package com.ungs.formar;

import com.ungs.formar.pruebas.Email;
import com.ungs.formar.vista.controladores.ControladorPantallaPrincipal;
import com.ungs.formar.vista.ventanas.PantallaPrincipal;

public class Main {

	public static void main(String[] args) {
		PantallaPrincipal ventanaPantallaPrincipal = new PantallaPrincipal();
		ControladorPantallaPrincipal controlador = new ControladorPantallaPrincipal(ventanaPantallaPrincipal);
		controlador.inicializar();
	}
	
}