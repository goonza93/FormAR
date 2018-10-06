package com.ungs.formar.persistencia.interfacesOBD;

import java.util.List;
import com.ungs.formar.persistencia.entidades.HorarioCursada;

public interface HorarioCursadaOBD {
	
	public List<HorarioCursada> select();
	
	public List<HorarioCursada> selectBySala(Integer sala);
	
}
