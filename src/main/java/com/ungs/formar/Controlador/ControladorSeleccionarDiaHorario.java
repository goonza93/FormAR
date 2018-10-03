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
		llenarComboOrdenarPor();
		llenarTablaDiaHorario();
	}

	private void llenarComboOrdenarPor() {
		JComboBox<String> ordenarPor = this.ventanaSeleccionarDiaHorario.getComboOrdenar();
		this.ventanaSeleccionarDiaHorario.getComboOrdenar().addItem("Seleccionar");
		this.ventanaSeleccionarDiaHorario.getComboOrdenar().addItem("Hora inicio ascendente");
		this.ventanaSeleccionarDiaHorario.getComboOrdenar().addItem("Hora inicio descendente");
		this.ventanaSeleccionarDiaHorario.getComboOrdenar().addItem("Hora fin ascendente");
		this.ventanaSeleccionarDiaHorario.getComboOrdenar().addItem("Hora fin descendente");
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
			if(fila_seleccionada != -1){
				//this.controladorCrearCurso.setHorariosCursada(//DIA HORARIO EN FILA SELECCIONADA);
			}
		} else if (e.getSource() == this.ventanaSeleccionarDiaHorario.getBtnCancelar()) {
			this.ventanaSeleccionarDiaHorario.dispose();
			this.controladorCrearCurso.inicializar();
		} else if (e.getSource() == this.ventanaSeleccionarDiaHorario.getBtnFiltrar()) {
			if (this.ventanaSeleccionarDiaHorario.getComboDia().getSelectedIndex() != 0) {
				List<Horario> horario;
				if (this.ventanaSeleccionarDiaHorario.getComboDia().getSelectedIndex() == 1) {
					// horario = obtenerHorarioPorDia LUNES;
				} else if (this.ventanaSeleccionarDiaHorario.getComboDia().getSelectedIndex() == 2) {
					// horario = obtenerHorarioPorDia MARTES;
				} else if (this.ventanaSeleccionarDiaHorario.getComboDia().getSelectedIndex() == 3) {
					// horario = obtenerHorarioPorDia MIERCOLES;
				} else if (this.ventanaSeleccionarDiaHorario.getComboDia().getSelectedIndex() == 4) {
					// horario = obtenerHorarioPorDia JUEVES;
				} else if (this.ventanaSeleccionarDiaHorario.getComboDia().getSelectedIndex() == 5) {
					// horario = obtenerHorarioPorDia VIERNES;
				} else if (this.ventanaSeleccionarDiaHorario.getComboDia().getSelectedIndex() == 6) {
					// horario = obtenerHorarioPorDia SABADO;
				}
			}
			if (this.ventanaSeleccionarDiaHorario.getComboOrdenar().getSelectedIndex() != 0) {
				if (this.ventanaSeleccionarDiaHorario.getComboOrdenar().getSelectedIndex() == 1) {
					// Ordenar los Dias Horarios por hora Inicio ascendente
					Collections.sort(this.horarios_en_tabla, new Comparator<Horario>() {
						public int compare(Horario obj1, Horario obj2) {
							return obj1.getHoraInicio().compareTo(obj2.getHoraInicio());
						}
					});
				} else if (this.ventanaSeleccionarDiaHorario.getComboOrdenar().getSelectedIndex() == 2) {
					// Ordenar los Dias Horarios por hora Inicio descendente

				} else if (this.ventanaSeleccionarDiaHorario.getComboOrdenar().getSelectedIndex() == 3) {
					// Ordenar los Dias Horarios por hora Fin ascendente
					Collections.sort(this.horarios_en_tabla, new Comparator<Horario>() {
						public int compare(Horario obj1, Horario obj2) {
							return obj1.getHoraFin().compareTo(obj2.getHoraFin());
						}
					});
				} else if (this.ventanaSeleccionarDiaHorario.getComboOrdenar().getSelectedIndex() == 4) {
					// Ordenar los Dias Horarios por hora Fin descendente

				}
			}
		} else if (e.getSource() == ventanaSeleccionarDiaHorario.getBtnAgregar()) {
			this.ventanaSeleccionarDiaHorario.setVisible(false);
			this.ventanaAltaModifDiaHorario = new AltaModifDiaHorario();
			this.ventanaAltaModifDiaHorario.setVisible(true);
			//new ControladorAltaModifDiaHorario(this.ventanaAltaModifDiaHorario,this);
		}

	}
}