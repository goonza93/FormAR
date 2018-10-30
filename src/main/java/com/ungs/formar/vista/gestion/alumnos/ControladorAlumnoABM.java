package com.ungs.formar.vista.gestion.alumnos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.ungs.formar.negocios.AlumnoManager;
import com.ungs.formar.negocios.Validador;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.vista.consulta.Consultable;
import com.ungs.formar.vista.consulta.cursos.ControladorCursosInscriptos;
import com.ungs.formar.vista.consulta.cursos.VentanaCursosInscriptos;
import com.ungs.formar.vista.controladores.ControladorPantallaPrincipal;
import com.ungs.formar.vista.util.Formato;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.Popup;

public class ControladorAlumnoABM implements ActionListener, Consultable {
	private VentanaAlumnoABM ventanaABM;
	private VentanaAlumnoAM ventanaAM;
	private ControladorPantallaPrincipal controlador;
	private List<Alumno> alumnos;

	public ControladorAlumnoABM(VentanaAlumnoABM ventanaABM, ControladorPantallaPrincipal controlador) {
		this.ventanaABM = ventanaABM;
		this.controlador = controlador;
		this.ventanaABM.getCancelar().addActionListener(this);
		this.ventanaABM.getAgregar().addActionListener(this);
		this.ventanaABM.getEditar().addActionListener(this);
		this.ventanaABM.getBorrar().addActionListener(this);
		this.ventanaABM.getMostrar().addActionListener(this);
		this.ventanaABM.getOcultar().addActionListener(this);
		this.ventanaABM.getInscripciones().addActionListener(this);

		this.ventanaABM.getVentana().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentanaABM();
			}
		});
		this.inicializar();
	}

	public void inicializar() {
		llenarTabla();
		ventanaABM.getVentana().setVisible(true);
		ventanaABM.getVentana().setEnabled(true);
	}

	private void llenarTabla() {
		ventanaABM.getModeloAlumnos().setRowCount(0);
		ventanaABM.getModeloAlumnos().setColumnCount(0);
		ventanaABM.getModeloAlumnos().setColumnIdentifiers(ventanaABM.getNombreColumnas());

		alumnos = AlumnoManager.traerAlumnos();
		for (Alumno alumno : alumnos) {
			Object[] fila = { alumno.getApellido(), alumno.getNombre(), alumno.getDNI(), alumno.getEmail(),
					alumno.getTelefono() };
			ventanaABM.getModeloAlumnos().addRow(fila);

			// seteo la altura de la celda
			int registro = ventanaABM.getModeloAlumnos().getRowCount() - 1;
			int altura = Formato.calcularAlturaDeCelda(fila);
			ventanaABM.getTablaAlumnos().setRowHeight(registro, altura);
		}
	}

	public void actionPerformed(ActionEvent e) {
		// BOTON AGREGAR DEL ABM
		if (e.getSource() == ventanaABM.getAgregar())
			abrirVentanaAlta();

		// BOTON CANCELAR DEL ABM
		else if (e.getSource() == ventanaABM.getCancelar())
			cerrarVentanaABM();

		// BOTON EDITAR DEL ABM
		else if (e.getSource() == ventanaABM.getEditar())
			abrirVentanaModificacion();

		// BOTON BORRAR DEL ABM
		else if (e.getSource() == ventanaABM.getBorrar())
			eliminarSeleccion();

		// BOTON VER INSCRIPCIONES DEL ABM
		else if (e.getSource() == ventanaABM.getInscripciones())
			mostrarInscripciones();

		// BOTON MOSTRAR FILTROS
		else if (e.getSource() == ventanaABM.getMostrar())
			mostrarFiltros();

		// BOTON OCULTAR FILTROS
		else if (e.getSource() == ventanaABM.getOcultar())
			ocultarFiltros();

		else if (ventanaAM != null) {
			// BOTON ACEPTAR DEL AM
			if (e.getSource() == ventanaAM.getAceptar())
				aceptarAM();

			// BOTON CANCELAR DEL AM
			else if (e.getSource() == ventanaAM.getCancelar()) {
				cancelarAM();
			}
		}
	}

	private void mostrarInscripciones() {
		List<Alumno> seleccion = obtenerAlumnosSeleccionados();

		if (seleccion.size() != 1) {
			Popup.mostrar("Seleccione exactamente 1 alumno para ver sus inscripciones");
			return;
		}

		Alumno alumno = seleccion.get(0);
		new ControladorCursosInscriptos(new VentanaCursosInscriptos(), this, alumno);
		ventanaABM.getVentana().setEnabled(false);
	}

	private void eliminarSeleccion() {
		List<Alumno> seleccion = obtenerAlumnosSeleccionados();

		if (seleccion.size() == 0) {
			Popup.mostrar("Seleccione al menos un alumno para borrar");
			return;
		}
		String msj = "";
		String pregunta = "¿Esta seguro de que desea borrar los siguientes alumnos?";
		for (Alumno alumno : seleccion)
			pregunta += "\n- " + alumno.getApellido() + ", " + alumno.getNombre();

		if (Popup.confirmar(pregunta)) {
			for (Alumno alumno : seleccion) {
				if (!AlumnoManager.estaCursando(alumno)) {
					AlumnoManager.eliminarAlumno(alumno);
				}else{
					if(msj.isEmpty())
						msj +="Los siguientes alumnos no se pueden borrar porque estan inscriptos a cursadas:";
					msj+="\n- " + alumno.getApellido() + ", " + alumno.getNombre();
				}		
			}
			if(!msj.isEmpty())
				Popup.mostrar(msj);
			llenarTabla();
		}
	}

	private void abrirVentanaModificacion() {
		List<Alumno> seleccion = obtenerAlumnosSeleccionados();

		if (seleccion.size() != 1) {
			Popup.mostrar("Seleccione exactamente 1 alumno para modificar");
			return;
		}

		Alumno alumno = seleccion.get(0);
		ventanaAM = new VentanaAlumnoAM(alumno);
		ventanaAM.getAceptar().addActionListener(this);
		ventanaAM.getCancelar().addActionListener(this);

		ventanaAM.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cancelarAM();
			}
		});
		ventanaAM.setVisible(true);
		ventanaABM.getVentana().setEnabled(false);
	}

	private void abrirVentanaAlta() {
		ventanaAM = new VentanaAlumnoAM();
		ventanaAM.getAceptar().addActionListener(this);
		ventanaAM.getCancelar().addActionListener(this);

		ventanaAM.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cancelarAM();
			}
		});
		ventanaAM.setVisible(true);
		ventanaABM.getVentana().setEnabled(false);
	}

	public void cerrarVentanaABM() {
		ventanaABM.getVentana().dispose();
		ventanaABM = null;
		controlador.inicializar();
	}

	private void aceptarAM() {
		if (validarCampos()) {
			Alumno alumno = ventanaAM.getAlumno();
			String apellido = ventanaAM.getApellido().getText();
			String nombre = ventanaAM.getNombre().getText();
			String dni = ventanaAM.getDNI().getText();
			String telefono = ventanaAM.getTelefono().getText();
			String email = ventanaAM.getEmail().getText();

			// Crear un nuevo alumno
			if (alumno == null)
				AlumnoManager.crearAlumno(dni, nombre, apellido, telefono, email);

			// Editar un alumno existente
			else {
				alumno.setApellido(apellido);
				alumno.setNombre(nombre);
				alumno.setDNI(dni);
				alumno.setTelefono(telefono);
				alumno.setEmail(email);
				AlumnoManager.editarAlumno(alumno);
			}

			llenarTabla();
			ventanaAM.dispose();
			ventanaAM = null;
			ventanaABM.getVentana().setEnabled(true);
			ventanaABM.getVentana().setVisible(true);
		}
	}

	private void cancelarAM() {
		int confirm = JOptionPane.showOptionDialog(null, "Esta seguro de salir sin guardar!?", "Confirmacion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == 0) {
			ventanaAM.dispose();
			ventanaAM = null;
			ventanaABM.getVentana().setEnabled(true);
			ventanaABM.getVentana().setVisible(true);
		}
	}

	private boolean validarCampos() {
		String apellido = ventanaAM.getApellido().getText();
		String nombre = ventanaAM.getNombre().getText();
		String dni = ventanaAM.getDNI().getText();
		String telefono = ventanaAM.getTelefono().getText();
		String email = ventanaAM.getEmail().getText();

		boolean isOk = true;
		String mensaje = "Se encontraron los siguientes errores:\n";

		if (apellido == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese el APELLIDO.\n";

		} else if (!Validador.validarApellido(apellido)) {
			isOk = false;
			mensaje += "    -El APELLIDO solo puede consistir de letras y espacios.\n";
		} else if (apellido.length() > 50) {
			isOk = false;
			mensaje += "    -El APELLIDO debe tener una longitud maxima de 50\n";
		}

		if (nombre == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese el NOMBRE.\n";

		} else if (!Validador.validarNombre(nombre)) {
			isOk = false;
			mensaje += "    -El NOMBRE solo puede consistir de letras y espacios.\n";
		} else if (nombre.length() > 50) {
			isOk = false;
			mensaje += "    -El NOMBRE debe tener una longitud maxima de 50\n";
		}

		// VALIDACION DE DNI
		if (dni == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese el DNI.\n";
		} else if (!Validador.validarDNI(dni) && ventanaAM.getAlumno() == null) {
			isOk = false;
			mensaje += "    -El DNI solo puede consistir de numeros.\n";
		} else if (AlumnoManager.estaEnUsoDNI(dni)) {
			Alumno alumnoEdicion = ventanaAM.getAlumno();
			Alumno alumnoBD = AlumnoManager.traerAlumnoSegunDNI(dni);

			// caso: es un nuevo alumno
			if (alumnoEdicion == null) {
				isOk = false;
				mensaje += "    -El DNI ya esta siendo utilizado por otro alumno.\n";
			}

			// caso: se esta editando uno existente
			else if (alumnoBD.getID() != alumnoEdicion.getID()) {
				isOk = false;
				mensaje += "    -El DNI ya esta siendo utilizado por otro alumno.\n";
			}

		} else if (dni.length() > 20) {
			isOk = false;
			mensaje += "    -El DNI debe tener una longitud maxima de 20\n";
		}

		if (telefono == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese el TELEFONO.\n";

		} else if (!Validador.validarTelefono(telefono)) {
			isOk = false;
			mensaje += "    -El TELEFONO solo puede consistir de numeros.\n";
		} else if (telefono.length() > 50) {
			isOk = false;
			mensaje += "    -El TELEFONO debe tener una longitud maxima de 50\n";
		}

		if (email == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese el E-MAIL.\n";

		} else if (!Validador.validarEmail(email)) {
			isOk = false;
			mensaje += "    -El E-MAIL es invalido\n";
		} else if (email.length() > 50) {
			isOk = false;
			mensaje += "    -El EMAIL debe tener una longitud maxima de 50\n";
		}

		if (isOk == false)
			JOptionPane.showMessageDialog(null, mensaje);

		return isOk;
	}

	private List<Alumno> obtenerAlumnosSeleccionados() {
		List<Alumno> registros = new ArrayList<Alumno>();
		int[] indices = ventanaABM.getTablaAlumnos().getSelectedRows();

		for (int indice : indices) {
			int registro = ventanaABM.getTablaAlumnos().convertRowIndexToModel(indice);
			registros.add(alumnos.get(registro));
		}

		return registros;
	}

	private void mostrarFiltros() {
		PanelHorizontal panel = ventanaABM.getPanelFiltrar();
		panel.removeAll();
		panel.add(ventanaABM.getPanelConFiltros());
		panel.add(ventanaABM.getOcultar());
		panel.revalidate();
	}

	private void ocultarFiltros() {
		PanelHorizontal panel = ventanaABM.getPanelFiltrar();
		panel.removeAll();
		panel.add(ventanaABM.getMostrar());
		panel.revalidate();
	}

}