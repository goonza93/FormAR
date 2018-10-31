package com.ungs.formar;

import com.ungs.formar.vista.login.ControladorLogin;
import com.ungs.formar.vista.login.VentanaIniciarSesion;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPantallaPrincipal;
import com.ungs.formar.vista.pantallasPrincipales.PantallaPrincipalAdministrativo;

public class Main {

	public static void main(String[] args) {
		
		/*PantallaPrincipalAdministrativo ventanaPantallaPrincipal = new PantallaPrincipalAdministrativo();
		ControladorPantallaPrincipal controlador = new ControladorPantallaPrincipal(ventanaPantallaPrincipal);
		controlador.inicializar();
		*/
		
		VentanaIniciarSesion v = new VentanaIniciarSesion();
		ControladorLogin c = new ControladorLogin(v);
		c.inicializar();
		
	}
	
}