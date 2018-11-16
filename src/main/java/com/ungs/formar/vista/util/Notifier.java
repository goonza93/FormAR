package com.ungs.formar.vista.util;

import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.util.List;

import com.ungs.formar.negocios.Mensajero;
import com.ungs.formar.negocios.NotificacionManager;
import com.ungs.formar.persistencia.entidades.Notificacion;
import com.ungs.formar.persistencia.entidades.Recado;

public class Notifier implements Runnable {
	Integer lastCant;
	Integer lastNotif;
	TrayIcon trayIcon;
	boolean firstCant;
	boolean firstNotif;
	
	public Notifier(TrayIcon icon){
		firstCant = true;
		firstNotif = true;
		lastCant=0;
		lastNotif=0;
		trayIcon = icon;
	}

	public void run() {
		if(Sesion.getEmpleado()!=null){
			List<Recado> mensajes = Mensajero.traerMensajesSinLeer(Sesion.getEmpleado());
			String contenido = "";
			if(!mensajes.isEmpty() && lastCant<mensajes.size()){
				lastCant= mensajes.size();
				if(firstCant){
					if(mensajes.size()==1){
						contenido += "¡Tenes 1 mensaje sin leer!\n";
					} else {
						contenido += "¡Tenes "+mensajes.size()+" mensajes sin leer!\n";
					}
				}
				else{
					firstCant = false;
					contenido += "¡Tenes un mensaje nuevo!\n";
				}
			}
			// if notif.contenido.equals("Mensaje") then
			// al crear recados crear notif con el mensaje "Mensaje" para diferenciarlo de las normales
			// hay que sacar toda la mitad de arriba y usar notificaciones nomas
			
			List<Notificacion> notif = NotificacionManager.traerNotificacionesSinMostrar(Sesion.getEmpleado());
			if(!notif.isEmpty() && lastNotif<notif.size()){
				lastNotif = notif.size();
				if(firstNotif){
					if(notif.size()==1){
						contenido += "¡Tenes 1 notificacion!\n";
					}else{
						contenido += "¡Tenes "+notif.size()+" notificaciones!\n";
					}
				} else {
					contenido += "¡Tenes 1 notificacion nueva!\n";
				}
				for(Notificacion not:notif){
					not.setMostrado(true);
					NotificacionManager.editarNotificacion(not);
				}
			}
			
			if(!contenido.equals(""))
				trayIcon.displayMessage("Notificacion", contenido , MessageType.NONE);
		}
	}

}
