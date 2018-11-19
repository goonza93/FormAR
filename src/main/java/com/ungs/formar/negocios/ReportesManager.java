package com.ungs.formar.negocios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Examen;
import com.ungs.formar.persistencia.entidades.Interaccion;
import com.ungs.formar.persistencia.entidades.Interesado;
import com.ungs.formar.vista.reportes.HistorialContacto;
import com.ungs.formar.vista.util.Popup;

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

	public static void traerHistorialContactoPorAno(Interesado interesadoHistorial, Integer anoBuscar) {
		List<Interaccion> contactos = ContactoManager.traerInteraccionPorContacto(interesadoHistorial.getID());
		List<Interaccion> contactosAño = new ArrayList<Interaccion>();
		
		for(Interaccion interaccion : contactos){
			if(Almanaque.añoFecha(interaccion.getFechaInteraccion())  == anoBuscar)
				contactosAño.add(interaccion);
		}
		
		if(!contactosAño.isEmpty()){
			HistorialContacto reporte = new HistorialContacto(contactosAño);
			reporte.mostrar();
		}
		else{
			Popup.mostrar("No hay contactos en el año "+anoBuscar+" para la persona "+interesadoHistorial.getApellido()+", "+
					interesadoHistorial.getNombre());
		}
	}

	public static void traerHistorialContactoRango(Interesado interesadoHistorial, Date fechaDesde, Date fechaHasta) {
		List<Interaccion> contactos = ContactoManager.traerInteraccionPorContacto(interesadoHistorial.getID());
		List<Interaccion> contactosRango = new ArrayList<Interaccion>();
		
		for(Interaccion interaccion : contactos){
			if(interaccion.getFechaInteraccion().after(fechaDesde) && interaccion.getFechaInteraccion().before(fechaHasta))
				contactosRango.add(interaccion);
		}
		
		if(!contactosRango.isEmpty()){
			HistorialContacto reporte = new HistorialContacto(contactosRango);
			reporte.mostrar();
		}
		else{
			Popup.mostrar("No hay contactos en el rango de fechas seleccionado para la persona "+interesadoHistorial.getApellido()+", "+
					interesadoHistorial.getNombre());
		}
		
	}

}
