package com.ungs.formar.test.persistencia;

import java.util.List;

import com.ungs.formar.negocios.CursoManager;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.interfaces.ExamenOBD;

public class ExamenOBDTest {
	
	public static void selectExamenesTest(Curso curso) {
		System.out.println("___ Iniciando select examenes test");
		ExamenOBD obd = FactoryODB.crearExamenOBD();
		List<String> lista = obd.selectExamenes(curso);
		for (String elemento : lista)
			System.out.println(elemento);
		System.out.println("Cantidad: "+lista.size());
	}
	
	public static void main(String[] args) {
		Curso curso = CursoManager.traerCursoPorId(1);
		selectExamenesTest(curso);
	}

}