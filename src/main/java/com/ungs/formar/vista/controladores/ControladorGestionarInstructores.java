package com.ungs.formar.vista.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JComboBox;

import com.ungs.formar.negocios.DiaManager;
import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.persistencia.entidades.Dia;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.vista.controladores.ControladorCrearCurso;
import com.ungs.formar.vista.ventanas.CrearCurso;
import com.ungs.formar.vista.ventanas.GestionarInstructores;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarDiaHorario;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarInstructor;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarResponsable;
import com.ungs.formar.vista.ventanas.seleccion.SeleccionarSala;

public class ControladorGestionarInstructores implements ActionListener {
	private GestionarInstructores ventanaGestionarInstructores;
	private ControladorPantallaPrincipal controladorPantallaPrincipal;
	private List<Empleado> instructores_en_tabla;

	public ControladorGestionarInstructores(GestionarInstructores ventanaGestionarInstructores,
			ControladorPantallaPrincipal controladorPantallaPrincipal) {
		this.ventanaGestionarInstructores = ventanaGestionarInstructores;
		this.controladorPantallaPrincipal = controladorPantallaPrincipal;
		this.ventanaGestionarInstructores.getBtnCancelar().addActionListener(this);
		this.ventanaGestionarInstructores.getBtnAgregar().addActionListener(this);
		this.inicializar();
	}

	public void inicializar() {
		this.llenarTablaInstructores();
		this.ventanaGestionarInstructores.mostrar();
	}

	private void llenarTablaInstructores() {
		this.ventanaGestionarInstructores.getModelInstructores().setRowCount(0); //Para vaciar la tabla
		this.ventanaGestionarInstructores.getModelInstructores().setColumnCount(0);
		this.ventanaGestionarInstructores.getModelInstructores().setColumnIdentifiers(this.ventanaGestionarInstructores.getNombreColumnas());
		
		this.instructores_en_tabla = EmpleadoManager.traerEmpleados();
		/*Collections.sort(this.personas_en_tabla, new Comparator<PersonaDTO>() {
			   public int compare(PersonaDTO obj1, PersonaDTO obj2) {
			      return obj1.getApellido().toUpperCase().compareTo(obj2.getApellido().toUpperCase());
			   }
			});
		*/
		for (int i = 0; i < this.instructores_en_tabla.size(); i ++){
			Object[] fila = {this.instructores_en_tabla.get(i).getNombre(), this.instructores_en_tabla.get(i).getApellido(),
					this.instructores_en_tabla.get(i).getDNI()};
			this.ventanaGestionarInstructores.getModelInstructores().addRow(fila);
		}			

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventanaGestionarInstructores.getBtnAgregar()) {

		} else if (e.getSource() == ventanaGestionarInstructores.getBtnCancelar()) {
			this.ventanaGestionarInstructores.ocultar();
			this.controladorPantallaPrincipal.inicializar();
		}
	}
}