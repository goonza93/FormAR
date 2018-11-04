package com.ungs.formar.negocios;

import java.sql.Date;
import java.util.List;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Interaccion;
import com.ungs.formar.persistencia.entidades.Interesado;
import com.ungs.formar.persistencia.interfaces.EmpleadoODB;
import com.ungs.formar.persistencia.interfaces.InteraccionOBD;
import com.ungs.formar.persistencia.interfaces.InteresadoOBD;

public class ContactoManager {
	
	public static void crearContacto(String dni, String nombre, String apellido, String telefono, String email) {
		Interesado interesado = new Interesado(-1, dni, nombre, apellido, telefono, email);
		InteresadoOBD odb = FactoryODB.crearInteresadoOBD();
		odb.insert(interesado);
	}

	public static void editarContacto(Interesado interesado) {
		InteresadoOBD odb = FactoryODB.crearInteresadoOBD();
		odb.update(interesado);
	}

	public static void eliminarContacto(Interesado interesado) {
		InteresadoOBD odb = FactoryODB.crearInteresadoOBD();
		odb.delete(interesado);
	}

	public static List<Interesado> traerContactos() {
		InteresadoOBD odb = FactoryODB.crearInteresadoOBD();
		return odb.select();
	}
	
	public static List<Interesado> traerContactoPorID(Integer id) {
		InteresadoOBD odb = FactoryODB.crearInteresadoOBD();
		return odb.selectByID(id);
	}
	
	public static boolean estaEnUsoDNI(String dni) {
		Interesado contacto = traerSegunDNI(dni);
		return contacto != null;
	}
	
	public static Interesado traerSegunDNI(String dni) {
		InteresadoOBD odb = FactoryODB.crearInteresadoOBD();
		return odb.selectByDNI(dni);
	}
	
	public static void crearInteraccion(Integer empleadoID, Integer interesadoID, Integer areaID, Integer cursoID, String descripcion) {
		Interaccion interaccion = new Interaccion(-1, empleadoID, interesadoID, areaID, cursoID, Almanaque.hoy(), descripcion);
		InteraccionOBD odb = FactoryODB.crearInteraccionOBD();
		odb.insert(interaccion);
	}

	public static void editarInteraccion(Interaccion interaccion) {
		InteraccionOBD odb = FactoryODB.crearInteraccionOBD();
		odb.update(interaccion);
	}

	public static void eliminarInteraccion(Interaccion interaccion) {
		InteraccionOBD odb = FactoryODB.crearInteraccionOBD();
		odb.delete(interaccion);
	}

	public static List<Interaccion> traerInteracciones() {
		InteraccionOBD odb = FactoryODB.crearInteraccionOBD();
		return odb.select();
	}
	
	public static List<Interaccion> traerInteraccionPorID(Integer id) {
		InteraccionOBD odb = FactoryODB.crearInteraccionOBD();
		return odb.selectByID(id);
	}
	
	public static List<Interaccion> traerInteraccionPorContacto(Integer id) {
		InteraccionOBD odb = FactoryODB.crearInteraccionOBD();
		return odb.selectByInteresado(id);
	}
	
	public static List<Interaccion> traerInteraccionPorEmpleado(Integer id) {
		InteraccionOBD odb = FactoryODB.crearInteraccionOBD();
		return odb.selectByEmpleado(id);
	}
	
}
