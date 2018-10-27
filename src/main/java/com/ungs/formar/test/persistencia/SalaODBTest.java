package com.ungs.formar.test.persistencia;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Sala;
import com.ungs.formar.persistencia.interfacesOBD.SalaODB;

public class SalaODBTest {
	
	public static void insertTest(Sala sala) {
		System.out.println("___ Insert test:"+sala.getNumero());
		SalaODB odb = FactoryODB.crearSalaODB();
		odb.insert(sala);
	}

	public static void selectTest() {
		System.out.println("___ Select test");
		SalaODB odb = FactoryODB.crearSalaODB();
		List<Sala> salas = odb.select();
		System.out.println("Cantidad:"+odb.select().size());
		for (Sala sala: salas)
			System.out.println(sala.getNumero());
	}
	
	public static void main(String[] args) {
		Sala sala = new Sala(-1, 168, 300, "Belgrano", true);
		insertTest(sala);
		selectTest();
	}

}