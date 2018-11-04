package com.ungs.formar.vista.util;

import java.util.List;

import com.ungs.formar.negocios.AlumnoManager;
import com.ungs.formar.negocios.CursoManager;
import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.HorarioCursadaManager;
import com.ungs.formar.negocios.ProgramaManager;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Area;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import com.ungs.formar.persistencia.entidades.Pago;
import com.ungs.formar.persistencia.entidades.Programa;

public class Formato {
	
	public static String empleado(Integer ID) {
		Empleado empleado = EmpleadoManager.traerEmpleado(ID);
		return empleado.getApellido()+", "+empleado.getNombre(); 
	}
	
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
		return curso.getEstado().toString();
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

	public static String alumno(Pago pago) {
		Alumno alumno = AlumnoManager.traerAlumnoSegunID(pago.getAlumno()); 
		return alumno.getApellido()+", "+alumno.getNombre();
	}
	
	public static String curso(Pago pago) {
		Curso curso = CursoManager.traerCursoPorId(pago.getCursada()); 
		return nombre(curso);
	}
	
	
	
	public static String prueba1(int cantidad) {
		String ret = "<html>prueba<br>";
		for (int i = 0; i < cantidad; i++)
			ret += "prueba<br>";
			
		return ret+"</html>";
	}
	
	public static String prueba2(int cantidad) {
		String ret = "<html>prueba<br></html>";
		for (int i = 0; i < cantidad; i++)
			ret += " - prueba";
			
		return ret;
	}
	
	public static Integer contarRenglones(String texto) {
		Integer cantidad = 1;
		
		for (int i = 0; i < texto.length()-3; i++)
			if (texto.charAt(i) == '<' && texto.charAt(i+1) == 'b' && texto.charAt(i+2) == 'r' && texto.charAt(i+3) == '>')
				cantidad++;
		
		return cantidad;
	}
	
	/**
	 * @param fila: Todos los objetos deben ser cadenas sin excepcion y no pueden ser null
	 * @return la altura estimada de la celda
	 */
	public static Integer calcularAlturaDeCelda(Object[] fila) {
		int renglonesMaximo = 0;
		for (Object objecto : fila) {
			int renglones = Formato.contarRenglones((String)objecto);
			if (renglones>renglonesMaximo)
				renglonesMaximo = renglones;
		}
		
		return renglonesMaximo*20;
	}

}