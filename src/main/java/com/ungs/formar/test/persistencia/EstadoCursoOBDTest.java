package com.ungs.formar.test.persistencia;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.EstadoCurso;
import com.ungs.formar.persistencia.interfacesOBD.EstadoCursoOBD;

public class EstadoCursoOBDTest {

	public static void selectTest() {
		System.out.println("___ Select test");
		
		EstadoCursoOBD obd = FactoryODB.crearEstadoCursoOBD();
		List<EstadoCurso> estados = obd.select();
		System.out.println("Cantidad:"+estados.size());
		for (EstadoCurso estado: estados)
			System.out.println(estado.getDescripcion());
	}
	
	public static void selectByIDTest(Integer ID) {
		System.out.println("___ Select by ID test");
		EstadoCursoOBD obd = FactoryODB.crearEstadoCursoOBD();
		EstadoCurso estado = obd.selectByID(ID);
		System.out.println(estado.getDescripcion());
	}
	
	public static void main(String[] args) {
		selectTest();
		selectByIDTest(3);
	}

}