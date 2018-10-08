package com.ungs.formar.persistencia.interfacesOBD;

import java.util.List;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.HorarioCursada;

public interface HorarioCursadaOBD {
	
	public void insert(HorarioCursada horarioCursada);
	
	public void update(HorarioCursada horarioCursada);
	
	public List<HorarioCursada> select();
	
	public List<HorarioCursada> selectBySala(Integer sala);
	
	public List<HorarioCursada> selectByCurso(Curso curso);

	public void delete(HorarioCursada horarioCursada);
	
}