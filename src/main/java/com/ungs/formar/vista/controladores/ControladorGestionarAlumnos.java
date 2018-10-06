package com.ungs.formar.vista.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JComboBox;

import com.ungs.formar.negocios.AlumnoManager;
import com.ungs.formar.negocios.DiaManager;
import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Dia;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.vista.controladores.ControladorCrearCurso;
import com.ungs.formar.vista.ventanas.AltaAlumno;
import com.ungs.formar.vista.ventanas.CrearCurso;
import com.ungs.formar.vista.ventanas.GestionarAlumnos;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarDiaHorario;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarInstructor;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarResponsable;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarSala;

public class ControladorGestionarAlumnos implements ActionListener {
	private GestionarAlumnos ventanaGestionarAlumnos;
	private ControladorPantallaPrincipal controladorPantallaPrincipal;
	private List<Alumno> alumnos_en_tabla;
	private AltaAlumno ventanaAltaAlumno;

	public ControladorGestionarAlumnos(GestionarAlumnos ventanaGestionarAlumnos,
			ControladorPantallaPrincipal controladorPantallaPrincipal) {
		this.ventanaGestionarAlumnos = ventanaGestionarAlumnos;
		this.controladorPantallaPrincipal = controladorPantallaPrincipal;
		this.ventanaGestionarAlumnos.getBtnCancelar().addActionListener(this);
		this.ventanaGestionarAlumnos.getBtnAgregar().addActionListener(this);
		this.inicializar();
	}

	public void inicializar() {
		this.llenarTablaAlumnos();
		this.ventanaGestionarAlumnos.mostrar();
	}

	private void llenarTablaAlumnos() {
		this.ventanaGestionarAlumnos.getModelAlumnos().setRowCount(0); //Para vaciar la tabla
		this.ventanaGestionarAlumnos.getModelAlumnos().setColumnCount(0);
		this.ventanaGestionarAlumnos.getModelAlumnos().setColumnIdentifiers(this.ventanaGestionarAlumnos.getNombreColumnas());
		
		this.alumnos_en_tabla = AlumnoManager.traerAlumno();
		/*Collections.sort(this.personas_en_tabla, new Comparator<PersonaDTO>() {
			   public int compare(PersonaDTO obj1, PersonaDTO obj2) {
			      return obj1.getApellido().toUpperCase().compareTo(obj2.getApellido().toUpperCase());
			   }
			});
		*/
		for (int i = 0; i < this.alumnos_en_tabla.size(); i ++){
			Object[] fila = {this.alumnos_en_tabla.get(i).getApellido(), this.alumnos_en_tabla.get(i).getNombre(),
					this.alumnos_en_tabla.get(i).getDni(), this.alumnos_en_tabla.get(i).getEmail(), this.alumnos_en_tabla.get(i).getTelefono()};
			this.ventanaGestionarAlumnos.getModelAlumnos().addRow(fila);
		}			

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventanaGestionarAlumnos.getBtnAgregar()) {
			this.ventanaAltaAlumno = new AltaAlumno();
			this.ventanaGestionarAlumnos.ocultar();
			this.ventanaGestionarAlumnos.ocultar();
			//new ControladorAltaAlumno(this.ventanaAltaAlumno, this);
		}

		else if (e.getSource() == ventanaGestionarAlumnos.getBtnCancelar()) {
			this.ventanaGestionarAlumnos.ocultar();
			this.controladorPantallaPrincipal.inicializar();
		}
	}
}