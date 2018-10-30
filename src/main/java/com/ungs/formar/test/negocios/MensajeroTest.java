package com.ungs.formar.test.negocios;

import java.util.List;

import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.negocios.Mensajero;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Recado;

public class MensajeroTest {
	
	public static void enviarRecadoTest(Empleado emisor, Empleado receptor, String mensaje) {
		System.out.println("___ Enviar recado Test");
		Mensajero.enviarRecado(emisor, receptor, mensaje);
	}
	
	public static void traerRecadosRecibidosTest(Empleado empleado) {
		System.out.println("___ Traer recados recibidos Test");
		List<Recado> recados = Mensajero.traerRecadosRecibidos(empleado);
		for (Recado recado : recados)
			System.out.println(recado.getMensaje());
		System.out.println("Cantidad de recados:"+recados.size());
	}
	
	public static void main(String[] args) {
		Empleado empleado1 = EmpleadoManager.traerEmpleado(1);
		Empleado empleado2 = EmpleadoManager.traerEmpleado(2);
		enviarRecadoTest(empleado1, empleado2, "el ultimo mensaje!");
		traerRecadosRecibidosTest(empleado2);
	}
	
}