package com.ungs.formar.vista.gestion.inscripciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JOptionPane;

import com.ungs.formar.negocios.AlumnoManager;
import com.ungs.formar.negocios.CursoManager;
import com.ungs.formar.negocios.Formato;
import com.ungs.formar.negocios.InscripcionManager;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.vista.consulta.alumnos.AlumnoConsultable;
import com.ungs.formar.vista.consulta.alumnos.ControladorAlumnosInscriptos;
import com.ungs.formar.vista.consulta.alumnos.VentanaAlumnosInscriptos;
import com.ungs.formar.vista.controladores.ControladorPantallaPrincipal;

public class ControladorInscripcionABM implements ActionListener, AlumnoConsultable{
	ControladorPantallaPrincipal controlador;
	private VentanaInscripcionABM ventanaABM;
	private VentanaInscripcionAlta ventanaAlta;
	private List<Curso> cursos;
	private List<Alumno> alumnos;
	private Curso cursoSeleccionado;

	public ControladorInscripcionABM(ControladorPantallaPrincipal controlador, VentanaInscripcionABM ventanaABM) {
		this.controlador = controlador;
		this.ventanaABM = ventanaABM;
		this.ventanaABM.getInscribir().addActionListener(this);
		this.ventanaABM.getConsultar().addActionListener(this);
		this.ventanaABM.getVolver().addActionListener(this);
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
			Object[] fila = {
					Formato.nombre(curso),
					Formato.area(curso),
					Formato.estado(curso),
					curso.getCupoMinimo(),
					curso.getCupoMaximo(),
					curso.getFechaInicio(),
					curso.getFechaFin(),
					Formato.instructor(curso),
					Formato.responsable(curso),
					Formato.horarios(curso),
					};
			ventanaABM.getModeloCursos().addRow(fila);
		}
	}

	private void llenarTablaAlta() {
		ventanaAlta.getModeloAlumnos().setRowCount(0);
		ventanaAlta.getModeloAlumnos().setColumnCount(0);
		ventanaAlta.getModeloAlumnos().setColumnIdentifiers(ventanaAlta.getNombreColumnas());

		alumnos = AlumnoManager.traerAlumnos();
		for (Alumno alumno: alumnos) {
			Object[] fila = {
					alumno.getApellido(),
					alumno.getNombre(),
					alumno.getDni(),
					alumno.getEmail(),
					alumno.getTelefono()
					};
			ventanaAlta.getModeloAlumnos().addRow(fila);
		}
	}

	public void actionPerformed(ActionEvent e) {
		
		// BOTON INSCRIBIR DEL ABM
		if (e.getSource() == ventanaABM.getInscribir())
			abrirVentanaAlta();
		
		// BOTON CONSULTAR DEL ABM
		else if (e.getSource() == ventanaABM.getConsultar())
			abrirVentanaConsultar();
		
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
		
	}

	private void cerrarVentanaAlta() {
		ventanaAlta.getVentana().dispose();
		ventanaAlta = null;
		inicializar();
	}

	private void inscribirAlumnos() {
		Alumno alumno = obtenerAlumnosSeleccionados();
		if (alumno != null) {
			InscripcionManager.inscribir(cursoSeleccionado, alumno, null);
			cerrarVentanaAlta();
		} else
			JOptionPane.showMessageDialog(null, "Seleccione exactamente 1 alumno para poder inscribir.");
	}

	private void volver() {
		ventanaABM.getVentana().dispose();
		ventanaABM = null;
		controlador.inicializar();
	}

	private void abrirVentanaConsultar() {
		cursoSeleccionado = obtenerCursoSeleccionado();
		if (cursoSeleccionado != null) {
			VentanaAlumnosInscriptos ventanaAlumnos = new VentanaAlumnosInscriptos(cursoSeleccionado);
			new ControladorAlumnosInscriptos(ventanaAlumnos, this, cursoSeleccionado);
			ventanaABM.getVentana().setEnabled(false);
		} else
			JOptionPane.showMessageDialog(null, "Seleccione exactamente 1 curso para poder inscribir.");
	}

	private void abrirVentanaAlta() {
		cursoSeleccionado = obtenerCursoSeleccionado();
		if (cursoSeleccionado != null) {
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
		
		} else
			JOptionPane.showMessageDialog(null, "Seleccione exactamente 1 curso para poder inscribir.");
	}

	private Curso obtenerCursoSeleccionado() {
		int registroTabla = ventanaABM.getTablaCursos().getSelectedRow(); //Indice de la tabla
		
		// No habia ningun registro seleccionado
		if (registroTabla == -1)
			return null;
		
		int registro = ventanaABM.getTablaCursos().convertRowIndexToModel(registroTabla); // Fix para el filtro
		return cursos.get(registro);
	}

	private Alumno obtenerAlumnosSeleccionados() {
		int registroTabla = ventanaAlta.getTablaAlumnos().getSelectedRow(); //Indice de la tabla
		
		// No habia ningun registro seleccionado
		if (registroTabla == -1)
			return null;
		
		int registro = ventanaAlta.getTablaAlumnos().convertRowIndexToModel(registroTabla); // Fix para el filtro
		return alumnos.get(registro);
	}
	
}