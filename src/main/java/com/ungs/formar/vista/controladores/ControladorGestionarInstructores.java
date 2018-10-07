package com.ungs.formar.vista.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.ungs.formar.negocios.AlumnoManager;
import com.ungs.formar.negocios.DiaManager;
import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.persistencia.entidades.Dia;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.vista.controladores.ControladorCrearCurso;
import com.ungs.formar.vista.ventanas.AltaAlumno;
import com.ungs.formar.vista.ventanas.AltaInstructor;
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
	private AltaInstructor ventanaAltaInstructor;

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
		this.ventanaGestionarInstructores.getModelInstructores().setRowCount(0); // Para
																					// vaciar
																					// la
																					// tabla
		this.ventanaGestionarInstructores.getModelInstructores().setColumnCount(0);
		this.ventanaGestionarInstructores.getModelInstructores()
				.setColumnIdentifiers(this.ventanaGestionarInstructores.getNombreColumnas());

		this.instructores_en_tabla = EmpleadoManager.traerInstructores();
		/*
		 * Collections.sort(this.personas_en_tabla, new Comparator<PersonaDTO>()
		 * { public int compare(PersonaDTO obj1, PersonaDTO obj2) { return
		 * obj1.getApellido().toUpperCase().compareTo(obj2.getApellido().
		 * toUpperCase()); } });
		 */
		for (int i = 0; i < this.instructores_en_tabla.size(); i++) {
			Object[] fila = { this.instructores_en_tabla.get(i).getApellido(),
					this.instructores_en_tabla.get(i).getNombre(), this.instructores_en_tabla.get(i).getDNI() };
			this.ventanaGestionarInstructores.getModelInstructores().addRow(fila);
		}

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventanaGestionarInstructores.getBtnAgregar()) {
			this.ventanaAltaInstructor = new AltaInstructor(this);
			this.ventanaAltaInstructor.mostrar();
			this.ventanaGestionarInstructores.ocultar();
		} else if (e.getSource() == ventanaGestionarInstructores.getBtnCancelar()) {
			this.ventanaGestionarInstructores.ocultar();
			this.controladorPantallaPrincipal.inicializar();
		} else if (e.getActionCommand() == "AgregarInstructor") {

			Pattern patronLetras = Pattern.compile("^[a-zA-Z ]*$");
			Pattern patronNumeros = Pattern.compile("^[0-9]*$");
			Pattern patronEmail = Pattern
					.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" + "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$");

			Matcher mDni = patronNumeros.matcher(this.ventanaAltaInstructor.getTxtDni().getText());
			Matcher mNombre = patronLetras.matcher(this.ventanaAltaInstructor.getTxtNombre().getText());
			Matcher mApellido = patronLetras.matcher(this.ventanaAltaInstructor.getTxtApellido().getText());
			Matcher mTelefono = patronNumeros.matcher(this.ventanaAltaInstructor.getTxtTelefono().getText());
			Matcher mEmail = patronEmail.matcher(this.ventanaAltaInstructor.getTxtEmail().getText());

			// Validaciones de null, y de tipos de datos validos
			if (!mDni.matches() || this.ventanaAltaInstructor.getTxtDni().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese un DNI valido");
			} else if (!mNombre.matches() || this.ventanaAltaInstructor.getTxtNombre().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre valido");
			} else if (!mApellido.matches() || this.ventanaAltaInstructor.getTxtApellido().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese un apellido valido");
			} else if (!mTelefono.matches() || this.ventanaAltaInstructor.getTxtTelefono().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese un telefono valido");
			} else if (!mEmail.matches() || this.ventanaAltaInstructor.getTxtEmail().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese un email valido");
			} else {
				String dni = this.ventanaAltaInstructor.getTxtDni().getText();
				String nombre = this.ventanaAltaInstructor.getTxtNombre().getText();
				String apellido = this.ventanaAltaInstructor.getTxtApellido().getText();
				String telefono = this.ventanaAltaInstructor.getTxtTelefono().getText();
				String email = this.ventanaAltaInstructor.getTxtEmail().getText();
				Date fechaIngreso = this.ventanaAltaInstructor.getDateFechaIngreso().getDate();
				Integer rol = new Integer(2);
				System.out.println(rol);

				EmpleadoManager.crearEmpleado(rol, dni, nombre, apellido, telefono, email,
						new java.sql.Date(fechaIngreso.getTime()), null);
				this.ventanaAltaInstructor.dispose();
				this.inicializar();
			}
		}

		else if (e.getActionCommand() == "CancelarAgregarInstructor") {
			this.ventanaAltaInstructor.dispose();
			this.ventanaGestionarInstructores.mostrar();
		}
	}
}