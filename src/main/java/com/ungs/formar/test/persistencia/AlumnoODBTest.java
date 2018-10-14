package com.ungs.formar.test.persistencia;

import java.util.List;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.interfacesOBD.AlumnoODB;

public class AlumnoODBTest {
	static AlumnoODB odb = FactoryODB.crearAlumnoODB();
	static Alumno test = new Alumno(-1, "37905622", "gonzalo", "bruckmann", "1531769550", "goonza.93@live.com.ar");
	
	public static void main(String[] args) {
		//testInsert();
		//testSelect();
		//testEdit();
		//testDelete();
	}
	
	public static void testInsert(){
		odb.insert(test);
	}
	
	public static void testSelect(){ // los 3 selects
		List<Alumno> lista = odb.select();
		System.out.println(lista.get(0).getNombre());
		Alumno testnombre = odb.selectByNombre(lista.get(0).getNombre());
		Alumno testID = odb.selectByID(lista.get(0).getClienteID());
		System.out.println(testnombre.getApellido());
		System.out.println(testID.getEmail());
	}
	
	public static void testEdit(){
		List<Alumno> lista = odb.select(); // traigo todos
		Alumno aEditar = lista.get(0);     // quiero editar el primero
		aEditar.setEmail("gonbruck1993@gmail.com");
		odb.update(aEditar);
		Alumno editado = odb.selectByID(aEditar.getClienteID());
		System.out.println(editado.getEmail());
	}
	
	public static void testDelete(){
		odb.delete(test);
	}
	

}