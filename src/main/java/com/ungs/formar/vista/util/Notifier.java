package com.ungs.formar.vista.util;

import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.util.List;
import com.ungs.formar.negocios.Almanaque;
import com.ungs.formar.negocios.Mensajero;
import com.ungs.formar.negocios.NotificacionManager;
import com.ungs.formar.persistencia.definidos.TipoNotificacion;
import com.ungs.formar.persistencia.entidades.Notificacion;
import com.ungs.formar.persistencia.entidades.Recado;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPrincipal;

public class Notifier implements Runnable {
	ControladorPrincipal principal;
	Integer lastCant;
	TrayIcon trayIcon;
	
	public Notifier(TrayIcon icon,ControladorPrincipal prin){
		this.principal = prin;
		lastCant=0;
		trayIcon = icon;
	}

	public void run() {
		if(Sesion.getEmpleado()!=null){
			mostrarNotificaciones();					
			actualizarMenuBar();
		}
	}

	private void mostrarNotificaciones(){
		String contenido = "";
		List<Notificacion> notif = NotificacionManager.traerNotificacionesSinMostrar(Sesion.getEmpleado());
		int cantNotif=0;
		int cantRecado=0;
		for(Notificacion not:notif){
			if(not.getTipo()==TipoNotificacion.RECADO){
				cantRecado++;
			} else{
				if(not.getFechaAMostrar().after(Almanaque.hoy()));
					cantNotif++;
			}
			not.setMostrado(true);
			NotificacionManager.editarNotificacion(not);
		}
		
		if(!notif.isEmpty()){
			if(cantRecado ==1){
				contenido += "Tenes 1 mensaje nuevo!\n";
			} else if(cantRecado>1){
				contenido += "Tenes "+cantRecado+" mensajes nuevos!\n";
			}
			if(cantNotif==1){
				contenido += "Tenes 1 notificacion nueva!\n";
			} else if(cantNotif>1){
				contenido += "Tenes "+cantNotif+" notificaciones nuevas!\n";
			}
		}
		
		if(!contenido.equals(""))
			trayIcon.displayMessage("Notificacion", contenido , MessageType.NONE);
	}
	
	private void actualizarMenuBar() {
		actualizarRecados();
		actualizarNotificaciones();
	}

	private void actualizarRecados() {
		List<Recado> nuevos = Mensajero.traerMensajesSinLeer(Sesion.getEmpleado());
		if(!nuevos.isEmpty()){
			principal.getMenuRecados().setText("  Recados("+nuevos.size()+")");
		} else {
			principal.getMenuRecados().setText("  Recados");
		}
	}

	private void actualizarNotificaciones() {
		List<Notificacion> nuevos = NotificacionManager.traerNotificacionesSinLeer(Sesion.getEmpleado());
		if(!nuevos.isEmpty()){
			// Si hay nuevas notificaciones
			principal.getMenuNotificaciones().setText("  Notificaciones("+nuevos.size()+")");
			if (principal.getControladorNotificaciones() != null)
				principal.getControladorNotificaciones().llenarTabla();
			
		} else {
			principal.getMenuNotificaciones().setText("  Notificaciones");
		}
	}

}
