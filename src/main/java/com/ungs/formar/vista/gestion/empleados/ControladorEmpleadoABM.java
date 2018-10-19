package com.ungs.formar.vista.gestion.empleados;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.Validador;
import com.ungs.formar.persistencia.definidos.Rol;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.controladores.ControladorPantallaPrincipal;

public class ControladorEmpleadoABM implements ActionListener {
	private VentanaEmpleadoABM ventanaABM;
	private VentanaEmpleadoAM ventanaAM;
	private ControladorPantallaPrincipal controlador;
	private List<Empleado> empleados;
	private Rol rol;

	public ControladorEmpleadoABM(VentanaEmpleadoABM ventanaABM, ControladorPantallaPrincipal controlador, Rol rol) {
		this.ventanaABM = ventanaABM;
		this.controlador = controlador;
		this.rol = rol;
		this.ventanaABM.getAgregar().addActionListener(this);
		this.ventanaABM.getBorrar().addActionListener(this);
		this.ventanaABM.getEditar().addActionListener(this);
		this.ventanaABM.getCancelar().addActionListener(this);
		this.inicializar();
	}

	public void inicializar() {
		llenarTabla();
		ventanaABM.mostrar();
	}

	private void llenarTabla() {
		ventanaABM.getModeloEmpleados().setRowCount(0);
		ventanaABM.getModeloEmpleados().setColumnCount(0);
		ventanaABM.getModeloEmpleados().setColumnIdentifiers(ventanaABM.getNombreColumnas());

		if (rol == Rol.INSTRUCTOR)
			empleados = EmpleadoManager.traerInstructores();
		else if (rol == Rol.ADMINISTRATIVO)
			empleados = EmpleadoManager.traerAdministrativos();
		else if (rol == Rol.COMPLETO)
			empleados = EmpleadoManager.traerEmpleados();

		for (Empleado empleado : empleados) {
			Object[] fila = {
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

	public void actionPerformed(ActionEvent e) {

		// BOTON AGREGAR DEL ABM
		if (e.getSource() == ventanaABM.getAgregar())
			mostrarEmpleadoAlta();

		// BOTON CANCELAR DEL ABM
		else if (e.getSource() == ventanaABM.getCancelar())
			cerrarEmpleadoABM();

		// BOTON EDITAR DEL ABM
		else if (e.getSource() == ventanaABM.getEditar())
			mostrarEmpleadoModificacion();

		// BOTON BORRAR DEL ABM
		else if (e.getSource() == ventanaABM.getBorrar())
			borrarEmpleado();

		// BOTON ACEPTAR DEL AM
		else if (e.getSource() == ventanaAM.getAceptar())
			aceptarEmpleado();

		// BOTON CANCELAR DEL AM
		else if (e.getSource() == ventanaAM.getCancelar())
			cerrarVentanaAM();
	}

	private void mostrarEmpleadoModificacion() {
		Empleado empleado = obtenerEmpleadoSeleccionado();
		ventanaAM = new VentanaEmpleadoAM(empleado, Rol.INSTRUCTOR);
		ventanaAM.getAceptar().addActionListener(this);
		ventanaAM.getCancelar().addActionListener(this);
		ventanaAM.setVisible(true);
		ventanaABM.getFrame().setEnabled(false);

	}

	private void borrarEmpleado() {
		Empleado empleado = obtenerEmpleadoSeleccionado();
		if (empleado != null)
			EmpleadoManager.eliminarEmpleado(empleado);
		inicializar();
	}

	private void cerrarVentanaAM() {
		ventanaAM.dispose();
		ventanaABM.getFrame().setEnabled(true);
		ventanaABM.getFrame().toFront();
	}

	private void aceptarEmpleado() {
		if (validarCampos()) {
			Empleado empleado = ventanaAM.getEmpleado();
			Rol rol = ventanaAM.getRol();
			String apellido = ventanaAM.getTxtApellido().getText();
			String nombre = ventanaAM.getTxtNombre().getText();
			String dni = ventanaAM.getTxtDni().getText();
			String telefono = ventanaAM.getTxtTelefono().getText();
			String email = ventanaAM.getTxtEmail().getText();
			Date fechaIngreso = new Date(ventanaAM.getDateFechaIngreso().getDate().getTime());

			if (empleado == null)
				EmpleadoManager.crearEmpleado(rol, dni, nombre, apellido, telefono, email, fechaIngreso, null);
			else {
				empleado.setApellido(apellido);
				empleado.setNombre(nombre);
				empleado.setDNI(dni);
				empleado.setTelefono(telefono);
				empleado.setEmail(email);
				empleado.setFechaIngreso(fechaIngreso);
				EmpleadoManager.modificarEmpleado(empleado);
			}

			ventanaAM.dispose();
			inicializar();
		}
	}

	private void cerrarEmpleadoABM() {
		ventanaABM.ocultar();
		controlador.inicializar();
	}

	private void mostrarEmpleadoAlta() {
		ventanaAM = new VentanaEmpleadoAM(Rol.INSTRUCTOR);
		ventanaAM.getAceptar().addActionListener(this);
		ventanaAM.getCancelar().addActionListener(this);
		ventanaAM.setVisible(true);
		ventanaABM.getFrame().setEnabled(false);

	}

	private boolean validarCampos() {
		String apellido = ventanaAM.getTxtApellido().getText();
		String nombre = ventanaAM.getTxtNombre().getText();
		String dni = ventanaAM.getTxtDni().getText();
		String telefono = ventanaAM.getTxtTelefono().getText();
		String email = ventanaAM.getTxtEmail().getText();

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
			else if (empleadoBD.getEmpleadoID() != empleadoEdicion.getEmpleadoID()) {
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
		}else if (email.length()> 50) {
			isOk = false;
			mensaje += "    -El E-MAIL debe tener una longitud maxima de 50\n";
		}

		if (ventanaAM.getDateFechaIngreso().getDate() == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese la FECHA DE INGRESO.\n";
		}

		if (isOk == false)
			JOptionPane.showMessageDialog(null, mensaje);

		return isOk;
	}

	private Empleado obtenerEmpleadoSeleccionado() {
		int registroTabla = ventanaABM.getTablaEmpleados().getSelectedRow();
		if (registroTabla == -1)
			return null;
		int registro = ventanaABM.getTablaEmpleados().convertRowIndexToModel(registroTabla); // Fix
		return empleados.get(registro);
	}

}