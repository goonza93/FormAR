package com.ungs.formar.vista.controladores.seleccion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JComboBox;

import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.vista.controladores.ControladorCrearCurso;
import com.ungs.formar.vista.ventanas.CrearCurso;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarDiaHorario;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarInstructor;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarResponsable;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarSala;

public class ControladorSeleccionarInstructor implements ActionListener {
	private SeleccionarInstructor ventanaSeleccionarInstructor;
	private ControladorCrearCurso controladorCrearCurso;
	private List<Empleado> instructores_en_tabla;

	public ControladorSeleccionarInstructor(SeleccionarInstructor ventanaSeleccionarInstructor,
			ControladorCrearCurso controladorCrearCurso) {
		this.ventanaSeleccionarInstructor = ventanaSeleccionarInstructor;
		this.controladorCrearCurso = controladorCrearCurso;
		this.ventanaSeleccionarInstructor.getBtnCancelar().addActionListener(this);
		this.ventanaSeleccionarInstructor.getBtnSeleccionar().addActionListener(this);
	}

	public void inicializar() {
		this.ventanaSeleccionarInstructor.setVisible(true);
		llenarTablaInstructores();
	}

	private void llenarTablaInstructores() {

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventanaSeleccionarInstructor.getBtnSeleccionar()) {
			int fila_seleccionada = this.ventanaSeleccionarInstructor.getTablaInstructores().getSelectedRow();
			if (fila_seleccionada != -1) {
				// this.controladorCrearCurso.setInstructor(//INSTRUCTOR EN FILA
				// SELECCIONADA);
			}

		} else if (e.getSource() == ventanaSeleccionarInstructor.getBtnCancelar()) {
			this.ventanaSeleccionarInstructor.dispose();
			this.controladorCrearCurso.inicializar();
		}
	}
}