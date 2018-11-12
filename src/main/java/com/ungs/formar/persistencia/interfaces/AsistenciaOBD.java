package com.ungs.formar.persistencia.interfaces;

import java.sql.Date;
import java.util.List;

import com.ungs.formar.persistencia.entidades.Asistencia;

public interface AsistenciaOBD {

	public void insert(Asistencia asistencia);
	
	public List<Asistencia> select();
	
	public List<Asistencia> selectByCursoFecha(Integer cursoID, Date fecha);
	
}