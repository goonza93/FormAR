package com.ungs.formar.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.ungs.formar.vista.CrearCurso;
import com.ungs.formar.vista.SeleccionarDiaHorario;
import com.ungs.formar.vista.SeleccionarInstructor;
import com.ungs.formar.vista.SeleccionarPrograma;
import com.ungs.formar.vista.SeleccionarResponsable;
import com.ungs.formar.vista.SeleccionarSala;

public class ControladorCrearCurso implements ActionListener {
	private CrearCurso ventanaCrearCurso;
	private SeleccionarInstructor ventanaSeleccionarInstructor;
	private SeleccionarPrograma ventanaSeleccionarPrograma;
	private SeleccionarResponsable ventanaSeleccionarResponsable;
	private SeleccionarSala ventanaSeleccionarSala;
	private SeleccionarDiaHorario ventanaSeleccionarDiaHorario;
	private ControladorGestionarCurso controladorGestionarCurso;

	public ControladorCrearCurso(CrearCurso ventanaCrearCurso, ControladorGestionarCurso controladorGestionarCurso) {
		this.ventanaCrearCurso = ventanaCrearCurso;
		this.ventanaCrearCurso.getBtnAgregar().addActionListener(this);
		this.ventanaCrearCurso.getBtnCancelar().addActionListener(this);
		this.ventanaCrearCurso.getBtnSeleccionarInstructor().addActionListener(this);
		this.ventanaCrearCurso.getBtnSeleccionarPrograma().addActionListener(this);
		this.ventanaCrearCurso.getBtnSeleccionarResponsable().addActionListener(this);
		this.ventanaCrearCurso.getBtnSeleccionarSala().addActionListener(this);
		this.ventanaCrearCurso.getBtnAgregarDia().addActionListener(this);
		this.ventanaCrearCurso.getBtnBorrarDia().addActionListener(this);
		this.controladorGestionarCurso = controladorGestionarCurso;
	}

	public void inicializar() {
		this.ventanaCrearCurso.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventanaCrearCurso.getBtnAgregar()) {

			Pattern patronLetras = Pattern.compile("^[a-zA-Z ]*$");
			Pattern patronNumeros = Pattern.compile("^[0-9]*$");
			Pattern patronLetrasNumeros = Pattern.compile("^[a-zA-Z0-9 ]*$");

			Matcher cupoMinimo = patronNumeros.matcher(this.ventanaCrearCurso.getTxtCupoMinimo().getText());
			Matcher cupoMaximo = patronNumeros.matcher(this.ventanaCrearCurso.getTxtCupoMaximo().getText());
			Matcher clasesTotal = patronNumeros.matcher(this.ventanaCrearCurso.getTxtCantidadTotalClases().getText());
			Matcher horasTotal = patronNumeros.matcher(this.ventanaCrearCurso.getTxtHorasTotalesClases().getText());

			if (!cupoMinimo.find()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese un cupo minimo valido");
			} else if (!cupoMaximo.find()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese un cupo maximo valido");
			} else if (this.ventanaCrearCurso.getDateFechaInicio().getDate() == null) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese una fecha de inicio valida");
			} else if (this.ventanaCrearCurso.getDateFechaFin().getDate() == null) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese una fecha de fin valida");
			} else if (!clasesTotal.find()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese una cantidad total de clases valida");
			} else if (!horasTotal.find()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese una cantidad total de horas valida");
			} else if (this.ventanaCrearCurso.getInstructor() == null) {
				JOptionPane.showMessageDialog(null, "Por favor, seleccione un instructor");
			}
			else if (this.ventanaCrearCurso.getSala() == null){
				JOptionPane.showMessageDialog(null, "Por favor, seleccione una sala");
			}
			else if (this.ventanaCrearCurso.getPrograma() == null){
				JOptionPane.showMessageDialog(null, "Por favor,  seleccione un programa");
			}
			else if (this.ventanaCrearCurso.getResponsable() == null){
				JOptionPane.showMessageDialog(null, "Por favor, seleccione un responsable");
			}
			else if (this.ventanaCrearCurso.getHorariosCursada().length == 0){
				JOptionPane.showMessageDialog(null, "Por favor, seleccione un horario de cursada");
			}

			if (Integer.parseInt(this.ventanaCrearCurso.getTxtCupoMaximo().getText()) < Integer
					.parseInt(this.ventanaCrearCurso.getTxtCupoMinimo().getText())) {

			}
		} else if (e.getSource() == ventanaCrearCurso.getBtnCancelar()) {
			this.ventanaCrearCurso.dispose();
			this.controladorGestionarCurso.inicializar();
		} else if (e.getSource() == ventanaCrearCurso.getBtnSeleccionarInstructor()) {
			this.ventanaSeleccionarInstructor = new SeleccionarInstructor();
			this.ventanaSeleccionarInstructor.setVisible(true);
			this.ventanaCrearCurso.setVisible(false);
			new ControladorSeleccionarInstructor(this.ventanaSeleccionarInstructor, this);
		} else if (e.getSource() == ventanaCrearCurso.getBtnSeleccionarPrograma()) {
			this.ventanaSeleccionarPrograma = new SeleccionarPrograma();
			this.ventanaSeleccionarPrograma.setVisible(true);
			this.ventanaCrearCurso.setVisible(false);
			new ControladorSeleccionarPrograma(this.ventanaSeleccionarPrograma, this);
		} else if (e.getSource() == ventanaCrearCurso.getBtnSeleccionarResponsable()) {
			this.ventanaSeleccionarResponsable = new SeleccionarResponsable();
			this.ventanaSeleccionarResponsable.setVisible(true);
			this.ventanaCrearCurso.setVisible(false);
			new ControladorSeleccionarResponsable(this.ventanaSeleccionarResponsable, this);
		} else if (e.getSource() == ventanaCrearCurso.getBtnSeleccionarSala()) {
			this.ventanaSeleccionarSala = new SeleccionarSala();
			this.ventanaSeleccionarSala.setVisible(true);
			this.ventanaCrearCurso.setVisible(false);
			new ControladorSeleccionarSala(this.ventanaSeleccionarSala, this);
		} else if (e.getSource() == ventanaCrearCurso.getBtnAgregarDia()) {
			this.ventanaSeleccionarDiaHorario = new SeleccionarDiaHorario();
			this.ventanaSeleccionarDiaHorario.setVisible(true);
			this.ventanaCrearCurso.setVisible(false);
			new ControladorSeleccionarDiaHorario(this.ventanaSeleccionarDiaHorario, this);
		} else if (e.getSource() == ventanaCrearCurso.getBtnBorrarDia()) {
			int[] filas_seleccionadas = this.ventanaCrearCurso.getTablaDiasHorarios().getSelectedRows();
			for (int fila : filas_seleccionadas) {
				// this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
			}
			// this.llenarTablaDiasHorarios();
		}
	}
}