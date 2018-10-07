package com.ungs.formar.vista.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

import com.ungs.formar.negocios.CursoManager;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.persistencia.entidades.Sala;
import com.ungs.formar.vista.controladores.seleccion.ControladorSeleccionarDiaHorario;
import com.ungs.formar.vista.controladores.seleccion.ControladorSeleccionarInstructor;
import com.ungs.formar.vista.controladores.seleccion.ControladorSeleccionarPrograma;
import com.ungs.formar.vista.controladores.seleccion.ControladorSeleccionarResponsable;
import com.ungs.formar.vista.controladores.seleccion.ControladorSeleccionarSala;
import com.ungs.formar.vista.ventanas.ABMHorario;
import com.ungs.formar.vista.ventanas.CrearCurso;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarDiaHorario;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarInstructor;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarPrograma;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarResponsable;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarSala;

public class ControladorCrearCurso implements ActionListener {
	private CrearCurso ventanaCrearCurso;
	private SeleccionarInstructor ventanaSeleccionarInstructor;
	private SeleccionarPrograma ventanaSeleccionarPrograma;
	private SeleccionarResponsable ventanaSeleccionarResponsable;
	private SeleccionarSala ventanaSeleccionarSala;
	private ABMHorario ventanaABMHorario;
	private ControladorGestionarCurso controladorGestionarCurso;
	private Empleado instructor;
	private Empleado responsable;
	private Programa programa;
	private List<HorarioCursada> horariosCursada;

	public ControladorCrearCurso(CrearCurso ventanaCrearCurso, ControladorGestionarCurso controladorGestionarCurso) {
		this.ventanaCrearCurso = ventanaCrearCurso;
		this.ventanaCrearCurso.getBtnAgregar().addActionListener(this);
		this.ventanaCrearCurso.getBtnCancelar().addActionListener(this);
		this.ventanaCrearCurso.getBtnSeleccionarInstructor().addActionListener(this);
		this.ventanaCrearCurso.getBtnSeleccionarPrograma().addActionListener(this);
		this.ventanaCrearCurso.getBtnSeleccionarResponsable().addActionListener(this);
		this.ventanaCrearCurso.getBtnAgregarDia().addActionListener(this);
		this.ventanaCrearCurso.getBtnBorrarDia().addActionListener(this);
		this.controladorGestionarCurso = controladorGestionarCurso;
	}

	public void inicializar() {
		llenarTablaDiasHorarios();
		this.ventanaCrearCurso.setVisible(true);
	}

