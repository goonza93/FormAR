package com.ungs.formar.negocios;

import java.util.List;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Examen;

public class ReportesManager {

	public static Integer notaFinal(Curso cursada, Alumno alumno) {
		List<String> examenes = Instructor.traerExamenesDeCurso(cursada);
		Integer cantExamenes = 0;
		Integer notas = 0;
		for (int i = 0; i < examenes.size(); i++) {
			Examen examen = Instructor.traerNota(cursada, alumno, examenes.get(i));
			if (examen != null) {
				notas += examen.getNota();
				cantExamenes++;
			}
		}
		return notas / cantExamenes;
	}

}
