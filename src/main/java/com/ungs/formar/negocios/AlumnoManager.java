package com.ungs.formar.negocios;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.interfacesOBD.AlumnoODB;

public class AlumnoManager {

	public static void crearAlumno(String dni, String nombre, String apellido, String telefono, String email){
		Alumno alumno = new Alumno(-1, dni, nombre, apellido, telefono, email);
		AlumnoODB odb = FactoryODB.crearAlumnoODB();
		odb.insert(alumno);
	}
	
	public static void editarAlumno(Alumno alumno) {
		AlumnoODB odb = FactoryODB.crearAlumnoODB();
		odb.update(alumno);
	}
	
	public static List<Alumno> traerAlumnos() {
		AlumnoODB odb = FactoryODB.crearAlumnoODB();
		return odb.select();
	}
	
	public static Alumno traerAlumnoSegunDNI(String dni){
		AlumnoODB odb = FactoryODB.crearAlumnoODB();
		return odb.selectByDNI(dni);
	}

}