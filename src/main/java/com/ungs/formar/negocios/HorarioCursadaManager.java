package com.ungs.formar.negocios;

import java.util.List;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Dia;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import com.ungs.formar.persistencia.entidades.Sala;
import com.ungs.formar.persistencia.interfacesOBD.HorarioOBD;
import com.ungs.formar.persistencia.interfacesOBD.SalaODB;

public class HorarioCursadaManager {
	
	public static String obtenerDia(HorarioCursada hc) {
		return null;
	}
	
	public static String obtenerHoraInicio(HorarioCursada hc) {
		return null;
	}
	
	public static String obtenerHoraFin(HorarioCursada hc) {
		return null;
	}
	
	public static String obtenerSala(HorarioCursada hc) {
		Integer salaID = hc.getSala();
		SalaODB odb = FactoryODB.crearSalaODB();
		Sala sala = odb.selectByID(salaID);
		
		
		return null;
	}
	
	
	
	
	
	public static void crearHorarioCursada() {
			
	}
	
	public static List<HorarioCursada> traerHorariosCursada(int idCurso) {
		//Si queda Modelado asi, nos faltaria un HorarioManager, que pasando un Id, devuelva un horario
		return null;
	}
	
	public static Integer crearHorario(Dia dia, Integer horaI, Integer horaF, Integer minutoI, Integer minutoFin){
		// implementar ambas, el crear y el devolver el int del id del creado.
		return null;
	}

	public static Horario traerHorarioSegunID(Integer ID) {
		HorarioOBD obd = FactoryODB.crearHorarioOBD();
		return obd.selectByID(ID);
	}
		
}