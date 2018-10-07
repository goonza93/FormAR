package com.ungs.formar.vista.controladores.seleccion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.ungs.formar.negocios.CursoManager;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.persistencia.entidades.Sala;
import com.ungs.formar.vista.controladores.ControladorCrearCurso;
import com.ungs.formar.vista.ventanas.ABMHorario;
import com.ungs.formar.vista.ventanas.CrearCurso;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarDiaHorario;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarInstructor;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarResponsable;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarSala;

public class ControladorSeleccionarDiaHorario implements ActionListener {
	private ControladorCrearCurso controladorCrearCurso;
	private ABMHorario ventanaAltaModifDiaHorario;
	private Sala sala;
	private SeleccionarSala ventanaSeleccionarSala;
	private ControladorSeleccionarSala controladorSeleccionarSala;

	public ControladorSeleccionarDiaHorario(ABMHorario ventanaAltaModifDiaHorario,
			ControladorCrearCurso controladorCrearCurso) {
		this.ventanaAltaModifDiaHorario = ventanaAltaModifDiaHorario;
		this.controladorCrearCurso = controladorCrearCurso;
		this.ventanaAltaModifDiaHorario.getBtnAgregar().addActionListener(this);
		this.ventanaAltaModifDiaHorario.getBtnCancelar().addActionListener(this);
		this.ventanaAltaModifDiaHorario.getBtnSeleccionarSala().addActionListener(this);
		this.inicializar();
	}

	public void inicializar() {
		this.ventanaAltaModifDiaHorario.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.ventanaAltaModifDiaHorario.getBtnSeleccionarSala()) {
			this.ventanaSeleccionarSala = new SeleccionarSala();
			this.controladorSeleccionarSala = new ControladorSeleccionarSala(ventanaSeleccionarSala, this);
			this.ventanaAltaModifDiaHorario.setVisible(false);

		} else if (e.getSource() == this.ventanaAltaModifDiaHorario.getBtnCancelar()) {
			this.ventanaAltaModifDiaHorario.dispose();
			this.controladorCrearCurso.inicializar();
		} else if (e.getSource() == ventanaAltaModifDiaHorario.getBtnAgregar()) {

			Pattern patronNumeros = Pattern.compile("^[0-9]*$");

			Matcher mHoraInicio = patronNumeros.matcher(this.ventanaAltaModifDiaHorario.getTxtHorasInicio().getText());
			Matcher mMinutosInicio = patronNumeros
					.matcher(this.ventanaAltaModifDiaHorario.getTxtMinutosInicio().getText());
			Matcher mHoraFin = patronNumeros.matcher(this.ventanaAltaModifDiaHorario.getTxtHorasFin().getText());
			Matcher mMinutosFin = patronNumeros.matcher(this.ventanaAltaModifDiaHorario.getTxtMinutosFin().getText());

			// Validaciones de tipo y null
			if (!mHoraInicio.matches() || this.ventanaAltaModifDiaHorario.getTxtHorasInicio().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese una hora de inicio valida");
			} else if (!mMinutosInicio.matches()
					|| this.ventanaAltaModifDiaHorario.getTxtMinutosInicio().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese minutos de inicio validos");
			} else if (!mHoraFin.matches() || this.ventanaAltaModifDiaHorario.getTxtHorasFin().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese una hora de fin valida");
			} else if (!mMinutosFin.matches()
					|| this.ventanaAltaModifDiaHorario.getTxtMinutosFin().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese minutos de fin validos");
			}

			// Validaciones Logicas
			else if (Integer.parseInt(this.ventanaAltaModifDiaHorario.getTxtMinutosFin().getText()) > 59) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese minutos de fin validos");
			} else if (Integer.parseInt(this.ventanaAltaModifDiaHorario.getTxtMinutosInicio().getText()) > 59) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese minutos de inicio validos");
			} else if (Integer.parseInt(this.ventanaAltaModifDiaHorario.getTxtHorasInicio().getText()) > 23) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese una hora de inicio valida");
			} else if (Integer.parseInt(this.ventanaAltaModifDiaHorario.getTxtHorasFin().getText()) > 23) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese una hora de fin valida");
			} else {
				// this.ventanaAltaModifDiaHorario.setVisible(false);
				// HorarioManager.crearHorario();
				// ControladorCrearCurso.addHorario();
				// this.controladorCrearCurso.inicializar();
			}
		}

	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
		this.ventanaAltaModifDiaHorario.getTxtSala().setText(sala.getNumero().toString());
	}

}