package com.ungs.formar.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import com.ungs.formar.vista.CrearCurso;
import com.ungs.formar.vista.SeleccionarDiaHorario;
import com.ungs.formar.vista.SeleccionarInstructor;
import com.ungs.formar.vista.SeleccionarPrograma;
import com.ungs.formar.vista.SeleccionarResponsable;
import com.ungs.formar.vista.SeleccionarSala;

public class ControladorSeleccionarPrograma implements ActionListener {
	private SeleccionarPrograma ventanaSeleccionarPrograma;
	private ControladorCrearCurso controladorCrearCurso;

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
		llenarComboOrdenarPor();
		llenarTablaInstructores();
	}

	private void llenarComboOrdenarPor() {
		JComboBox<String> ordenarPor = this.ventanaSeleccionarPrograma.getComboOrdenarPor();
		ordenarPor.addItem("Seleccionar");
		ordenarPor.addItem("Nombre a-z");
		ordenarPor.addItem("Nombre z-a");
		ordenarPor.addItem("Area de interes a-z");
		ordenarPor.addItem("Area de interes z-a");
		ordenarPor.addItem("Fecha Creacion ascendente");
		ordenarPor.addItem("Fecha Creacion descendente");
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

		} else if (e.getSource() == ventanaSeleccionarPrograma.getBtnCancelar()) {
			this.ventanaSeleccionarPrograma.dispose();
			this.controladorCrearCurso.inicializar();
		} else if (e.getSource() == ventanaSeleccionarPrograma.getBtnFiltrar()) {

		}

	}
}