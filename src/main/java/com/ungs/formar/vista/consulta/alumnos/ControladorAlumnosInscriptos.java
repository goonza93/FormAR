package com.ungs.formar.vista.consulta.alumnos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import com.ungs.formar.negocios.InscripcionManager;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.vista.consulta.Consultable;

public class ControladorAlumnosInscriptos implements ActionListener{
	private VentanaAlumnosInscriptos ventana;
	private Consultable invocador;
	private List<Alumno> alumnos;
	private Curso curso;

	public ControladorAlumnosInscriptos(VentanaAlumnosInscriptos ventana, Consultable invocador, Curso curso) {
		this.ventana= ventana;
		this.invocador = invocador;
		this.curso = curso;
		this.ventana.getVolver().addActionListener(this);
		this.inicializar();
	}

	public void inicializar() {
		llenarTabla();
		ventana.getVentana().setVisible(true);
	}

	private void llenarTabla() {
		ventana.getModeloAlumnos().setRowCount(0);
		ventana.getModeloAlumnos().setColumnCount(0);
		ventana.getModeloAlumnos().setColumnIdentifiers(ventana.getNombreColumnas());

		alumnos = InscripcionManager.traerAlumnosInscriptos(curso);
		for (Alumno alumno: alumnos) {
			Object[] fila = {
					alumno.getApellido(),
					alumno.getNombre(),
					alumno.getDni(),
					alumno.getEmail(),
					alumno.getTelefono()
					};
			ventana.getModeloAlumnos().addRow(fila);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		// BOTON VOLVER
		if (e.getSource() == ventana.getVolver())
			cerrarVentana();
	}
	
	private void cerrarVentana() {
		ventana.getVentana().dispose();
		ventana = null;
		invocador.inicializar();
	}
	
}