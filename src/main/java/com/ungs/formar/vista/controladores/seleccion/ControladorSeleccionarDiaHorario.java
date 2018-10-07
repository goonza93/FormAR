package com.ungs.formar.vista.controladores.seleccion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JComboBox;

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
	private List<Horario> horarios_en_tabla;
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
			//this.ventanaAltaModifDiaHorario.setVisible(false);
			//HorarioManager.crearHorario();
			//ControladorCrearCurso.addHorario();
			//this.controladorCrearCurso.inicializar();
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