	private void llenarTablaDiasHorarios() {

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventanaCrearCurso.getBtnAgregar()) {

			Pattern patronNumeros = Pattern.compile("^[0-9]*$");

			Matcher mcupoMinimo = patronNumeros.matcher(this.ventanaCrearCurso.getTxtCupoMinimo().getText());
			Matcher mcupoMaximo = patronNumeros.matcher(this.ventanaCrearCurso.getTxtCupoMaximo().getText());
			Matcher mhorasTotal = patronNumeros.matcher(this.ventanaCrearCurso.getTxtHorasTotalesClases().getText());

			// Validaciones de null, y de tipos de datos validos
			if (!mcupoMinimo.matches() || this.ventanaCrearCurso.getTxtCupoMinimo().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese un cupo minimo valido");
			} else if (!mcupoMaximo.matches() || this.ventanaCrearCurso.getTxtCupoMaximo().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese un cupo maximo valido");
			} else if (this.ventanaCrearCurso.getDateFechaInicio().getDate() == null) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese una fecha de inicio valida");
			} else if (!mhorasTotal.matches() || this.ventanaCrearCurso.getTxtHorasTotalesClases().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese una cantidad total de horas valida");
			} else if (this.instructor == null) {
				JOptionPane.showMessageDialog(null, "Por favor, seleccione un instructor");
			}else if (this.programa == null) {
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
			}
			else{
				Integer cupoMinimo = new Integer(this.ventanaCrearCurso.getTxtCupoMinimo().getText());
				Integer cupoMaximo = new Integer(this.ventanaCrearCurso.getTxtCupoMaximo().getText());
				Integer horas = new Integer(this.ventanaCrearCurso.getTxtHorasTotalesClases().getText());
				Integer estado = new Integer(1);
				Integer responsable = this.responsable.getEmpleadoID();
				Integer instructor = this.instructor.getEmpleadoID();
				Integer programa = this.programa.getProgramaID();
				String contenido = this.ventanaCrearCurso.getTxtProgramaEspecifico().getText();
				Date fechaInicio = this.ventanaCrearCurso.getDateFechaInicio().getDate();
				Date fechaFin = this.ventanaCrearCurso.getDateFechaFin().getDate();
				
				CursoManager.crearCurso(cupoMinimo, cupoMaximo, horas, estado, responsable, instructor, programa, contenido, 
						new java.sql.Date(fechaInicio.getTime()), new java.sql.Date(fechaFin.getTime()));
				this.ventanaCrearCurso.dispose();
				this.controladorGestionarCurso.inicializar();
			}

		} else if (e.getSource() == ventanaCrearCurso.getBtnCancelar()) {
			this.ventanaCrearCurso.dispose();
			this.controladorGestionarCurso.inicializar();
		} else if (e.getSource() == ventanaCrearCurso.getBtnSeleccionarInstructor()) {
			this.ventanaSeleccionarInstructor = new SeleccionarInstructor();
			this.ventanaCrearCurso.setVisible(false);
			new ControladorSeleccionarInstructor(this.ventanaSeleccionarInstructor, this);
		} else if (e.getSource() == ventanaCrearCurso.getBtnSeleccionarPrograma()) {
			this.ventanaSeleccionarPrograma = new SeleccionarPrograma();
			this.ventanaCrearCurso.setVisible(false);
			new ControladorSeleccionarPrograma(this.ventanaSeleccionarPrograma, this);
		} else if (e.getSource() == ventanaCrearCurso.getBtnSeleccionarResponsable()) {
			this.ventanaSeleccionarResponsable = new SeleccionarResponsable();
			this.ventanaCrearCurso.setVisible(false);
			new ControladorSeleccionarResponsable(this.ventanaSeleccionarResponsable, this);
		} else if (e.getSource() == ventanaCrearCurso.getBtnAgregarDia()) {
			
			Pattern patronNumeros = Pattern.compile("^[0-9]*$");
			Matcher cupoMaximo = patronNumeros.matcher(this.ventanaCrearCurso.getTxtCupoMaximo().getText());
			
			if (!cupoMaximo.matches() || this.ventanaCrearCurso.getTxtCupoMaximo().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, primero ingrese un cupo maximo");
			} else if (this.ventanaCrearCurso.getDateFechaInicio().getDate() == null) {
				JOptionPane.showMessageDialog(null, "Por favor, primero ingrese una fecha de inicio");
			} 
			else{
				this.ventanaABMHorario = new ABMHorario();
				this.ventanaCrearCurso.setVisible(false);
				new ControladorSeleccionarDiaHorario(this.ventanaABMHorario, this);
			}
		} else if (e.getSource() == ventanaCrearCurso.getBtnBorrarDia()) {
			int fila = this.ventanaCrearCurso.getTablaDiasHorarios().getSelectedRow();
			// this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
			// this.llenarTablaDiasHorarios();
		}/*else if (e.getSource() == ventanaCrearCurso.getBtnSeleccionarSala()) {
			this.ventanaSeleccionarSala = new SeleccionarSala();
			this.ventanaCrearCurso.setVisible(false);
			new ControladorSeleccionarSala(this.ventanaSeleccionarSala, this);
		} */
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

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
		this.ventanaCrearCurso.getTxtPrograma().setText(this.programa.getNombre());
	}

	public List<HorarioCursada> getHorariosCursada() {
		return horariosCursada;
	}

	public void setHorariosCursada(HorarioCursada horarioCursada) {
		this.horariosCursada.add(horarioCursada);
		llenarTablaDiasHorarios();
	}

}