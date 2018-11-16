package com.ungs.formar.negocios;

import java.util.List;

import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Tarea;
import com.ungs.formar.persistencia.interfaces.TareaOBD;

public class TareaManager {
	
	public static void crearTarea(Integer empleadoID, String contenido){
		Tarea tarea = new Tarea(-1, empleadoID, contenido, true);
		TareaOBD odb = FactoryODB.crearTareaOBD();
		odb.insert(tarea);
	}
	
	public static void editarTarea(Tarea tarea) {
		TareaOBD odb = FactoryODB.crearTareaOBD();
		odb.update(tarea);
	}

	public static void eliminarTarea(Tarea tarea) {
		TareaOBD odb = FactoryODB.crearTareaOBD();
		odb.delete(tarea);
	}

	public static List<Tarea> traerTareas() {
		TareaOBD odb = FactoryODB.crearTareaOBD();
		return odb.select();
	}
	
}
