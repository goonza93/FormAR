package com.ungs.formar.persistencia.interfaces;

import java.util.List;

import com.ungs.formar.persistencia.entidades.Programa;

public interface ProgramaODB {
	
	public List<Programa> select();

	public Programa selectByID(Integer id);

	public void insert(Programa programa);

	public void update(Programa programa);

	public void delete(Programa programa);

	public List<Programa> selectByArea(Integer id);
	
	public Programa selectByCodigo(String codigo);
	
}