package com.ungs.formar.negocios;

import java.sql.Date;
import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Dia;
import com.ungs.formar.persistencia.entidades.EstadoCurso;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import com.ungs.formar.persistencia.interfacesOBD.CursoODB;
import com.ungs.formar.persistencia.interfacesOBD.DiaOBD;
import com.ungs.formar.persistencia.interfacesOBD.EstadoCursoOBD;

public class HorarioCursadaManager {
	
	public static void crearHorarioCursada(){
			
	}
	
	public static List<HorarioCursada> traerHorariosCursada(int idCurso) {
		//Si queda Modelado asi, nos faltaria un HorarioManager, que pasando un Id, devuelva un horario
		return null;
	}
	

}
