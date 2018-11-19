package com.ungs.formar.vista.instructores.asistencia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.JInternalFrame;

import com.ungs.formar.negocios.Almanaque;
import com.ungs.formar.negocios.Instructor;
import com.ungs.formar.persistencia.definidos.EstadoCurso;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.vista.instructores.asistencia.alta.ControladorTomarAsistencia;
import com.ungs.formar.vista.instructores.asistencia.consultar.ControladorConsultarAsistencia;
import com.ungs.formar.vista.instructores.notas.alta.ControladorCargarExamen;
import com.ungs.formar.vista.instructores.notas.consultar.ControladorConsultarNotas;
import com.ungs.formar.vista.pantallasPrincipales.ControladorInterno;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPrincipal;
import com.ungs.formar.vista.util.Popup;

public class ControladorGestionAsistencias2 implements ActionListener, ControladorInterno {
	private ControladorPrincipal invocador;
	private VentanaGestionAsistencias2 ventana;
	
	public ControladorGestionAsistencias2(ControladorPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaGestionAsistencias2();
		ventana.botonConsultar().addActionListener(this);
		ventana.botonTomar().addActionListener(this);
		ventana.botonConsultarNotas().addActionListener(this);
		ventana.botonCargar().addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == ventana.botonConsultar())
			consultar();
		
		else if (e.getSource() == ventana.botonTomar())
			tomar();
		
		else if (e.getSource() == ventana.botonConsultarNotas())
			consultarNotas();
		
		else if (e.getSource() == ventana.botonCargar())
			cargar();
	}

	private void consultar() {
		List<Curso> seleccion = ventana.getTabla().obtenerSeleccion();
		if (seleccion.size() != 1) {
			Popup.mostrar("Debe seleccionar extamente 1 curso para consultar su asistencia.");
			return;
		}
		
		Curso curso = seleccion.get(0);
		if(!(curso.getEstado() == EstadoCurso.INICIADO || curso.getEstado() == EstadoCurso.FINALIZADO)){
			Popup.mostrar("Solo se pueden consultar asistencias de cursos en estado iniciado y finalizado.");
			return;
		}
		
		if(Instructor.traerFechasTomarAsistencia(curso).isEmpty()){
			Popup.mostrar("Las aistencias no estan disponibles.");
			return;
		}
		
		invocador.getVentana().setEnabled(false);
		new ControladorConsultarAsistencia(this, seleccion.get(0));
	}

	private void tomar() {
		List<Curso> seleccion = ventana.getTabla().obtenerSeleccion();
		if (seleccion.size() != 1) {
			Popup.mostrar("Debe seleccionar extamente 1 curso para tomarle asistencia.");
			return;
		}
		
		Curso curso = seleccion.get(0);
		if (curso.getEstado() != EstadoCurso.INICIADO) {
			Popup.mostrar("No se puede tomar asistencia de un curso que no esta en estado iniciado.");
			return;
		}
		
		Date fecha = Instructor.proximaFechaTomarAsistencia(curso);
		if (fecha == null) {
			Popup.mostrar("Ya se han tomado todas las asistensias posibles para este curso.");
			return;
		}
		
		if (fecha.after(Almanaque.hoy())) {
			Popup.mostrar("Ya se han cargado todas las asistensias posibles hasta el dia de hoy.");
			return;
		}
		
		invocador.getVentana().setEnabled(false);
		new ControladorTomarAsistencia(this, seleccion.get(0));
	}
	
	private void consultarNotas() {
		List<Curso> seleccion = ventana.getTabla().obtenerSeleccion();
		if (seleccion.size() != 1) {
			Popup.mostrar("Debe seleccionar extamente 1 curso para consultar las notas de los examenes.");
			return;
		}
		
		invocador.getVentana().setEnabled(false);
		new ControladorConsultarNotas(this, seleccion.get(0));
	}
	
	private void cargar() {
		List<Curso> seleccion = ventana.getTabla().obtenerSeleccion();
		if (seleccion.size() != 1) {
			Popup.mostrar("Debe seleccionar extamente 1 curso para cargar las notas de un examen.");
			return;
		}
		
		invocador.getVentana().setEnabled(false);
		new ControladorCargarExamen(this, seleccion.get(0));
	}

	public void mostrar() {
		ventana.mostrar();
	}

	public void habilitarPrincipal(){
		invocador.getVentana().setEnabled(true);
		invocador.getVentana().toFront();
	}
	public boolean finalizar() {
		return true;
	}

	public JInternalFrame getVentana() {
		return ventana;
	}
	
}