package com.ungs.formar.test.persistencia;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import com.ungs.formar.persistencia.interfacesOBD.HorarioCursadaOBD;

public class HorarioCursadaODBTest {

	public static void selectTest() {
		System.out.println("___ Select test");
		
		HorarioCursadaOBD obd = FactoryODB.crearHorarioCursada();
		List<HorarioCursada> horarios = obd.select();
		System.out.println("Cantidad:"+horarios.size());
		for (HorarioCursada horario: horarios)
			System.out.println(horario.getHorarioID());
	}
	
	public static void main(String[] args) {
		selectTest();
	}

}