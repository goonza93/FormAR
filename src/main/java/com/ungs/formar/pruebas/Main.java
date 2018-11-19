package com.ungs.formar.pruebas;

import java.sql.Date;

import com.ungs.formar.negocios.Almanaque;

public class Main {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// create 2 dates
		Date hoy = Almanaque.hoy();
		Date despues = Almanaque.hoy();
		despues.setYear(2019);
		  
		System.out.println("Hoy: "+hoy);
		System.out.println("Despues: "+despues);
		System.out.println("Hoy es before que despues: " + hoy.before(despues));
		System.out.println("Hoy es after que despues: " + hoy.after(despues));
	}
	
}