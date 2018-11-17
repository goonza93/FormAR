package com.ungs.formar.persistencia.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Tarea;
import com.ungs.formar.persistencia.interfaces.TareaOBD;

public class TareaOBDMySQL extends ODB implements TareaOBD {
	private final String campos = "empleado, contenido, pendiente";
	private final String tabla = "for_tareas";

	public void insert(Tarea tarea) {
		String empleado = "'"+tarea.getEmpleado()+"'";
		String contenido = "'"+tarea.getContenido()+"'";
		
		String valores = empleado
				+", "+ contenido
				+", "+ tarea.isPendiente();
				
		String consulta = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(consulta);
	}

	public void update(Tarea tarea) {
		String empleado = "'"+tarea.getEmpleado()+"'";
		String contenido = "'"+tarea.getContenido()+"'";
		String condicion = "ID ="+tarea.getID();
		
		String consulta = "update " + tabla
				+" set empleado = "+ empleado
				+", contenido = "+ contenido
				+", pendiente = "+ tarea.isPendiente()
				+"  where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	public void delete(Tarea tarea) {
		String condicion = "ID = "+tarea.getID();
		String consulta = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	public List<Tarea> select() {
		String condicion = "true";
		List<Tarea> tareas = selectByCondicion(condicion);
		return tareas;
	}

	private List<Tarea> selectByCondicion(String condicion) {
		List<Tarea> tareas = new ArrayList<Tarea>();
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				tareas.add(new Tarea(
						resultados.getInt("ID"),
						resultados.getInt("empleado"),
						resultados.getString("contenido"),
						resultados.getBoolean("pendiente")
						));
				}
			
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
		}
			
		return tareas;
	}

}
