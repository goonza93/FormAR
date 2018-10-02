package com.ungs.formar.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import com.ungs.formar.vista.CrearCurso;
import com.ungs.formar.vista.SeleccionarDiaHorario;
import com.ungs.formar.vista.SeleccionarInstructor;
import com.ungs.formar.vista.SeleccionarResponsable;
import com.ungs.formar.vista.SeleccionarSala;

public class ControladorSeleccionarSala implements ActionListener {
	private SeleccionarSala ventanaSeleccionarSala;
	private ControladorCrearCurso controladorCrearCurso;

	public ControladorSeleccionarSala(SeleccionarSala ventanaSeleccionarSala,
			ControladorCrearCurso controladorCrearCurso) {
		this.ventanaSeleccionarSala = ventanaSeleccionarSala;
		this.controladorCrearCurso = controladorCrearCurso;
		this.ventanaSeleccionarSala.getBtnCancelar().addActionListener(this);
		this.ventanaSeleccionarSala.getBtnFiltrar().addActionListener(this);
		this.ventanaSeleccionarSala.getBtnSeleccionar().addActionListener(this);
	}

	public void inicializar() {
		this.ventanaSeleccionarSala.setVisible(true);
		llenarComboFiltrarPor();
		llenarComboOrdenarPor();
		llenarTablaSalas();
	}

	private void llenarComboOrdenarPor() {
		JComboBox<String> ordenarPor = this.ventanaSeleccionarSala.getComboOrdenar();
		ordenarPor.addItem("Seleccionar");
		ordenarPor.addItem("Numero ascendente");
		ordenarPor.addItem("Numero descendente");
		ordenarPor.addItem("Nombre a-z");
		ordenarPor.addItem("Nombre z-a");
		ordenarPor.addItem("Capacidad ascendente");
		ordenarPor.addItem("Capacidad descendente");
	}

	private void llenarComboFiltrarPor() {
		JComboBox<String> filtrarPor = this.ventanaSeleccionarSala.getComboFiltrar();
		filtrarPor.addItem("Seleccionar");
		filtrarPor.addItem("Hasta 20");
		filtrarPor.addItem("21 - 40");
		filtrarPor.addItem("41 - 60");
		filtrarPor.addItem("61 - 80");
		filtrarPor.addItem("Mas de 80");
	}

	private void llenarTablaSalas() {

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventanaSeleccionarSala.getBtnSeleccionar()) {

		} else if (e.getSource() == ventanaSeleccionarSala.getBtnCancelar()) {
			this.ventanaSeleccionarSala.dispose();
			this.controladorCrearCurso.inicializar();
		} else if (e.getSource() == ventanaSeleccionarSala.getBtnFiltrar()) {

		}

	}
}