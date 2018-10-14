package com.ungs.formar.negocios;

public class Validador {

	// METODOS INTERNOS DEL VALIDADOR
	private static boolean formatoNumero(String texto) {
		return texto.matches("[0-9]+");
	}

	private static boolean formatoLetra(String texto) {
		return texto.matches("[a-zA-Z]+");
	}

	private static boolean formatoLetraEspacio(String texto) {
		return texto.matches("[a-zA-Z\\s]+");
	}
	
	private static boolean formatoNumeroLetraEspacio(String texto) {
		return texto.matches("[a-zA-Z0-9\\s]+");
	}
	
	
	// METODOS QUE USAN LOS DEMAS PARA VALIDAR CAMPOS
	public static boolean validarEmail(String texto) {
		return texto.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+[.][a-zA-Z0-9]+") || texto.equals("-");
	}
	
	public static boolean validarApellido(String texto) {
		return (formatoLetraEspacio(texto));
	}
	
	public static boolean validarNombre(String texto) {
		return (formatoLetraEspacio(texto));
	}
	
	public static boolean validarDNI(String texto) {
		return (formatoNumero(texto));
	}
	
	public static boolean validarTelefono(String texto) {
		return (formatoNumero(texto));
	}
		
	public static void main(String[] args) {
		formatoLetra("hola");
		formatoNumeroLetraEspacio("prueba");
	}
			
}