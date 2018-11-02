package com.ungs.formar.negocios;

import java.util.Date;
import java.util.List;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Area;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.persistencia.interfaces.AreaOBD;
import com.ungs.formar.persistencia.interfaces.CursoODB;
import com.ungs.formar.persistencia.interfaces.ProgramaODB;

public class AreaManager {

	public static List<Area> traerTodo() {
		AreaOBD obd = FactoryODB.crearAreaOBD();
		return obd.select();
	}

	public static void crearArea(String nombre, String descripcion) {
		Area area = new Area(-1, nombre, descripcion, true);
		AreaOBD odb = FactoryODB.crearAreaOBD();
		odb.insert(area);
	}

	public static void editarArea(Area area) {
		AreaOBD odb = FactoryODB.crearAreaOBD();
		odb.update(area);
	}

	public static void eliminarArea(Area area) {
		AreaOBD odb = FactoryODB.crearAreaOBD();
		odb.delete(area);
	}

	public static boolean estaAsignada(Area area) {
		ProgramaODB obd = FactoryODB.crearProgramaODB();
		List<Programa> programas = obd.selectByArea(area.getID());

		return !(programas.isEmpty());
	}

}
