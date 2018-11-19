package com.ungs.formar.vista.consulta.asignaciones;

import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.gestion.empleados.ControladorEmpleadoABM;

public class ControladorAsignaciones {
	ControladorEmpleadoABM invocador;
	VentanaAsignaciones ventana;
	
	public ControladorAsignaciones(ControladorEmpleadoABM invocador, Empleado empleado) {
		this.invocador = invocador;
		ventana = new VentanaAsignaciones(empleado);
		ventana.botonCerrar().addActionListener(e -> cerrar());
	}

	private void cerrar() {
		ventana.dispose();
		ventana = null;
		invocador.inicializar();
	}

}