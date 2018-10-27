package com.ungs.formar.test.persistencia;

import java.util.List;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.interfacesOBD.AlumnoODB;

public class AlumnoODBTest {
	
	public static void insertTest(Alumno alumno){
		System.out.println("___ Insert Test: "+alumno.getNombre());
		AlumnoODB odb = FactoryODB.crearAlumnoODB();
		odb.insert(alumno);
	}
	
	public static void selectTest(){
		System.out.println("___ Select test");
		AlumnoODB odb = FactoryODB.crearAlumnoODB();
		List<Alumno> alumnos = odb.select();
		System.out.println("Cantidad: "+alumnos.size());
		for (Alumno alumno : alumnos)
			System.out.println(alumno.getNombre());
	}
	
	public static void main(String[] args) {
		Alumno alumno = new Alumno(-1, "37905623", "gonzalo", "bruckmann", "1531769550", "goonza.93@live.com.ar", true);
		insertTest(alumno);
		selectTest();
	}	

}