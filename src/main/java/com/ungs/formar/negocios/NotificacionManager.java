package com.ungs.formar.negocios;

import java.sql.Date;
import java.util.List;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.definidos.TipoNotificacion;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Notificacion;
import com.ungs.formar.persistencia.interfaces.NotificacionOBD;

public class NotificacionManager {

	public static void crearNotificacion(TipoNotificacion tipo, Integer empleadoID, String contenido, Date fechaANotificar){
		Notificacion notificacion = new Notificacion(-1,tipo, empleadoID, contenido, false, false, fechaANotificar);
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
	
	public static List<Notificacion> traerNotificaciones(Empleado empleado) {
		NotificacionOBD odb = FactoryODB.crearNotificacionOBD();
		return odb.selectByEmpleado(empleado);
	}
	
	public static List<Notificacion> traerNotificacionesSinMostrar(Empleado empleado) {
		NotificacionOBD odb = FactoryODB.crearNotificacionOBD();
		return odb.selectByEmpleadoSinMostrar(empleado);
	}
	
	public static List<Notificacion> traerNotificacionesSinLeer(Empleado empleado) {
		NotificacionOBD odb = FactoryODB.crearNotificacionOBD();
		return odb.selectByEmpleadoSinLeer(empleado);
	}
	
}
