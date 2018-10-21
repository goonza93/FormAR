package com.ungs.formar.persistencia.interfacesOBD;

import java.util.List;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Empleado;

public interface CursoODB {

	public void insert(Curso curso);
	
	public void delete(Curso curso);
	
	public void update(Curso curso);
	
	public List<Curso> select();
	
	public Curso selectByID(Integer id);
	
	public Integer selectIDMasReciente();
	
	public List<Curso> selectByPrograma(Integer IDPrograma);
	
	public List<Curso> selectByInstructor(Empleado empleado);
	
}