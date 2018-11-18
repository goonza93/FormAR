package com.ungs.formar.vista.instructores.notas.consultar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import com.ungs.formar.negocios.AlumnoManager;
import com.ungs.formar.negocios.InscripcionManager;
import com.ungs.formar.negocios.Instructor;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Examen;
import com.ungs.formar.persistencia.entidades.Inscripcion;
import com.ungs.formar.vista.consulta.Consultable;
import com.ungs.formar.vista.instructores.notas.ControladorGestionNotas;
import com.ungs.formar.vista.util.Formato;

public class ControladorConsultarNotas implements ActionListener, Consultable {
	private ControladorGestionNotas controlador;
	private VentanaConsultarNotas ventana;
	private Curso curso;

	public ControladorConsultarNotas(ControladorGestionNotas controlador, Curso curso) {
		this.controlador = controlador;
		this.curso = curso;
		ventana = new VentanaConsultarNotas();
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
		List<String> examenes = Instructor.traerExamenesDeCurso(curso);
		String[] columnas = Formato.columnasDeNotas(curso);
		
		ventana.getModelo().setRowCount(0);
		ventana.getModelo().setColumnCount(0);
		ventana.getModelo().setColumnIdentifiers(columnas);

		for (Inscripcion inscripcion : inscripciones) {
			Alumno alumno = AlumnoManager.traerAlumnoSegunID(inscripcion.getAlumno());
			Object[] fila = new Object[columnas.length];
			fila[0] = alumno.getNombre();
			fila[1] = alumno.getApellido();
			
			for (int i=0; i<examenes.size(); i++) {
				Examen examen = Instructor.traerNota(curso, alumno, examenes.get(i));
				if (examen != null)
					fila[i+2] = examen.getNota();
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

	@Override
	public void habilitarPrincipal() {
		// TODO Auto-generated method stub
		
	}
	
}