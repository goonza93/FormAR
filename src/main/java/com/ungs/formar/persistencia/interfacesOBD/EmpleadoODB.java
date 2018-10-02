package com.ungs.formar.persistencia.interfacesOBD;

import java.util.List;
import com.ungs.formar.persistencia.entidades.Empleado;

public interface EmpleadoODB {
	
	public List<Empleado> select();
	
}