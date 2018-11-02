package com.ungs.formar.persistencia.interfaces;

import java.util.List;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Inscripcion;

public interface InscripcionOBD {
	
	public void insert(Inscripcion inscripcion);
	
	public void delete(Inscripcion inscripcion);

	public List<Inscripcion> select();
	
	public List<Inscripcion> selectByAlumno(Alumno alumno);
	
	public List<Inscripcion> selectByCurso(Curso curso);
	
	public Inscripcion selectByCursoAlumno(Curso curso, Alumno alumno);

}