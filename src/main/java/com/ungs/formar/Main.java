package com.ungs.formar;

import com.ungs.formar.vista.controladores.ControladorCrearCurso;
import com.ungs.formar.vista.controladores.ControladorGestionarCurso;
import com.ungs.formar.vista.ventanas.CrearCurso;
import com.ungs.formar.vista.ventanas.GestionarCursos;



public class Main {

	public static void main(String[] args) {
		
			GestionarCursos ventanaGestionarCursos = new GestionarCursos();
			ControladorGestionarCurso controlador = new ControladorGestionarCurso(ventanaGestionarCursos);
			controlador.inicializar();
	}

}
