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
	private VentanaEmpleadoABM ventanaEmpleadoABM;
	private VentanaEmpleadoAM ventanaEmpleadoAM;
	private ControladorPantallaPrincipal controlador;
	private List<Empleado> empleados;
	private Rol rol;
	
	public ControladorEmpleadoABM(VentanaEmpleadoABM ventanaEmpleadoABM, ControladorPantallaPrincipal controlador, Rol rol) {
		this.ventanaEmpleadoABM = ventanaEmpleadoABM;
		this.controlador = controlador;
		this.rol = rol;
		this.ventanaEmpleadoABM.getCancelar().addActionListener(this);
		this.ventanaEmpleadoABM.getAgregar().addActionListener(this);
		this.ventanaEmpleadoABM.getBorrar().addActionListener(this);
		this.ventanaEmpleadoABM.getEditar().addActionListener(this);
		this.inicializar();
	}

	public void inicializar() {
		llenarTabla();
		ventanaEmpleadoABM.mostrar();
	}

	private void llenarTabla() {
		ventanaEmpleadoABM.getModeloEmpleados().setRowCount(0);
		ventanaEmpleadoABM.getModeloEmpleados().setColumnCount(0);
		ventanaEmpleadoABM.getModeloEmpleados().setColumnIdentifiers(ventanaEmpleadoABM.getNombreColumnas());

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
			ventanaEmpleadoABM.getModeloEmpleados().addRow(fila);
		}
	}

	public void actionPerformed(ActionEvent e) {
		
		// BOTON AGREGAR DEL ABM
		if (e.getSource() == ventanaEmpleadoABM.getAgregar())
			mostrarEmpleadoAlta();

		// BOTON CANCELAR DEL ABM
		else if (e.getSource() == ventanaEmpleadoABM.getCancelar())
			cerrarEmpleadoABM();

		// BOTON EDITAR DEL ABM
		else if (e.getSource() == ventanaEmpleadoABM.getEditar())
			mostrarEmpleadoModificacion();

		// BOTON BORRAR DEL ABM
		else if (e.getSource() == ventanaEmpleadoABM.getBorrar())
			borrarEmpleado();

		// BOTON ACEPTAR DEL AM
		else if (e.getSource() == ventanaEmpleadoAM.getAceptar())
			aceptarEmpleado();
		
		// BOTON CANCELAR DEL AM
		else if (e.getSource() == ventanaEmpleadoAM.getCancelar())
			cerrarVentanaAM();
	}

	private void mostrarEmpleadoModificacion() {
		Empleado empleado = obtenerEmpleadoSeleccionado();
		ventanaEmpleadoAM = new VentanaEmpleadoAM(empleado, Rol.INSTRUCTOR);
		ventanaEmpleadoAM.getAceptar().addActionListener(this);
		ventanaEmpleadoAM.getCancelar().addActionListener(this);
		ventanaEmpleadoAM.setVisible(true);
		ventanaEmpleadoABM.ocultar();		
	}

	private void borrarEmpleado() {
		Empleado empleado = obtenerEmpleadoSeleccionado();
		if (empleado != null)
			EmpleadoManager.eliminarEmpleado(empleado);
		inicializar();		
	}

	private void cerrarVentanaAM() {
		ventanaEmpleadoAM.dispose();
		ventanaEmpleadoABM.mostrar();
	}

	private void aceptarEmpleado() {
		if (validarCampos()) {
			Empleado empleado = ventanaEmpleadoAM.getEmpleado();
			Rol rol = ventanaEmpleadoAM.getRol();
			String apellido = ventanaEmpleadoAM.getTxtApellido().getText();
			String nombre = ventanaEmpleadoAM.getTxtNombre().getText();
			String dni = ventanaEmpleadoAM.getTxtDni().getText();
			String telefono = ventanaEmpleadoAM.getTxtTelefono().getText();
			String email = ventanaEmpleadoAM.getTxtEmail().getText();
			Date fechaIngreso = new Date(ventanaEmpleadoAM.getDateFechaIngreso().getDate().getTime());
			
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
			
			ventanaEmpleadoAM.dispose();
			inicializar();
		}
	}

	private void cerrarEmpleadoABM() {
		ventanaEmpleadoABM.ocultar();
		controlador.inicializar();
	}

	private void mostrarEmpleadoAlta() {
		ventanaEmpleadoAM = new VentanaEmpleadoAM(Rol.INSTRUCTOR);
		ventanaEmpleadoAM.getAceptar().addActionListener(this);
		ventanaEmpleadoAM.getCancelar().addActionListener(this);
		ventanaEmpleadoAM.setVisible(true);
		ventanaEmpleadoABM.ocultar();
	}

	private boolean validarCampos() {
		String apellido = ventanaEmpleadoAM.getTxtApellido().getText();
		String nombre = ventanaEmpleadoAM.getTxtNombre().getText();
		String dni = ventanaEmpleadoAM.getTxtDni().getText();
		String telefono = ventanaEmpleadoAM.getTxtTelefono().getText();		
		String email = ventanaEmpleadoAM.getTxtEmail().getText();

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
		
		if (ventanaEmpleadoAM.getDateFechaIngreso().getDate() == null) {
			isOk = false;
			mensaje += "    -Por favor ingrese la FECHA DE INGRESO.\n";
		}
		
		if (isOk == false)
			JOptionPane.showMessageDialog(null, mensaje);
			
		return isOk;
	}
	
	private Empleado obtenerEmpleadoSeleccionado() {
		int registroTabla = ventanaEmpleadoABM.getTablaEmpleados().getSelectedRow(); //Indice de la tabla
		
		// No habia ningun registro seleccionado
		if (registroTabla == -1)
			return null;
		
		int registro = ventanaEmpleadoABM.getTablaEmpleados().convertRowIndexToModel(registroTabla); // Fix para el filtro
		return empleados.get(registro);
	}
	
}