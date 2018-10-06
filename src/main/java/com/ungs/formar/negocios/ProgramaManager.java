package com.ungs.formar.negocios;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Area;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.persistencia.interfacesOBD.AreaOBD;
import com.ungs.formar.persistencia.interfacesOBD.ProgramaODB;

public class ProgramaManager {
	
	public static List<Programa> traerProgramas() {
		ProgramaODB odb = FactoryODB.crearProgramaODB();
		return odb.select();
	}
	
	public static Programa traerProgramaSegunID(Integer id){
		ProgramaODB odb = FactoryODB.crearProgramaODB();
		return odb.selectByID(id);
	}
	
	public static List<Area> traerAreasDeInteres() {
		AreaOBD obd = FactoryODB.crearAreaOBD();
		return obd.select();
	}

	public static Area traerAreaSegunID(Integer ID) {
		AreaOBD obd = FactoryODB.crearAreaOBD();
		return obd.selectByID(ID);
	}
	
}