package com.ungs.formar.vista.gestion.empleados;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.Instructor;
import com.ungs.formar.negocios.Validador;
import com.ungs.formar.persistencia.definidos.Rol;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.controladores.ControladorPantallaPrincipal;
import com.ungs.formar.vista.util.Popup;

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
			Object[] fila = { empleado.getApellido(), empleado.getNombre(), empleado.getDNI(), empleado.getEmail(),
					empleado.getTelefono(), empleado.getFechaIngreso(), empleado.getFechaEgreso() };
			ventanaABM.getModeloEmpleados().addRow(fila);
		}
	}

	public void actionPerformed(ActionEvent e) {

		// BOTON AGREGAR DEL ABM
		if (e.getSource() == ventanaABM.getAgregar()) {
			abrirVentanaAlta();
		}
		// BOTON CANCELAR DEL ABM
		else if (e.getSource() == ventanaABM.getCancelar()) {
			cerrarVentanaABM();
		}
		// BOTON EDITAR DEL ABM
		else if (e.getSource() == ventanaABM.getEditar()) {
			abrirVentanaModificacion();
		}
		// BOTON BORRAR DEL ABM
		else if (e.getSource() == ventanaABM.getBorrar()) {
			baja();
		}
		// BOTON ACEPTAR DEL AM
		else if (ventanaAM != null) {
			if (e.getSource() == ventanaAM.getAceptar()) {
				aceptarAM();
			}
			// BOTON CANCELAR DEL AM
			else if (e.getSource() == ventanaAM.getCancelar()) {
				cerrarVentanaAM();
			}
		}
	}

	private void abrirVentanaAlta() {
		ventanaAM = new VentanaEmpleadoAM(rol);
		ventanaAM.getAceptar().addActionListener(this);
		ventanaAM.getCancelar().addActionListener(this);
		ventanaAM.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentanaAM();
			}
		});
		ventanaAM.setVisible(true);
		ventanaABM.getVentana().setEnabled(false);
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

		ventanaAM = new VentanaEmpleadoAM(seleccionados.get(0), Rol.INSTRUCTOR);
		ventanaAM.getAceptar().addActionListener(this);
		ventanaAM.getCancelar().addActionListener(this);
		ventanaAM.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarVentanaAM();
			}
		});
		ventanaAM.setVisible(true);
		ventanaABM.getVentana().setEnabled(false);
	}

	private void baja() {
		List<Empleado> seleccionados = obtenerRegistrosSeleccionados();

		if (seleccionados.size() == 0) {
			Popup.mostrar("Seleccione al menos un empleado para poder borrarlo");
			return;
		}

		boolean mostrarMensaje = false;
		boolean mostrarMensajeYaBorrado = false;
		String mensaje = "Los siguientes empleados no pueden ser borrados porque tiene asignado al menos un curso:";
		String mensajeYaBorrado = "\nLos siguientes empleados ya no pertenecen a la institucion: ";
		
		if (Popup.confirmar("Esta seguro que quiere borrar los empleados seleccionados?")) {
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

	private void cerrarVentanaAM() {
		int confirm = JOptionPane.showOptionDialog(null, "Esta seguro de salir sin guardar!?", "Confirmacion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == 0) {
			ventanaAM.dispose();
			ventanaAM = null;
			ventanaABM.getVentana().setEnabled(true);
			ventanaABM.getVentana().setVisible(true);
		}
	}

	private void aceptarAM() {
		if (validarCampos()) {
			Empleado empleado = ventanaAM.getEmpleado();
			Rol rol = ventanaAM.getRol();
			String apellido = ventanaAM.getApellido().getText();
			String nombre = ventanaAM.getNombre().getText();
			String dni = ventanaAM.getDNI().getText();
			String telefono = ventanaAM.getTelefono().getText();
			String email = ventanaAM.getEmail().getText();
			Date fechaIngreso = new Date(ventanaAM.getFechaIngreso().getDate().getTime());

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
			ventanaAM = null;
			ventanaABM.getVentana().setEnabled(true);
			ventanaABM.getVentana().setVisible(true);
			inicializar();
		}
	}

	private void cerrarVentanaABM() {
		ventanaABM.getVentana().dispose();
		ventanaABM = null;
		controlador.inicializar();
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

}