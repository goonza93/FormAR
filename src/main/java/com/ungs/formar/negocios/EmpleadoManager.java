package com.ungs.formar.negocios;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.interfacesOBD.EmpleadoODB;

public class EmpleadoManager {
	
	public static List<Empleado> traerEmpleados() {
		EmpleadoODB odb = FactoryODB.crearEmpleadoODB();
		return odb.select();
	}
	
	public static List<Empleado> traerAdministrativos(){
		EmpleadoODB odb = FactoryODB.crearEmpleadoODB();
		return odb.selectByRol(1);
	}
	
	public static List<Empleado> traerInstructores(){
		EmpleadoODB odb = FactoryODB.crearEmpleadoODB();
		return odb.selectByRol(2);
	}
	
	public static Empleado traerEmpleado(Integer id){
		EmpleadoODB odb = FactoryODB.crearEmpleadoODB();
		return odb.selectByID(id);
	}

}
