package com.ungs.formar.vista.gestion.tareas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import com.ungs.formar.negocios.NotificacionManager;
import com.ungs.formar.negocios.TareaManager;
import com.ungs.formar.persistencia.definidos.TipoNotificacion;
import com.ungs.formar.persistencia.entidades.Tarea;
import com.ungs.formar.vista.pantallasPrincipales.ControladorInterno;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPrincipal;
import com.ungs.formar.vista.util.Formato;
import com.ungs.formar.vista.util.Popup;
import com.ungs.formar.vista.util.Sesion;

public class ControladorTareaABM implements ActionListener, ControladorInterno {
	private VentanaTareaABM ventanaABM;
	private VentanaTareaAM ventanaAM;
	private ControladorPrincipal controlador;
	private List<Tarea> tareas;

	public ControladorTareaABM(VentanaTareaABM ventanaSalaABM, ControladorPrincipal controlador) {
		this.ventanaABM = ventanaSalaABM;
		this.controlador = controlador;
		this.ventanaABM.getAgregar().addActionListener(s -> abrirAlta());
		this.ventanaABM.getBorrar().addActionListener(s -> borrar());
		this.ventanaABM.getEditar().addActionListener(s -> marcarRealizada());
		this.inicializar();
	}
	
	public ControladorTareaABM(ControladorPrincipal controlador) {
		this.controlador = controlador;
		ventanaAM = new VentanaTareaAM();
		ventanaAM.botonAceptar().addActionListener(s -> crearTareaP());
		ventanaAM.botonCancelar().addActionListener(s -> cerrarVentanaAM());
		ventanaAM.setVisible(true);
		controlador.getVentana().setEnabled(false);
	}

	public void inicializar() {
		llenarTabla();
		controlador.getVentana().setVisible(true);
	}

	private void llenarTabla() {
		ventanaABM.getModelo().setRowCount(0);
		ventanaABM.getModelo().setColumnCount(0);
		ventanaABM.getModelo().setColumnIdentifiers(ventanaABM.getNombreColumnas());

		tareas = TareaManager.traerTareas();
		for (Tarea tarea : tareas) {
			Object[] fila = {
					tarea.getContenido(),
					tarea.isPendiente()
					};
			ventanaABM.getModelo().addRow(fila);
		}
		ventanaABM.getTabla().removeColumn(ventanaABM.getTabla().getColumnModel().getColumn(1));
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
			controlador.getVentana().setEnabled(true);
			controlador.getVentana().toFront();
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
			NotificacionManager.crearNotificacion(TipoNotificacion.TAREA, Sesion.getEmpleado().getID(), contenidoNotificacion, fecha);
		}
		
		ventanaAM.dispose();
		ventanaAM = null;
		inicializar();
		controlador.getVentana().setEnabled(true);
		controlador.getVentana().toFront();
		
		// Si se esta visualizando la tabla de tareas le digo que se actualice
		if (controlador != null && controlador.getControladorTareaABM() != null)
			controlador.getControladorTareaABM().llenarTabla();
	}
	
	// Carlos: ¿Cual es la diferencia con el de arriba?
	private void crearTareaP() {
		// creo la tarea
		String contenido = ventanaAM.getContenido().getText();
		TareaManager.crearTarea(Sesion.getEmpleado().getID(), contenido);
		
		// crea la notificacion solo si puso fecha
		String hora = "hora"; //ventana.get
		String minutos = "minutos"; //ventana.get
		
		Date fecha = ventanaAM.getDateChooser().getDate() == null ? null : new Date(ventanaAM.getDateChooser().getDate().getTime());
		if(fecha != null) {
			String contenidoNotificacion = "Tenes una tarea pendiente para hoy";
			NotificacionManager.crearNotificacion(TipoNotificacion.TAREA, Sesion.getEmpleado().getID(), contenidoNotificacion, fecha);
		}
		
		ventanaAM.dispose();
		ventanaAM = null;
		controlador.getVentana().setEnabled(true);
		controlador.getVentana().toFront();

		// Si se esta visualizando la tabla de tareas le digo que se actualice
		if (controlador != null && controlador.getControladorTareaABM() != null)
			controlador.getControladorTareaABM().llenarTabla();
	}
	
	private void abrirAlta() {
		ventanaAM = new VentanaTareaAM();
		ventanaAM.botonAceptar().addActionListener(s -> crearTarea());
		ventanaAM.botonCancelar().addActionListener(s -> cerrarVentanaAM());
		ventanaAM.setVisible(true);
		controlador.getVentana().setEnabled(false);
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

	@Override
	public boolean finalizar() {
		return true;
	}

	@Override
	public JInternalFrame getVentana() {
		return ventanaABM;
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}

}