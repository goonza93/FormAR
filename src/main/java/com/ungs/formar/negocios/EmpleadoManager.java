package com.ungs.formar.negocios;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.definidos.Rol;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.interfacesOBD.EmpleadoODB;

public class EmpleadoManager {

	public static void crearEmpleado(Rol rol, String DNI, String nombre, String apellido, String telefono, String email,
			Date fechaIngreso, Date fechaEgreso) {
		
		Empleado empleado = new Empleado(-1, DNI, nombre, apellido, telefono, email, nombre+"."+apellido, "123", fechaIngreso, fechaEgreso, rol, true);
		EmpleadoODB odb = FactoryODB.crearEmpleadoODB();
		odb.insert(empleado);
	}
		
	public static void eliminarEmpleado(Empleado empleado) {
		EmpleadoODB odb = FactoryODB.crearEmpleadoODB();
		empleado.setFechaEgreso(Almanaque.hoy());
		empleado.setActivo(false);
		odb.update(empleado);
	}
	
	public static void modificarEmpleado(Empleado empleado) {
		EmpleadoODB odb = FactoryODB.crearEmpleadoODB();
		odb.update(empleado);
	}

	public static List<Empleado> traerEmpleados() {
		EmpleadoODB odb = FactoryODB.crearEmpleadoODB();
		return odb.select();
	}
	
	public static List<Empleado> traerAdministrativos(){
		EmpleadoODB odb = FactoryODB.crearEmpleadoODB();
		return odb.selectByRol(Rol.ADMINISTRATIVO);
	}
	
	public static List<Empleado> traerInstructores(){
		EmpleadoODB odb = FactoryODB.crearEmpleadoODB();
		return odb.selectByRol(Rol.INSTRUCTOR);
	}
	
	public static List<Empleado> traerInstructoresActivos(){
		List<Empleado> instructores = traerInstructores();
		List<Empleado> ret = new ArrayList<Empleado>();
		for(Empleado e : instructores){
			if(e.getActivo())
				ret.add(e);
		}
		return ret;
	}
	
	public static List<Empleado> traerAdministrativosActivos(){
		List<Empleado> administrativos = traerAdministrativos();
		List<Empleado> ret = new ArrayList<Empleado>();
		for(Empleado e : administrativos){
			if(e.getActivo())
				ret.add(e);
		}
		return ret;
	}
	
	public static Empleado traerEmpleado(Integer id){
		EmpleadoODB odb = FactoryODB.crearEmpleadoODB();
		return odb.selectByID(id);
	}

	public static boolean estaEnUsoDNI(String dni) {
		Empleado empleado = traerSegunDNI(dni);
		return empleado != null;
	}

	public static Empleado traerSegunDNI(String dni) {
		EmpleadoODB odb = FactoryODB.crearEmpleadoODB();
		return odb.selectByDNI(dni);
	}
	
	public static Empleado traerSegunUsuario(String usuario) {
		EmpleadoODB odb = FactoryODB.crearEmpleadoODB();
		return odb.selectByUsuario(usuario);
	}
	
	public static Empleado traerSegunEmail(String email) {
		EmpleadoODB odb = FactoryODB.crearEmpleadoODB();
		return odb.selectByEmail(email);
	}

	public static void darDeAltaEmpleado(Empleado empleado) {
		empleado.setActivo(true);
		empleado.setFechaEgreso(null);
		modificarEmpleado(empleado);
	}

}