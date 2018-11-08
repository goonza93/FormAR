package com.ungs.formar.negocios;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Recado;
import com.ungs.formar.persistencia.interfaces.RecadoOBD;

public class Mensajero {
	
	public static void enviarMensaje(Empleado emisor, Empleado receptor, String titulo, String contenido) {
		Recado recadoEmisor = new Recado(-1, emisor.getID(), receptor.getID(), emisor.getID(), contenido, titulo, false, false, Almanaque.hoy());
		Recado recadoReceptor = new Recado(-1, receptor.getID(), receptor.getID(), emisor.getID(), contenido, titulo, false, false, Almanaque.hoy());
		RecadoOBD obd = FactoryODB.crearRecadoOBD();
		obd.insert(recadoEmisor);
		obd.insert(recadoReceptor);
	}

	public static List<Recado> traerMensajesRecibidos(Empleado empleado) {
		RecadoOBD obd = FactoryODB.crearRecadoOBD();
		return obd.selectByEmpleadoReceptorArchivado(empleado, empleado, false);
	}
	
	public static List<Recado> traerMensajesSinLeer(Empleado empleado){
		RecadoOBD obd = FactoryODB.crearRecadoOBD();
		return obd.selectByEmpleadoReceptorSinLeer(empleado);
	}

	public static List<Recado> traerMensajesEnviados(Empleado empleado) {
		RecadoOBD obd = FactoryODB.crearRecadoOBD();
		return obd.selectByEmpleadoEmisorArchivado(empleado, empleado, false);
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

	public static void marcarComoLeido(Recado recado) {
		recado.setLeido(true);
		RecadoOBD obd = FactoryODB.crearRecadoOBD();
		obd.update(recado);	
	}

}