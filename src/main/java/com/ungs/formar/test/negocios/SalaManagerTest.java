package com.ungs.formar.test.negocios;

import java.util.List;
import com.ungs.formar.negocios.SalaManager;
import com.ungs.formar.persistencia.entidades.Sala;

public class SalaManagerTest {

	public static void traerSalasTest() {
		System.out.println("___ Traer salas Test");
		List<Sala> salas = SalaManager.traerSalas();
		for (Sala sala: salas)
			System.out.println(sala.getNumero());
		System.out.println("Cantidad de salas:"+salas.size());
	}
	
	public static void main(String[] args) {
		traerSalasTest();
	}

}