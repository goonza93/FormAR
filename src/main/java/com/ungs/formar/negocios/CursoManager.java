package com.ungs.formar.negocios;

import java.sql.Date;
import java.util.List;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.EstadoCurso;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import com.ungs.formar.persistencia.interfacesOBD.CursoODB;
import com.ungs.formar.persistencia.interfacesOBD.EstadoCursoOBD;

public class CursoManager {
	
	public static Integer crearCurso(
			Integer cupoMinimo, Integer cupoMaximo, Integer horas, Integer estado,Integer responsable,
			Integer instructor, Integer programa, 
			String contenido, Date fechaInicio, Date fechaFin) {
		
		Curso curso = new Curso(-1, cupoMinimo, cupoMaximo, horas, 
				contenido, fechaInicio, fechaFin, instructor, programa, estado, responsable);
		
		CursoODB odb = FactoryODB.crearCursoODB();
		odb.insert(curso);
		// falta implementar que busque el id del insertado
		return null;
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
	
	public static Date calcularFechaFin(List<HorarioCursada> hc, Integer horas, Date fechaInicio){
		boolean[] dias = {false,false,false,false,false,false,false};
		Integer[] horasdia = {0,0,0,0,0,0,0};
		//encontrarlos.
		
		return buscarFechaFin(fechaInicio, horas,dias,horasdia);
		
	}

	private static Date buscarFechaFin(Date fechaInicio, Integer horas,boolean[] dia,Integer[] horasdia){
		Date asd = new Date(1);
		// algoritmo pensado
		return asd;
	}
	
	
	
}
