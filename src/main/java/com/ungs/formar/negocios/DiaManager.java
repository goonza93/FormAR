package com.ungs.formar.negocios;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Dia;
import com.ungs.formar.persistencia.interfaces.DiaOBD;

public class DiaManager {
	
	public static List<Dia> traerDias() {
		DiaOBD odb = FactoryODB.crearDiaOBD();
		return odb.select();
	}
	
	public static Dia traerDiaSegunID(Integer ID) {
		DiaOBD obd = FactoryODB.crearDiaOBD();
		return obd.selectByID(ID);
	}

}