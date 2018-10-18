package com.ungs.formar.vista.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.ungs.formar.negocios.DiaManager;
import com.ungs.formar.negocios.HorarioCursadaManager;
import com.ungs.formar.negocios.SalaManager;
import com.ungs.formar.persistencia.entidades.Dia;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import com.ungs.formar.persistencia.entidades.Sala;
import com.ungs.formar.vista.controladores.seleccion.ControladorSeleccionarSala;
import com.ungs.formar.vista.gestion.cursos.ControladorCrearCurso;
import com.ungs.formar.vista.ventanas.ABMHorario;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarSala;

public class ControladorAgregarHorario implements ActionListener {
	private ControladorCrearCurso controladorCrearCurso;
	private ABMHorario ventanaAgregarHorario;
	private Sala sala;
	private SeleccionarSala ventanaSeleccionarSala;
	public boolean esEdicion = false;
	public int indiceHorarioEdicion = -1;

	public ControladorAgregarHorario(ABMHorario ventanaAltaModifDiaHorario,
			ControladorCrearCurso controladorCrearCurso) {
		this.ventanaAgregarHorario = ventanaAltaModifDiaHorario;
		this.controladorCrearCurso = controladorCrearCurso;
		this.ventanaAgregarHorario.getBtnAgregar().addActionListener(this);
		this.ventanaAgregarHorario.getBtnCancelar().addActionListener(this);
		this.ventanaAgregarHorario.getBtnSeleccionarSala().addActionListener(this);
		this.inicializar();
	}

	public void inicializar() {
		this.ventanaAgregarHorario.setVisible(true);
		this.ventanaAgregarHorario.setEnabled(true);
	}

	public void actionPerformed(ActionEvent e) {

		// BOTON SELECCIONAR SALA
		if (e.getSource() == this.ventanaAgregarHorario.getBtnSeleccionarSala()) {
			seleccionarSala();

			// BOTON CANCELAR
		} else if (e.getSource() == this.ventanaAgregarHorario.getBtnCancelar()) {
			this.ventanaAgregarHorario.dispose();
			this.controladorCrearCurso.inicializar();

			// BOTON AGREGAR HORARIO
		} else if (e.getSource() == ventanaAgregarHorario.getBtnAgregar()) {
			validarAgregarHorario();
		}
	}

	private void seleccionarSala() {

		String msjError = obtenerMsjError();
		// Si tengo horarios cargados y estan correctamente Dejo seleccionar
		// sala
		if (msjError.isEmpty()) {
			this.ventanaSeleccionarSala = new SeleccionarSala();
			ControladorSeleccionarSala controlador = new ControladorSeleccionarSala(ventanaSeleccionarSala, this);

			Horario horarioIngresado = crearHorarioIngresado();
			controlador.setHorarioIngresado(horarioIngresado);
			controlador.inicializar();
			this.ventanaAgregarHorario.setEnabled(false);
		} else {
			JOptionPane.showMessageDialog(null, msjError);
		}
	}

	private Horario crearHorarioIngresado() {
		// CONSIGO LOS DATOS PARA CREAR UN HORARIO
		JComboBox<Dia> inDia = ventanaAgregarHorario.getComboDias();
		Dia dia = (Dia) inDia.getSelectedItem();

		JTextField inHoraInicio = ventanaAgregarHorario.getTxtHorasInicio();
		Integer horaInicio = Integer.decode(inHoraInicio.getText());

		JTextField inHoraFin = ventanaAgregarHorario.getTxtHorasFin();
		Integer horaFin = Integer.parseInt(inHoraFin.getText());

		String minI = this.ventanaAgregarHorario.getTxtMinutosInicio().getText();
		String minF = this.ventanaAgregarHorario.getTxtMinutosFin().getText();
		if (minI.isEmpty()) {
			minI += "0";
		}
		if (minF.isEmpty()) {
			minF += "0";
		}

		Integer minutoInicio = Integer.decode(minI);

		Integer minutoFin = Integer.decode(minF);
		// CREO EL HORARIO PARA VALIDAR CON LA SALA QUE ELIJA
		return new Horario(-1, dia.getDiaID(), horaInicio, horaFin, minutoInicio, minutoFin);
	}

	private void agregarHorario(String minI, String minF) {
		// CONSIGO LOS DATOS PARA CREAR UN HORARIO
		JComboBox<Dia> inDia = ventanaAgregarHorario.getComboDias();
		Dia dia = (Dia) inDia.getSelectedItem();

		JTextField inHoraInicio = ventanaAgregarHorario.getTxtHorasInicio();
		Integer horaInicio = Integer.decode(inHoraInicio.getText());

		JTextField inHoraFin = ventanaAgregarHorario.getTxtHorasFin();
		Integer horaFin = Integer.parseInt(inHoraFin.getText());

		Integer minutoInicio = Integer.decode(minI);

		Integer minutoFin = Integer.decode(minF);
		// GUARDO EL HORARIO EN LA BD Y OBTENGO EL ID
		Integer horarioID = HorarioCursadaManager.crearHorario(dia, horaInicio, horaFin, minutoInicio, minutoFin);

		// CREO EL HORARIO CURSADA PERO LO MANTENGO SI SUBIR AUN
		Integer salaId = null;
		if(sala!=null){
			salaId = sala.getSalaID();
		}
		HorarioCursada horarioCursada = new HorarioCursada(-1, -1, horarioID, salaId);
		if (controladorCrearCurso.agregarHorarioDeCursada(horarioCursada)) {
			controladorCrearCurso.actualizarFechaFin();
			ventanaAgregarHorario.dispose();
			controladorCrearCurso.inicializar();
		}
	}

