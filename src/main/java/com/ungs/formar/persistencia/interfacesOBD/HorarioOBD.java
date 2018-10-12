package com.ungs.formar.persistencia.interfacesOBD;

import java.util.List;
import com.ungs.formar.persistencia.entidades.Horario;

public interface HorarioOBD {
	
	public void insert(Horario horario);
	
	public void update(Horario horario);
	
	public void delete(Horario horario);
	
	public List<Horario> select();
	
	public Horario selectByID(Integer ID);
	
	public Integer selectIDMasReciente();
	
}