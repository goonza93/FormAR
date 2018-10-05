package com.ungs.formar.test.persistencia;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Dia;
import com.ungs.formar.persistencia.interfacesOBD.DiaOBD;

public class DiaODBTest {

	public static void selectTest() {
		System.out.println("___ Select test");
		
		DiaOBD odb = FactoryODB.crearDiaOBD();
		List<Dia> dias = odb.select();
		System.out.println("Cantidad:"+odb.select().size());
		for (Dia dia: dias)
			System.out.println(dia.getDescripcion());
	}
	
	public static void main(String[] args) {
		selectTest();
	}

}