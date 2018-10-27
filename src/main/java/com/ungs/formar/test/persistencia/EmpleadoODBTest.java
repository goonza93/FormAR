package com.ungs.formar.test.persistencia;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.interfacesOBD.EmpleadoODB;

public class EmpleadoODBTest {

	public static void selectTest() {
		System.out.println("___ Select test");
		EmpleadoODB odb = FactoryODB.crearEmpleadoODB();
		List<Empleado> empleados = odb.select();
		System.out.println("Cantidad:"+odb.select().size());
		for (Empleado empleado: empleados)
			System.out.println(empleado.getNombre());
	}
	
	public static void main(String[] args) {
		
		selectTest();
	}

}