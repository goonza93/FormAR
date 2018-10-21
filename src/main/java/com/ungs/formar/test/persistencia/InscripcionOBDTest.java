package com.ungs.formar.test.persistencia;

import java.util.List;
import com.ungs.formar.negocios.Almanaque;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Inscripcion;
import com.ungs.formar.persistencia.interfacesOBD.InscripcionOBD;

public class InscripcionOBDTest {
	
	public static void insertTest(Inscripcion inscripcion) {
		System.out.println("___ Insert test");
		InscripcionOBD obd = FactoryODB.crearInscripcionOBD();
		obd.insert(inscripcion);
	}
	
	public static void selectTest() {
		System.out.println("___ Select test");
		InscripcionOBD obd = FactoryODB.crearInscripcionOBD();
		List<Inscripcion> inscripciones = obd.select();
		System.out.println("Cantidad: "+inscripciones.size());
		for (Inscripcion inscripcion:inscripciones)
			System.out.println(inscripcion.getInscripcionID());
	}
	
	public static void deleteTest(Inscripcion inscripcion) {
		System.out.println("___ Delete test");
		InscripcionOBD obd = FactoryODB.crearInscripcionOBD();
		obd.delete(inscripcion);
	}
	
	public static void main(String[] args) {
		System.out.println("Comienza la prueba: Registros actuales");
		selectTest();
		
		System.out.println("Paso 1: Realizo un insert y veo que se haya agregado a la lista");
		int cursoID = 1, alumnoID = 1;
		insertTest(new Inscripcion(-1, alumnoID, cursoID, 1, Almanaque.hoy(), 1.0));
		selectTest();

		System.out.println("Paso 2: Recupero la inscripcion agregada y la elimino");
		Alumno alumno = FactoryODB.crearAlumnoODB().selectByID(alumnoID);
		Curso curso = FactoryODB.crearCursoODB().selectByID(cursoID);
		InscripcionOBD obd = FactoryODB.crearInscripcionOBD();
		Inscripcion inscripcion = obd.selectByCursoAlumno(curso, alumno);
		deleteTest(inscripcion);
		selectTest();
	}

}