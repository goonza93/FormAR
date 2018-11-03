package com.ungs.formar.test.persistencia;

import java.util.List;

import com.ungs.formar.negocios.Almanaque;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Recado;
import com.ungs.formar.persistencia.interfaces.RecadoOBD;

public class RecadoODBTest {
	
	public static void insertTest(Recado recado) {
		System.out.println("___ Insert test");
		RecadoOBD obd = FactoryODB.crearRecadoOBD();
		obd.insert(recado);
	}

	public static void selectTest() {
		System.out.println("___ Select test");
		RecadoOBD odb = FactoryODB.crearRecadoOBD();
		List<Recado> recados = odb.select();
		System.out.println("Cantidad: "+recados.size());
		for (Recado recado : recados)
			System.out.println(recado.getContenido());
	}
	
	public static void main(String[] args) {
		Recado recado = new Recado(-1, 2, 2, 1, "Contenido 2", "Titulo 2", false, false, Almanaque.hoy());
		insertTest(recado);
		selectTest();
	}

}