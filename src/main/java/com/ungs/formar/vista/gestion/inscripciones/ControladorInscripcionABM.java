package com.ungs.formar.vista.gestion.inscripciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.ungs.formar.negocios.AlumnoManager;
import com.ungs.formar.negocios.CursoManager;
import com.ungs.formar.negocios.Formato;
import com.ungs.formar.negocios.InscripcionManager;
import com.ungs.formar.negocios.ProgramaManager;
import com.ungs.formar.persistencia.definidos.EstadoCurso;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.vista.consulta.Consultable;
import com.ungs.formar.vista.consulta.alumnos.ControladorAlumnosInscriptos;
import com.ungs.formar.vista.consulta.alumnos.VentanaAlumnosInscriptos;
import com.ungs.formar.vista.controladores.ControladorPantallaPrincipal;
import com.ungs.formar.vista.util.Popup;

public class ControladorInscripcionABM implements ActionListener, Consultable {
	private ControladorPantallaPrincipal controlador;
	private VentanaInscripcionABM ventanaABM;
	private VentanaInscripcionAlta ventanaAlta;
	private VentanaInscripcionBaja ventanaBaja;
	private List<Curso> cursos;
	private List<Alumno> alumnos;
	private Curso cursoSeleccionado;

	public ControladorInscripcionABM(ControladorPantallaPrincipal controlador, VentanaInscripcionABM ventanaABM) {
		this.controlador = controlador;
		this.ventanaABM = ventanaABM;
		this.ventanaABM.getInscribir().addActionListener(this);
		this.ventanaABM.getConsultar().addActionListener(this);
		this.ventanaABM.getVolver().addActionListener(this);
		this.ventanaABM.getBorrar().addActionListener(this);

		this.ventanaABM.getVentana().setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		this.ventanaABM.getVentana().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				volver();
			}
		});
		inicializar();
	}

	public void inicializar() {
		ventanaABM.getVentana().setEnabled(true);
		ventanaABM.getVentana().setVisible(true);
		llenarTabla();
	}

	private void llenarTabla() {
		ventanaABM.getModeloCursos().setRowCount(0);
		ventanaABM.getModeloCursos().setColumnCount(0);
		ventanaABM.getModeloCursos().setColumnIdentifiers(ventanaABM.getNombreColumnas());

		cursos = CursoManager.traerCursos();
		for (Curso curso : cursos) {
			Object[] fila = { Formato.nombre(curso), Formato.area(curso), Formato.estado(curso), curso.getCupoMinimo(),
					curso.getCupoMaximo(), curso.getFechaInicio(), curso.getFechaFin(), Formato.instructor(curso),
					Formato.responsable(curso), Formato.horarios(curso), };
			ventanaABM.getModeloCursos().addRow(fila);
		}
	}

	private void llenarTablaAlta() {
		ventanaAlta.getModeloAlumnos().setRowCount(0);
		ventanaAlta.getModeloAlumnos().setColumnCount(0);
		ventanaAlta.getModeloAlumnos().setColumnIdentifiers(ventanaAlta.getNombreColumnas());

		alumnos = AlumnoManager.traerAlumnos();
		for (Alumno alumno : alumnos) {
			Object[] fila = { alumno.getApellido(), alumno.getNombre(), alumno.getDNI(), alumno.getEmail(),
					alumno.getTelefono() };
			ventanaAlta.getModeloAlumnos().addRow(fila);
		}
	}

	private void llenarTablaBaja() {
		ventanaBaja.getModeloAlumnos().setRowCount(0);
		ventanaBaja.getModeloAlumnos().setColumnCount(0);
		ventanaBaja.getModeloAlumnos().setColumnIdentifiers(ventanaBaja.getNombreColumnas());

		alumnos = InscripcionManager.traerAlumnosInscriptos(cursoSeleccionado);
		for (Alumno alumno : alumnos) {
			Object[] fila = { alumno.getApellido(), alumno.getNombre(), alumno.getDNI(), alumno.getEmail(),
					alumno.getTelefono() };
			ventanaBaja.getModeloAlumnos().addRow(fila);
		}
	}

	public void actionPerformed(ActionEvent e) {

		// BOTON INSCRIBIR DEL ABM
		if (e.getSource() == ventanaABM.getInscribir())
			abrirVentanaAlta();

		// BOTON CONSULTAR DEL ABM
		else if (e.getSource() == ventanaABM.getConsultar())
			abrirVentanaConsultar();

		// BOTON CANCELAR INSCRIPCION DEL ABM
		else if (e.getSource() == ventanaABM.getBorrar())
			abrirVentanaBaja();

		// BOTON VOLVER DEL ABM
		else if (e.getSource() == ventanaABM.getVolver())
			volver();

		else if (ventanaAlta != null) {

			// BOTON INSCRIBIR DEL ALTA
			if (e.getSource() == ventanaAlta.getBtnInscribir())
				inscribirAlumnos();

			// BOTON VOLVER DEL ALTA
			else if (e.getSource() == ventanaAlta.getBtnVolver())
				cerrarVentanaAlta();
		}

		else if (ventanaBaja != null) {

			// BOTON CANCELAR INSCRIPCION DE BAJAS
			if (e.getSource() == ventanaBaja.getBaja())
				cancelarInscripcion();

			// BOTON VOLVER DE BAJAS
			else if (e.getSource() == ventanaBaja.getVolver())
				cerrarVentanaBaja();
		}

	}

	private void cancelarInscripcion() {
		/*
		 * Alumno alumno = obtenerAlumnoSeleccionadoBaja(); if (alumno != null)
		 * { InscripcionManager.cancelarInscripcion(alumno, cursoSeleccionado);
		 * cerrarVentanaBaja(); llenarTabla(); } else
		 * Popup.mostrar("Seleccione exactamente 1 alumno para dar de baja");
		 */
		List<Alumno> alumnos = obtenerAlumnosSeleccionadosBaja2();
		if (alumnos.size() == 0) {
			Popup.mostrar("Seleccione al menos 1 alumno para dar de baja.");
		} else {
			if (Popup.confirmar("Estas seguro que deseas dar de bajo a los alumnos seleccionados?")) {
				for (Alumno alumno : alumnos) {
					InscripcionManager.cancelarInscripcion(alumno, cursoSeleccionado);
				}
				cerrarVentanaBaja();
			}
		}
	}

	private void abrirVentanaBaja() {
		cursoSeleccionado = obtenerCursoSeleccionado();
		if (cursoSeleccionado != null) {

			if (cursoSeleccionado.getEstado() == EstadoCurso.FINALIZADO || cursoSeleccionado.getEstado() == EstadoCurso.CANCELADO) {
				Popup.mostrar("No se pueden cancelar inscripciones de un curso cuando esta finalizado o cerrado");
				return;
			}

			ventanaBaja = new VentanaInscripcionBaja(cursoSeleccionado);
			ventanaBaja.getVentana().setVisible(true);
			ventanaBaja.getBaja().addActionListener(this);
			ventanaBaja.getVolver().addActionListener(this);

			ventanaBaja.getVentana().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			ventanaBaja.getVentana().addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					cerrarVentanaBaja();
				}
			});

			ventanaABM.getVentana().setEnabled(false);
			llenarTablaBaja();
		} else
			Popup.mostrar("Seleccione exactamente 1 curso para poder gestonar bajas.");
	}

	private void cerrarVentanaAlta() {
		ventanaAlta.getVentana().dispose();
		ventanaAlta = null;
		inicializar();
	}

	private void cerrarVentanaBaja() {
		ventanaBaja.getVentana().dispose();
		ventanaBaja = null;
		inicializar();
	}

	private void inscribirAlumnos() {
		/*
		 * Alumno alumno = obtenerAlumnosSeleccionados(); if (alumno != null) {
		 * try { InscripcionManager.inscribir(cursoSeleccionado, alumno, null);
		 * cerrarVentanaAlta(); } catch (Exception e) {
		 * Popup.mostrar(e.getMessage()); } } else {
		 * Popup.mostrar("Seleccione exactamente 1 alumno para poder inscribir."
		 * ); }
		 */

		List<Alumno> alumnos = obtenerAlumnosSeleccionados2();
		boolean continuar = true;
		if (alumnos.size() == 0) {
			Popup.mostrar("Seleccione al menos 1 alumno para poder inscribir.");
			continuar = false;
		} else if (alumnos.size()
				+ InscripcionManager.traerAlumnosInscriptos(cursoSeleccionado).size() > cursoSeleccionado
						.getCupoMaximo()) {
			int confirm = JOptionPane.showOptionDialog(null,
					"La cantidad de alumnos seleccionados supera \nel cupo maximo, desea continuar!?", "Confirmacion",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (confirm != 0) {
				continuar = false;
			}
		}

		if (continuar) {
			for (Alumno alumno : alumnos) {
				try {
					InscripcionManager.inscribir(cursoSeleccionado, alumno, null);
				} catch (Exception e) {
					Popup.mostrar(e.getMessage());
				}
			}
			cerrarVentanaAlta();
		}

	}

	private void volver() {
		if (ventanaABM != null) {
			ventanaABM.getVentana().dispose();
			ventanaABM = null;
		}
		controlador.inicializar();
	}

	private void abrirVentanaConsultar() {
		cursoSeleccionado = obtenerCursoSeleccionado();
		if (cursoSeleccionado != null) {
			VentanaAlumnosInscriptos ventanaAlumnos = new VentanaAlumnosInscriptos(cursoSeleccionado);
			new ControladorAlumnosInscriptos(ventanaAlumnos, this, cursoSeleccionado);
			ventanaABM.getVentana().setEnabled(false);
		} else
			Popup.mostrar("Seleccione exactamente 1 curso para poder inscribir.");
	}

	private void abrirVentanaAlta() {
		boolean mostrar = true;
		cursoSeleccionado = obtenerCursoSeleccionado();

		if (cursoSeleccionado == null) {
			Popup.mostrar("Seleccione exactamente 1 curso para poder inscribir.");
			return;
		}

		// VALIDO LOS ESTADOS
		EstadoCurso estado = cursoSeleccionado.getEstado();

		if (estado == EstadoCurso.CREADO || estado == EstadoCurso.INICIADO)
			mostrar = mostrar && Popup.confirmar("El curso no esta abierto a inscripciones ¿Desea continuar?");

		if (estado == EstadoCurso.FINALIZADO || estado == EstadoCurso.CANCELADO) {
			mostrar = false;
			Popup.mostrar("No se pueden inscribir alumnos a este curso");
		}
		if (InscripcionManager.traerAlumnosInscriptos(cursoSeleccionado).size() >= cursoSeleccionado.getCupoMaximo()) {
			mostrar = false;
			int confirm = JOptionPane.showOptionDialog(null, "El curso seleccionado esta lleno, deseas continuar!?",
					"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (confirm == 0) {
				mostrar = true;
			}
		}

		if (mostrar) {
			ventanaAlta = new VentanaInscripcionAlta(cursoSeleccionado);
			ventanaAlta.getBtnInscribir().addActionListener(this);
			ventanaAlta.getBtnVolver().addActionListener(this);
			ventanaAlta.getVentana().addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					cerrarVentanaAlta();
				}
			});
			ventanaAlta.getVentana().setVisible(true);
			ventanaABM.getVentana().setEnabled(false);
			llenarTablaAlta();
		}
	}

	private Curso obtenerCursoSeleccionado() {
		int registroTabla = ventanaABM.getTablaCursos().getSelectedRow();
		// No habia ningun registro seleccionado
		if (registroTabla == -1)
			return null;
		int registro = ventanaABM.getTablaCursos().convertRowIndexToModel(registroTabla); 
		return cursos.get(registro);
	}

	private Alumno obtenerAlumnosSeleccionados() {
		int registroTabla = ventanaAlta.getTablaAlumnos().getSelectedRow();
		// No habia ningun registro seleccionado
		if (registroTabla == -1)
			return null;

		int registro = ventanaAlta.getTablaAlumnos().convertRowIndexToModel(registroTabla); 
		return alumnos.get(registro);
	}

	private List<Alumno> obtenerAlumnosSeleccionados2() {
		List<Alumno> ret = new ArrayList<Alumno>();
		int[] registroTabla = ventanaAlta.getTablaAlumnos().getSelectedRows();
		// No habia ningun registro seleccionado
		for (int fila : registroTabla) {
			int registro = ventanaAlta.getTablaAlumnos().convertRowIndexToModel(fila);
			ret.add(alumnos.get(registro));
		}
		return ret;
	}

	private Alumno obtenerAlumnoSeleccionadoBaja() {
		int registroTabla = ventanaBaja.getTablaAlumnos().getSelectedRow(); 

		// No habia ningun registro seleccionado
		if (registroTabla == -1)
			return null;

		int registro = ventanaBaja.getTablaAlumnos().convertRowIndexToModel(registroTabla);
		return alumnos.get(registro);
	}

	private List<Alumno> obtenerAlumnosSeleccionadosBaja2() {
		List<Alumno> ret = new ArrayList<Alumno>();
		int[] registroTabla = ventanaBaja.getTablaAlumnos().getSelectedRows(); 
		// No habia ningun registro seleccionado
		for (int fila : registroTabla) {
			int registro = ventanaBaja.getTablaAlumnos().convertRowIndexToModel(fila); 
			ret.add(alumnos.get(registro));
		}
		return ret;
	}

}