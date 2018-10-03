package com.ungs.formar.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.persistencia.entidades.Sala;
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
	private Empleado instructor;
	private Empleado responsable;
	private Sala sala;
	private Programa programa;
	private List<HorarioCursada> horariosCursada;

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

	private boolean estaDisponibleSala() {
		for (HorarioCursada horario : this.horariosCursada) {
			// Horario horaDia = obtenerHorario por el ID;
			// if(this.ventanaCrearCurso.getSala() esta asignada a otro curso en
			// alguno de TODOS los dias y horarios elegidos){
			// JOptionPane.showMessageDialog(null, "La sala
			// "+this.ventanaCrearCurso.getSala().getNombre()+" numero"+
			// this.ventanaCrearCurso.getSala().getNumero()+
			// " no esta disponible el dia "+ horaDia.getDia() + " en el horario
			// seleccionado");
			// return false;
			// }
		}
		return true;
	}

	private void llenarTablaDiasHorarios() {

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

			// Validaciones de null, y de tipos de datos validos
			if (!cupoMinimo.matches() || this.ventanaCrearCurso.getTxtCupoMinimo().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese un cupo minimo valido");
			} else if (!cupoMaximo.matches() || this.ventanaCrearCurso.getTxtCupoMaximo().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese un cupo maximo valido");
			} else if (this.ventanaCrearCurso.getDateFechaInicio().getDate() == null) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese una fecha de inicio valida");
			} else if (this.ventanaCrearCurso.getDateFechaFin().getDate() == null) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese una fecha de fin valida");
			} else if (!clasesTotal.matches()
					|| this.ventanaCrearCurso.getTxtCantidadTotalClases().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese una cantidad total de clases valida");
			} else if (!horasTotal.matches() || this.ventanaCrearCurso.getTxtHorasTotalesClases().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese una cantidad total de horas valida");
			} else if (this.instructor == null) {
				JOptionPane.showMessageDialog(null, "Por favor, seleccione un instructor");
			} else if (this.sala == null) {
				JOptionPane.showMessageDialog(null, "Por favor, seleccione una sala");
			} else if (this.programa == null) {
				JOptionPane.showMessageDialog(null, "Por favor,  seleccione un programa");
			} else if (this.responsable == null) {
				JOptionPane.showMessageDialog(null, "Por favor, seleccione un responsable");
			} else if (this.horariosCursada.size() == 0) {
				JOptionPane.showMessageDialog(null, "Por favor, seleccione un horario de cursada");
			}

			// validaciones logicas
			else if (Integer.parseInt(this.ventanaCrearCurso.getTxtCupoMaximo().getText()) < Integer
					.parseInt(this.ventanaCrearCurso.getTxtCupoMinimo().getText())) {
				JOptionPane.showMessageDialog(null, "El cupo maximo no puede ser menor que el cupo minimo");
			} else if (this.ventanaCrearCurso.getDateFechaFin().getDate()
					.before(this.ventanaCrearCurso.getDateFechaInicio().getDate())) {
				JOptionPane.showMessageDialog(null, "La fecha de fin tiene que ser anterior a la fecha de inicio");
			} else if (this.ventanaCrearCurso.getDateFechaFin().getDate()
					.compareTo(this.ventanaCrearCurso.getDateFechaInicio().getDate()) == 0) {
				JOptionPane.showMessageDialog(null, "La fecha de fin no puede ser igual a la fecha de inicio");
			} else if (!estaDisponibleSala()) {

			} else if (Integer.parseInt(this.ventanaCrearCurso.getTxtCupoMaximo().getText()) > this.sala
					.getCapacidad()) {
				JOptionPane.showMessageDialog(null,
						"La capacidad maxima establecida es mayor a la capacidad de la sala seleccionada");
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
			int filas_seleccionadas = this.ventanaCrearCurso.getTablaDiasHorarios().getSelectedRow();
			// this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
			// this.llenarTablaDiasHorarios();
		}
	}

	public Empleado getInstructor() {
		return instructor;
	}

	public void setInstructor(Empleado instructor) {
		this.instructor = instructor;
		this.ventanaCrearCurso.getTxtInstructor()
				.setText(this.instructor.getNombre() + " " + this.instructor.getApellido());
	}

	public Empleado getResponsable() {
		return responsable;
	}

	public void setResponsable(Empleado responsable) {
		this.responsable = responsable;
		this.ventanaCrearCurso.getTxtResponsable()
				.setText(this.responsable.getNombre() + " " + this.responsable.getApellido());
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
		this.ventanaCrearCurso.getTxtSala().setText(this.sala.getNumero() + " " + this.sala.getNombre());
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
		this.ventanaCrearCurso.getTxtPrograma().setText(this.programa.getNombreMateria());
	}

	public List<HorarioCursada> getHorariosCursada() {
		return horariosCursada;
	}

	public void setHorariosCursada(HorarioCursada horarioCursada) {
		this.horariosCursada.add(horarioCursada);
		llenarTablaDiasHorarios();
	}

}