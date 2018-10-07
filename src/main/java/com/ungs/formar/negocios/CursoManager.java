package com.ungs.formar.negocios;

import java.sql.Date;
import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.EstadoCurso;
import com.ungs.formar.persistencia.interfacesOBD.CursoODB;
import com.ungs.formar.persistencia.interfacesOBD.EstadoCursoOBD;

public class CursoManager {
	
	public static void crearCurso(
			Integer cupoMinimo, Integer cupoMaximo, Integer horas, Integer estado,Integer responsable,
			Integer instructor, Integer programa, 
			String contenido, Date fechaInicio, Date fechaFin) {
		
		Curso curso = new Curso(-1, cupoMinimo, cupoMaximo, horas, 
				contenido, fechaInicio, fechaFin, instructor, programa, estado, responsable);
		
		CursoODB odb = FactoryODB.crearCursoODB();
		odb.insert(curso);
	}
	
	public static List<Curso> traerCursos() {
		CursoODB odb = FactoryODB.crearCursoODB();
		return odb.select();
	}
	
	public static List<EstadoCurso> traerEstadosDeCurso() {
		EstadoCursoOBD obd = FactoryODB.crearEstadoCursoOBD();
		return obd.select();
	}
	
	public static EstadoCurso traerEstadoSegunID(Integer ID) {
		EstadoCursoOBD obd = FactoryODB.crearEstadoCursoOBD();
		return obd.selectByID(ID);
	}

	public static void borrarCurso(Integer idCurso){
		
	}
	
	public static void updateCurso(/*idCursoAmodificar, cupoMinimo, cupoMaximo, horas, estado, responsable, instructor, programa, contenido, 
			fechaInicio, fechaFin*/){
		
	}
	
	public static Curso traerCursoPorId(Integer idCurso){
		
		return null;
	}
}
