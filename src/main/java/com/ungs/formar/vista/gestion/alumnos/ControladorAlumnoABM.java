package com.ungs.formar.vista.gestion.alumnos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JOptionPane;
import com.ungs.formar.negocios.AlumnoManager;
import com.ungs.formar.negocios.Formato;
import com.ungs.formar.negocios.InscripcionManager;
import com.ungs.formar.negocios.Validador;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.vista.consulta.cursos.VentanaCursosInscriptos;
import com.ungs.formar.vista.controladores.ControladorPantallaPrincipal;
import com.ungs.formar.vista.util.PanelHorizontal;

public class ControladorAlumnoABM implements ActionListener {
	private VentanaAlumnoABM ventanaABM;
	private VentanaAlumnoAM ventanaAM;
	private VentanaCursosInscriptos ventanaInscripciones;
	private ControladorPantallaPrincipal controlador;
	private List<Alumno> alumnos;
	private List<Curso> cursosInscriptos;

	public ControladorAlumnoABM(VentanaAlumnoABM ventanaABM, ControladorPantallaPrincipal controlador) {
		this.ventanaABM = ventanaABM;
		this.controlador = controlador;
		this.ventanaABM.getCancelar().addActionListener(this);
		this.ventanaABM.getAgregar().addActionListener(this);
		this.ventanaABM.getEditar().addActionListener(this);
		this.ventanaABM.getBorrar().addActionListener(this);
		this.ventanaABM.getMostrar().addActionListener(this);
		this.ventanaABM.getOcultar().addActionListener(this);
		this.ventanaABM.getBtnInscripciones().addActionListener(this);
		
		
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
	}

	private void llenarTabla() {
		ventanaABM.getModeloAlumnos().setRowCount(0);
		ventanaABM.getModeloAlumnos().setColumnCount(0);
		ventanaABM.getModeloAlumnos().setColumnIdentifiers(ventanaABM.getNombreColumnas());

		alumnos = AlumnoManager.traerAlumnos();
		for (Alumno alumno: alumnos) {
			Object[] fila = {
					alumno.getApellido(),
					alumno.getNombre(),
					alumno.getDni(),
					alumno.getEmail(),
					alumno.getTelefono()
					};
			ventanaABM.getModeloAlumnos().addRow(fila);
		}
	}

	private void llenarTablaInscripciones(Alumno alumno) {
		ventanaInscripciones.getModeloCursos().setRowCount(0);
		ventanaInscripciones.getModeloCursos().setColumnCount(0);
		ventanaInscripciones.getModeloCursos().setColumnIdentifiers(ventanaInscripciones.getNombreColumnas());

		cursosInscriptos = InscripcionManager.traerCursosInscriptos(alumno);
		for (Curso curso : cursosInscriptos) {
			Object[] fila = {
					Formato.nombre(curso),
					Formato.area(curso),
					Formato.estado(curso),
					curso.getCupoMinimo(),
					curso.getCupoMaximo(),
					curso.getFechaInicio(),
					curso.getFechaFin(),
					Formato.instructor(curso),
					Formato.responsable(curso),
					Formato.horarios(curso),
			};
			ventanaInscripciones.getModeloCursos().addRow(fila);
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
		else if (e.getSource() == ventanaABM.getBtnInscripciones())
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
			else if (e.getSource() == ventanaAM.getCancelar())
				cancelarAM();
		}
		
		else if (ventanaInscripciones != null) {
			// BOTON VOLVER DE INSCRIPCIONES
			if (e.getSource() == ventanaInscripciones.getVolver())
				cerrarVentanaInscripciones();
		}
		
	}
	
