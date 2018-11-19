package com.ungs.formar.vista.seleccion.empleado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.persistencia.definidos.Rol;
import com.ungs.formar.persistencia.entidades.Empleado;

public class ControladorSeleccionarEmpleado implements ActionListener {
	private VentanaSeleccionarEmpleado ventana;
	private EmpleadoSeleccionable invocador;
	private List<Empleado> empleados;
	private Rol rol;

	public ControladorSeleccionarEmpleado(VentanaSeleccionarEmpleado ventana, EmpleadoSeleccionable invocador, Rol rol) {
		this.ventana = ventana;
		this.invocador = invocador;
		this.rol = rol;
		this.ventana.getBtnCancelar().addActionListener(this);
		this.ventana.getBtnSeleccionar().addActionListener(this);
		llenarTablaInstructores();
		this.ventana.setVisible(true);
	}

	private void llenarTablaInstructores() {
		ventana.getModelEmpleados().setRowCount(0);
		ventana.getModelEmpleados().setColumnCount(0);
		ventana.getModelEmpleados().setColumnIdentifiers(ventana.getNombreColumnas());
		
		if (rol.equals(Rol.INSTRUCTOR))
			empleados = EmpleadoManager.traerInstructores();
		
		if (rol.equals(Rol.ADMINISTRATIVO))
			empleados = EmpleadoManager.traerAdministrativos();
		
		if (rol.equals(Rol.COMPLETO))
			empleados = EmpleadoManager.traerEmpleadosActivos();
		
		for (Empleado empleado : empleados) {
			Object[] fila = {
					empleado.getApellido(),
					empleado.getNombre(),
					empleado.getDNI()
					};
			ventana.getModelEmpleados().addRow(fila);
		}			

	}

	public void actionPerformed(ActionEvent e) {
		// BOTON SELECCIONAR
		if (e.getSource() == ventana.getBtnSeleccionar())
			seleccionarEmpleados();
	
		// BOTON CANCELAR
		else if (e.getSource() == ventana.getBtnCancelar())
			cancelar();
	}
	
	private void seleccionarEmpleados() {		
		List<Empleado> registros = new ArrayList<Empleado>();
		int[] indices = ventana.getTablaEmpleados().getSelectedRows();

		for (int indice : indices) {
			int registro = ventana.getTablaEmpleados().convertRowIndexToModel(indice);
			registros.add(empleados.get(registro));
		}
		invocador.setEmpleado(registros);
		ventana.dispose();
		invocador.mostrar();
	}
		
	private void cancelar() {
		ventana.dispose();
		invocador.mostrar();
	}
	
}