package com.ungs.formar.negocios;

import com.ungs.formar.persistencia.entidades.Empleado;

public class Identificador {

	public static boolean nombreLibre (String usuario) throws Exception {
		//Jugador jugador = traerJugadorPorUsuario(usuario);
		//return jugador == null;
		return true;
	}
	
	public static Empleado iniciarSesion(String usuario, String password) throws Exception {
		Empleado empleado = EmpleadoManager.traerSegunUsuario(usuario);
		
		if (empleado == null)
			throw new Exception("No existe un empleado registrado con el nombre de usuario: "+usuario);
		
		if(!empleado.getPassword().equals(password))
			throw new Exception("Password incorrecta");
					
		return empleado;
	}
	

}