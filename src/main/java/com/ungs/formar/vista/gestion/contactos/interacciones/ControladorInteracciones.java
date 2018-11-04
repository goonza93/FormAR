package com.ungs.formar.vista.gestion.contactos.interacciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.ungs.formar.negocios.AreaManager;
import com.ungs.formar.negocios.ContactoManager;
import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.ProgramaManager;
import com.ungs.formar.persistencia.entidades.Area;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Interaccion;
import com.ungs.formar.persistencia.entidades.Interesado;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.vista.gestion.contactos.ControladorContactos;

public class ControladorInteracciones implements ActionListener {
	private VentanaInteracciones ventanaGestionarInteracciones;
	private VentanaInteraccionesAM ventanaAM;
	private Interesado contactoSeleccionado;
	private ControladorContactos controlador;
	private List<Interaccion> interacciones;
	
	public ControladorInteracciones(VentanaInteracciones ventana, ControladorContactos invocador, Interesado seleccionado){
		this.ventanaGestionarInteracciones = ventana;
		this.controlador = invocador;
		this.contactoSeleccionado = seleccionado;
		this.ventanaGestionarInteracciones.getAgregar().addActionListener(this);
		this.ventanaGestionarInteracciones.getBorrar().addActionListener(this);
		this.ventanaGestionarInteracciones.getEditar().addActionListener(this);
		this.ventanaGestionarInteracciones.getCancelar().addActionListener(this);
		this.ventanaGestionarInteracciones.getVentana().setTitle("GESTION DE INTERACCIONES");
		this.inicializar();
	}
	
	public void inicializar() {
		llenarTabla();
		ventanaGestionarInteracciones.mostrar();
	}

	private void llenarTabla() {
		ventanaGestionarInteracciones.getModeloInteracciones().setRowCount(0);
		ventanaGestionarInteracciones.getModeloInteracciones().setColumnCount(0);
		ventanaGestionarInteracciones.getModeloInteracciones().setColumnIdentifiers(ventanaGestionarInteracciones.getNombreColumnas());
		
		interacciones = ContactoManager.traerInteraccionPorContacto(contactoSeleccionado.getID());
		
		for (Interaccion interaccion : interacciones) {
			Empleado empleado = EmpleadoManager.traerEmpleado(interaccion.getEmpleadoID());
			Area area = AreaManager.traerPorID(interaccion.getAreaID());
			String areaNombre = area == null ? "" : area.getNombre();
			Programa programa = ProgramaManager.traerProgramaSegunID(interaccion.getCursoID());
			String programaNombre = programa == null ? "" : programa.getNombre();
			Object[] fila = { empleado.getApellido()+", "+empleado.getNombre(), interaccion.getFechaInteraccion(),
					areaNombre, programaNombre, interaccion.getDescripcion()};
			ventanaGestionarInteracciones.getModeloInteracciones().addRow(fila);
		}
	}

	public void actionPerformed(ActionEvent e) {
		// BOTON AGREGAR DEL ABM
		if (e.getSource() == ventanaGestionarInteracciones.getAgregar()) {
			//abrirVentanaAlta();
		}
		// BOTON CANCELAR DEL ABM
		else if (e.getSource() == ventanaGestionarInteracciones.getCancelar()) {
			cerrarVentanaInteracciones();
		}
		// BOTON EDITAR DEL ABM
		else if (e.getSource() == ventanaGestionarInteracciones.getEditar()) {
			//abrirVentanaModificacion();
		}
		// BOTON BORRAR DEL ABM
		else if (e.getSource() == ventanaGestionarInteracciones.getBorrar()) {
			//borrar();
		}
		/*
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
		*/
	}

	private void cerrarVentanaInteracciones() {
		ventanaGestionarInteracciones.getVentana().dispose();
		ventanaGestionarInteracciones = null;
		controlador.inicializar();
	}

}
