package com.ungs.formar.test.persistencia;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Area;
import com.ungs.formar.persistencia.interfacesOBD.AreaOBD;

public class AreaOBDTest {

	public static void selectTest() {
		System.out.println("___ Select test");
		AreaOBD obd = FactoryODB.crearAreaOBD();
		List<Area> areas = obd.select();
		System.out.println("Cantidad: "+areas.size());
		for (Area area: areas)
			System.out.println(area.getNombre());
	}
	
	public static void selectByIDTest(Integer ID) {
		System.out.println("___ Select by ID test");
		AreaOBD obd = FactoryODB.crearAreaOBD();
		Area area = obd.selectByID(ID);
		System.out.println(area.getNombre());
	}
	
	public static void main(String[] args) {
		selectTest();
		selectByIDTest(3);
	}

}