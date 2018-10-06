package com.ungs.formar.negocios;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Sala;
import com.ungs.formar.persistencia.interfacesOBD.SalaODB;

public class SalaManager {
	
	public static List<Sala> traerSalas() {
		SalaODB odb = FactoryODB.crearSalaODB();
		return odb.select();
	}
	
	public static Sala traerSala(Integer id){
		SalaODB odb = FactoryODB.crearSalaODB();
		return odb.selectByID(id);
	}

}
