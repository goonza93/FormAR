package com.ungs.formar.test.persistencia;

import java.util.List;

import com.ungs.formar.negocios.Almanaque;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Asistencia;
import com.ungs.formar.persistencia.interfaces.AsistenciaOBD;

public class AsistenciaODBTest {
	
	public static void insertTest(Asistencia asistencia) {
		System.out.println("___ Insert test");
		AsistenciaOBD obd = FactoryODB.crearAsistenciaOBD();
		obd.insert(asistencia);
	}

	public static void selectTest() {
		System.out.println("___ Select test");
		AsistenciaOBD obd = FactoryODB.crearAsistenciaOBD();
		List<Asistencia> lista = obd.select();
		System.out.println("Cantidad: "+lista.size());
		for (Asistencia elemento : lista)
			System.out.println(elemento.isPresente());
	}
	
	public static void main(String[] args) {
		Asistencia asistencia = new Asistencia(-1, 1, 2, Almanaque.hoy(), false);
		insertTest(asistencia);
		selectTest();
	}

}