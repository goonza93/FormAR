package com.ungs.formar.negocios;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.interfacesOBD.AlumnoODB;

public class AlumnoManager {

	public static List<Alumno> traerAlumno() {
		AlumnoODB odb = FactoryODB.crearAlumnoODB();
		return odb.select();
	}
}
