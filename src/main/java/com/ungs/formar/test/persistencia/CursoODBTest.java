package com.ungs.formar.test.persistencia;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.interfacesOBD.CursoODB;

public class CursoODBTest {

	public static void insertTest(Curso curso) {
		System.out.println("___ Insert test");
		CursoODB odb = FactoryODB.crearCursoODB();
		odb.insert(curso);
	}

	public static void selectIDMasRecienteTest() {
		System.out.println("___ Select ID mas reciente test");
		CursoODB odb = FactoryODB.crearCursoODB();
		System.out.println(odb.selectIDMasReciente());
	}

	public static void selectTest() {
		System.out.println("___ Select test");
		CursoODB odb = FactoryODB.crearCursoODB();
		List<Curso> cursos = odb.select();
		System.out.println("Cantidad:"+odb.select().size());
		for (Curso curso : cursos)
			System.out.println("Curso ID: "+curso.getID());
	}
	
	public static void main(String[] args) throws ParseException {
		DateFormat formato = new SimpleDateFormat("MM-dd-yyyy");
		Date sqlDate = new Date(formato.parse("02-04-2015").getTime());
		selectTest();
		selectIDMasRecienteTest();
	}

}