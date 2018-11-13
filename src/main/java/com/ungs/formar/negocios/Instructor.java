package com.ungs.formar.negocios;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Asistencia;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Examen;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
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
	
	public static List<Date> traerFechasTomarAsistencia(Curso curso){
		List<Date> ret = new ArrayList<Date>();
		
		//List<HorarioCursada> hc, Integer horas, Date fechaInicio
		List<HorarioCursada> hc = CursoManager.obtenerHorariosDeCursada(curso);
		Integer horas = curso.getHoras();
		Date fechaInicio = curso.getFechaInicio();
		boolean[] dias = { false, false, false, false, false, false, false };
		Integer[] minutosdia = { 0, 0, 0, 0, 0, 0, 0 };
		// encontrarlos.
		for (HorarioCursada a : hc) {
			Horario b = HorarioCursadaManager.traerHorarioSegunID(a.getHorario());
			dias[b.getDia() - 1] = true;
			minutosdia[b.getDia() - 1] += (CursoManager.diferencia(b.getHoraInicio(), b.getHoraFin(), b.getMinutoInicio(),
					b.getMinutoFin())); // falta traducir horarios a matematica
		}
		
		// segunda mitad, ya con dias y tiempo por dia en arrays
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaInicio);
		Integer quedan = horas * 60; // pase a minutos asi es mas facil
		while ((quedan) > 0) {
			int indexActual = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if (dias[indexActual]) {
				// este dia esta en la cursada entonces lo agrego
				ret.add(new Date(cal.getTimeInMillis()));
				quedan -= minutosdia[indexActual];
			}
			cal.add(Calendar.DATE, 1);
		}
		return ret;
	}
	
	public static Date proximaFechaTomarAsistencia(Curso curso) {
		// las trae ordenadas...
		List<Date> fechas = traerFechasTomarAsistencia(curso);
		AsistenciaOBD obd = FactoryODB.crearAsistenciaOBD();
		for(Date fecha : fechas){
			if(obd.selectByCursoFecha(curso.getID(), fecha).isEmpty()){
				return fecha;
			}
		}
		return null;
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
	
	public static Asistencia traerAsistencia(Curso curso, Alumno alumno, Date fecha) {
		AsistenciaOBD obd = FactoryODB.crearAsistenciaOBD();
		return obd.selectByCursoAlumnoFecha(curso, alumno, fecha);
	}
	
	public static List<String> traerExamenesDeCurso(Curso curso) {
		ExamenOBD obd = FactoryODB.crearExamenOBD();
		return obd.selectExamenes(curso);
	}
	
}