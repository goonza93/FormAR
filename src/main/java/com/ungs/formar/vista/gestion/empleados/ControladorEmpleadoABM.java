package com.ungs.formar.vista.gestion.empleados;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import com.ungs.formar.negocios.Almanaque;
import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.Instructor;
import com.ungs.formar.negocios.Validador;
import com.ungs.formar.persistencia.definidos.Rol;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.pantallasPrincipales.ControladorInterno;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPrincipal;
import com.ungs.formar.vista.util.Popup;
import com.ungs.formar.vista.util.Sesion;

public class ControladorEmpleadoABM implements ActionListener, ControladorInterno {
	private VentanaEmpleadoABM ventanaABM;
	private VentanaEmpleadoAM ventanaAM;
	private ControladorPrincipal controlador;
	private List<Empleado> empleados;
	private Rol rol;

	public ControladorEmpleadoABM(VentanaEmpleadoABM ventanaABM, ControladorPrincipal controlador) {
		this.ventanaABM = ventanaABM;
		this.controlador = controlador;
		this.rol = Sesion.getEmpleado().getRol();
		this.ventanaABM.getAgregar().addActionListener(s -> abrirVentanaAlta());
		this.ventanaABM.getBorrar().addActionListener(s -> baja());
		this.ventanaABM.getEditar().addActionListener(s -> abrirVentanaModificacion());
		this.ventanaABM.getDarDeAlta().addActionListener(s -> alta());
		this.inicializar();
	}
	
