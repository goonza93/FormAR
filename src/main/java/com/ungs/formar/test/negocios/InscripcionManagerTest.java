package com.ungs.formar.test.negocios;

import java.util.List;
import com.ungs.formar.negocios.ProgramaManager;
import com.ungs.formar.persistencia.entidades.Programa;

public class InscripcionManagerTest {

	public static void traerProgramasTest() {
		System.out.println("___ Traer programas Test");
		List<Programa> programas = ProgramaManager.traerProgramas();
		for (Programa programa: programas)
			System.out.println(programa.getNombre());
		System.out.println("Cantidad de programas:"+programas.size());
	}
	
	public static void main(String[] args) {
//		InscripcionManager.cancelarInscripcion(alumno, curso);
		traerProgramasTest();
	}

}