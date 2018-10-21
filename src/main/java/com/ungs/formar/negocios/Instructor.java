package com.ungs.formar.negocios;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.interfacesOBD.CursoODB;

public class Instructor {
	
	public static boolean tieneAsignaciones(Empleado empleado) {
		CursoODB odb = FactoryODB.crearCursoODB();
		List<Curso> cursos = odb.selectByInstructor(empleado);
		return cursos.size()>0;
	}

}