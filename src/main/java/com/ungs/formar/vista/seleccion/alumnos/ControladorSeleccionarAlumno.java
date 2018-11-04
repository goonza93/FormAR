package com.ungs.formar.vista.seleccion.alumnos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.vista.util.Popup;

public class ControladorSeleccionarAlumno implements ActionListener {
	private VentanaSeleccionarAlumno ventana;
	private AlumnoSeleccionable invocador;

	public ControladorSeleccionarAlumno(AlumnoSeleccionable invocador) {
		this.invocador = invocador;
		ventana = new VentanaSeleccionarAlumno();
		ventana.getBtnCancelar().addActionListener(this);
		ventana.getBtnSeleccionar().addActionListener(this);
		ventana.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		// BOTON SELECCIONAR
		if (e.getSource() == ventana.getBtnSeleccionar())
			seleccionar();
	
		// BOTON CANCELAR
		else if (e.getSource() == ventana.getBtnCancelar())
			cancelar();
	}
	
	private void seleccionar() {
		List<Alumno> alumnos = ventana.getTabla().obtenerSeleccion();
		if (alumnos.size() != 1)
			Popup.mostrar("Debe seleccionar exactamente un alumno");
		else {
			invocador.setAlumno(alumnos.get(0));
			ventana.dispose();
			invocador.mostrar();
		}
	}
		
	private void cancelar() {
		ventana.dispose();
		invocador.mostrar();
	}
	
}