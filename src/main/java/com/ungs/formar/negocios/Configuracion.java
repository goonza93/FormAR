package com.ungs.formar.negocios;

import com.ungs.formar.persistencia.Propiedades;

public class Configuracion {
	
	public static String leerIP() {
		return Propiedades.recuperar("IP");
	}
	
	public static String leerPuerto() {
		return Propiedades.recuperar("puerto");
	}
	
	public static String leerUsuarioBD() {
		return Propiedades.recuperar("usuario_BD");
	}

	public static String leerPasswordBD() {
		return Propiedades.recuperar("password_BD");
	}
	
	public static String leerDireccionEmail() {
		return Propiedades.recuperar("direccion_email");
	}
	
	public static String leerPasswordEmail() {
		return Propiedades.recuperar("password_email");
	}

	public static void guardarIP(String valor) {
		Propiedades.guardar("IP", valor);
	}
	
	public static void guardarPuerto(String valor) {
		Propiedades.guardar("puerto", valor);
	}
	
	public static void guardarUsuarioBD(String valor) {
		Propiedades.guardar("usuario_BD", valor);
	}

	public static void guardarPasswordBD(String valor) {
		Propiedades.guardar("password_BD", valor);
	}
	
	public static void guardarDireccionEmail(String valor) {
		Propiedades.guardar("direccion_email", valor);
	}
	
	public static void guardarPasswordEmail(String valor) {
		Propiedades.guardar("password_email", valor);
	}
	
}	