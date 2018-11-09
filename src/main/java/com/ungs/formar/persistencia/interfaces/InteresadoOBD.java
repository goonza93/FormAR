package com.ungs.formar.persistencia.interfaces;

import java.util.List;

import com.ungs.formar.persistencia.entidades.Interesado;


public interface InteresadoOBD {
	
	public void insert (Interesado interesado);
	
	public void update (Interesado interesado);
	
	public void delete (Interesado interesado);
	
	public List<Interesado> select();
	
	public Interesado selectByID(Integer id);

	public Interesado selectByDNI(String dni);

}
