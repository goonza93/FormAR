package com.ungs.formar.persistencia.interfaces;

import java.util.List;

import com.ungs.formar.persistencia.entidades.Interaccion;

public interface InteraccionOBD {
	
	public void insert (Interaccion interaccion);
	
	public void update (Interaccion interaccion);
	
	public void delete (Interaccion interaccion);
	
	public List<Interaccion> select();

}
