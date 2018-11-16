package com.ungs.formar.persistencia.interfaces;

import java.util.List;

import com.ungs.formar.persistencia.entidades.Tarea;

public interface TareaOBD {

	public void insert (Tarea tarea);
	
	public void update (Tarea tarea);
	
	public void delete (Tarea tarea);
	
	public List<Tarea> select();
}
