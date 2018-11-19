package com.ungs.formar.persistencia;

import com.ungs.formar.persistencia.definidos.EstadoCurso;
import com.ungs.formar.persistencia.definidos.Rol;
import com.ungs.formar.persistencia.definidos.TipoNotificacion;

public class Definido {
	
	public static Integer tipoNotificacion(TipoNotificacion tipo){
		Integer ret = null;
		if(tipo == TipoNotificacion.RECADO)
			ret = 1;
		else if( tipo == TipoNotificacion.TAREA)
			ret = 2;
		return ret;
	}
	
	public static TipoNotificacion tipoNotificacion(Integer tipo){
		TipoNotificacion ret = null;
		if(tipo == 1)
			ret = TipoNotificacion.RECADO;
		else if (tipo == 2)
			ret = TipoNotificacion.TAREA;
		return ret;
	}
	
	public static Integer rol(Rol rol) {
		Integer ret = null;
		if (rol == Rol.ADMINISTRATIVO)
			ret = 1;
		else if (rol == Rol.INSTRUCTOR)
			ret = 2;
		else if (rol == Rol.SUPERVISOR)
			ret = 3;
		return ret;
	}

	public static Rol rol(Integer rol) {
		Rol ret = null;
		if (rol == 1)
			ret = Rol.ADMINISTRATIVO;
		else if (rol == 2)
			ret = Rol.INSTRUCTOR;
		else if (rol == 3)
			ret = Rol.SUPERVISOR;
		return ret;
	}
	
	public static EstadoCurso estadoCurso(Integer estado) {
		EstadoCurso ret = null;
		if (estado == 1)
			ret = EstadoCurso.CREADO;
		else if (estado == 2)
			ret = EstadoCurso.PUBLICADO;
		else if (estado == 3)
			ret = EstadoCurso.INICIADO;
		else if (estado == 4)
			ret = EstadoCurso.FINALIZADO;
		else if (estado == 5)
			ret = EstadoCurso.CANCELADO;
		return ret;
	}
	
	public static Integer estadoCurso(EstadoCurso estado) {
		Integer ret = null;
		if (estado == EstadoCurso.CREADO)
			ret = 1;
		else if (estado == EstadoCurso.PUBLICADO)
			ret = 2;
		else if (estado == EstadoCurso.INICIADO)
			ret = 3;
		else if (estado == EstadoCurso.FINALIZADO)
			ret = 4;
		else if (estado == EstadoCurso.CANCELADO)
			ret = 5;
		return ret;
	}
	
}