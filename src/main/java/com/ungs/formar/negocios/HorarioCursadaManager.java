package com.ungs.formar.negocios;

import java.util.List;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import com.ungs.formar.persistencia.interfacesOBD.HorarioOBD;

public class HorarioCursadaManager {
	
	public static void crearHorarioCursada() {
			
	}
	
	public static List<HorarioCursada> traerHorariosCursada(int idCurso) {
		//Si queda Modelado asi, nos faltaria un HorarioManager, que pasando un Id, devuelva un horario
		return null;
	}
	
	public static Integer crearHorario(Integer dia, Integer horaI, Integer horaF, Integer minutoI, Integer minutoFin){
		// implementar ambas, el crear y el devolver el int del id del creado.
		return null;
	}

	public static Horario traerHorarioSegunID(Integer ID) {
		HorarioOBD obd = FactoryODB.crearHorarioOBD();
		return obd.selectByID(ID);
	}
		
}