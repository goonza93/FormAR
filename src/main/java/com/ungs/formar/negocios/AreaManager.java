package com.ungs.formar.negocios;

import java.util.Date;
import java.util.List;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Area;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.persistencia.interfacesOBD.AreaOBD;
import com.ungs.formar.persistencia.interfacesOBD.CursoODB;
import com.ungs.formar.persistencia.interfacesOBD.ProgramaODB;

public class AreaManager {
	
	public static List<Area> traerTodo() {
		AreaOBD obd = FactoryODB.crearAreaOBD();
		return obd.select();
	}
	
	public static void crearArea(String nombre, String descripcion){
	}
	
	public static void editarArea(Area area) {
		
	}
	
	public static void eliminarArea(Area area) {
	}
	
	public static boolean estaAsignada(Area area){
		return false;
	}


}
