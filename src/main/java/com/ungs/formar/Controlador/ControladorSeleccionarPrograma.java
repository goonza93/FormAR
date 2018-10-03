package com.ungs.formar.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JComboBox;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.ungs.formar.persistencia.entidades.AreaDeInteres;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.vista.CrearCurso;
import com.ungs.formar.vista.SeleccionarDiaHorario;
import com.ungs.formar.vista.SeleccionarInstructor;
import com.ungs.formar.vista.SeleccionarPrograma;
import com.ungs.formar.vista.SeleccionarResponsable;
import com.ungs.formar.vista.SeleccionarSala;

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
		this.ventanaSeleccionarPrograma.getBtnFiltrar().addActionListener(this);

	}

	public void inicializar() {
		this.ventanaSeleccionarPrograma.setVisible(true);
		llenarComboFiltrarPor();
		llenarTablaInstructores();
	}

	private void llenarComboFiltrarPor() {
		JComboBox<String> filtrarPor = this.ventanaSeleccionarPrograma.getComboFiltrar();
		filtrarPor.addItem("Seleccionar");
		filtrarPor.addItem("Nombre");
		filtrarPor.addItem("Area de interes");
	}

	private void llenarTablaInstructores() {

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
		} else if (e.getSource() == ventanaSeleccionarPrograma.getBtnFiltrar()) {
			if (this.ventanaSeleccionarPrograma.getComboFiltrar().getSelectedIndex() != 0) {
				if (this.ventanaSeleccionarPrograma.getComboFiltrar().getSelectedIndex() == 1) {
					// this.programas_en_tabla = obtener por nombre
					// de lo ingresasdo en
					// this.ventanaSeleccionarPrograma.getTxtFiltro().getText();
				} else if (this.ventanaSeleccionarPrograma.getComboFiltrar().getSelectedIndex() == 2) {
					// this.programas_en_tabla = obtener por nombre
					// de lo ingresasdo en
					// this.ventanaSeleccionarPrograma.getTxtFiltro().getText();
				}
				this.llenarTablaInstructores();
			}
		}

	}
}