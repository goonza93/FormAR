package com.ungs.formar.vista.controladores.seleccion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JComboBox;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.ungs.formar.persistencia.entidades.Area;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.vista.controladores.ControladorCrearCurso;
import com.ungs.formar.vista.ventanas.CrearCurso;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarDiaHorario;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarInstructor;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarPrograma;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarResponsable;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarSala;

public class ControladorSeleccionarPrograma implements ActionListener {
	private SeleccionarPrograma ventanaSeleccionarPrograma;
	private ControladorCrearCurso controladorCrearCurso;
	private List<Programa> programas_en_tabla;

	public ControladorSeleccionarPrograma(SeleccionarPrograma ventanaSeleccionarPrograma,
			ControladorCrearCurso controladorCrearCurso) {
		this.ventanaSeleccionarPrograma = ventanaSeleccionarPrograma;
		this.controladorCrearCurso = controladorCrearCurso;
		this.ventanaSeleccionarPrograma.getBtnCancelar().addActionListener(this);
		this.ventanaSeleccionarPrograma.getBtnSeleccionar().addActionListener(this);
		this.inicializar();
	}

	public void inicializar() {
		this.ventanaSeleccionarPrograma.setVisible(true);
		llenarTablaProgramas();
	}

	private void llenarTablaProgramas() {

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventanaSeleccionarPrograma.getBtnSeleccionar()) {
			int fila_seleccionada = this.ventanaSeleccionarPrograma.getTablaProgramas().getSelectedRow();
			if (fila_seleccionada != -1) {
				// this.controladorCrearCurso.setPrograma(//PROGRAMA EN FILA
				// SELECCIONADA);
			}
		} else if (e.getSource() == ventanaSeleccionarPrograma.getBtnCancelar()) {
			this.ventanaSeleccionarPrograma.dispose();
			this.controladorCrearCurso.inicializar();
		}
	}
}