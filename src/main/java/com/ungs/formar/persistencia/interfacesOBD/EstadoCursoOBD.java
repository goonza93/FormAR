package com.ungs.formar.persistencia.interfacesOBD;

import java.util.List;
import com.ungs.formar.persistencia.entidades.EstadoCurso;

public interface EstadoCursoOBD {

	public List<EstadoCurso> select();

	public EstadoCurso selectByID(Integer ID);
	
}