	private void mostrarInscripciones() {
		Alumno alumno = obtenerAlumnoSeleccionado();
		if (alumno != null) {			
			ventanaInscripciones = new VentanaCursosInscriptos();
			ventanaInscripciones.getVolver().addActionListener(this);
			ventanaInscripciones.getVentana().addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					cerrarVentanaInscripciones();
				}
			});
			ventanaInscripciones.getVentana().setVisible(true);
			ventanaABM.getVentana().setEnabled(false);
			llenarTablaInscripciones(alumno);
		} else
			JOptionPane.showMessageDialog(null, "Seleccione exactamente 1 alumno para ver a que cursos esta inscripto.");
	}

	private void eliminarSeleccion() {
		Alumno alumno = obtenerAlumnoSeleccionado();
		if (alumno != null) {
			AlumnoManager.eliminarAlumno(alumno);
			llenarTabla();
			
		} else
			JOptionPane.showMessageDialog(null, "Seleccione un alumno");
	}

	private void abrirVentanaModificacion() {
		Alumno alumno = obtenerAlumnoSeleccionado();
		if (alumno != null) {
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
		
		} else
			JOptionPane.showMessageDialog(null, "Seleccione un alumno");
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

	public void cerrarVentanaABM(){
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
				alumno.setDni(dni);
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

	private void cancelarAM(){
		ventanaAM.dispose();
		ventanaAM = null;
		ventanaABM.getVentana().setEnabled(true);
		ventanaABM.getVentana().setVisible(true);
	}

	private void cerrarVentanaInscripciones(){
		ventanaInscripciones.getVentana().dispose();
		ventanaABM.getVentana().setEnabled(true);
		ventanaABM.getVentana().setVisible(true);
	}
	
	private boolean validarCampos(){
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
		
		} else if (!Validador.validarApellido(apellido)){
			isOk = false;
			mensaje += "    -El APELLIDO solo puede consistir de letras y espacios.\n";
		} else if (apellido.length()> 50) {
			isOk = false;
			mensaje += "    -El APELLIDO debe tener una longitud maxima de 50\n";
		}
		

		if (nombre == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese el NOMBRE.\n";
		
		} else if (!Validador.validarNombre(nombre)){
			isOk = false;
			mensaje += "    -El NOMBRE solo puede consistir de letras y espacios.\n";
		}
		else if (nombre.length()> 50) {
			isOk = false;
			mensaje += "    -El NOMBRE debe tener una longitud maxima de 50\n";
		}

		// VALIDACION DE DNI
		if (dni == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese el DNI.\n";
		} else if (!Validador.validarDNI(dni) && ventanaAM.getAlumno()==null){
			isOk = false;
			mensaje += "    -El DNI solo puede consistir de numeros.\n";
		} else if (AlumnoManager.estaEnUsoDNI(dni)){
			Alumno alumnoEdicion = ventanaAM.getAlumno();
			Alumno alumnoBD = AlumnoManager.traerAlumnoSegunDNI(dni);
			
			// caso: es un nuevo alumno
			if (alumnoEdicion == null) {
				isOk = false;
				mensaje += "    -El DNI ya esta siendo utilizado por otro alumno.\n";
			}
			
			// caso: se esta editando uno existente
			else if (alumnoBD.getClienteID() != alumnoEdicion.getClienteID()) {
				isOk = false;
				mensaje += "    -El DNI ya esta siendo utilizado por otro alumno.\n";
			}
		
		} else if (dni.length()> 20) {
			isOk = false;
			mensaje += "    -El DNI debe tener una longitud maxima de 20\n";
		}
		
		if (telefono == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese el TELEFONO.\n";
		
		} else if (!Validador.validarTelefono(telefono)){
			isOk = false;
			mensaje += "    -El TELEFONO solo puede consistir de numeros.\n";
		} else if (telefono.length()> 50) {
			isOk = false;
			mensaje += "    -El TELEFONO debe tener una longitud maxima de 50\n";
		}
		
		if (email == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese el E-MAIL.\n";
		
		} else if (!Validador.validarEmail(email)){
			isOk = false;
			mensaje += "    -El E-MAIL es invalido\n";
		}
		else if (email.length()> 50) {
			isOk = false;
			mensaje += "    -El EMAIL debe tener una longitud maxima de 50\n";
		}
		
		if (isOk == false)
			JOptionPane.showMessageDialog(null, mensaje);
			
		return isOk;
	}

	private Alumno obtenerAlumnoSeleccionado() {
		int registroTabla = ventanaABM.getTablaAlumnos().getSelectedRow(); //Indice de la tabla
		
		// No habia ningun registro seleccionado
		if (registroTabla == -1)
			return null;
		
		int registro = ventanaABM.getTablaAlumnos().convertRowIndexToModel(registroTabla); // Fix para el filtro
		return alumnos.get(registro);
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