package com.ungs.formar.vista.instructores.asistencia.alta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ungs.formar.negocios.AlumnoManager;
import com.ungs.formar.negocios.InscripcionManager;
import com.ungs.formar.negocios.Instructor;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Asistencia;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Inscripcion;
import com.ungs.formar.vista.consulta.Consultable;
import com.ungs.formar.vista.instructores.asistencia.ControladorGestionAsistencias;
import com.ungs.formar.vista.util.Popup;

public class ControladorTomarAsistencia implements ActionListener, Consultable {
	private VentanaTomarAsistencia ventana;
	private ControladorGestionAsistencias controlador;
	private List<Asistencia> asistencias;
	private Curso curso;

	public ControladorTomarAsistencia(ControladorGestionAsistencias controlador, Curso curso) {
		this.controlador = controlador;
		this.curso = curso;
		this.ventana = new VentanaTomarAsistencia();
		this.ventana.botonGuardar().addActionListener(this);
		this.ventana.botonCancelar().addActionListener(this);

		this.ventana.getVentana().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cancelar();
			}
		});
		this.inicializar();
	}

	public void inicializar() {
		llenarTabla();
		ventana.getVentana().setVisible(true);
		ventana.getVentana().setEnabled(true);
	}

	private void llenarTabla() {
		ventana.getModelo().setRowCount(0);
		ventana.getModelo().setColumnCount(0);
		ventana.getModelo().setColumnIdentifiers(ventana.getColumnas());

		Date fecha = Instructor.proximaFechaTomarAsistencia(curso);
		List<Inscripcion> inscripciones = InscripcionManager.traerInscripciones(curso);
		asistencias = new ArrayList<Asistencia>();
		
		for (Inscripcion inscripcion : inscripciones)
			asistencias.add(new Asistencia(-1, curso.getID(), inscripcion.getAlumno(), fecha, false));
			
		for (Asistencia asistencia: asistencias) {
			Alumno alumno = AlumnoManager.traerAlumnoSegunID(asistencia.getAlumno());
			
			Object[] fila = {
					alumno.getApellido(),
					alumno.getNombre(),
					asistencia.isPresente()
					};
			ventana.getModelo().addRow(fila);
		}
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == ventana.botonGuardar())
			guardar();

		else if (e.getSource() == ventana.botonCancelar())
			cancelar();

	}

	private void guardar() {
		
		// Coloco a mi lista de asistencias los presentes que tomo el instructor
		for (int i=0; i < asistencias.size(); i++)
			asistencias.get(i).setPresente((Boolean)ventana.getModelo().getValueAt(i, 2)); // 2 es la columna presente, i es la fila
		
		Instructor.guardarAsistencias(asistencias);
		Popup.mostrar("Las asistencias se han guardado correctamente.");
		volver();
	}

	public void cancelar() {
		if (Popup.confirmar("Si vuelve atras se perderan los datos\n¿Seguro que desea salir de esta ventana?"))
			volver();
	}

	public void volver() {
		ventana.getVentana().dispose();
		ventana = null;
		controlador.mostrar();
	}
	
}