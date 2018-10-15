package com.ungs.formar.negocios;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import com.ungs.formar.persistencia.entidades.Sala;
import com.ungs.formar.persistencia.interfacesOBD.HorarioCursadaOBD;
import com.ungs.formar.persistencia.interfacesOBD.HorarioOBD;
import com.ungs.formar.persistencia.interfacesOBD.SalaODB;

public class SalaManager {
	
	public static void crear(Integer numero, String nombre, Integer capacidad) {
		Sala sala = new Sala(-1, numero, capacidad, nombre);
		SalaODB odb = FactoryODB.crearSalaODB();
		odb.insert(sala);
	}

	public static void modificar(Sala sala) {
		SalaODB odb = FactoryODB.crearSalaODB();
		odb.update(sala);
	}
	
	public static void eliminar(Sala sala) {
		SalaODB odb = FactoryODB.crearSalaODB();
		odb.delete(sala);
	}
	
	public static List<Sala> traerTodo() {
		SalaODB odb = FactoryODB.crearSalaODB();
		return odb.select();
	}
	
	public static Sala traerSegunID(Integer id){
		SalaODB odb = FactoryODB.crearSalaODB();
		return odb.selectByID(id);
	}
	
	
	
	
	
	
	
	public static boolean validarHorarioDeCursada(HorarioCursada nuevo) {
		// Traigo los horario que ocupan la misma sala
		HorarioCursadaOBD obd = FactoryODB.crearHorarioCursada();
		List<HorarioCursada> horarios = obd.selectBySala(nuevo.getSala());
		
		// Comparo el nuevo Horario de cursada con los ya existentes para ver si alguno se superpone
		// Si uno solo se superpone entonces el nuevo horario de cursada no es valido
		// Tener en cuneta que un horario de cursada incluye la sala
		HorarioOBD horarioOBD = FactoryODB.crearHorarioOBD();
		boolean valido = true;
		for (HorarioCursada horarioCursada : horarios) {
			Horario horario1 = horarioOBD.selectByID(nuevo.getHorario());
			Horario horario2 = horarioOBD.selectByID(horarioCursada.getHorario());
			valido = valido && !horariosSupuerpuestos(horario1, horario2);	
		}
		
		return valido;
	}
	
	
	
	public static boolean horariosSupuerpuestos(Horario horario1, Horario horario2) {
		// Si no son el mismo dia no pueden superponerse
		if (horario1.getDia() != horario2.getDia())
			return false;
		
		// Si horario 2 comienza despues de horario 1 tampoco
		if (horario2.getHoraInicio()>horario1.getHoraFin())
			return false;
		
		// Podria se diferencia de minutos
		if (horario2.getHoraInicio()==horario1.getHoraFin()
				&& horario2.getMinutoInicio() >= horario1.getMinutoFin())
			return false;
		
		// Ahora lo mismo pero al reves
		if (horario1.getHoraInicio()>horario2.getHoraFin())
			return false;
		
		// Podria se diferencia de minutos
		if (horario1.getHoraInicio()==horario2.getHoraFin()
				&& horario1.getMinutoInicio() >= horario2.getMinutoFin())
			return false;
		
		return true;
	}
	
}