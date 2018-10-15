package com.ungs.formar.vista.gestion.alumnos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JOptionPane;
import com.ungs.formar.negocios.AlumnoManager;
import com.ungs.formar.negocios.Validador;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.vista.controladores.ControladorPantallaPrincipal;

public class ControladorAlumnoABM implements ActionListener {
	private VentanaAlumnoABM ventanaAlumnoABM;
	private VentanaAlumnoAM ventanaAlumnoAM;
	private ControladorPantallaPrincipal controlador;
	private List<Alumno> alumnos;

	public ControladorAlumnoABM(VentanaAlumnoABM ventanaAlumnoABM, ControladorPantallaPrincipal controlador) {
		this.ventanaAlumnoABM = ventanaAlumnoABM;
		this.controlador = controlador;
		this.ventanaAlumnoABM.getCancelar().addActionListener(this);
		this.ventanaAlumnoABM.getAgregar().addActionListener(this);
		this.ventanaAlumnoABM.getEditar().addActionListener(this);
		this.ventanaAlumnoABM.getBorrar().addActionListener(this);
		this.ventanaAlumnoABM.getVentana().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentanaABM();
			}
		});
		this.inicializar();
	}

	public void inicializar() {
		llenarTabla();
		ventanaAlumnoABM.getVentana().setVisible(true);
	}

	private void llenarTabla() {
		ventanaAlumnoABM.getModeloAlumnos().setRowCount(0);
		ventanaAlumnoABM.getModeloAlumnos().setColumnCount(0);
		ventanaAlumnoABM.getModeloAlumnos().setColumnIdentifiers(ventanaAlumnoABM.getNombreColumnas());

		alumnos = AlumnoManager.traerAlumnos();
		for (Alumno alumno: alumnos) {
			Object[] fila = {
					alumno.getApellido(),
					alumno.getNombre(),
					alumno.getDni(),
					alumno.getEmail(),
					alumno.getTelefono()
					};
			ventanaAlumnoABM.getModeloAlumnos().addRow(fila);
		}
	}

	public void actionPerformed(ActionEvent e) {
		// BOTON AGREGAR DEL ABM
		if (e.getSource() == ventanaAlumnoABM.getAgregar())
			abrirVentanaAlta();
		
		// BOTON CANCELAR DEL ABM
		else if (e.getSource() == ventanaAlumnoABM.getCancelar())
			cerrarVentanaABM();
		
		// BOTON EDITAR DEL ABM
		else if (e.getSource() == ventanaAlumnoABM.getEditar())
			abrirVentanaModificacion();
		
		// BOTON BORRAR DEL ABM
		else if (e.getSource() == ventanaAlumnoABM.getBorrar())
			eliminarSeleccion();
		
		else if (ventanaAlumnoAM != null) {
			// BOTON ACEPTAR DEL AM
			if (e.getSource() == ventanaAlumnoAM.getAceptar())
				aceptarAM();
		
			// BOTON CANCELAR DEL AM
			else if (e.getSource() == ventanaAlumnoAM.getCancelar())
				cancelarAM();
		}
	}
	
	private void eliminarSeleccion() {
		Alumno alumno = obtenerAlumnoSeleccionado();
		AlumnoManager.eliminarAlumno(alumno);
		llenarTabla();
	}

	private void abrirVentanaModificacion() {
		Alumno alumno = obtenerAlumnoSeleccionado();
		ventanaAlumnoAM = new VentanaAlumnoAM(alumno);
		ventanaAlumnoAM.getAceptar().addActionListener(this);
		ventanaAlumnoAM.getCancelar().addActionListener(this);
		
		ventanaAlumnoAM.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cancelarAM();
			}
		});
		ventanaAlumnoAM.setVisible(true);
		ventanaAlumnoABM.getVentana().setVisible(false);
	}

	private void abrirVentanaAlta() {
		ventanaAlumnoAM = new VentanaAlumnoAM();
		ventanaAlumnoAM.getAceptar().addActionListener(this);
		ventanaAlumnoAM.getCancelar().addActionListener(this);
		
		ventanaAlumnoAM.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cancelarAM();
			}
		});
		ventanaAlumnoAM.setVisible(true);
		ventanaAlumnoABM.getVentana().setVisible(false);
	}

	public void cerrarVentanaABM(){
		ventanaAlumnoABM.getVentana().dispose();
		ventanaAlumnoABM = null;
		controlador.inicializar();
	}
	
	private void aceptarAM() {
		if (validarCampos()) {
			Alumno alumno = ventanaAlumnoAM.getAlumno();
			String apellido = ventanaAlumnoAM.getApellido().getText();
			String nombre = ventanaAlumnoAM.getNombre().getText();
			String dni = ventanaAlumnoAM.getDNI().getText();
			String telefono = ventanaAlumnoAM.getTelefono().getText();
			String email = ventanaAlumnoAM.getEmail().getText();
			
			// Crear un nuevo alumno
			if (alumno == null)
				AlumnoManager.crearAlumno(dni, nombre, apellido, telefono, email);
			
			// Editar un alumno existente
			else {
				alumno.setApellido(apellido);
				alumno.setNombre(nombre);
				alumno.setDni(dni);
				alumno.setTelefono(telefono);
				alumno.setEmail(email);
				AlumnoManager.editarAlumno(alumno);
			}
			
			llenarTabla();
			ventanaAlumnoAM.dispose();
			ventanaAlumnoABM.getVentana().setVisible(true);
		}	
	}

	private void cancelarAM(){
		ventanaAlumnoAM.dispose();
		ventanaAlumnoABM.getVentana().setVisible(true);
	}
	
	private boolean validarCampos(){
		String apellido = ventanaAlumnoAM.getApellido().getText();
		String nombre = ventanaAlumnoAM.getNombre().getText();
		String dni = ventanaAlumnoAM.getDNI().getText();
		String telefono = ventanaAlumnoAM.getTelefono().getText();
		String email = ventanaAlumnoAM.getEmail().getText();

		boolean isOk = true;
		String mensaje = "Se encontraron los siguientes errores:\n";

		if (apellido == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese el APELLIDO.\n";
		
		} else if (!Validador.validarApellido(apellido)){
			isOk = false;
			mensaje += "    -El APELLIDO solo puede consistir de letras y espacios.\n";
		}

		if (nombre == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese el NOMBRE.\n";
		
		} else if (!Validador.validarNombre(nombre)){
			isOk = false;
			mensaje += "    -El NOMBRE solo puede consistir de letras y espacios.\n";
		}

		if (dni == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese el DNI.\n";
		
		} else if (!Validador.validarDNI(dni)){
			isOk = false;
			mensaje += "    -El DNI solo puede consistir de numeros.\n";
		}
		
		if (telefono == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese el TELEFONO.\n";
		
		} else if (!Validador.validarTelefono(telefono)){
			isOk = false;
			mensaje += "    -El TELEFONO solo puede consistir de numeros.\n";
		}
		
		if (email == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese el E-MAIL.\n";
		
		} else if (!Validador.validarDNI(dni)){
			isOk = false;
			mensaje += "    -El E-MAIL debe tener el formato NL + @ + NL + . +NL (Siendo NL uno o mas numeros o letras).\n";
		}
		
		if (isOk == false)
			JOptionPane.showMessageDialog(null, mensaje);
			
		return isOk;
	}

	private Alumno obtenerAlumnoSeleccionado() {
		int registroTabla = ventanaAlumnoABM.getTablaAlumnos().getSelectedRow(); //Indice de la tabla
		
		// No habia ningun registro seleccionado
		if (registroTabla == -1)
			return null;
		
		int registro = ventanaAlumnoABM.getTablaAlumnos().convertRowIndexToModel(registroTabla); // Fix para el filtro
		return alumnos.get(registro);
	}
	
}