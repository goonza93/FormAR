package com.ungs.formar.vista.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.ungs.formar.negocios.HorarioCursadaManager;
import com.ungs.formar.persistencia.entidades.Dia;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import com.ungs.formar.persistencia.entidades.Sala;
import com.ungs.formar.vista.controladores.seleccion.ControladorSeleccionarSala;
import com.ungs.formar.vista.ventanas.ABMHorario;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarSala;

public class ControladorAgregarHorario implements ActionListener {
	private ControladorCrearCurso controladorCrearCurso;
	private ABMHorario ventanaAgregarHorario;
	private Sala sala;
	private SeleccionarSala ventanaSeleccionarSala;

	public ControladorAgregarHorario(ABMHorario ventanaAltaModifDiaHorario, ControladorCrearCurso controladorCrearCurso) {
		this.ventanaAgregarHorario = ventanaAltaModifDiaHorario;
		this.controladorCrearCurso = controladorCrearCurso;
		this.ventanaAgregarHorario.getBtnAgregar().addActionListener(this);
		this.ventanaAgregarHorario.getBtnCancelar().addActionListener(this);
		this.ventanaAgregarHorario.getBtnSeleccionarSala().addActionListener(this);
		this.inicializar();
	}

	public void inicializar() {
		this.ventanaAgregarHorario.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
		// BOTON SELECCIONAR SALA
		if (e.getSource() == this.ventanaAgregarHorario.getBtnSeleccionarSala()) {
			this.ventanaSeleccionarSala = new SeleccionarSala();
			new ControladorSeleccionarSala(ventanaSeleccionarSala, this);
			this.ventanaAgregarHorario.setVisible(false);

		// BOTON CANCELAR
		} else if (e.getSource() == this.ventanaAgregarHorario.getBtnCancelar()) {
			this.ventanaAgregarHorario.dispose();
			this.controladorCrearCurso.inicializar();
		
		// BOTON AGREGAR HORARIO		
		} else if (e.getSource() == ventanaAgregarHorario.getBtnAgregar()) {

			Pattern patronNumeros = Pattern.compile("^[0-9]*$");

			Matcher mHoraInicio = patronNumeros.matcher(this.ventanaAgregarHorario.getTxtHorasInicio().getText());
			Matcher mMinutosInicio = patronNumeros
					.matcher(this.ventanaAgregarHorario.getTxtMinutosInicio().getText());
			Matcher mHoraFin = patronNumeros.matcher(this.ventanaAgregarHorario.getTxtHorasFin().getText());
			Matcher mMinutosFin = patronNumeros.matcher(this.ventanaAgregarHorario.getTxtMinutosFin().getText());

			// Validaciones de tipo y null
			if (!mHoraInicio.matches() || this.ventanaAgregarHorario.getTxtHorasInicio().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese una hora de inicio valida");
			} else if (!mMinutosInicio.matches()
					|| this.ventanaAgregarHorario.getTxtMinutosInicio().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese minutos de inicio validos");
			} else if (!mHoraFin.matches() || this.ventanaAgregarHorario.getTxtHorasFin().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese una hora de fin valida");
			} else if (!mMinutosFin.matches()
					|| this.ventanaAgregarHorario.getTxtMinutosFin().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese minutos de fin validos");
			}else if (this.sala == null) {
				JOptionPane.showMessageDialog(null, "Por favor, seleccione una sala");
			}

			// Validaciones Logicas
			else if (Integer.parseInt(this.ventanaAgregarHorario.getTxtMinutosFin().getText()) > 59) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese minutos de fin validos");
			} else if (Integer.parseInt(this.ventanaAgregarHorario.getTxtMinutosInicio().getText()) > 59) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese minutos de inicio validos");
			} else if (Integer.parseInt(this.ventanaAgregarHorario.getTxtHorasInicio().getText()) > 23) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese una hora de inicio valida");
			} else if (Integer.parseInt(this.ventanaAgregarHorario.getTxtHorasFin().getText()) > 23) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese una hora de fin valida");
			} else if ((Integer.parseInt(this.ventanaAgregarHorario.getTxtHorasInicio().getText()) > 
				Integer.parseInt(this.ventanaAgregarHorario.getTxtHorasFin().getText())) 
					|| 
					(Integer.parseInt(this.ventanaAgregarHorario.getTxtHorasInicio().getText()) ==
							Integer.parseInt(this.ventanaAgregarHorario.getTxtHorasFin().getText()) 
							&& 
							Integer.parseInt(this.ventanaAgregarHorario.getTxtMinutosInicio().getText()) >=
							Integer.parseInt(this.ventanaAgregarHorario.getTxtMinutosFin().getText()))) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese nuevamente los horarios. El horario fin debe"
						+ " \nser posterior al horario de inicio");
			}else {
				// PASO LAS VALIDACIONES ASI QUE EL HORARIO SE AGREGA
				agregarHorario();
			}
		}

	}

	private void agregarHorario() {
		// CONSIGO LOS DATOS PARA CREAR UN HORARIO
		JComboBox<Dia> inDia = ventanaAgregarHorario.getComboDias();
		Dia dia = (Dia) inDia.getSelectedItem();
		
		JTextField inHoraInicio = ventanaAgregarHorario.getTxtHorasInicio();
		Integer horaInicio = Integer.decode(inHoraInicio.getText());
		
		JTextField inHoraFin = ventanaAgregarHorario.getTxtHorasFin();
		Integer horaFin = Integer.decode(inHoraFin.getText());

		JTextField inMinutoInicio = ventanaAgregarHorario.getTxtMinutosInicio();
		Integer minutoInicio = Integer.decode(inMinutoInicio.getText());

		JTextField inMinutoFin = ventanaAgregarHorario.getTxtMinutosFin();
		Integer minutoFin = Integer.decode(inMinutoFin.getText());
		
		// GUARDO EL HORARIO EN LA BD Y OBTENGO EL ID
		Integer horarioID = HorarioCursadaManager.crearHorario(dia, horaInicio, horaFin, minutoInicio, minutoFin);
		
		// CREO EL HORARIO CURSADA PERO LO MANTENGO SI SUBIR AUN
		HorarioCursada horarioCursada = new HorarioCursada(-1, -1, horarioID, sala.getSalaID());
		controladorCrearCurso.agregarHorarioDeCursada(horarioCursada);
		controladorCrearCurso.actualizarFechaFin();
		ventanaAgregarHorario.dispose();
		controladorCrearCurso.inicializar();
	}
		
	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala seleccion) {
		sala = seleccion;
		ventanaAgregarHorario.getTxtSala().setText(sala.getNumero().toString());
	}

}