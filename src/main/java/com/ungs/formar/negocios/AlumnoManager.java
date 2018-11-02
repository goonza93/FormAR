package com.ungs.formar.negocios;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.definidos.EstadoCurso;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.interfaces.AlumnoODB;

public class AlumnoManager {

	public static void crearAlumno(String dni, String nombre, String apellido, String telefono, String email) {
		Alumno alumno = new Alumno(-1, dni, nombre, apellido, telefono, email, true);
		AlumnoODB odb = FactoryODB.crearAlumnoODB();
		odb.insert(alumno);
	}

	public static void editarAlumno(Alumno alumno) {
		AlumnoODB odb = FactoryODB.crearAlumnoODB();
		odb.update(alumno);
	}

	public static void eliminarAlumno(Alumno alumno) {
		AlumnoODB odb = FactoryODB.crearAlumnoODB();
		odb.delete(alumno);
	}

	public static List<Alumno> traerAlumnos() {
		AlumnoODB odb = FactoryODB.crearAlumnoODB();
		return odb.select();
	}

	public static Alumno traerAlumnoSegunDNI(String dni) {
		AlumnoODB odb = FactoryODB.crearAlumnoODB();
		return odb.selectByDNI(dni);
	}

	public static Alumno traerAlumnoSegunID(Integer ID) {
		AlumnoODB odb = FactoryODB.crearAlumnoODB();
		return odb.selectByID(ID);
	}

	public static boolean estaEnUsoDNI(String dni) {
		Alumno alumno = traerAlumnoSegunDNI(dni);
		return alumno != null;
	}

	public static boolean tieneInscripcion(Alumno alumno){
		List<Curso> cursadas = InscripcionManager.traerCursosInscriptos(alumno);
		
		if(!cursadas.isEmpty())
			return true;	
		
		return false;
	}
}