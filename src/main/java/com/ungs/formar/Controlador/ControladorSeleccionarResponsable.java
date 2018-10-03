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
		this.ventanaSeleccionarResponsable.getBtnFiltrar().addActionListener(this);
		this.ventanaSeleccionarResponsable.getBtnSeleccionar().addActionListener(this);
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
		} else if (e.getSource() == ventanaSeleccionarResponsable.getBtnFiltrar()) {
			if (this.ventanaSeleccionarResponsable.getComboFiltrar().getSelectedIndex() != 0) {
				if (this.ventanaSeleccionarResponsable.getComboFiltrar().getSelectedIndex() == 1) {
					// this.responsables_en_tabla = obtener por nombre
					// de lo ingresasdo en
					// this.ventanaSeleccionarResponsable.getTxtFiltro().getText();
				} else if (this.ventanaSeleccionarResponsable.getComboFiltrar().getSelectedIndex() == 2) {
					// this.responsables_en_tabla = obtener por apellido
					// de lo ingresasdo en
					// this.ventanaSeleccionarResponsable.getTxtFiltro().getText();
				} else if (this.ventanaSeleccionarResponsable.getComboFiltrar().getSelectedIndex() == 3) {
					// this.responsables_en_tabla = obtener por DNI
					// de lo ingresasdo en
					// this.ventanaSeleccionarResponsable.getTxtFiltro().getText();
				}
			}
			if (this.ventanaSeleccionarResponsable.getComboOrdenarPor().getSelectedIndex() != 0) {
				if (this.ventanaSeleccionarResponsable.getComboOrdenarPor().getSelectedIndex() == 1) {
					// Ordenar los RESPONSABLES por Nombre ascendente
					Collections.sort(this.responsables_en_tabla, new Comparator<Empleado>() {
						public int compare(Empleado obj1, Empleado obj2) {
							return obj1.getNombre().compareTo(obj2.getNombre());
						}
					});
				} else if (this.ventanaSeleccionarResponsable.getComboOrdenarPor().getSelectedIndex() == 2) {
					// Ordenar los RESPONSABLES por Nombre descendente
				} else if (this.ventanaSeleccionarResponsable.getComboOrdenarPor().getSelectedIndex() == 3) {
					// Ordenar los RESPONSABLES por apellido ascendente
					Collections.sort(this.responsables_en_tabla, new Comparator<Empleado>() {
						public int compare(Empleado obj1, Empleado obj2) {
							return obj1.getApellido().compareTo(obj2.getApellido());
						}
					});
				} else if (this.ventanaSeleccionarResponsable.getComboOrdenarPor().getSelectedIndex() == 4) {
					// Ordenar los RESPONSABLES por Apellido descendente
				} else if (this.ventanaSeleccionarResponsable.getComboOrdenarPor().getSelectedIndex() == 5) {
					// Ordenar los RESPONSABLES por DNI ascendente
					Collections.sort(this.responsables_en_tabla, new Comparator<Empleado>() {
						public int compare(Empleado obj1, Empleado obj2) {
							return obj1.getDNI().compareTo(obj2.getDNI());
						}
					});
				} else if (this.ventanaSeleccionarResponsable.getComboOrdenarPor().getSelectedIndex() == 6) {
					// Ordenar los RESPONSABLES por DNI descendente
				}
			}
			this.llenarTablaResponsables();
		}

	}
}