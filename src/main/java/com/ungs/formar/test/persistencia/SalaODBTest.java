package com.ungs.formar.test.persistencia;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Sala;
import com.ungs.formar.persistencia.interfacesOBD.SalaODB;

public class SalaODBTest {

	public static void selectTest() {
		System.out.println("___ Select test");
		SalaODB odb = FactoryODB.crearSalaODB();
		List<Sala> salas = odb.select();
		System.out.println("Cantidad:"+odb.select().size());
		for (Sala sala: salas)
			System.out.println(sala.getNombre());
	}
	
	public static void main(String[] args) {
		selectTest();
	}

}