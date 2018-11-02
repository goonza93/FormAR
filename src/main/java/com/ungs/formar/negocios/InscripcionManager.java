package com.ungs.formar.negocios;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Inscripcion;
import com.ungs.formar.persistencia.interfaces.InscripcionOBD;

public class InscripcionManager {
	
	public static void inscribir(Curso curso, Alumno alumno, Empleado empleado) throws Exception {
		if (estaInscripto(curso, alumno))
			throw new Exception("El alumno "+ alumno.getNombre()+" "+alumno.getApellido()+" ya estaba inscripto al curso.");
		
		Date fecha = Almanaque.hoy(); 
		Inscripcion inscripcion = new Inscripcion(-1, alumno.getID(), curso.getID(), 1, fecha, 1.0);
		InscripcionOBD obd = FactoryODB.crearInscripcionOBD();
		obd.insert(inscripcion);
	}
	
	public static boolean estaInscripto(Curso curso, Alumno alumno) {
		InscripcionOBD obd = FactoryODB.crearInscripcionOBD();
		Inscripcion inscripcion = obd.selectByCursoAlumno(curso, alumno); 
		return inscripcion != null;
	}
	
	public static List<Inscripcion> traerInscripciones(Curso curso) {
		InscripcionOBD obd = FactoryODB.crearInscripcionOBD();
		return obd.selectByCurso(curso);
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
	
	public static List<Alumno> traerAlumnosInscriptos(Curso curso) {
		List<Inscripcion> inscripciones = traerInscripciones(curso);
		List<Alumno> alumnos = new ArrayList<Alumno>();

		for (Inscripcion inscripcion : inscripciones)
			alumnos.add(AlumnoManager.traerAlumnoSegunID(inscripcion.getAlumno()));
		
		return alumnos;
	}

	public static void cancelarInscripcion(Alumno alumno, Curso curso) {
		InscripcionOBD obd = FactoryODB.crearInscripcionOBD();
		Inscripcion inscripcion = obd.selectByCursoAlumno(curso, alumno); 
		obd.delete(inscripcion);
	}

}