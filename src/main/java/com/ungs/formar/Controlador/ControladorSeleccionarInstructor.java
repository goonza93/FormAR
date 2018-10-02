package com.ungs.formar.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import com.ungs.formar.vista.CrearCurso;
import com.ungs.formar.vista.SeleccionarDiaHorario;
import com.ungs.formar.vista.SeleccionarInstructor;
import com.ungs.formar.vista.SeleccionarResponsable;
import com.ungs.formar.vista.SeleccionarSala;

public class ControladorSeleccionarInstructor implements ActionListener {
	private SeleccionarInstructor ventanaSeleccionarInstructor;
	private ControladorCrearCurso controladorCrearCurso;

	public ControladorSeleccionarInstructor(SeleccionarInstructor ventanaSeleccionarInstructor,
			ControladorCrearCurso controladorCrearCurso) {
		this.ventanaSeleccionarInstructor = ventanaSeleccionarInstructor;
		this.controladorCrearCurso = controladorCrearCurso;
		this.ventanaSeleccionarInstructor.getBtnCancelar().addActionListener(this);
		this.ventanaSeleccionarInstructor.getBtnFiltrar().addActionListener(this);
		this.ventanaSeleccionarInstructor.getBtnSeleccionar().addActionListener(this);
	}

	public void inicializar() {
		this.ventanaSeleccionarInstructor.setVisible(true);
		llenarComboFiltrarPor();
		llenarComboOrdenarPor();
		llenarTablaInstructores();
	}

	private void llenarComboOrdenarPor() {
		JComboBox<String> ordenarPor = this.ventanaSeleccionarInstructor.getComboOrdenarPor();
		ordenarPor.addItem("Seleccionar");
		ordenarPor.addItem("Nombre a-z");
		ordenarPor.addItem("Nombre z-a");
		ordenarPor.addItem("Apellido a-z");
		ordenarPor.addItem("Apellido z-a");
		ordenarPor.addItem("DNI ascendente");
		ordenarPor.addItem("DNI descendente");
	}

	private void llenarComboFiltrarPor() {
		JComboBox<String> filtrarPor = this.ventanaSeleccionarInstructor.getComboFiltrar();
		filtrarPor.addItem("Seleccionar");
		filtrarPor.addItem("Nombre");
		filtrarPor.addItem("Apellido");
		filtrarPor.addItem("DNI");
	}

	private void llenarTablaInstructores() {

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventanaSeleccionarInstructor.getBtnSeleccionar()) {

		} else if (e.getSource() == ventanaSeleccionarInstructor.getBtnCancelar()) {
			this.ventanaSeleccionarInstructor.dispose();
			this.controladorCrearCurso.inicializar();
		} else if (e.getSource() == ventanaSeleccionarInstructor.getBtnFiltrar()) {

		}

	}
}