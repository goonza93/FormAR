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
		this.ventanaSeleccionarDiaHorario.getBtnFiltrar().addActionListener(this);
		this.ventanaSeleccionarDiaHorario.getBtnCancelar().addActionListener(this);
		this.ventanaSeleccionarDiaHorario.getBtnSeleccionar().addActionListener(this);

	}

	public void inicializar() {
		this.ventanaSeleccionarDiaHorario.setVisible(true);
		llenarComboDia();
		llenarTablaDiaHorario();
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
		if (e.getSource() == this.ventanaSeleccionarDiaHorario.getBtnSeleccionar()) {
			int fila_seleccionada = this.ventanaSeleccionarDiaHorario.getTablaDiasHorarios().getSelectedRow();
			if (fila_seleccionada != -1) {
				// this.controladorCrearCurso.setHorariosCursada(//DIA HORARIO
				// EN FILA SELECCIONADA);
			}
		} else if (e.getSource() == this.ventanaSeleccionarDiaHorario.getBtnCancelar()) {
			this.ventanaSeleccionarDiaHorario.dispose();
			this.controladorCrearCurso.inicializar();
		} else if (e.getSource() == this.ventanaSeleccionarDiaHorario.getBtnFiltrar()) {
			if (this.ventanaSeleccionarDiaHorario.getComboDia().getSelectedIndex() != 0) {
				List<Horario> horario = null;
				if (this.ventanaSeleccionarDiaHorario.getComboDia().getSelectedIndex() == 1) {
					// this.horarios_en_tabla = obtenerHorarioPorDia LUNES;
				} else if (this.ventanaSeleccionarDiaHorario.getComboDia().getSelectedIndex() == 2) {
					// this.horarios_en_tabla = obtenerHorarioPorDia MARTES;
				} else if (this.ventanaSeleccionarDiaHorario.getComboDia().getSelectedIndex() == 3) {
					// this.horarios_en_tabla = obtenerHorarioPorDia MIERCOLES;
				} else if (this.ventanaSeleccionarDiaHorario.getComboDia().getSelectedIndex() == 4) {
					// this.horarios_en_tabla = obtenerHorarioPorDia JUEVES;
				} else if (this.ventanaSeleccionarDiaHorario.getComboDia().getSelectedIndex() == 5) {
					// this.horarios_en_tabla = obtenerHorarioPorDia VIERNES;
				} else if (this.ventanaSeleccionarDiaHorario.getComboDia().getSelectedIndex() == 6) {
					// this.horarios_en_tabla = obtenerHorarioPorDia SABADO;
				}
				this.llenarTablaDiaHorario();
			}
		} else if (e.getSource() == ventanaSeleccionarDiaHorario.getBtnAgregar()) {
			this.ventanaSeleccionarDiaHorario.setVisible(false);
			this.ventanaAltaModifDiaHorario = new AltaModifDiaHorario();
			this.ventanaAltaModifDiaHorario.setVisible(true);
			// new
			// ControladorAltaModifDiaHorario(this.ventanaAltaModifDiaHorario,this);
		}

	}
}