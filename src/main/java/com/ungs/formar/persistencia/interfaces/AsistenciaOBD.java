package com.ungs.formar.persistencia.interfaces;

import java.sql.Date;
import java.util.List;

import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Asistencia;
import com.ungs.formar.persistencia.entidades.Curso;

public interface AsistenciaOBD {

	public void insert(Asistencia asistencia);
	
	public List<Asistencia> select();
	
	public List<Asistencia> selectByCursoFecha(Integer cursoID, Date fecha);
	
	public Asistencia selectByCursoAlumnoFecha(Curso curso, Alumno alumno, Date fecha);
	
}