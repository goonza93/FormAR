package com.ungs.formar.test.persistencia;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.interfacesOBD.CursoODB;

public class CursoODBTest {

	public static void insertTest(Curso curso) {
		System.out.println("___ Insert test: "+curso.getNombre());
		CursoODB odb = FactoryODB.crearCursoODB();
		odb.insert(curso);
	}

	public static void selectTest() {
		System.out.println("___ Select test");
		CursoODB odb = FactoryODB.crearCursoODB();
		List<Curso> cursos = odb.select();
		System.out.println("Cantidad:"+odb.select().size());
		for (Curso curso : cursos)
			System.out.println(curso.getNombre());
	}
	
	public static void main(String[] args) {
		Curso curso = new Curso(-1, 10, 40, 50, 1, 1, 1, "Crso definitivo de supervivencia", "nada", null, null);
		insertTest(curso);
		selectTest();
	}

}