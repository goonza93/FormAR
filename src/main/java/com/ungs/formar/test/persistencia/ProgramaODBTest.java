package com.ungs.formar.test.persistencia;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.persistencia.interfaces.ProgramaODB;

public class ProgramaODBTest {

	public static void selectTest() {
		System.out.println("___ Select test");
		ProgramaODB odb = FactoryODB.crearProgramaODB();
		List<Programa> programas = odb.select();
		System.out.println("Cantidad:"+odb.select().size());
		for (Programa programa: programas)
			System.out.println(programa.getNombre());
	}
	
	public static void main(String[] args) {
		selectTest();
	}

}