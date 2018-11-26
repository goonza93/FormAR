package com.ungs.formar.vista.instructores.asistencia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.ungs.formar.negocios.Almanaque;
import com.ungs.formar.negocios.CursoManager;
import com.ungs.formar.negocios.Instructor;
import com.ungs.formar.negocios.PdfManager;
import com.ungs.formar.persistencia.definidos.EstadoCurso;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Pdf;
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
	private Pdf contenido;
	
	public ControladorGestionAsistencias2(ControladorPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaGestionAsistencias2();
		ventana.botonConsultar().addActionListener(this);
		ventana.botonTomar().addActionListener(this);
		ventana.botonConsultarNotas().addActionListener(this);
		ventana.botonCargar().addActionListener(this);
		ventana.botonVerArchivo().addActionListener(this);
		ventana.botonSelArchivo().addActionListener(this);
		
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
		
		else if (e.getSource() == ventana.botonVerArchivo())
			abrirArchivo();
		
		else if (e.getSource() == ventana.botonSelArchivo())
			seleccionarArchivo();
	}

	private void seleccionarArchivo() {
		List<Curso> seleccion = ventana.getTabla().obtenerSeleccion();
		if (seleccion.size() != 1) {
			Popup.mostrar("Debe seleccionar extamente 1 curso para consultar las notas de los examenes.");
			return;
		}
		
		Curso curso = seleccion.get(0);
		if (curso.getEstado() == EstadoCurso.FINALIZADO || curso.getEstado() == EstadoCurso.CANCELADO) {
			Popup.mostrar("No se puede asignar programas a cursadas finalizadas o canceladas.");
			return;
		}
		
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF only", "pdf", "txt");
		chooser.setFileFilter(filter);
		int result = chooser.showOpenDialog(null);
		File archivo = chooser.getSelectedFile();
		if (result == JFileChooser.APPROVE_OPTION) {
			if (archivo != null) {
				if(Popup.confirmar("¿Esta seguro desea asignar "+archivo.getName()+" como programa especifico?")){
					this.contenido = PdfManager.crearPdf(archivo);
					PdfManager.guardarPdf(this.contenido);
					seleccion.get(0).setContenido(contenido.getContenidoID());
					CursoManager.actualizarPdfCurso(seleccion.get(0));
				}
			} else {
				this.contenido = null;
			}
		}
	}

	private void abrirArchivo() {
		List<Curso> seleccion = ventana.getTabla().obtenerSeleccion();
		if (seleccion.size() != 1) {
			Popup.mostrar("Debe seleccionar exactamente 1 curso para consultar las notas de los examenes.");
			return;
		}
		if(seleccion.get(0).getContenido() != null){
			this.contenido = PdfManager.traerPdfByID(seleccion.get(0).getContenido());
		}else{
			this.contenido = null;
		}
		if (this.contenido == null || this.contenido.getContenidoID() == null) {
			JOptionPane.showMessageDialog(null, "No hay ningun programa especifico para mostrar.");
		} else {
			PdfManager.abrirPdf(this.contenido.getContenidoID());
		}
	}

	private void consultar() {
		List<Curso> seleccion = ventana.getTabla().obtenerSeleccion();
		if (seleccion.size() != 1) {
			Popup.mostrar("Debe seleccionar exactamente 1 curso para consultar su asistencia.");
			return;
		}
		
		Curso curso = seleccion.get(0);
		if(!(curso.getEstado() == EstadoCurso.INICIADO || curso.getEstado() == EstadoCurso.FINALIZADO)){
			Popup.mostrar("Solo se pueden consultar asistencias de cursos en estado iniciado y finalizado.");
			return;
		}
		
		if(Instructor.traerFechasTomarAsistencia(curso).isEmpty()){
			Popup.mostrar("Las asistencias no estan disponibles.");
			return;
		}
		
		invocador.getVentana().setEnabled(false);
		new ControladorConsultarAsistencia(this, seleccion.get(0));
	}

	private void tomar() {
		List<Curso> seleccion = ventana.getTabla().obtenerSeleccion();
		if (seleccion.size() != 1) {
			Popup.mostrar("Debe seleccionar exactamente 1 curso para tomarle asistencia.");
			return;
		}
		
		Curso curso = seleccion.get(0);
		if (curso.getEstado() != EstadoCurso.INICIADO) {
			Popup.mostrar("No se puede tomar asistencia de un curso que no esta en estado iniciado.");
			return;
		}
		
		Date fecha = Instructor.proximaFechaTomarAsistencia(curso);
		if (fecha == null) {
			Popup.mostrar("Ya se han tomado todas las asistencias posibles para este curso.");
			return;
		}
		
		if (fecha.after(Almanaque.hoy())) {
			Popup.mostrar("Ya se han cargado todas las asistencias posibles hasta el dia de hoy.");
			return;
		}
		
		invocador.getVentana().setEnabled(false);
		new ControladorTomarAsistencia(this, seleccion.get(0));
	}
	
	private void consultarNotas() {
		List<Curso> seleccion = ventana.getTabla().obtenerSeleccion();
		if (seleccion.size() != 1) {
			Popup.mostrar("Debe seleccionar exactamente 1 curso para consultar las notas de los examenes.");
			return;
		}

		// Solo se puede consultar notas de un curso inicado
		Curso curso = seleccion.get(0);
		if (!(curso.getEstado() == EstadoCurso.INICIADO || curso.getEstado() == EstadoCurso.FINALIZADO)) {
			Popup.mostrar("Solo se pueden consultar notas de un examen para cursos en estado iniciado y finalizado.");
			return;
		}
		
		invocador.getVentana().setEnabled(false);
		new ControladorConsultarNotas(this, curso);
	}
	
	private void cargar() {
		List<Curso> seleccion = ventana.getTabla().obtenerSeleccion();
		if (seleccion.size() != 1) {
			Popup.mostrar("Debe seleccionar exactamente 1 curso para cargar las notas de un examen.");
			return;
		}
		
		// Solo se puede cargar notas de un curso inicado
		Curso curso = seleccion.get(0);
		if (curso.getEstado() != EstadoCurso.INICIADO) {
			Popup.mostrar("Solo se pueden cargar notas de un examen para cursos en estado INICIADO.");
			return;
		}
		
		invocador.getVentana().setEnabled(false);
		new ControladorCargarExamen(this, curso);
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