package com.ungs.formar.negocios;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Recado;
import com.ungs.formar.persistencia.interfacesOBD.RecadoOBD;

public class Mensajero {
	
	public static void enviarMensaje(Empleado emisor, Empleado receptor, String mensaje) {
		Recado recadoEmisor = new Recado(-1, emisor.getID(), receptor.getID(), emisor.getID(), mensaje, false, false, Almanaque.hoy());
		Recado recadoReceptor = new Recado(-1, receptor.getID(), receptor.getID(), emisor.getID(), mensaje, false, false, Almanaque.hoy());
		RecadoOBD obd = FactoryODB.crearRecadoOBD();
		obd.insert(recadoEmisor);
		obd.insert(recadoReceptor);
	}

	public static List<Recado> traerMensajesRecibidos(Empleado empleado) {
		RecadoOBD obd = FactoryODB.crearRecadoOBD();
		return obd.selectByEmpleadoReceptorArchivado(empleado, empleado, false);
	}

	public static void borrarMensaje(Recado recado) {
		RecadoOBD obd = FactoryODB.crearRecadoOBD();
		obd.delete(recado);
	}

	public static void archivarMensaje(Recado recado) {
		recado.setArchivado(true);
		RecadoOBD obd = FactoryODB.crearRecadoOBD();
		obd.update(recado);
	}
	
	public static List<Recado> traerMensajesArchivados(Empleado empleado) {
		RecadoOBD obd = FactoryODB.crearRecadoOBD();
		return obd.selectByEmpleadoReceptorArchivado(empleado, empleado, true);
	}

}