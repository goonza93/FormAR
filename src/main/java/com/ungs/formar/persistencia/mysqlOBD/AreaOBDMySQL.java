package com.ungs.formar.persistencia.mysqlOBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Area;
import com.ungs.formar.persistencia.interfacesOBD.AreaOBD;

public class AreaOBDMySQL extends ODB implements AreaOBD {
	private final String tabla = "for_areas";

	public List<Area> select() {
		String condicion = "1=1";
		List<Area> areas = selectByCondicion(condicion);
		return areas;
	}

	public Area selectByID(Integer ID) {
		String condicion = "area_ID ="+ID;
		List<Area> areas = selectByCondicion(condicion);
		return areas.get(0);
	}
	
	private List<Area> selectByCondicion(String condicion) {
		List<Area> areas = new ArrayList<Area>();
		String campos = "area_ID, nombre, descripcion";
		String comandoSQL = "select "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				areas.add(new Area(
						resultados.getInt("area_ID"),
						resultados.getString("nombre"),
						resultados.getString("descripcion")
						));
			}
			
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
		}
			
		return areas;
	}

}