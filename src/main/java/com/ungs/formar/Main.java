package com.ungs.formar;

import com.ungs.formar.Controlador.ControladorCrearCurso;
import com.ungs.formar.Controlador.ControladorGestionarCurso;
import com.ungs.formar.vista.CrearCurso;
import com.ungs.formar.vista.GestionarCursos;



public class Main {

	public static void main(String[] args) {
		
			GestionarCursos ventanaGestionarCursos = new GestionarCursos();
			ControladorGestionarCurso controlador = new ControladorGestionarCurso(ventanaGestionarCursos);
			controlador.inicializar();
	}

}
