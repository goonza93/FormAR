package com.ungs.formar.negocios;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.EstadoCurso;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import com.ungs.formar.persistencia.interfacesOBD.CursoODB;
import com.ungs.formar.persistencia.interfacesOBD.EstadoCursoOBD;

public class CursoManager {
	
	public static Integer crearCurso(
			Integer cupoMinimo, Integer cupoMaximo, Integer horas, Integer estado,Integer responsable,
			Integer instructor, Integer programa, 
			String contenido, Date fechaInicio, Date fechaFin) {
		
		Curso curso = new Curso(-1, cupoMinimo, cupoMaximo, horas, 
				contenido, fechaInicio, fechaFin, instructor, programa, estado, responsable);
		
		CursoODB odb = FactoryODB.crearCursoODB();
		odb.insert(curso);
		// falta implementar que busque el id del insertado
		return null;
	}
	
	public static List<Curso> traerCursos() {
		CursoODB odb = FactoryODB.crearCursoODB();
		return odb.select();
	}
	
	public static List<EstadoCurso> traerEstadosDeCurso() {
		EstadoCursoOBD obd = FactoryODB.crearEstadoCursoOBD();
		return obd.select();
	}
	
	public static EstadoCurso traerEstadoSegunID(Integer ID) {
		EstadoCursoOBD obd = FactoryODB.crearEstadoCursoOBD();
		return obd.selectByID(ID);
	}

	public static void borrarCurso(Integer idCurso){
		
	}
	
	public static void updateCurso(/*idCursoAmodificar, cupoMinimo, cupoMaximo, horas, estado, responsable, instructor, programa, contenido, 
			fechaInicio, fechaFin*/){
		
	}
	
	public static Curso traerCursoPorId(Integer idCurso){
		
		return null;
	}
	
	public static Date calcularFechaFin(List<HorarioCursada> hc, Integer horas, Date fechaInicio){
		boolean[] dias = {false,false,false,false,false,false,false};
		Integer[] minutosdia = {0,0,0,0,0,0,0};
		//encontrarlos.
		for(HorarioCursada a : hc){
			Horario b = HorarioCursadaManager.traerHorarioSegunID(a.getHorario());
			dias[b.getDia()-1] = true;
			minutosdia[b.getDia()-1] += (diferencia(b.getHoraInicio(),b.getHoraFin()
					,b.getMinutoInicio(),b.getMinutoFin())); //falta traducir horarios a matematica
		}
		
		return buscarFechaFin(fechaInicio, horas,dias,minutosdia);
		
	}
	
	private static Integer diferencia(Integer hIn, Integer hFin, Integer mIn, Integer mFin){
		String dateStart = "11/03/14 "+String.valueOf(hIn)+":"+String.valueOf(mIn)+":00";
		String dateStop = "11/03/14 "+String.valueOf(hFin)+":"+String.valueOf(mFin)+":00";

		// Custom date format
		SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");  

		java.util.Date d1 = null;
		java.util.Date d2 = null;
		try {
		    d1 = format.parse(dateStart);
		    d2 = format.parse(dateStop);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		long diff = d2.getTime() - d1.getTime();
		Integer minutes = (int) TimeUnit.MILLISECONDS.toMinutes(diff);
		return minutes;
	}
	
	

	private static Date buscarFechaFin(Date fechaInicio, Integer horas,boolean[] dia,Integer[] minutosdia){
		Integer totalDias = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaInicio);
		Integer quedan = horas*60; // pase a minutos asi es mas facil
		while ((quedan)>0){
			int indexActual = cal.get(Calendar.DAY_OF_WEEK)-1; //indexActual es el dia de la semanaActual
			if(dia[indexActual]){
				quedan -= minutosdia[indexActual];
			}
			cal.add(Calendar.DATE, 1);
			totalDias++;
		}
		
		// reseteo el cal denuevo para reusarlo en el calculo final
		cal.setTime(fechaInicio);
		cal.add(Calendar.DATE, totalDias);
		return new Date(cal.getTime().getTime()); // doble get time*** de Calendar -> util.date -> long para tener .sql.date
	}
	
	
}
