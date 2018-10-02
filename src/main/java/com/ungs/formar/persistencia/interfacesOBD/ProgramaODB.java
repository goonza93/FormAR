package com.ungs.formar.persistencia.interfacesOBD;

import java.util.List;
import com.ungs.formar.persistencia.entidades.Programa;

public interface ProgramaODB {
	
	public List<Programa> select();
	
}