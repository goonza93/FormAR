package com.ungs.formar.test.persistencia;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Dia;

public class DiaODBTest {

	public static void selectTest() {
		System.out.println("___ Select test");
		List<Dia> dias = FactoryODB.crearDiaOBD().select();
		System.out.println("Cantidad: "+dias.size());
		for (Dia dia: dias)
			System.out.println(dia.getDescripcion());
	}
	
	public static void selectByIDTest(Integer ID) {
		System.out.println("___ Select by ID test");
		Dia dia = FactoryODB.crearDiaOBD().selectByID(ID);
		System.out.println(dia.getDescripcion());
	}
	
	public static void main(String[] args) {
		selectTest();
		selectByIDTest(1);
	}

}