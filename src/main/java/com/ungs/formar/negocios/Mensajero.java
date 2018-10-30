package com.ungs.formar.negocios;

import java.util.List;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Recado;
import com.ungs.formar.persistencia.interfacesOBD.RecadoOBD;

public class Mensajero {
	
	public static void enviarRecado(Empleado emisor, Empleado receptor, String mensaje) {
		Recado recado = new Recado(-1, emisor.getID(), receptor.getID(), emisor.getID(), mensaje, false, false, Almanaque.hoy());
		RecadoOBD obd = FactoryODB.crearRecadoOBD();
		obd.insert(recado);
	}

	public static List<Recado> traerRecadosRecibidos(Empleado actual) {
		RecadoOBD obd = FactoryODB.crearRecadoOBD();
		return obd.selectByReceptor(actual);
	}

	public static void borrarMensaje(Recado recado) {
		RecadoOBD obd = FactoryODB.crearRecadoOBD();
		obd.delete(recado);
	}

}