package com.ungs.formar.persistencia.interfaces;

import java.util.List;
import com.ungs.formar.persistencia.entidades.Alumno;

public interface AlumnoODB {
	
	public void insert (Alumno alumno);
	
	public void update (Alumno alumno);
	
	public void delete (Alumno alumno);
	
	public List<Alumno> select();
	
	public Alumno selectByNombre(String nombre);
	
	public Alumno selectByID(Integer id);
	
	public Alumno selectByDNI(String dni);
	
}