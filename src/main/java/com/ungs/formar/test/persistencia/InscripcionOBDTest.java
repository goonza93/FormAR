package com.ungs.formar.test.persistencia;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Area;
import com.ungs.formar.persistencia.entidades.Inscripcion;
import com.ungs.formar.persistencia.interfacesOBD.AreaOBD;
import com.ungs.formar.persistencia.interfacesOBD.InscripcionOBD;

public class InscripcionOBDTest {

	public static void selectTest() {
		System.out.println("___ Select test");
		InscripcionOBD obd = FactoryODB.crearInscripcionOBD();
		List<Inscripcion> inscripciones = obd.select();
		System.out.println("Cantidad: "+inscripciones.size());
		for (Inscripcion inscripcion:inscripciones)
			System.out.println(inscripcion.getInscripcionID());
	}
	
	public static void selectByIDTest(Integer ID) {
		System.out.println("___ Select by ID test");
		AreaOBD obd = FactoryODB.crearAreaOBD();
		Area area = obd.selectByID(ID);
		System.out.println(area.getNombre());
	}
	
	public static void main(String[] args) {
		selectTest();
//		selectByIDTest(3);
	}

}