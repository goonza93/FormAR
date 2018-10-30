package com.ungs.formar.negocios;

import java.util.List;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Recado;
import com.ungs.formar.persistencia.interfacesOBD.RecadoOBD;

public class Mensajero {
	
	public static void enviarRecado(Empleado emisor, Empleado receptor, String mensaje) {
		
	}

	public static List<Recado> traerRecadosRecibidos(Empleado actual) {
		RecadoOBD obd = FactoryODB.crearRecadoOBD();
		return obd.selectByReceptor(actual);
	}

}
