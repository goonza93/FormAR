package com.ungs.formar.persistencia;

import com.ungs.formar.persistencia.definidos.Rol;

public class Definido {
	
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
	
}