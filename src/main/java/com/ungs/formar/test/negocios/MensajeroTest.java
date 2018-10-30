package com.ungs.formar.test.negocios;

import java.util.List;

import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.Mensajero;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Recado;

public class MensajeroTest {
	
	public static void enviarMensajeTest(Empleado emisor, Empleado receptor, String mensaje) {
		System.out.println("___ Enviar mensaje Test");
		Mensajero.enviarMensaje(emisor, receptor, mensaje);
	}

	public static void traerMensajesRecibidosTest(Empleado empleado) {
		System.out.println("___ Traer mensajes recibidos Test");
		List<Recado> recados = Mensajero.traerMensajesRecibidos(empleado);
		for (Recado recado : recados)
			System.out.println(recado.getMensaje());
		System.out.println("Cantidad de mensajes:"+recados.size());
	}
	

	public static void traerMensajesArchivadosTest(Empleado empleado) {
		System.out.println("___ Traer mensajes archivados Test");
		List<Recado> recados = Mensajero.traermensajesArchivados(empleado);
		for (Recado recado : recados)
			System.out.println(recado.getMensaje());
		System.out.println("Cantidad de mensajes:"+recados.size());
	}
	
	public static void main(String[] args) {
		Empleado empleado1 = EmpleadoManager.traerEmpleado(1);
		Empleado empleado2 = EmpleadoManager.traerEmpleado(2);
		enviarMensajeTest(empleado1, empleado2, "Mensaje 6");
		//traerMensajesRecibidosTest(empleado2);
		traerMensajesArchivadosTest(empleado2);
	}
	
}