	public ControladorEmpleadoABM(ControladorPrincipal controlador) {
		this.controlador = controlador;
		rol = Sesion.getEmpleado().getRol();
		ventanaAM = new VentanaEmpleadoAM(Sesion.getEmpleado().getRol());
		ventanaAM.botonAceptar().addActionListener(s -> aceptarAMP());
		ventanaAM.botonCancelar().addActionListener(s -> cerrarVentanaAM());
		ventanaAM.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentanaAM();
			}
		});
		ventanaAM.getFechaIngreso().setDate(Almanaque.hoy());
		ventanaAM.setVisible(true);
		this.controlador.getVentana().setEnabled(false);
	}
	

	public void inicializar() {
		llenarTabla();
		ventanaABM.mostrar();
	}

	private void llenarTabla() {
		ventanaABM.getModeloEmpleados().setRowCount(0);
		ventanaABM.getModeloEmpleados().setColumnCount(0);
		ventanaABM.getModeloEmpleados().setColumnIdentifiers(ventanaABM.getNombreColumnas());

		empleados = EmpleadoManager.traerEmpleados();
		for (Empleado empleado : empleados) {
			if (rol == Rol.ADMINISTRATIVO) {
				if (empleado.getRol() == Rol.INSTRUCTOR) {
					Object[] fila = {
							empleado.getRol(),
							empleado.getApellido(),
							empleado.getNombre(),
							empleado.getDNI(),
							empleado.getEmail(),
							empleado.getTelefono(),
							empleado.getFechaIngreso(),
							empleado.getFechaEgreso()
							};
					ventanaABM.getModeloEmpleados().addRow(fila);
				}
			} else {
				Object[] fila = {
						empleado.getRol(),
						empleado.getApellido(),
						empleado.getNombre(),
						empleado.getDNI(),
						empleado.getEmail(),
						empleado.getTelefono(),
						empleado.getFechaIngreso(),
						empleado.getFechaEgreso()
						};
				ventanaABM.getModeloEmpleados().addRow(fila);
			}
		}
	}

	private void abrirVentanaAlta() {
		ventanaAM = new VentanaEmpleadoAM(rol);
		ventanaAM.botonAceptar().addActionListener(s -> aceptarAM());
		ventanaAM.botonCancelar().addActionListener(s -> cerrarVentanaAM());
		ventanaAM.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentanaAM();
			}
		});
		ventanaAM.getFechaIngreso().setDate(Almanaque.hoy());
		ventanaAM.setVisible(true);
		controlador.getVentana().setEnabled(false);
	}

	private void abrirVentanaModificacion() {
		List<Empleado> seleccionados = obtenerRegistrosSeleccionados();

		if (seleccionados.size() != 1) {
			Popup.mostrar("Seleccione exactamente un empleado para poder editarlo");
			return;
		}
		if(seleccionados.get(0).getFechaEgreso()!= null){
			Empleado emp = seleccionados.get(0);
			Popup.mostrar(emp.getApellido()+", " + emp.getNombre()+" ya no pertenece a la institucion");
			return;
		}

		ventanaAM = new VentanaEmpleadoAM(seleccionados.get(0), Sesion.getEmpleado().getRol());
		ventanaAM.botonAceptar().addActionListener(s -> aceptarAM());
		ventanaAM.botonCancelar().addActionListener(s -> cerrarVentanaAM());
		ventanaAM.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentanaAM();
			}
		});
		ventanaAM.setVisible(true);
		controlador.getVentana().setEnabled(false);
	}

	private void baja() {
		List<Empleado> seleccionados = obtenerRegistrosSeleccionados();

		if (seleccionados.size() == 0) {
			Popup.mostrar("Seleccione al menos un empleado para poder darlo de baja");
			return;
		}

		boolean mostrarMensaje = false;
		boolean mostrarMensajeYaBorrado = false;
		String mensaje = "Los siguientes empleados no pueden ser dados de baja porque tiene asignado al menos un curso:";
		String mensajeYaBorrado = "\nLos siguientes empleados ya no pertenecen a la institucion: ";
		
		if (Popup.confirmar("¿Esta seguro que quiere dar de baja los empleados seleccionados?")) {
			for (Empleado empleado : seleccionados) {
				if(empleado.getFechaEgreso()!=null){
					mostrarMensajeYaBorrado = true;
					mensajeYaBorrado += "\n    -" + empleado.getApellido() + ", " + empleado.getNombre();
				}
				else if (Instructor.tieneAsignaciones(empleado)) {
					mostrarMensaje = true;
					mensaje += "\n    -" + empleado.getApellido() + ", " + empleado.getNombre();
				} else
					EmpleadoManager.eliminarEmpleado(empleado);
			}
		}
		String msjErrorCompleto ="";
		if (mostrarMensaje)
			msjErrorCompleto += mensaje;
		if (mostrarMensajeYaBorrado)
			msjErrorCompleto += mensajeYaBorrado;
		if(mostrarMensaje || mostrarMensajeYaBorrado)
			Popup.mostrar(msjErrorCompleto);
		
		inicializar();

	}

	private void alta() {
		List<Empleado> seleccionados = obtenerRegistrosSeleccionados();

		if (seleccionados.size() == 0) {
			Popup.mostrar("Seleccione al menos un empleado para poder Darlo de alta");
			return;
		}

		boolean mostrarMensajeYaActivo = false;
		String mensajeYaActivo = "Los siguientes empleados ya estan activos:";
		
		if (Popup.confirmar("¿Esta seguro que quiere dar de alta los empleados seleccionados?")) {
			for (Empleado empleado : seleccionados) {
				if(empleado.getActivo()){
					mostrarMensajeYaActivo = true;
					mensajeYaActivo += "\n    -" + empleado.getApellido() + ", " + empleado.getNombre();
				}
				else
					EmpleadoManager.darDeAltaEmpleado(empleado);
			}
		}
	
		if(mostrarMensajeYaActivo)
			Popup.mostrar(mensajeYaActivo);
		
		inicializar();

	}
	
	private void cerrarVentanaAM() {
		int confirm = JOptionPane.showOptionDialog(null, "¿¡Esta seguro de salir sin guardar!?", "Confirmacion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == 0) {
			ventanaAM.dispose();
			ventanaAM = null;
			controlador.getVentana().setEnabled(true);
			controlador.getVentana().setVisible(true);
			controlador.getVentana().toFront();
		}
	}

	private void aceptarAM() {
		
		if (validarCampos()) {
			Empleado empleado = ventanaAM.getEmpleado();
			Rol rol = (Rol) ventanaAM.getRol().getSelectedItem();
			String apellido = ventanaAM.getApellido().getText();
			String nombre = ventanaAM.getNombre().getText();
			String dni = ventanaAM.getDNI().getText();
			String telefono = ventanaAM.getTelefono().getText();
			String email = ventanaAM.getEmail().getText();
			Date fechaIngreso = new Date(ventanaAM.getFechaIngreso().getDate().getTime());

			// Creo un nuevo empleado
			if (empleado == null) {
				EmpleadoManager.crearEmpleado(rol, dni, nombre, apellido, telefono, email, fechaIngreso, null);
				Popup.mostrar("El empleado se creado exitosamente");
			
			// Edito un empleado existente
			} else {
				empleado.setApellido(apellido);
				empleado.setNombre(nombre);
				empleado.setDNI(dni);
				empleado.setTelefono(telefono);
				empleado.setEmail(email);
				empleado.setFechaIngreso(fechaIngreso);
				empleado.setRol(rol);
				EmpleadoManager.modificarEmpleado(empleado);
				Popup.mostrar("El empleado se editado exitosamente");
			}

			ventanaAM.dispose();
			ventanaAM = null;
			controlador.getVentana().setEnabled(true);
			controlador.getVentana().setVisible(true);
			controlador.getVentana().toFront();
			inicializar();
		}
	}
	
	private void aceptarAMP() {
		if (validarCampos()) {
			Empleado empleado = ventanaAM.getEmpleado();
			Rol rol = (Rol) ventanaAM.getRol().getSelectedItem();
			String apellido = ventanaAM.getApellido().getText();
			String nombre = ventanaAM.getNombre().getText();
			String dni = ventanaAM.getDNI().getText();
			String telefono = ventanaAM.getTelefono().getText();
			String email = ventanaAM.getEmail().getText();
			Date fechaIngreso = new Date(ventanaAM.getFechaIngreso().getDate().getTime());

			// Creo uno nuevo
			if (empleado == null) {
				EmpleadoManager.crearEmpleado(rol, dni, nombre, apellido, telefono, email, fechaIngreso, null);
				Popup.mostrar("El empleado se creado exitosamente");
				
			// Edito uno existente
			} else {
				empleado.setApellido(apellido);
				empleado.setNombre(nombre);
				empleado.setDNI(dni);
				empleado.setTelefono(telefono);
				empleado.setEmail(email);
				empleado.setFechaIngreso(fechaIngreso);
				empleado.setRol(rol);
				EmpleadoManager.modificarEmpleado(empleado);
			}

			ventanaAM.dispose();
			ventanaAM = null;
			controlador.getVentana().setEnabled(true);
			controlador.getVentana().setVisible(true);
			controlador.getVentana().toFront();
		}
	}
