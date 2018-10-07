package com.ungs.formar.test.persistencia;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.persistencia.interfacesOBD.HorarioOBD;

public class HorarioOBDTest {

	public static void selectTest() {
		System.out.println("___ Select test");
		
		HorarioOBD obd = FactoryODB.crearHorarioOBD();
		List<Horario> horarios = obd.select();
		System.out.println("Cantidad:"+horarios.size());
		for (Horario horario:horarios)
			System.out.println(horario.getHoraInicio());
	}
	
	public static void selectByIDTest(Integer ID) {
		System.out.println("___ Select by ID test");
		HorarioOBD obd = FactoryODB.crearHorarioOBD();
		Horario horario = obd.selectByID(ID);
		System.out.println(horario.getHoraInicio());
	}
	
	public static void main(String[] args) {
		selectTest();
		selectByIDTest(3);
	}

}