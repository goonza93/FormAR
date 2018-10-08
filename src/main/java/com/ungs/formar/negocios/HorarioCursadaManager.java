package com.ungs.formar.negocios;

import java.util.List;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Dia;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import com.ungs.formar.persistencia.entidades.Sala;
import com.ungs.formar.persistencia.interfacesOBD.HorarioCursadaOBD;
import com.ungs.formar.persistencia.interfacesOBD.HorarioOBD;
import com.ungs.formar.persistencia.interfacesOBD.SalaODB;

public class HorarioCursadaManager {
	
	public static String obtenerDia(HorarioCursada hc) {
		Integer horarioID = hc.getHorario();
		HorarioOBD obd = FactoryODB.crearHorarioOBD();
		Horario horario = obd.selectByID(horarioID);
		Dia dia = DiaManager.traerDiaSegunID(horario.getDia());
		return dia.getDescripcion();
	}
	
	public static String obtenerHoraInicio(HorarioCursada hc) {
		Horario horario = traerHorarioSegunID(hc.getHorario());
		return horario.getHoraInicio()+":"+horario.getMinutoInicio();
	}
	
	public static String obtenerHoraFin(HorarioCursada hc) {
		Horario horario = traerHorarioSegunID(hc.getHorario());
		return horario.getHoraFin()+":"+horario.getMinutoFin();
	}
	
	public static String obtenerSala(HorarioCursada hc) {
		Integer salaID = hc.getSala();
		SalaODB odb = FactoryODB.crearSalaODB();
		Sala sala = odb.selectByID(salaID);
		return sala.getNumero().toString();
	}
	
	public static void crearHorarioCursada(HorarioCursada horarioCursada) {
		HorarioCursadaOBD obd = FactoryODB.crearHorarioCursada();
		obd.insert(horarioCursada);
	}
	
	public static List<HorarioCursada> traerHorariosCursada(int idCurso) {
		//Si queda Modelado asi, nos faltaria un HorarioManager, que pasando un Id, devuelva un horario
		return null;
	}
	
	public static Integer crearHorario(Dia dia, Integer horaI, Integer horaF, Integer minutoI, Integer minutoFin){
		Horario horario = new Horario(-1, dia.getDiaID(), horaI, horaF, minutoI, minutoFin);
		HorarioOBD obd = FactoryODB.crearHorarioOBD();
		obd.insert(horario);
		return obd.selectIDMasReciente();
	}

	public static Horario traerHorarioSegunID(Integer ID) {
		HorarioOBD obd = FactoryODB.crearHorarioOBD();
		return obd.selectByID(ID);
	}
		
}