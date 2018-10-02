package com.ungs.formar.test.negocios;

import java.sql.Date;
import java.util.List;
import com.ungs.formar.negocios.CursoManager;
import com.ungs.formar.persistencia.entidades.Curso;

public class CursoManagerTest {
	
	public static void crearCursoTest(
			int cupoMinimo, int cupoMaximo, int cantidadClases, int instructor, int sala, int programa,
			String nombre, String contenido,
			Date fecha_inicio, Date fecha_fin) {
		
		System.out.println("___ Crear curso Test");
		CursoManager.crearCurso(cupoMinimo, cupoMaximo, cantidadClases, instructor, sala, programa, nombre, contenido, fecha_inicio, fecha_fin);
		
	}
	
	public static void traerCursosTest() {
		System.out.println("___ Traer cursos Test");
		List<Curso> cursos = CursoManager.traerCursos();
		for (Curso curso: cursos)
			System.out.println(curso.getNombre());
		System.out.println("Cantidad de cursos:"+cursos.size());
	}
	
	public static void main(String[] args) {
		int cupoMinimo = 10;
		int cupoMaximo = 50;
		int cantidadClases = 10;
		int instructor = 1;
		int sala = 1;
		int programa = 1;
		String nombre = "Curso intensivo de anime";
		String contenido= "DBZ";
		Date fecha_inicio = null;
		Date fecha_fin = null;
		
		crearCursoTest(cupoMinimo, cupoMaximo, cantidadClases, instructor, sala, programa, nombre, contenido, fecha_inicio, fecha_fin);
		traerCursosTest();
	}

}