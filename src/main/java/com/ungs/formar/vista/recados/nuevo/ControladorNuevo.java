package com.ungs.formar.vista.recados.nuevo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.List;

import com.ungs.formar.negocios.Mensajero;
import com.ungs.formar.negocios.NotificacionManager;
import com.ungs.formar.persistencia.definidos.Rol;
import com.ungs.formar.persistencia.definidos.TipoNotificacion;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPrincipal;
import com.ungs.formar.vista.recados.ControladorRecados;
import com.ungs.formar.vista.seleccion.empleado.ControladorSeleccionarEmpleado;
import com.ungs.formar.vista.seleccion.empleado.EmpleadoSeleccionable;
import com.ungs.formar.vista.seleccion.empleado.VentanaSeleccionarEmpleado;
import com.ungs.formar.vista.util.Formato;
import com.ungs.formar.vista.util.Popup;
import com.ungs.formar.vista.util.Sesion;

public class ControladorNuevo implements ActionListener, EmpleadoSeleccionable{
	private ControladorRecados invocador;
	private ControladorPrincipal principal;
	private VentanaNuevo ventana;
	private List<Empleado> receptores;

	public ControladorNuevo(ControladorRecados invocador) {
		this.invocador = invocador;
		ventana = new VentanaNuevo();
		ventana.getSeleccionar().addActionListener(s -> seleccionarEmpleado());
		ventana.getCancelar().addActionListener(s -> volver());
		ventana.getEnviar().addActionListener(s -> enviar());
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				volver();
			}
		});/*
		this.invocador.getVentana().toFront();
		ventana.toFront();*/
	}
	
	public ControladorNuevo(ControladorPrincipal invocador) {
		this.principal = invocador;
		ventana = new VentanaNuevo();
		ventana.getSeleccionar().addActionListener(s -> seleccionarEmpleado());
		ventana.getCancelar().addActionListener(s -> volverP());
		ventana.getEnviar().addActionListener(s -> enviarP());
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				volverP();
			}
		});
		principal.getVentana().setEnabled(false);
	}
	
	public void actionPerformed(ActionEvent e) {}

	private void enviar() {
		Empleado emisor = Sesion.getEmpleado();
		String titulo = ventana.getTitulo().getText();
		String mensaje = ventana.getMensaje().getText();
		if(receptores==null || receptores.isEmpty()){
			Popup.mostrar("No hay ningun destinatario seleccionado.");
			return;
		}
		if(titulo.equals("")){
			titulo = "Sin titulo";
		}
		if(mensaje.equals("")){
			Popup.mostrar("El mensaje esta vacio.");
			return;
		}
		
		for(Empleado receptor: receptores){
			Mensajero.enviarMensaje(emisor, receptor, titulo, mensaje);
			String contenido = "El usuario "+ Formato.empleado(emisor.getID())+ " te ha enviado un recado.";
			Date asd = null;
			NotificacionManager.crearNotificacion(TipoNotificacion.RECADO, receptor.getID(), contenido, asd);
		}
		ventana.dispose();
		ventana = null;
		invocador.inicializar();
		invocador.habilitarPrincipal();
	}

	private void volver() {
		if(Popup.confirmar("¿Esta seguro que desea cancelar?")){
			ventana.dispose();
			ventana = null;
			invocador.inicializar();
			invocador.habilitarPrincipal();
		}
	}

	private void seleccionarEmpleado() {
		VentanaSeleccionarEmpleado v = new VentanaSeleccionarEmpleado(Rol.COMPLETO);
		new ControladorSeleccionarEmpleado(v, this, Rol.COMPLETO);
		ventana.deshabilitar();
	}
	
	private void enviarP() {
		Empleado emisor = Sesion.getEmpleado();
		String titulo = ventana.getTitulo().getText();
		String mensaje = ventana.getMensaje().getText();
		if(receptores==null || receptores.isEmpty()){
			Popup.mostrar("No hay ningun destinatario seleccionado.");
			return;
		}
		if(titulo.equals("")){
			titulo = "Sin titulo";
		}
		if(mensaje.equals("")){
			Popup.mostrar("El mensaje esta vacio.");
			return;
		}
		
		for(Empleado receptor: receptores){
			Mensajero.enviarMensaje(emisor, receptor, titulo, mensaje);
			String contenido = "El usuario "+ Formato.empleado(emisor.getID())+ " te ha enviado un recado.";
			Date asd = null;
			NotificacionManager.crearNotificacion(TipoNotificacion.RECADO, receptor.getID(), contenido, asd);
		}
		ventana.dispose();
		ventana = null;
		principal.getVentana().setEnabled(true);
		principal.getVentana().toFront();
		
		// si se esta viendo la tabla de enviados se recarga
		if (principal != null && principal.getControladorEnviados() != null)
			principal.getControladorEnviados().recargar();
	}

	private void volverP() {
		if(Popup.confirmar("¿Esta seguro que desea cancelar?")){
			ventana.dispose();
			ventana = null;
			principal.getVentana().setEnabled(true);
			principal.getVentana().toFront();;
		}
	}

	public void setEmpleado(List<Empleado> empleados) {
		String nombres = "";
		for(Empleado uno: empleados){
			nombres += uno.getNombre() +", ";
		}
		ventana.getDestinatario().setText(nombres);
		receptores = empleados;
	}

	public void mostrar() {
		
		ventana.mostrar();
		ventana.toFront();
	}
	
}