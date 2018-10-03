package com.ungs.formar.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JComboBox;

import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.vista.CrearCurso;
import com.ungs.formar.vista.SeleccionarDiaHorario;
import com.ungs.formar.vista.SeleccionarInstructor;
import com.ungs.formar.vista.SeleccionarResponsable;
import com.ungs.formar.vista.SeleccionarSala;

public class ControladorSeleccionarResponsable implements ActionListener {
	private SeleccionarResponsable ventanaSeleccionarResponsable;
	private ControladorCrearCurso controladorCrearCurso;
	private List<Empleado> responsables_en_tabla;

	public ControladorSeleccionarResponsable(SeleccionarResponsable ventanaSeleccionarResponsable,
			ControladorCrearCurso controladorCrearCurso) {
		this.ventanaSeleccionarResponsable = ventanaSeleccionarResponsable;
		this.controladorCrearCurso = controladorCrearCurso;
		this.ventanaSeleccionarResponsable.getBtnCancelar().addActionListener(this);
		this.ventanaSeleccionarResponsable.getBtnSeleccionar().addActionListener(this);
	}

	public void inicializar() {
		this.ventanaSeleccionarResponsable.setVisible(true);
		llenarTablaResponsables();
	}

	private void llenarTablaResponsables() {

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.ventanaSeleccionarResponsable.getBtnSeleccionar()) {
			int fila_seleccionada = this.ventanaSeleccionarResponsable.getTablaResponsables().getSelectedRow();
			if (fila_seleccionada != -1) {
				// this.controladorCrearCurso.setResponsable(//Responsable EN
				// FILA
				// SELECCIONADA);
			}
		} else if (e.getSource() == this.ventanaSeleccionarResponsable.getBtnCancelar()) {
			this.ventanaSeleccionarResponsable.dispose();
			this.controladorCrearCurso.inicializar();
		} 
	}
}