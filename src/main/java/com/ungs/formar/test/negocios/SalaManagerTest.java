package com.ungs.formar.test.negocios;

import java.util.List;
import com.ungs.formar.negocios.SalaManager;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.persistencia.entidades.Sala;

public class SalaManagerTest {

	public static void traerSalasTest() {
		System.out.println("___ Traer salas Test");
		List<Sala> salas = SalaManager.traerTodo();
		for (Sala sala: salas)
			System.out.println(sala.getNumero());
		System.out.println("Cantidad de salas:"+salas.size());
	}
	
	public static void horariosSuperpuestosTest(Horario h1, Horario h2) {
		System.out.println("___ Horarios superpuestos Test");
		System.out.println("Resultado:"+SalaManager.horariosSupuerpuestos(h1, h2));
	}
	
	public static void main(String[] args) {
		//traerSalasTest();
		Horario h1 = new Horario(1, 1, 10, 12, 0, 30);
		Horario h2 = new Horario(1, 1, 12, 13, 0, 0);
		horariosSuperpuestosTest(h1, h2);
	}

}