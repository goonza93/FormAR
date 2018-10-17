package com.ungs.formar.negocios;

import java.util.List;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Area;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.persistencia.interfacesOBD.AreaOBD;

public class AreaManager {
	
	public static List<Area> traerTodo() {
		AreaOBD obd = FactoryODB.crearAreaOBD();
		return obd.select();
	}
	

}
