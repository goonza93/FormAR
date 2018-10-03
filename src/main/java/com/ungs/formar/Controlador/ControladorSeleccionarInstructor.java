package com.ungs.formar.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JComboBox;

import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.vista.CrearCurso;
import com.ungs.formar.vista.SeleccionarDiaHorario;
import com.ungs.formar.vista.SeleccionarInstructor;
import com.ungs.formar.vista.SeleccionarResponsable;
import com.ungs.formar.vista.SeleccionarSala;

public class ControladorSeleccionarInstructor implements ActionListener {
	private SeleccionarInstructor ventanaSeleccionarInstructor;
	private ControladorCrearCurso controladorCrearCurso;
	private List<Empleado> instructores_en_tabla;

	public ControladorSeleccionarInstructor(SeleccionarInstructor ventanaSeleccionarInstructor,
			ControladorCrearCurso controladorCrearCurso) {
		this.ventanaSeleccionarInstructor = ventanaSeleccionarInstructor;
		this.controladorCrearCurso = controladorCrearCurso;
		this.ventanaSeleccionarInstructor.getBtnCancelar().addActionListener(this);
		this.ventanaSeleccionarInstructor.getBtnSeleccionar().addActionListener(this);
		this.ventanaSeleccionarInstructor.getBtnFiltrar().addActionListener(this);

	}

	public void inicializar() {
		this.ventanaSeleccionarInstructor.setVisible(true);
		llenarComboFiltrarPor();
		llenarComboOrdenarPor();
		llenarTablaInstructores();
	}

	private void llenarComboOrdenarPor() {
		JComboBox<String> ordenarPor = this.ventanaSeleccionarInstructor.getComboOrdenarPor();
		ordenarPor.addItem("Seleccionar");
		ordenarPor.addItem("Nombre a-z");
		ordenarPor.addItem("Nombre z-a");
		ordenarPor.addItem("Apellido a-z");
		ordenarPor.addItem("Apellido z-a");
		ordenarPor.addItem("DNI ascendente");
		ordenarPor.addItem("DNI descendente");
	}

	private void llenarComboFiltrarPor() {
		JComboBox<String> filtrarPor = this.ventanaSeleccionarInstructor.getComboFiltrar();
		filtrarPor.addItem("Seleccionar");
		filtrarPor.addItem("Nombre");
		filtrarPor.addItem("Apellido");
		filtrarPor.addItem("DNI");
	}

	private void llenarTablaInstructores() {

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventanaSeleccionarInstructor.getBtnSeleccionar()) {
			int fila_seleccionada = this.ventanaSeleccionarInstructor.getTablaInstructores().getSelectedRow();
			if (fila_seleccionada != -1) {
				// this.controladorCrearCurso.setInstructor(//INSTRUCTOR EN FILA
				// SELECCIONADA);
			}

		} else if (e.getSource() == ventanaSeleccionarInstructor.getBtnCancelar()) {
			this.ventanaSeleccionarInstructor.dispose();
			this.controladorCrearCurso.inicializar();
		} else if (e.getSource() == ventanaSeleccionarInstructor.getBtnFiltrar()) {
			if (this.ventanaSeleccionarInstructor.getComboFiltrar().getSelectedIndex() != 0) {
				if (this.ventanaSeleccionarInstructor.getComboFiltrar().getSelectedIndex() == 1) {
					// this.instructores_en_tabla = obtener por nombre
					// de lo ingresasdo en
					// this.ventanaSeleccionarInstructor.getTxtFiltro().getText();

				} else if (this.ventanaSeleccionarInstructor.getComboFiltrar().getSelectedIndex() == 2) {
					// this.instructores_en_tabla = obtener por Apellido
					// de lo ingresasdo en
					// this.ventanaSeleccionarInstructor.getTxtFiltro().getText();
				} else if (this.ventanaSeleccionarInstructor.getComboFiltrar().getSelectedIndex() == 3) {
					// this.instructores_en_tabla = obtener por DNI
					// de lo ingresasdo en
					// this.ventanaSeleccionarInstructor.getTxtFiltro().getText();
				}
			}
			if (this.ventanaSeleccionarInstructor.getComboOrdenarPor().getSelectedIndex() != 0) {
				if (this.ventanaSeleccionarInstructor.getComboOrdenarPor().getSelectedIndex() == 1) {
					// Ordenar los INSTRUCTORES por Nombre ascendente
					Collections.sort(this.instructores_en_tabla, new Comparator<Empleado>() {
						public int compare(Empleado obj1, Empleado obj2) {
							return obj1.getNombre().compareTo(obj2.getNombre());
						}
					});
				} else if (this.ventanaSeleccionarInstructor.getComboOrdenarPor().getSelectedIndex() == 2) {
					// Ordenar los INSTRUCTORES por Nombre Descendente
				} else if (this.ventanaSeleccionarInstructor.getComboOrdenarPor().getSelectedIndex() == 3) {
					// Ordenar los INSTRUCTORES por Apellido ascendente
					Collections.sort(this.instructores_en_tabla, new Comparator<Empleado>() {
						public int compare(Empleado obj1, Empleado obj2) {
							return obj1.getApellido().compareTo(obj2.getApellido());
						}
					});
				} else if (this.ventanaSeleccionarInstructor.getComboOrdenarPor().getSelectedIndex() == 4) {
					// Ordenar los INSTRUCTORES por Apellido Descendente
				} else if (this.ventanaSeleccionarInstructor.getComboOrdenarPor().getSelectedIndex() == 5) {
					// Ordenar los INSTRUCTORES por DNI ascendente
					Collections.sort(this.instructores_en_tabla, new Comparator<Empleado>() {
						public int compare(Empleado obj1, Empleado obj2) {
							return obj1.getDNI().compareTo(obj2.getDNI());
						}
					});
				} else if (this.ventanaSeleccionarInstructor.getComboOrdenarPor().getSelectedIndex() == 6) {
					// Ordenar los INSTRUCTORES por DNI Descendente
				}
			}
			this.llenarTablaInstructores();
		}

	}
}