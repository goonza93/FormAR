package com.ungs.formar.test.negocios;

import com.ungs.formar.negocios.Configuracion;

public class ConfiguracionTest {
	
	public static void leerIPTest() {
		System.out.println("___Leer IP test: "+Configuracion.leerIP());
	}
	
	public static void leerPuertoTest() {
		System.out.println("___Leer puerto test: "+Configuracion.leerPuerto());
	}
	
	public static void leerUsuarioBDTest() {
		System.out.println("___Leer usuario BD test: "+Configuracion.leerUsuarioBD());
	}
	
	public static void leerPasswordBDTest() {
		System.out.println("___Leer password BD test: "+Configuracion.leerPasswordBD());
	}
	
	public static void leerDireccionEmailTest() {
		System.out.println("___Leer direccion email test: "+Configuracion.leerDireccionEmail());
	}
	
	public static void leerPasswordEmailTest() {
		//System.out.println("___Leer password email test: "+Configuracion.leerPasswordEmail());
	}
	
	public static void guardarIPTest(String valor) {
		System.out.println("___ Guardar IP test");
		Configuracion.guardarIP(valor);
	}
	
	public static void guardarPuertoTest(String valor) {
		System.out.println("___ Guardar puerto test");
		Configuracion.guardarPuerto(valor);
	}
	
	public static void guardarUsuarioBDTest(String valor) {
		System.out.println("___ Guardar usuario BD test");
		Configuracion.guardarUsuarioBD(valor);
	}

	public static void guardarPasswordBDTest(String valor) {
		System.out.println("___ Guardar password BD test");
		Configuracion.guardarPasswordBD(valor);
	}

	public static void guardarDireccionEmailTest(String valor) {
		System.out.println("___ Guardar direccion de email test");
		Configuracion.guardarDireccionEmail(valor);
	}

	public static void guardarPasswordEmailTest(String valor) {
		System.out.println("___ Guardar password email test");
		Configuracion.guardarPasswordEmail(valor);
	}
	
	public static void main(String[] args) {
		guardarIPTest("localhost");
		guardarPuertoTest("3306");
		guardarUsuarioBDTest("root");
		guardarPasswordBDTest("root");
		guardarDireccionEmailTest("root1");
		guardarPasswordEmailTest("root2");
		
		leerIPTest();
		leerPuertoTest();
		leerUsuarioBDTest();
		leerPasswordBDTest();
		leerDireccionEmailTest();
		leerPasswordEmailTest();
	}

}