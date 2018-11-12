package com.ungs.formar.negocios;

import java.sql.Date;
import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Asistencia;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Examen;
import com.ungs.formar.persistencia.interfaces.AsistenciaOBD;
import com.ungs.formar.persistencia.interfaces.CursoODB;
import com.ungs.formar.persistencia.interfaces.ExamenOBD;

public class Instructor {
	
	public static boolean tieneAsignaciones(Empleado instructor) {
		return traerCursos(instructor).size()>0;
	}
	
	public static List<Curso> traerCursos(Empleado instructor) {
		CursoODB odb = FactoryODB.crearCursoODB();
		return odb.selectByInstructor(instructor);
	}
	
	public static Date proximaFechaTomarAsistencia() {
		// COMPLETAR TODO
		return Almanaque.hoy();
	}
	
	public static void guardarAsistencias(List<Asistencia> asistencias) {
		AsistenciaOBD obd = FactoryODB.crearAsistenciaOBD();
		for (Asistencia asistencia : asistencias)
			obd.insert(asistencia);
	}
	
	public static void guardarNotasDeExamen(List<Examen> examenes) {
		ExamenOBD obd = FactoryODB.crearExamenOBD();
		for (Examen examen : examenes)
			obd.insert(examen);
	}

}