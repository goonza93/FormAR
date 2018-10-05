package com.ungs.formar.test.negocios;

import java.util.List;
import com.ungs.formar.negocios.EmpleadoManager;
import com.ungs.formar.persistencia.entidades.Empleado;

public class EmpleadoManagerTest {

	public static void traerEmpleadosTest() {
		System.out.println("___ Traer empleados Test");
		List<Empleado> empleados = EmpleadoManager.traerEmpleados();
		for (Empleado empleado: empleados)
			System.out.println(empleado.getNombre());
		System.out.println("Cantidad de empleados:"+empleados.size());
	}
	
	public static void main(String[] args) {
		traerEmpleadosTest();
	}

}