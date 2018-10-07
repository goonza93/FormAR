package com.ungs.formar.vista.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

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
		this.ventanaGestionarAlumnos.getModelAlumnos().setRowCount(0); // Para
																		// vaciar
																		// la
																		// tabla
		this.ventanaGestionarAlumnos.getModelAlumnos().setColumnCount(0);
		this.ventanaGestionarAlumnos.getModelAlumnos()
				.setColumnIdentifiers(this.ventanaGestionarAlumnos.getNombreColumnas());

		this.alumnos_en_tabla = AlumnoManager.traerAlumnos();
		/*
		 * Collections.sort(this.personas_en_tabla, new Comparator<PersonaDTO>()
		 * { public int compare(PersonaDTO obj1, PersonaDTO obj2) { return
		 * obj1.getApellido().toUpperCase().compareTo(obj2.getApellido().
		 * toUpperCase()); } });
		 */
		for (int i = 0; i < this.alumnos_en_tabla.size(); i++) {
			Object[] fila = { this.alumnos_en_tabla.get(i).getApellido(), this.alumnos_en_tabla.get(i).getNombre(),
					this.alumnos_en_tabla.get(i).getDni(), this.alumnos_en_tabla.get(i).getEmail(),
					this.alumnos_en_tabla.get(i).getTelefono() };
			this.ventanaGestionarAlumnos.getModelAlumnos().addRow(fila);
		}

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventanaGestionarAlumnos.getBtnAgregar()) {
			this.ventanaAltaAlumno = new AltaAlumno(this);
			this.ventanaAltaAlumno.mostrar();
			this.ventanaGestionarAlumnos.ocultar();
		}

		else if (e.getSource() == ventanaGestionarAlumnos.getBtnCancelar()) {
			this.ventanaGestionarAlumnos.ocultar();
			this.controladorPantallaPrincipal.inicializar();
		}

		else if (e.getActionCommand() == "AgregarAlumno") {

			Pattern patronLetras = Pattern.compile("^[a-zA-Z ]*$");
			Pattern patronNumeros = Pattern.compile("^[0-9]*$");
			Pattern patronEmail = Pattern
					.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" + "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$");

			Matcher mDni = patronNumeros.matcher(this.ventanaAltaAlumno.getTxtDni().getText());
			Matcher mNombre = patronLetras.matcher(this.ventanaAltaAlumno.getTxtNombre().getText());
			Matcher mApellido = patronLetras.matcher(this.ventanaAltaAlumno.getTxtApellido().getText());
			Matcher mTelefono = patronNumeros.matcher(this.ventanaAltaAlumno.getTxtTelefono().getText());
			Matcher mEmail = patronEmail.matcher(this.ventanaAltaAlumno.getTxtEmail().getText());

			// Validaciones de null, y de tipos de datos validos
			if (!mDni.matches() || this.ventanaAltaAlumno.getTxtDni().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese un DNI valido");
			} else if (!mNombre.matches() || this.ventanaAltaAlumno.getTxtNombre().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre valido");
			} else if (!mApellido.matches() || this.ventanaAltaAlumno.getTxtApellido().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese un apellido valido");
			} else if (!mTelefono.matches() || this.ventanaAltaAlumno.getTxtTelefono().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese un telefono valido");
			} else if (!mEmail.matches() || this.ventanaAltaAlumno.getTxtEmail().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, ingrese un email valido");
			} else {
				Integer dni = Integer.valueOf(this.ventanaAltaAlumno.getTxtDni().getText());
				String nombre = this.ventanaAltaAlumno.getTxtNombre().getText();
				String apellido = this.ventanaAltaAlumno.getTxtApellido().getText();
				String telefono = this.ventanaAltaAlumno.getTxtTelefono().getText();
				String email = this.ventanaAltaAlumno.getTxtEmail().getText();
				AlumnoManager.crearAlumno(dni, nombre, apellido, telefono, email);
				this.ventanaAltaAlumno.dispose();
				this.inicializar();
			}
		}

		else if (e.getActionCommand() == "CancelarAgregarAlumno") {
			this.ventanaAltaAlumno.dispose();
			this.ventanaGestionarAlumnos.mostrar();
		}
	}
}