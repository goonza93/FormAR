package com.ungs.formar.negocios;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.EstadoCurso;
import com.ungs.formar.persistencia.interfacesOBD.EstadoCursoOBD;

public class EstadoCursoManager {
	
	public static EstadoCurso traerEstadoDeCurso(Integer ID) {
		EstadoCursoOBD obd = FactoryODB.crearEstadoCursoOBD();
		return obd.selectByID(ID);
	}

}