	private void actualizarHorario(String minI, String minF) {
		HorarioCursada horarioCursadaEdicion = controladorCrearCurso.getHorarioCursada(this.indiceHorarioEdicion);
		Horario horarioEdicion = HorarioCursadaManager.traerHorarioSegunID(horarioCursadaEdicion.getHorario());

		// CONSIGO LOS DATOS PARA CREAR UN HORARIO
		JComboBox<Dia> inDia = ventanaAgregarHorario.getComboDias();
		Dia dia = (Dia) inDia.getSelectedItem();

		JTextField inHoraInicio = ventanaAgregarHorario.getTxtHorasInicio();
		Integer horaInicio = Integer.decode(inHoraInicio.getText());

		JTextField inHoraFin = ventanaAgregarHorario.getTxtHorasFin();
		Integer horaFin = Integer.parseInt(inHoraFin.getText());

		Integer minutoInicio = Integer.decode(minI);

		Integer minutoFin = Integer.decode(minF);

		horarioEdicion.setDia(dia.getDiaID());
		horarioEdicion.setHoraInicio(horaInicio);
		horarioEdicion.setHoraFin(horaFin);
		horarioEdicion.setMinutoInicio(minutoInicio);
		horarioEdicion.setMinutoFin(minutoFin);

		HorarioCursadaManager.actualizarHorario(horarioEdicion);

		controladorCrearCurso.actualizarHorarioCursada(indiceHorarioEdicion, horarioCursadaEdicion);
		controladorCrearCurso.actualizarFechaFin();
		ventanaAgregarHorario.dispose();
		controladorCrearCurso.inicializar();
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala seleccion) {
		sala = seleccion;
		if(sala!= null)
			ventanaAgregarHorario.getTxtSala().setText(sala.getNumero().toString());
	}

	private void validarAgregarHorario() {
		String msjError = obtenerMsjError();

		String minI = this.ventanaAgregarHorario.getTxtMinutosInicio().getText();
		String minF = this.ventanaAgregarHorario.getTxtMinutosFin().getText();
		if (minI.isEmpty()) {
			minI += "0";
		}
		if (minF.isEmpty()) {
			minF += "0";
		}

		if (msjError.isEmpty()) {
			// PASO LAS VALIDACIONES ASI QUE EL HORARIO SE AGREGA
			// Si estoy editando, actualizo el horario, sino lo agrego
			if (this.esEdicion) {
				actualizarHorario(minI, minF);
			} else {
				agregarHorario(minI, minF);
			}
		} else {
			JOptionPane.showMessageDialog(null, msjError);
		}
	}

	private String obtenerMsjError() {
		String minI = this.ventanaAgregarHorario.getTxtMinutosInicio().getText();
		String minF = this.ventanaAgregarHorario.getTxtMinutosFin().getText();
		if (minI.isEmpty()) {
			minI += "0";
		}
		if (minF.isEmpty()) {
			minF += "0";
		}

		Pattern patronNumeros = Pattern.compile("^[0-9]*$");

		Matcher mHoraInicio = patronNumeros.matcher(this.ventanaAgregarHorario.getTxtHorasInicio().getText());
		Matcher mMinutosInicio = patronNumeros.matcher(minI);
		Matcher mHoraFin = patronNumeros.matcher(this.ventanaAgregarHorario.getTxtHorasFin().getText());
		Matcher mMinutosFin = patronNumeros.matcher(minF);

		String msjError = "";
		// Validaciones Hora inicio
		if (!mHoraInicio.matches() || this.ventanaAgregarHorario.getTxtHorasInicio().getText().isEmpty()
				|| Integer.parseInt(this.ventanaAgregarHorario.getTxtHorasInicio().getText()) > 23) {
			msjError += "- Por favor, ingrese una hora de inicio valida\n";
		}
		// validaciones minutos inicio
		if (!mMinutosInicio.matches() || Integer.parseInt(minI) > 59) {
			msjError += "- Por favor, ingrese minutos de inicio validos\n";
		}
		// validaciones hora fin
		if (!mHoraFin.matches() || this.ventanaAgregarHorario.getTxtHorasFin().getText().isEmpty()
				|| Integer.parseInt(this.ventanaAgregarHorario.getTxtHorasFin().getText()) > 23) {
			msjError += "- Por favor, ingrese una hora de fin valida\n";
		}
		// validaciones minutos fin
		if (!mMinutosFin.matches() || Integer.parseInt(minF) > 59) {
			msjError += "- Por favor, ingrese minutos de fin validos\n";
		}
		// validaciones entre hora inicio y hora fin
		if ((Integer.parseInt(this.ventanaAgregarHorario.getTxtHorasInicio().getText()) > Integer
				.parseInt(this.ventanaAgregarHorario.getTxtHorasFin().getText()))
				|| (Integer.parseInt(this.ventanaAgregarHorario.getTxtHorasInicio().getText()) == Integer
						.parseInt(this.ventanaAgregarHorario.getTxtHorasFin().getText())
						&& Integer.parseInt(minI) >= Integer.parseInt(minF))) {
			msjError += "- Por favor, ingrese nuevamente los horarios. El horario fin debe"
					+ " \nser posterior al horario de inicio\n";
		}
		// Validaciones disponibilidad de la sala en el horario
		if (!this.esEdicion && this.sala != null && !SalaManager.validarHorarioDeCursada(crearHorarioIngresado(), this.sala)) {
			msjError += "- La sala no esta disponible en el horario ingresado, seleccione otra sala.\n";
		}
		return msjError;
	}

}