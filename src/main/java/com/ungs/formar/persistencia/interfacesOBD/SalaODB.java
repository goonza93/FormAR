package com.ungs.formar.persistencia.interfacesOBD;

import java.util.List;
import com.ungs.formar.persistencia.entidades.Sala;

public interface SalaODB {
	
	public void insert(Sala sala);

	public void update(Sala sala);

	public void delete(Sala sala);

	public List<Sala> select();

	public Sala selectByID(Integer id);
	
	public Sala selectByNumero(Integer numero);
	
}