package com.ungs.formar.negocios;

import java.util.Date;
import java.util.List;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.persistencia.interfaces.AreaOBD;
import com.ungs.formar.persistencia.interfaces.CursoODB;
import com.ungs.formar.persistencia.interfaces.ProgramaODB;
import com.ungs.formar.persistencia.entidades.Area;
import com.ungs.formar.persistencia.entidades.Curso;

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
	
	public static void crearPrograma(Integer areaID, Integer horas, String nombre, String descripcion, Date fechaAprobacion, String codigo){
		java.sql.Date fechaEnSql = new java.sql.Date(fechaAprobacion.getTime());
		Programa programa = new Programa(-1,areaID,horas,nombre,descripcion,fechaEnSql, codigo);
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
	
	public static Programa traerProgramaPorCodigo(String codigo) {
		ProgramaODB odb = FactoryODB.crearProgramaODB();
		return odb.selectByCodigo(codigo);
	}
	
	public static boolean codigoEnUso(String codigo){
		if(traerProgramaPorCodigo(codigo)!=null){
			return true;
		}
		return false;
	}
}