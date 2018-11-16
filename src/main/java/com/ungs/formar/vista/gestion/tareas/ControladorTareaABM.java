package com.ungs.formar.vista.gestion.tareas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.ungs.formar.negocios.NotificacionManager;
import com.ungs.formar.negocios.TareaManager;
import com.ungs.formar.persistencia.entidades.Tarea;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPantallaPrincipal;
import com.ungs.formar.vista.util.Popup;
import com.ungs.formar.vista.util.Sesion;

public class ControladorTareaABM implements ActionListener {
	private VentanaTareaABM ventanaABM;
	private VentanaTareaAM ventanaAM;
	private ControladorPantallaPrincipal controlador;
	private List<Tarea> tareas;

	public ControladorTareaABM(VentanaTareaABM ventanaSalaABM, ControladorPantallaPrincipal controlador) {
		this.ventanaABM = ventanaSalaABM;
		this.controlador = controlador;
		this.ventanaABM.getCancelar().addActionListener(this);
		this.ventanaABM.getAgregar().addActionListener(this);
		this.ventanaABM.getBorrar().addActionListener(this);
		this.ventanaABM.getEditar().addActionListener(this);
		this.inicializar();
	}

	public void inicializar() {
		llenarTabla();
		ventanaABM.mostrar();
	}

	private void llenarTabla() {
		ventanaABM.getModelo().setRowCount(0);
		ventanaABM.getModelo().setColumnCount(0);
		ventanaABM.getModelo().setColumnIdentifiers(ventanaABM.getNombreColumnas());

		tareas = TareaManager.traerTareas();
		for (Tarea tarea : tareas) {
			Object[] fila = { tarea.getContenido() , tarea.isPendiente() };
			ventanaABM.getModelo().addRow(fila);
		}
		ventanaABM.getTabla().removeColumn(ventanaABM.getTabla().getColumnModel().getColumn(1));
	}

	public void actionPerformed(ActionEvent e) {

		// BOTON AGREGAR DEL ABM
		if (e.getSource() == ventanaABM.getAgregar()){
			abrirAlta();
		}
		// BOTON CANCELAR DEL ABM
		else if (e.getSource() == ventanaABM.getCancelar()){
			cerrarVentanaABM();
		}
		// BOTON EDITAR DEL ABM
		else if (e.getSource() == ventanaABM.getEditar()){
			marcarRealizada();
		}
		// BOTON BORRAR DEL ABM
		else if (e.getSource() == ventanaABM.getBorrar()){
			borrar();
		}
		else if (ventanaAM != null) {
			// BOTON ACEPTAR DEL AM
			if (e.getSource() == ventanaAM.getAceptar()){
				crearTarea();
			}
			// BOTON CANCELAR DEL AM
			else if (e.getSource() == ventanaAM.getCancelar()){
				cerrarVentanaAM();
			}
		}
	}

	private void marcarRealizada() {
		List<Tarea> tareas = obtenerSeleccionados();
		if (tareas.size() == 0) {
			Popup.mostrar("Para marcar como realizadas seleccione al menos una tarea.");
			return;
		}
		if(Popup.confirmar("¿Esta seguro desea marcar como realizadas lo seleccionado?")){
			for(Tarea tarea : tareas){
				tarea.setPendiente(false);
				TareaManager.editarTarea(tarea);
			}
		}
		llenarTabla();
	}

	private void borrar() {
		List<Tarea> tareas = obtenerSeleccionados();
		if (tareas.size() == 0) {
			Popup.mostrar("Para borrar seleccione al menos una tarea.");
			return;
		}
		if(Popup.confirmar("¿Esta seguro que desea borrar lo seleccionado?")){
			for(Tarea tarea : tareas){
				TareaManager.eliminarTarea(tarea);
			}
		}
		llenarTabla();
	}

	private void cerrarVentanaAM() {
		int confirm = JOptionPane.showOptionDialog(null, "¿¡Esta seguro de salir sin guardar!?",
				"Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == 0) {
			ventanaAM.dispose();
			ventanaAM = null;
			ventanaABM.getFrame().setEnabled(true);
			ventanaABM.getFrame().toFront();
		}
	}

	private void crearTarea() {
		// creo la tarea
		String contenido = ventanaAM.getContenido().getText();
		TareaManager.crearTarea(Sesion.getEmpleado().getID(), contenido);
		
		// crea la notificacion solo si puso fecha
		Date fecha = ventanaAM.getDateChooser().getDate() == null ? null : new Date(ventanaAM.getDateChooser().getDate().getTime());
		if(fecha != null) {
			String contenidoNotificacion = "Tenes una tarea pendiente para hoy";
			NotificacionManager.crearNotificacion(Sesion.getEmpleado().getID(), contenidoNotificacion, fecha);
		}
		
		ventanaAM.dispose();
		ventanaAM = null;
		inicializar();
		ventanaABM.getFrame().setEnabled(true);
	}

	private void cerrarVentanaABM() {
		ventanaABM.ocultar();
		controlador.inicializar();
	}

	private void abrirAlta() {
		ventanaAM = new VentanaTareaAM();
		ventanaAM.getAceptar().addActionListener(this);
		ventanaAM.getCancelar().addActionListener(this);
		ventanaAM.setVisible(true);
		ventanaABM.getFrame().setEnabled(false);
	}

	private List<Tarea> obtenerSeleccionados() {
		List<Tarea> ret = new ArrayList<Tarea>();
		int[] registroTabla = ventanaABM.getTabla().getSelectedRows(); //Indice de la tabla
		// No habia ningun registro seleccionado
		for (int fila : registroTabla) {
			int registro = ventanaABM.getTabla().convertRowIndexToModel(fila); // Fix para el filtro
			ret.add(tareas.get(registro));
		}
		return ret;
	}
}
