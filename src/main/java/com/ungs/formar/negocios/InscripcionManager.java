package com.ungs.formar.negocios;

import java.util.ArrayList;
import java.util.List;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Inscripcion;
import com.ungs.formar.persistencia.interfacesOBD.InscripcionOBD;

public class InscripcionManager {
	
	public static void inscribir(Curso curso, Alumno alumno, Empleado empleado) {
		
	}
	
	public static void cancelarInscripcion(Inscripcion inscripcion) {
		
	}
	
	public static List<Inscripcion> traerInscripciones(Curso curso) {
		
		return null;
	}
	
	public static List<Inscripcion> traerInscripciones(Alumno alumno) {
		InscripcionOBD obd = FactoryODB.crearInscripcionOBD();
		return obd.selectByAlumno(alumno);
	}
		
	public static List<Curso> traerCursosInscriptos(Alumno alumno) {
		List<Inscripcion> inscripciones = traerInscripciones(alumno);
		List<Curso> cursos = new ArrayList<Curso>();
		
		for (Inscripcion inscripcion : inscripciones)
			cursos.add(CursoManager.traerCursoPorId(inscripcion.getCurso()));
		
		return cursos;
	}
	

	

}
