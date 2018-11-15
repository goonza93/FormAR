package com.ungs.formar.vista.instructores.asistencia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.List;

import javax.swing.JInternalFrame;

import com.ungs.formar.negocios.Instructor;
import com.ungs.formar.persistencia.definidos.EstadoCurso;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.pruebas.ventanainterna.ControladorInterno;
import com.ungs.formar.pruebas.ventanainterna.ControladorPrincipal;
import com.ungs.formar.vista.instructores.asistencia.alta.ControladorTomarAsistencia;
import com.ungs.formar.vista.instructores.asistencia.consultar.ControladorConsultarAsistencia;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPantallaPrincipal;
import com.ungs.formar.vista.util.Popup;

public class ControladorGestionAsistencias2 implements ActionListener, ControladorInterno {
	private ControladorPrincipal invocador;
	private VentanaGestionAsistencias2 ventana;
	
	public ControladorGestionAsistencias2(ControladorPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaGestionAsistencias2();
		ventana.botonConsultar().addActionListener(this);
		ventana.botonTomar().addActionListener(this);
		ventana.getVolver().addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == ventana.botonConsultar())
			consultar();
		
		else if (e.getSource() == ventana.botonTomar())
			tomar();
		
		else if (e.getSource() == ventana.getVolver())
			volver();
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
		
		//ventana.deshabilitar();
		//new ControladorConsultarAsistencia(this, seleccion.get(0));
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
			Popup.mostrar("Ya se han tomado tods las asistensias posibles para este curso.");
			return;
		}
		
		ventana.deshabilitar();
		//new ControladorTomarAsistencia(this, seleccion.get(0));
	}
	
	private void volver() {
		ventana.dispose();
		ventana = null;
		//invocador.inicializar();
	}

	public void mostrar() {
		ventana.mostrar();
	}

	public boolean finalizar() {
		return true;
	}

	public JInternalFrame getVentana() {
		return ventana;
	}
	
}