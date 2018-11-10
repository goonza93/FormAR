package com.ungs.formar.negocios;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.interfaces.CursoODB;

public class Instructor {
	
	public static boolean tieneAsignaciones(Empleado instructor) {
		return traerCursos(instructor).size()>0;
	}
	
	public static List<Curso> traerCursos(Empleado instructor) {
		CursoODB odb = FactoryODB.crearCursoODB();
		return odb.selectByInstructor(instructor);
	}

}