package com.ungs.formar.negocios;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Usuario;
import com.ungs.formar.persistencia.interfacesOBD.UsuarioODB;

public class Identificador {
	
	public static boolean nombreLibre(String nombre) {
		UsuarioODB odb = FactoryODB.getUsuarioODB();
		Usuario usuario = odb.selectByNombre(nombre);
		return usuario == null;
	}

	public static Usuario registrarUsuario(String nombre, String password) throws Exception {
		if (!nombreLibre(nombre))
			throw new Exception("Ya existe un usuario registrado con el nombre:"+nombre);
		
		Usuario usuario = new Usuario(-1, nombre, password);
		UsuarioODB odb = FactoryODB.getUsuarioODB();
		odb.insert(usuario);
		return odb.selectByNombre(nombre);
	}
	
	public static Usuario iniciarSesion(String nombre, String password) throws Exception {
		UsuarioODB odb = FactoryODB.getUsuarioODB();
		Usuario usuario = odb.selectByNombre(nombre);
		
		if (usuario == null)
			throw new Exception("No existe ningun usuario registrado con el nombre: "+nombre);
		
		if (!usuario.getPassword().equals(password))
			throw new Exception("Password incorrecta");
		
		return usuario;
	}
	
}