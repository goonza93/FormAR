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
	}

	public void inicializar() {
		this.ventanaSeleccionarSala.setVisible(true);
		llenarTablaSalas();
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
		} 

	}
}