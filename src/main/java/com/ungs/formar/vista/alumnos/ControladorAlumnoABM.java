package com.ungs.formar.vista.alumnos;

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
	private ControladorPantallaPrincipal controladorPantallaPrincipal;
	private List<Alumno> alumnos;

	public ControladorAlumnoABM(VentanaAlumnoABM ventanaGestionarAlumnos,
			ControladorPantallaPrincipal controladorPantallaPrincipal) {
		this.ventanaAlumnoABM = ventanaGestionarAlumnos;
		this.controladorPantallaPrincipal = controladorPantallaPrincipal;
		this.ventanaAlumnoABM.getBtnCancelar().addActionListener(this);
		this.ventanaAlumnoABM.getBtnAgregar().addActionListener(this);
		this.ventanaAlumnoABM.getFrame().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarAlumnoABM();
			}
		});
		this.inicializar();
	}

	public void inicializar() {
		this.llenarTabla();
		this.ventanaAlumnoABM.mostrar();
	}

	private void llenarTabla() {
		// Reinicio completamente la tabla
		ventanaAlumnoABM.getModelAlumnos().setRowCount(0);
		ventanaAlumnoABM.getModelAlumnos().setColumnCount(0);
		ventanaAlumnoABM.getModelAlumnos().setColumnIdentifiers(ventanaAlumnoABM.getNombreColumnas());

		// Por cada alumno en mi lista agrego un registro a la tabla
		alumnos = AlumnoManager.traerAlumnos();
		for (Alumno alumno: alumnos) {
			Object[] fila = {
					alumno.getApellido(),
					alumno.getNombre(),
					alumno.getDni(),
					alumno.getEmail(),
					alumno.getTelefono()
					};
			ventanaAlumnoABM.getModelAlumnos().addRow(fila);
		}

	}

	public void actionPerformed(ActionEvent e) {
		// BOTON AGREGAR DEL ABM
		if (e.getSource() == ventanaAlumnoABM.getBtnAgregar())
			abrirAlumnoAM();
		
		// BOTON CANCELAR DEL ABM
		else if (e.getSource() == ventanaAlumnoABM.getBtnCancelar())
			cerrarAlumnoABM();
		
		else if (ventanaAlumnoAM != null) {
			// BOTON ACEPTAR DEL AM
			if (e.getSource() == ventanaAlumnoAM.getAceptar())
				aceptarAM();
		
			// BOTON CANCELAR DEL AM
			else if (e.getSource() == ventanaAlumnoAM.getCancelar())
				cerrarAlumnoAM();
		}
	}
	
	private void abrirAlumnoAM() {
		ventanaAlumnoAM = new VentanaAlumnoAM();
		ventanaAlumnoAM.getAceptar().addActionListener(this);
		ventanaAlumnoAM.getCancelar().addActionListener(this);
		
		ventanaAlumnoAM.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarAlumnoAM();
			}
		});
		ventanaAlumnoAM.mostrar();
		ventanaAlumnoABM.getFrame().setEnabled(false);
	}

	public void cerrarAlumnoABM(){
		ventanaAlumnoABM.ocultar();
		controladorPantallaPrincipal.inicializar();
	}
	
	private void aceptarAM() {
		if (validarCampos()) {
			String dni = ventanaAlumnoAM.getDNI().getText();
			String nombre = ventanaAlumnoAM.getNombre().getText();
			String apellido = ventanaAlumnoAM.getApellido().getText();
			String telefono = ventanaAlumnoAM.getTelefono().getText();
			String email = ventanaAlumnoAM.getEmail().getText();
			
			AlumnoManager.crearAlumno(dni, nombre, apellido, telefono, email);
			llenarTabla();
			ventanaAlumnoAM.dispose();
			ventanaAlumnoABM.getFrame().setEnabled(true);
		}	
	}

	private void cerrarAlumnoAM(){
		ventanaAlumnoAM.dispose();
		ventanaAlumnoABM.getFrame().setEnabled(true);
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
	
}