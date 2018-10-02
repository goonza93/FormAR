package com.ungs.formar.negocios;

import java.sql.Date;
import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.interfacesOBD.CursoODB;

public class CursoManager {
	
	public static void crearCurso(
			Integer cupoMinimo, Integer cupoMaximo, Integer cantidadClases,
			Integer instructor, Integer sala, Integer programa, String nombre,
			String contenido, Date fecha_inicio, Date fecha_fin) {
		
		Curso curso = new Curso(
				-1, cupoMinimo, cupoMaximo, cantidadClases,
				instructor, sala, programa, nombre,
				contenido, fecha_inicio, fecha_fin);
		
		CursoODB odb = FactoryODB.crearCursoODB();
		odb.insert(curso);
	}
	
	public static List<Curso> traerCursos() {
		CursoODB odb = FactoryODB.crearCursoODB();
		return odb.select();
	}

}
