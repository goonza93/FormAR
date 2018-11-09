package com.ungs.formar.vista.util;

import java.util.List;

import com.ungs.formar.negocios.Mensajero;
import com.ungs.formar.persistencia.entidades.Recado;

public class Notifier implements Runnable {

	public void run() {
		if(Sesion.getEmpleado()!=null){
			List<Recado> mensajes = Mensajero.traerMensajesSinLeer(Sesion.getEmpleado());
			if(!mensajes.isEmpty()){
				Popup.mostrar("Tenes mensajes nuevos!");
			}
		}
	}

}
