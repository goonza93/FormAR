package com.ungs.formar.persistencia.interfaces;

import java.util.List;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Programa;

public interface CursoODB {

	public void insert(Curso curso);
	
	public void delete(Curso curso);
	
	public void update(Curso curso);
	
	public List<Curso> select();
	
	public Curso selectByID(Integer id);
	
	public Integer selectIDMasReciente();
	
	public List<Curso> selectByPrograma(Integer IDPrograma);
	
	public Curso selectByProgramaComision(Programa programa, String comision);
	
	public List<Curso> selectByInstructor(Empleado empleado);
	
}