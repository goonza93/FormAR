package com.ungs.formar.negocios;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import com.ungs.formar.persistencia.interfaces.HorarioCursadaOBD;
import com.ungs.formar.persistencia.interfaces.HorarioOBD;

public class Almanaque {

	public static Date hoy() {
		return new Date(new java.util.Date().getTime());
	}

	public static List<HorarioCursada> obtenerHorariosDeCursada(Curso curso) {
		HorarioCursadaOBD obd = FactoryODB.crearHorarioCursada();
		return obd.selectByCurso(curso);
	}

	public static Horario obtenerHorario(HorarioCursada horarioCursada) {
		HorarioOBD obd = FactoryODB.crearHorarioOBD();
		return obd.selectByID(horarioCursada.getHorario());
	}

	public static Date calcularFechaFin(Date fechaInicio, Integer horas, List<HorarioCursada> horariosCursada) {
		List<Horario> horarios = new ArrayList<Horario>();
		for (HorarioCursada horarioCursada : horariosCursada)
			horarios.add(obtenerHorario(horarioCursada));

		Date fechaFin = (Date) fechaInicio.clone();
		Double total = (double) horas.floatValue();

		while (total > 0) {
			Horario horario = obtenerHorarioMasCercano(fechaFin, horarios);
			adelantarFecha(fechaFin, horario);
			total = total - cargaHoraria(horario);
		}

		return fechaFin;
	}

	private static Double cargaHoraria(Horario horario) {
		double ret = 0;
		int horas = horario.getHoraFin() - horario.getHoraInicio();
		int minutos = horario.getMinutoFin() - horario.getMinutoInicio();

		ret = ret + horas;
		ret = ret + (minutos / 60);
		return ret;
	}

	private static void adelantarFecha(Date fecha, Horario horario) {
		/*
		 * int diaActual = fecha.getDay()+1; // nuestro domingo empieza en 1, el
		 * suyo en 0 int horaActual = fecha.getHours(); int minutoActual =
		 * fecha.getMinutes(); int diaObjetivo = horario.getDia(); int
		 * horaObjetivo = horario.getHoraFin(); int minutoObjetivo =
		 * horario.getMinutoFin();
		 */
		// Adelanto la fecha tantos dias como para que sea el dia objetivo

	}

	private static Horario obtenerHorarioMasCercano(Date fecha, List<Horario> horarios) {

		/*
		 * int dia = fecha.getDay()+1; // nuestro domingo empieza en 1, el suyo
		 * en 0 int horaActual = fecha.getHours(); int minutoActual =
		 * fecha.getMinutes(); //Horario horario = new Horario(-1, dia,
		 * horaInicio, horaFin, minutoInicio, minutoFin)
		 * 
		 */

		return null;
	}

	public static Boolean horariosCompatiblesEntreSi(List<HorarioCursada> horarioCursadas) {
		List<Horario> horarios = new ArrayList<Horario>();
		for (HorarioCursada horarioCursada : horarioCursadas)
			horarios.add(HorarioCursadaManager.traerHorarioSegunID(horarioCursada.getHorario()));

		boolean compatible = true;
		for (Horario h1 : horarios)
			for (Horario h2 : horarios)
				if (!h1.equals(h2))
					compatible = compatible && !SalaManager.horariosSupuerpuestos(h1, h2);

		return compatible;
	}

	public static Integer diferenciaEnMeses(Date fechaInicio, Date fechaFin) {
		try {
			Calendar inicio = new GregorianCalendar();
			Calendar fin = new GregorianCalendar();
			inicio.setTime(fechaInicio);
			fin.setTime(fechaFin);
			Integer difA = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
			Integer difM = difA * 12 + fin.get(Calendar.MONTH) - inicio.get(Calendar.MONTH);
			return difM;
		} catch (Exception ex) {
		}
		return 0;
	}

	public static java.util.Date aumentarUnMes(Date fecha) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.MONTH, 1);

		return calendar.getTime();
	}

	public static Integer mesActual() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.MONTH) + 1;
	}
}