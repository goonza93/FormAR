package com.ungs.formar.test.negocios;

import java.sql.Date;
import java.util.List;

import com.ungs.formar.negocios.CursoManager;
import com.ungs.formar.negocios.Instructor;
import com.ungs.formar.persistencia.entidades.Curso;

public class InstructorTest {

	public static void traerFechasTest(Curso curso) {
		System.out.println("___Traer fechas test iniciando...");
		List<Date> fechas = Instructor.traerFechasTomarAsistencia(curso);
		for (Date date : fechas)
			System.out.println(date);
		System.out.println("___Traer fechas test terminado.");
	}

	public static void traerProximaFechasTest(Curso curso) {
		System.out.println("___Traer proxima fecha test iniciando...");
		System.out.println(Instructor.proximaFechaTomarAsistencia(curso));
		System.out.println("___Traer proxima fecha terminado.");
	}
	
	public static void main(String[] args) {
		Curso curso = CursoManager.traerCursoPorId(1);
		//traerFechasTest(curso);
		traerProximaFechasTest(curso);
	}

}