/*
	private void cerrarVentanaABM() {
		ventanaABM.getVentana().dispose();
		ventanaABM = null;
		controlador.inicializar();
	}*/

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

		// VALIDAR DNI
		if (dni == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese el DNI.\n";
		} else if (!Validador.validarDNI(dni)) {
			isOk = false;
			mensaje += "    -El DNI solo puede consistir de numeros.\n";
		} else if (EmpleadoManager.estaEnUsoDNI(dni)) {
			Empleado empleadoEdicion = ventanaAM.getEmpleado();
			Empleado empleadoBD = EmpleadoManager.traerSegunDNI(dni);

			// caso: es un nuevo empleado
			if (empleadoEdicion == null) {
				isOk = false;
				mensaje += "    -El DNI ya esta siendo utilizado por otro empleado.\n";
			}

			// caso: se esta editando uno existente
			else if (empleadoBD.getID() != empleadoEdicion.getID()) {
				isOk = false;
				mensaje += "    -El DNI ya esta siendo utilizado por otro empleado.\n";
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
		} else if (telefono.length() > 20) {
			isOk = false;
			mensaje += "    -El TELEFONO debe tener una longitud maxima de 20\n";
		}

		if (email == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese el E-MAIL.\n";

		} else if (!Validador.validarEmail(email)) {
			isOk = false;
			mensaje += "    -El E-MAIL ingresado no es valido\n";
		} else if (email.length() > 50) {
			isOk = false;
			mensaje += "    -El E-MAIL debe tener una longitud maxima de 50\n";
		}

		if (ventanaAM.getFechaIngreso().getDate() == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese la FECHA DE INGRESO.\n";
		}

		if (isOk == false)
			JOptionPane.showMessageDialog(null, mensaje);

		return isOk;
	}

	private List<Empleado> obtenerRegistrosSeleccionados() {
		List<Empleado> registros = new ArrayList<Empleado>();
		int[] indices = ventanaABM.getTablaEmpleados().getSelectedRows();

		for (int indice : indices) {
			int registro = ventanaABM.getTablaEmpleados().convertRowIndexToModel(indice);
			registros.add(empleados.get(registro));
		}

		return registros;
	}

	public void actionPerformed(ActionEvent e){}

	@Override
	public boolean finalizar() {
		return true;
	}

	@Override
	public JInternalFrame getVentana() {
		return ventanaABM;
	}
	
}