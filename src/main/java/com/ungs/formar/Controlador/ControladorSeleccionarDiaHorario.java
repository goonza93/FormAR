package com.ungs.formar.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import com.ungs.formar.vista.CrearCurso;
import com.ungs.formar.vista.SeleccionarDiaHorario;
import com.ungs.formar.vista.SeleccionarInstructor;
import com.ungs.formar.vista.SeleccionarResponsable;
import com.ungs.formar.vista.SeleccionarSala;

public class ControladorSeleccionarDiaHorario implements ActionListener {
	private SeleccionarDiaHorario ventanaSeleccionarDiaHorario;
	private ControladorCrearCurso controladorCrearCurso;

	public ControladorSeleccionarDiaHorario(SeleccionarDiaHorario ventanaSeleccionarDiaHorario,
			ControladorCrearCurso controladorCrearCurso) {
		this.ventanaSeleccionarDiaHorario = ventanaSeleccionarDiaHorario;
		this.controladorCrearCurso = controladorCrearCurso;
	}

	public void inicializar() {
		this.ventanaSeleccionarDiaHorario.setVisible(true);
		llenarComboDia();
		llenarComboOrdenarPor();
		llenarTablaDiaHorario();
	}

	private void llenarComboOrdenarPor() {
		JComboBox<String> ordenarPor = this.ventanaSeleccionarDiaHorario.getComboOrdenar();
		ordenarPor.addItem("Seleccionar");
		ordenarPor.addItem("Hora inicio ascendente");
		ordenarPor.addItem("Hora inicio descendente");
		ordenarPor.addItem("Hora fin ascendente");
		ordenarPor.addItem("Hora fin descendente");
	}

	private void llenarComboDia() {
		JComboBox<String> dia = this.ventanaSeleccionarDiaHorario.getComboDia();
		dia.addItem("Seleccionar");
		dia.addItem("Lunes");
		dia.addItem("Martes");
		dia.addItem("Miercoles");
		dia.addItem("Jueves");
		dia.addItem("Viernes");
		dia.addItem("Sabado");
	}

	private void llenarTablaDiaHorario() {

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventanaSeleccionarDiaHorario.getBtnSeleccionar()) {

		} else if (e.getSource() == ventanaSeleccionarDiaHorario.getBtnCancelar()) {
			this.ventanaSeleccionarDiaHorario.dispose();
			this.controladorCrearCurso.inicializar();
		} else if (e.getSource() == ventanaSeleccionarDiaHorario.getBtnFiltrar()) {

		} else if (e.getSource() == ventanaSeleccionarDiaHorario.getBtnAgregar()) {

		}

	}
}