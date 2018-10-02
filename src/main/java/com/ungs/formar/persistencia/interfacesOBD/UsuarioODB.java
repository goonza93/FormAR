package com.ungs.formar.persistencia.interfacesOBD;

import com.ungs.formar.persistencia.entidades.Usuario;

public interface UsuarioODB {

	public void insert(Usuario usuario);
	
	public Usuario selectByNombre(String nombre);
	
}