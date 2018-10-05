package com.ungs.formar.persistencia.interfacesOBD;

import java.util.List;
import com.ungs.formar.persistencia.entidades.Alumno;

public interface AlumnoODB {
	
	public void insert (Alumno alumno);
	public void edit (Alumno alumno);
	public void delete (Alumno alumno);
	public List<Alumno> select();
	public Alumno selectByNombre(String nombre);
	public Alumno selectByID(Integer id);
	
}
