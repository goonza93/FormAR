package com.ungs.formar.persistencia.interfacesOBD;

import java.util.List;

import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Inscripcion;

public interface InscripcionOBD {
	
	public List<Inscripcion> select();
	
	public List<Inscripcion> selectByAlumno(Alumno alumno);
	
	

}