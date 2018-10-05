package com.ungs.formar.vista.controladores.seleccion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.ungs.formar.vista.controladores.ControladorCrearCurso;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarSala;

public class ControladorSeleccionarSala implements ActionListener {
	private SeleccionarSala ventana;
	private ControladorCrearCurso controlador;

	public ControladorSeleccionarSala(SeleccionarSala ventana, ControladorCrearCurso controlador) {
		this.ventana = ventana;
		this.controlador = controlador;
		this.ventana.getBtnCancelar().addActionListener(this);
		this.ventana.getBtnSeleccionar().addActionListener(this);
		this.inicializar();
	}

	public void inicializar() {
		this.ventana.setVisible(true);
		llenarTablaSalas();
	}

	private void llenarTablaSalas() {
		// No me acuerdo como se hacia pero les dejo la lista de salas
		// List<Sala> salas = SalaManager.traerSalas();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventana.getBtnSeleccionar()) {
			int fila_seleccionada = this.ventana.getTablaSalas().getSelectedRow();
			if (fila_seleccionada != -1) {
				// this.controladorCrearCurso.setSala(//Sala EN
				// FILA
				// SELECCIONADA);
			}
		} else if (e.getSource() == ventana.getBtnCancelar()) {
			this.ventana.dispose();
			this.controlador.inicializar();
		} 
	}
	
}