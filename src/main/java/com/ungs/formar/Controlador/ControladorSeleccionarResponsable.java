package com.ungs.formar.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import com.ungs.formar.vista.CrearCurso;
import com.ungs.formar.vista.SeleccionarDiaHorario;
import com.ungs.formar.vista.SeleccionarInstructor;
import com.ungs.formar.vista.SeleccionarResponsable;
import com.ungs.formar.vista.SeleccionarSala;

public class ControladorSeleccionarResponsable implements ActionListener {
	private SeleccionarResponsable ventanaSeleccionarResponsable;
	private ControladorCrearCurso controladorCrearCurso;

	public ControladorSeleccionarResponsable(SeleccionarResponsable ventanaSeleccionarResponsable,
			ControladorCrearCurso controladorCrearCurso) {
		this.ventanaSeleccionarResponsable = ventanaSeleccionarResponsable;
		this.controladorCrearCurso = controladorCrearCurso;
	}

	public void inicializar() {
		this.ventanaSeleccionarResponsable.setVisible(true);
		llenarComboFiltrarPor();
		llenarComboOrdenarPor();
		llenarTablaResponsables();
	}

	private void llenarComboOrdenarPor() {
		JComboBox<String> ordenarPor = this.ventanaSeleccionarResponsable.getComboOrdenarPor();
		ordenarPor.addItem("Seleccionar");
		ordenarPor.addItem("Nombre a-z");
		ordenarPor.addItem("Nombre z-a");
		ordenarPor.addItem("Apellido a-z");
		ordenarPor.addItem("Apellido z-a");
		ordenarPor.addItem("DNI ascendente");
		ordenarPor.addItem("DNI descendente");
	}

	private void llenarComboFiltrarPor() {
		JComboBox<String> filtrarPor = this.ventanaSeleccionarResponsable.getComboFiltrar();
		filtrarPor.addItem("Seleccionar");
		filtrarPor.addItem("Nombre");
		filtrarPor.addItem("Apellido");
		filtrarPor.addItem("DNI");
	}
	
	private void llenarTablaResponsables(){
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventanaSeleccionarResponsable.getBtnSeleccionar()) {

		} else if (e.getSource() == ventanaSeleccionarResponsable.getBtnCancelar()) {
			this.ventanaSeleccionarResponsable.dispose();
			this.controladorCrearCurso.inicializar();
		}
		else if (e.getSource() == ventanaSeleccionarResponsable.getBtnFiltrar()) {
			
		}

	}
}