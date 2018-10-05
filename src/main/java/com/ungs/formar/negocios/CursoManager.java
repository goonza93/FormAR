package com.ungs.formar.negocios;

import java.sql.Date;
import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.interfacesOBD.CursoODB;

public class CursoManager {
	
	public static void crearCurso(
			Integer cupoMinimo, Integer cupoMaximo, Integer clases, Integer horas, Integer estado,Integer responsable,
			Integer instructor, Integer sala, Integer programa, String nombre,
			String contenido, Date fechaInicio, Date fechaFin) {
		
		Curso curso = new Curso(-1, cupoMinimo, cupoMaximo, clases, horas, nombre,
				contenido, fechaInicio, fechaFin, instructor, sala, programa, estado, responsable);
		
		CursoODB odb = FactoryODB.crearCursoODB();
		odb.insert(curso);
	}
	
	public static List<Curso> traerCursos() {
		CursoODB odb = FactoryODB.crearCursoODB();
		return odb.select();
	}

}
