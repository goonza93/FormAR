package com.ungs.formar.vista.prueba.seleccion.empleado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
			empleados = EmpleadoManager.traerEmpleados();
		
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
			seleccionarEmpleado();
	
		// BOTON CANCELAR
		else if (e.getSource() == ventana.getBtnCancelar())
			cancelar();
	}
	
	private void seleccionarEmpleado() {
		int fila_seleccionada = ventana.getTablaEmpleados().getSelectedRow();
		if (fila_seleccionada != -1) {
			// ESTE ES EL FIX PARA QUE FUNCIONE TMB CON FILTROS...
			// BASICAMENTE TOMO EL INDICE DE LA ROW Y LA TRADUZCO A LA DEL MODEL QUE EL CORRESPONDE
			int row = ventana.getTablaEmpleados().getSelectedRow(); // indice row de la tabla
			int modelFila = ventana.getTablaEmpleados().convertRowIndexToModel(row); // indice row del model de 
			//la row de la tabla
			
			invocador.setEmpleado(empleados.get(modelFila));
			ventana.dispose();
			invocador.inicializar();
		}
	}
		
	private void cancelar() {
		ventana.dispose();
		invocador.inicializar();
	}
	
}