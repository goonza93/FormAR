package com.ungs.formar.persistencia.interfacesOBD;

import java.util.List;
import com.ungs.formar.persistencia.entidades.Horario;

public interface HorarioOBD {
	
	public List<Horario> select();

	public Horario selectByID(Integer ID);
	
}