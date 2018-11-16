package com.ungs.formar.negocios;

import java.sql.Date;
import java.util.List;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Notificacion;
import com.ungs.formar.persistencia.interfaces.NotificacionOBD;

public class NotificacionManager {

	public static void crearNotificacion(Integer empleadoID, String contenido, boolean mostrado, boolean leido, Date fechaANotificar){
		Notificacion notificacion = new Notificacion(-1, empleadoID, contenido, false, false, fechaANotificar);
		NotificacionOBD odb = FactoryODB.crearNotificacionOBD();
		odb.insert(notificacion);
	}
	
	public static void editarNotificacion(Notificacion notificacion) {
		NotificacionOBD odb = FactoryODB.crearNotificacionOBD();
		odb.update(notificacion);
	}

	public static void eliminarNotificacion(Notificacion notificacion) {
		NotificacionOBD odb = FactoryODB.crearNotificacionOBD();
		odb.delete(notificacion);
	}

	public static List<Notificacion> traerNotificaciones() {
		NotificacionOBD odb = FactoryODB.crearNotificacionOBD();
		return odb.select();
	}
	
}
