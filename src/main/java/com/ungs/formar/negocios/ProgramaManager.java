package com.ungs.formar.negocios;

import java.util.Date;
import java.util.List;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.persistencia.entidades.Area;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.persistencia.interfacesOBD.ProgramaODB;
import com.ungs.formar.persistencia.interfacesOBD.AreaOBD;
import com.ungs.formar.persistencia.interfacesOBD.CursoODB;
import com.ungs.formar.persistencia.interfacesOBD.HorarioCursadaOBD;
import com.ungs.formar.persistencia.interfacesOBD.ProgramaODB;

public class ProgramaManager {
	
	public static List<Programa> traerProgramas() {
		ProgramaODB odb = FactoryODB.crearProgramaODB();
		return odb.select();
	}
	
	public static Programa traerProgramaSegunID(Integer id){
		ProgramaODB odb = FactoryODB.crearProgramaODB();
		return odb.selectByID(id);
	}
	
	public static List<Area> traerAreasDeInteres() {
		AreaOBD obd = FactoryODB.crearAreaOBD();
		return obd.select();
	}

	public static Area traerAreaSegunID(Integer ID) {
		AreaOBD obd = FactoryODB.crearAreaOBD();
		return obd.selectByID(ID);
	}
	
	public static void crearPrograma(Integer areaID, Integer horas, String nombre, String descripcion, Date fechaAprobacion){
		java.sql.Date fechaEnSql = new java.sql.Date(fechaAprobacion.getTime());
		Programa programa = new Programa(-1,areaID,horas,nombre,descripcion,fechaEnSql);
		ProgramaODB odb = FactoryODB.crearProgramaODB();
		odb.insert(programa);
	}
	
	public static void editarPrograma(Programa programa) {
		ProgramaODB odb = FactoryODB.crearProgramaODB();
		odb.update(programa);
	}
	
	public static void eliminarPrograma(Programa programa) {
		ProgramaODB odb = FactoryODB.crearProgramaODB();
		odb.delete(programa);
	}
	
	public static boolean estaAsignado(Programa programa){
		CursoODB obd = FactoryODB.crearCursoODB();
		List<Curso> cursos = obd.selectByPrograma(programa.getProgramaID());
		
		return !(cursos.isEmpty());
	}
}