package com.ungs.formar.negocios;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.persistencia.interfacesOBD.ProgramaODB;

public class ProgramaManager {
	
	public static List<Programa> traerProgramas() {
		ProgramaODB odb = FactoryODB.crearProgramaODB();
		return odb.select();
	}

}
