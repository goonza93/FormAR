package com.ungs.formar.persistencia.interfacesOBD;

import java.util.List;
import com.ungs.formar.persistencia.entidades.Horario;

public interface HorarioOBD {
	
	public void insert(Horario horario);
	
	public List<Horario> select();
	
	public void delete(Horario horario);

	public Horario selectByID(Integer ID);
	
	public Integer selectIDMasReciente();
	
}