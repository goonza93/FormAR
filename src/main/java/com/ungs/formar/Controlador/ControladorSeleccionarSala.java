package com.ungs.formar.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JComboBox;

import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Sala;
import com.ungs.formar.vista.CrearCurso;
import com.ungs.formar.vista.SeleccionarDiaHorario;
import com.ungs.formar.vista.SeleccionarInstructor;
import com.ungs.formar.vista.SeleccionarResponsable;
import com.ungs.formar.vista.SeleccionarSala;

public class ControladorSeleccionarSala implements ActionListener {
	private SeleccionarSala ventanaSeleccionarSala;
	private ControladorCrearCurso controladorCrearCurso;
	private List<Sala> salas_en_tabla;

	public ControladorSeleccionarSala(SeleccionarSala ventanaSeleccionarSala,
			ControladorCrearCurso controladorCrearCurso) {
		this.ventanaSeleccionarSala = ventanaSeleccionarSala;
		this.controladorCrearCurso = controladorCrearCurso;
		this.ventanaSeleccionarSala.getBtnCancelar().addActionListener(this);
		this.ventanaSeleccionarSala.getBtnSeleccionar().addActionListener(this);
		this.ventanaSeleccionarSala.getBtnFiltrar().addActionListener(this);
	}

	public void inicializar() {
		this.ventanaSeleccionarSala.setVisible(true);
		llenarComboFiltrarPor();
		llenarTablaSalas();
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
			int fila_seleccionada = this.ventanaSeleccionarSala.getTablaSalas().getSelectedRow();
			if (fila_seleccionada != -1) {
				// this.controladorCrearCurso.setSala(//Sala EN
				// FILA
				// SELECCIONADA);
			}
		} else if (e.getSource() == ventanaSeleccionarSala.getBtnCancelar()) {
			this.ventanaSeleccionarSala.dispose();
			this.controladorCrearCurso.inicializar();
		} else if (e.getSource() == ventanaSeleccionarSala.getBtnFiltrar()) {
			if (this.ventanaSeleccionarSala.getComboFiltrar().getSelectedIndex() != 0) {
				if (this.ventanaSeleccionarSala.getComboFiltrar().getSelectedIndex() == 1) {
					// this.salas_en_tabla = obtener hasta 20
				} else if (this.ventanaSeleccionarSala.getComboFiltrar().getSelectedIndex() == 2) {
					// this.salas_en_tabla = obtener 21 - 40
				} else if (this.ventanaSeleccionarSala.getComboFiltrar().getSelectedIndex() == 3) {
					// this.salas_en_tabla = obtener 41-60
				} else if (this.ventanaSeleccionarSala.getComboFiltrar().getSelectedIndex() == 4) {
					// this.salas_en_tabla = obtener 61-80
				} else if (this.ventanaSeleccionarSala.getComboFiltrar().getSelectedIndex() == 5) {
					// this.salas_en_tabla = obtener mas de 80
				}
				this.llenarTablaSalas();
			}
		}

	}
}