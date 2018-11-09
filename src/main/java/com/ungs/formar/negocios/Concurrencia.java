package com.ungs.formar.negocios;

import com.ungs.formar.persistencia.entidades.Alumno;

public class Concurrencia {
	
	public static boolean estaBloqueado(Alumno elemento) {
		Alumno elementoBD = AlumnoManager.traerAlumnoSegunID(elemento.getID());
		return elementoBD.getBloqueado();
	}

	public static void bloquear(Alumno elemento) {
		elemento.setBloqueado(true);
		AlumnoManager.editarAlumno(elemento);
	}
	
	public static void desbloquear(Alumno elemento) {
		elemento.setBloqueado(false);
		AlumnoManager.editarAlumno(elemento);
	}

}