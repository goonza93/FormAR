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
		String horaInicio = "";
		String minutoInicio = "";
		if(horario.getHoraInicio().toString().length()==1){
			horaInicio += "0";
		}
		if(horario.getMinutoInicio().toString().length()==1){
			minutoInicio += "0";
		}
		return horaInicio+horario.getHoraInicio().toString()+":"+minutoInicio+horario.getMinutoInicio().toString();
	}
	
	public static String obtenerHoraFin(HorarioCursada hc) {
		Horario horario = traerHorarioSegunID(hc.getHorario());
		String horaFin = "";
		String minutoFin = "";
		if(horario.getHoraFin().toString().length()==1){
			horaFin += "0";
		}
		if(horario.getMinutoFin().toString().length()==1){
			minutoFin += "0";
		}
		return horaFin+horario.getHoraFin()+":"+minutoFin+horario.getMinutoFin();
	}
	
	public static String obtenerSala(HorarioCursada hc) {
		Integer salaID = hc.getSala();
		SalaODB odb = FactoryODB.crearSalaODB();
		Sala sala = odb.selectByID(salaID);
		if(sala == null)
			return "";
		if(sala.getSalaID()==1)
			return "A DESIGNAR";
		return sala.getNumero().toString();
	}
	
	public static String obtenerVistaDeHorariosYSalas(List<HorarioCursada> horarios) {
		String ret = "<html>";
		for (HorarioCursada horarioCursada : horarios) {
			String nuevo = formatoHorarioCursada(horarioCursada);
			if (horarioCursada != horarios.get(0))
				nuevo = "<br>"+nuevo;
			
			ret += nuevo;
		}
		if(ret == "<html>"){
			return "A DESIGNAR";
		}
		System.out.println(ret);
		return ret += "</html>";
	}
	
	public static String formatoHorarioCursada(HorarioCursada horarioCursada) {
		String dia = obtenerDia(horarioCursada);
		String sala = obtenerSala(horarioCursada);
		Horario horario = traerHorarioSegunID(horarioCursada.getHorario());
		String horaInicio = "";
		String horaFin = "";
		String minutoInicio = "";
		String minutoFin = "";
		if(horario.getHoraInicio().toString().length()==1){
			horaInicio += "0";
		}
		if(horario.getMinutoInicio().toString().length()==1){
			minutoInicio += "0";
		}
		if(horario.getHoraFin().toString().length()==1){
			horaFin += "0";
		}
		if(horario.getMinutoFin().toString().length()==1){
			minutoFin += "0";
		}
	
		return dia +" de "+horaInicio+horario.getHoraInicio()+":"+minutoInicio+horario.getMinutoInicio()
		+ " a "+horaFin+horario.getHoraFin()+":"+minutoFin+horario.getMinutoFin()+"("+sala+")";
	}
	
	
	
	
	public static void eliminarHorarioDeCursada(HorarioCursada horarioCursada) {
		// elimino sus horarios relacionados y luego el horario cursada en si
		Horario horario = traerHorarioSegunID(horarioCursada.getHorario());
		FactoryODB.crearHorarioOBD().delete(horario);
		FactoryODB.crearHorarioCursada().delete(horarioCursada);
	}
	
	public static void actualizarHorarioDeCursada(HorarioCursada horarioCursada) {
		// elimino sus horarios relacionados y luego el horario cursada en si
		Horario horario = traerHorarioSegunID(horarioCursada.getHorario());
		FactoryODB.crearHorarioOBD().update(horario);
		FactoryODB.crearHorarioCursada().update(horarioCursada);
	}
	
	
	public static void eliminarHorario(HorarioCursada horarioCursada) {
		// elimino sus horarios relacionados y luego el horario cursada en si
		Horario horario = traerHorarioSegunID(horarioCursada.getHorario());
		FactoryODB.crearHorarioOBD().delete(horario);
	}
	
	public static void crearHorarioCursada(HorarioCursada horarioCursada) {
		HorarioCursadaOBD obd = FactoryODB.crearHorarioCursada();
		obd.insert(horarioCursada);
	}
	
	public static Integer crearHorario(Dia dia, Integer horaI, Integer horaF, Integer minutoI, Integer minutoFin){
		Horario horario = new Horario(-1, dia.getDiaID(), horaI, horaF, minutoI, minutoFin);
		HorarioOBD obd = FactoryODB.crearHorarioOBD();
		obd.insert(horario);
		return obd.selectIDMasReciente();
	}
	
	public static void actualizarHorario(Horario horario){
		FactoryODB.crearHorarioOBD().update(horario);
	}

	public static Horario traerHorarioSegunID(Integer ID) {
		HorarioOBD obd = FactoryODB.crearHorarioOBD();
		return obd.selectByID(ID);
	}
		
}