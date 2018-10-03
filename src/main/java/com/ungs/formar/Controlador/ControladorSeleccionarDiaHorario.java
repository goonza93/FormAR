package com.ungs.formar.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JComboBox;

import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.vista.AltaModifDiaHorario;
import com.ungs.formar.vista.CrearCurso;
import com.ungs.formar.vista.SeleccionarDiaHorario;
import com.ungs.formar.vista.SeleccionarInstructor;
import com.ungs.formar.vista.SeleccionarResponsable;
import com.ungs.formar.vista.SeleccionarSala;

public class ControladorSeleccionarDiaHorario implements ActionListener {
	private SeleccionarDiaHorario ventanaSeleccionarDiaHorario;
	private ControladorCrearCurso controladorCrearCurso;
	private AltaModifDiaHorario ventanaAltaModifDiaHorario;
	private List<Horario> horarios_en_tabla;

	public ControladorSeleccionarDiaHorario(SeleccionarDiaHorario ventanaSeleccionarDiaHorario,
			ControladorCrearCurso controladorCrearCurso) {
		this.ventanaSeleccionarDiaHorario = ventanaSeleccionarDiaHorario;
		this.controladorCrearCurso = controladorCrearCurso;
		this.ventanaSeleccionarDiaHorario.getBtnAgregar().addActionListener(this);
		this.ventanaSeleccionarDiaHorario.getBtnCancelar().addActionListener(this);
		this.ventanaSeleccionarDiaHorario.getBtnSeleccionar().addActionListener(this);

	}

	public void inicializar() {
		this.ventanaSeleccionarDiaHorario.setVisible(true);
		llenarTablaDiaHorario();
	}

	private void llenarTablaDiaHorario() {

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.ventanaSeleccionarDiaHorario.getBtnSeleccionar()) {
			int fila_seleccionada = this.ventanaSeleccionarDiaHorario.getTablaDiasHorarios().getSelectedRow();
			if (fila_seleccionada != -1) {
				// this.controladorCrearCurso.setHorariosCursada(//DIA HORARIO
				// EN FILA SELECCIONADA);
			}
		} else if (e.getSource() == this.ventanaSeleccionarDiaHorario.getBtnCancelar()) {
			this.ventanaSeleccionarDiaHorario.dispose();
			this.controladorCrearCurso.inicializar();
		} else if (e.getSource() == ventanaSeleccionarDiaHorario.getBtnAgregar()) {
			this.ventanaSeleccionarDiaHorario.setVisible(false);
			this.ventanaAltaModifDiaHorario = new AltaModifDiaHorario();
			this.ventanaAltaModifDiaHorario.setVisible(true);
			// new
			// ControladorAltaModifDiaHorario(this.ventanaAltaModifDiaHorario,this);
		}

	}
}