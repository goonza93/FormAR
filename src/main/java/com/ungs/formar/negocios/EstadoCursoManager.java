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

public class EstadoCursoManager {
	
	public static EstadoCurso traerEstadoCurso(int idEstado) {
		//O que sino devuelva solo la descripcion, como prefieran
		//Si devuelve el String descripcion, nos ahorramos el EstadoCurso.getDescripcion();
		return null;
	}
	

}
