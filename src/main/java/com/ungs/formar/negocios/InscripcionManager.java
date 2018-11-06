package com.ungs.formar.negocios;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.definidos.EstadoCurso;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Inscripcion;
import com.ungs.formar.persistencia.interfaces.InscripcionOBD;
import com.ungs.formar.vista.util.Formato;
import com.ungs.formar.vista.util.Popup;

public class InscripcionManager {

	public static void inscribir(Curso curso, Alumno alumno, Empleado empleado) throws Exception {
		if (estaInscripto(curso, alumno))
			throw new Exception(
					"El alumno " + alumno.getNombre() + " " + alumno.getApellido() + " ya estaba inscripto al curso.");
		String msjError = "";
		msjError = validarSuperposicionHorarios(alumno, curso);
		if (!msjError.isEmpty()) {
			if (!Popup.confirmar(msjError + "Desea inscribirse de todas maneras?")) {
				return;
			}
		}
		Date fecha = Almanaque.hoy();
		Inscripcion inscripcion = new Inscripcion(-1, alumno.getID(), empleado.getID(), curso.getID(), fecha, 1.0);
		InscripcionOBD obd = FactoryODB.crearInscripcionOBD();
		obd.insert(inscripcion);
	}

	public static boolean estaInscripto(Curso curso, Alumno alumno) {
		InscripcionOBD obd = FactoryODB.crearInscripcionOBD();
		Inscripcion inscripcion = obd.selectByCursoAlumno(curso, alumno);
		return inscripcion != null;
	}

	public static List<Inscripcion> traerInscripciones(Curso curso) {
		InscripcionOBD obd = FactoryODB.crearInscripcionOBD();
		return obd.selectByCurso(curso);
	}

	public static List<Inscripcion> traerInscripciones(Alumno alumno) {
		InscripcionOBD obd = FactoryODB.crearInscripcionOBD();
		return obd.selectByAlumno(alumno);
	}

	public static List<Curso> traerCursosInscriptos(Alumno alumno) {
		List<Inscripcion> inscripciones = traerInscripciones(alumno);
		List<Curso> cursos = new ArrayList<Curso>();

		for (Inscripcion inscripcion : inscripciones)
			cursos.add(CursoManager.traerCursoPorId(inscripcion.getCurso()));

		return cursos;
	}

	public static List<Alumno> traerAlumnosInscriptos(Curso curso) {
		List<Inscripcion> inscripciones = traerInscripciones(curso);
		List<Alumno> alumnos = new ArrayList<Alumno>();

		for (Inscripcion inscripcion : inscripciones)
			alumnos.add(AlumnoManager.traerAlumnoSegunID(inscripcion.getAlumno()));

		return alumnos;
	}

	public static void cancelarInscripcion(Alumno alumno, Curso curso) {
		InscripcionOBD obd = FactoryODB.crearInscripcionOBD();
		Inscripcion inscripcion = obd.selectByCursoAlumno(curso, alumno);
		obd.delete(inscripcion);
	}

	public static Inscripcion traerInscripcion(Alumno alumno, Curso cursoSeleccionado) {
		InscripcionOBD obd = FactoryODB.crearInscripcionOBD();
		Inscripcion inscripcion = obd.selectByCursoAlumno(cursoSeleccionado, alumno);
		return inscripcion;
	}

	private static String validarSuperposicionHorarios(Alumno alumno, Curso curso) {
		List<Curso> cursosInscripto = traerCursosInscriptos(alumno);
		String msj = "";
		if (!cursosInscripto.isEmpty()) {
			for (Curso cursada : cursosInscripto) {
				if (cursada.getFechaFin()!= null && curso.getFechaFin()!= null &&
						seSuperponenFechas(curso, cursada)) {
					msj += "- Se le superpone la cursada de " + Formato.nombre(curso) + " con \n la de "
							+ Formato.nombre(cursada) + "\n";
				}
			}
		}
		if(!msj.isEmpty())
			return "A "+alumno.getApellido()+", "+alumno.getNombre()+" :\n"+msj;
		return msj;
	}

	private static boolean seSuperponenFechas(Curso curso, Curso cursada){
		if (((cursada.getFechaInicio().before(curso.getFechaFin()) && cursada.getFechaFin().after(curso.getFechaInicio()))
				|| ((curso.getFechaInicio().before(cursada.getFechaFin()) && curso.getFechaFin().after(cursada.getFechaInicio()))))
				&& cursada.getEstado() != EstadoCurso.CANCELADO
				&& cursada.getEstado() != EstadoCurso.FINALIZADO)
			return true;
		return false;
	}
}