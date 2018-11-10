package com.ungs.formar.persistencia.interfaces;

import java.util.List;
import com.ungs.formar.persistencia.entidades.Asistencia;

public interface AsistenciaOBD {

	public void insert(Asistencia asistencia);
	
	public List<Asistencia> select();
	
}