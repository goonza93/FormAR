package com.ungs.formar.negocios;

import java.util.List;

import com.ungs.formar.persistencia.entidades.Area;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.EstadoCurso;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import com.ungs.formar.persistencia.entidades.Programa;

public class Formato {
	
	public static String instructor(Curso curso) {
		String ret = "Sin asignar";
		Empleado empleado = EmpleadoManager.traerEmpleado(curso.getInstructor());
		if (empleado != null)
			ret = empleado.getApellido()+" "+empleado.getNombre(); 
		return ret;
	}

	public static String responsable(Curso curso) {
		String ret = "Sin asignar";
		Empleado empleado = EmpleadoManager.traerEmpleado(curso.getResponsable());
		if (empleado != null)
			ret = empleado.getApellido()+" "+empleado.getNombre(); 
		return ret;
	}

	public static String nombre(Curso curso) {
		Programa programa = ProgramaManager.traerProgramaSegunID(curso.getPrograma());
		return programa.getNombre();
	}
	
	public static String estado(Curso curso) {
		EstadoCurso estado = EstadoCursoManager.traerEstadoDeCurso(curso.getEstado());
		return estado.getDescripcion();
	}
	
	public static String horarios(Curso curso) {
		List<HorarioCursada> horarios = CursoManager.obtenerHorariosDeCursada(curso);
		return HorarioCursadaManager.obtenerVistaDeHorariosYSalas(horarios);
	}
	
	public static String area(Curso curso) {
		Programa programa = ProgramaManager.traerProgramaSegunID(curso.getPrograma());
		Area area = ProgramaManager.traerAreaSegunID(programa.getAreaID());
		return area.getNombre();
	}
	
	public static String precio(Integer precio) {
		return "$ "+precio+",00";
	}
}