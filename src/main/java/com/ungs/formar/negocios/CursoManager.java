package com.ungs.formar.negocios;

import java.sql.Date;
import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.interfacesOBD.CursoODB;

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

}
