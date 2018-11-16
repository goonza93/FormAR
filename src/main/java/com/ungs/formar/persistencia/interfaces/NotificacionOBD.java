package com.ungs.formar.persistencia.interfaces;

import java.util.List;

import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Notificacion;

public interface NotificacionOBD {
	
	public void insert (Notificacion notificacion);
	
	public void update (Notificacion notificacion);
	
	public void delete (Notificacion notificacion);
	
	public List<Notificacion> select();
	
	public List<Notificacion> selectByEmpleado(Empleado empleado);
	
	public List<Notificacion> selectByEmpleadoSinMostrar(Empleado empleado);
	
	public List<Notificacion> selectByEmpleadoSinLeer(Empleado empleado);
}