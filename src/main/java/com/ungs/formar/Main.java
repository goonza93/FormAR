package com.ungs.formar;

import com.ungs.formar.vista.controladores.ControladorGestionarCurso;
import com.ungs.formar.vista.controladores.ControladorPantallaPrincipal;
import com.ungs.formar.vista.ventanas.GestionarCursos;
import com.ungs.formar.vista.ventanas.PantallaPrincipal;

public class Main {

	public static void main(String[] args) {
		/* este estaba en el de carlos, como hay cambios en el constructor del controlador deje el de franco.
		GestionarCursos ventana = new GestionarCursos();
		ControladorGestionarCurso controlador = new ControladorGestionarCurso(ventana);
		controlador.inicializar();
		*/
		PantallaPrincipal ventanaPantallaPrincipal = new PantallaPrincipal();
		ControladorPantallaPrincipal controlador = new ControladorPantallaPrincipal(ventanaPantallaPrincipal);
		controlador.inicializar();
		
	}
}