package com.ungs.formar;

import com.ungs.formar.vista.controladores.ControladorGestionarCurso;
import com.ungs.formar.vista.ventanas.GestionarCursos;

public class Main {

	public static void main(String[] args) {
		GestionarCursos ventana = new GestionarCursos();
		ControladorGestionarCurso controlador = new ControladorGestionarCurso(ventana);
		controlador.inicializar();
	}

}