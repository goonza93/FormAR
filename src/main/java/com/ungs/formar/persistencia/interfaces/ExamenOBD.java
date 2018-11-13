package com.ungs.formar.persistencia.interfaces;

import java.util.List;

import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Examen;

public interface ExamenOBD {
	
	public void insert(Examen examen);
	
	public List<Examen> select();

	public List<String> selectExamenes(Curso curso);
	
	public List<Examen> selectByDescripcion(String descripcion);

}