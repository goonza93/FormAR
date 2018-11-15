package com.ungs.formar.vista.instructores.asistencia.consultar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
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
import com.ungs.formar.vista.util.Formato;

public class ControladorConsultarAsistencia implements ActionListener, Consultable {
	private ControladorGestionAsistencias controlador;
	private VentanaConsultarAsistencia ventana;
	private Curso curso;

	public ControladorConsultarAsistencia(ControladorGestionAsistencias controlador, Curso curso) {
		this.controlador = controlador;
		this.curso = curso;
		ventana = new VentanaConsultarAsistencia();
		ventana.botonVolver().addActionListener(this);

		this.ventana.getVentana().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				volver();
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
		List<Inscripcion> inscripciones = InscripcionManager.traerInscripciones(curso);
		List<Date> fechas = Instructor.traerFechasTomarAsistencia(curso);
		String[] columnas = Formato.columnasDeAsistencia(fechas);
		
		ventana.getModelo().setRowCount(0);
		ventana.getModelo().setColumnCount(0);
		ventana.getModelo().setColumnIdentifiers(columnas);

		for (Inscripcion inscripcion : inscripciones) {
			Alumno alumno = AlumnoManager.traerAlumnoSegunID(inscripcion.getAlumno());
			Object[] fila = new Object[columnas.length];
			fila[0] = alumno.getNombre();
			fila[1] = alumno.getApellido();
			
			for (int i=0; i<fechas.size(); i++) {
				Asistencia asistencia = Instructor.traerAsistencia(curso, alumno, fechas.get(i));
				if (asistencia != null)
					fila[i+2] = Formato.presente(asistencia.isPresente());
				else
					fila[i+2] = "";
			}
			
			ventana.getModelo().addRow(fila);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventana.botonVolver())
			volver();
	}

	public void volver() {
		ventana.getVentana().dispose();
		ventana = null;
		controlador.mostrar();
	}
	
}