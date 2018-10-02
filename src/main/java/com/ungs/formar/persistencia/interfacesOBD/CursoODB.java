package com.ungs.formar.persistencia.interfacesOBD;

import java.util.List;
import com.ungs.formar.persistencia.entidades.Curso;

public interface CursoODB {

	public void insert(Curso curso);
	
	public List<Curso> select();
	
}