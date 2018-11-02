package com.ungs.formar.persistencia.interfaces;

import java.util.List;
import com.ungs.formar.persistencia.entidades.Dia;

public interface DiaOBD {
	
	public List<Dia> select();
	
	public Dia selectByID(Integer ID);
	
}