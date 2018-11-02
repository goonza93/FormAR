package com.ungs.formar.persistencia.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Area;
import com.ungs.formar.persistencia.interfaces.AreaOBD;

public class AreaOBDMySQL extends ODB implements AreaOBD {
	private final String tabla = "for_areas";
	private final String campos = "nombre, descripcion, activo";

	public void insert (Area area) {
		String valores =
				"'"+area.getNombre()+"'"
				+", '"+area.getDescripcion()+"'"
				+", "+true;
						
		String consulta = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(consulta);
	}
	
	public void update(Area area) {
		String condicion = "ID = "+area.getID();
		String valores = "nombre = '"+area.getNombre()+"'" 
				+", descripcion = '"+area.getDescripcion()+"'"
				+", activo = "+area.getActivo();
		String consulta = "update "+tabla+" set "+valores+"  where ("+condicion+");";
		ejecutarSQL(consulta);
	}
	
	public void delete(Area area){
		String condicion = "ID = "+area.getID();
		String consulta = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	public List<Area> select() {
		String condicion = "true";
		List<Area> areas = selectByCondicion(condicion);
		return areas;
	}

	public Area selectByID(Integer ID) {
		String condicion = "ID = "+ID;
		List<Area> areas = selectByCondicion(condicion);
		if (areas.size()>0)
			return areas.get(0);
		return null;
	}
	
	private List<Area> selectByCondicion(String condicion) {
		List<Area> areas = new ArrayList<Area>();
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				areas.add(new Area(
						resultados.getInt("ID"),
						resultados.getString("nombre"),
						resultados.getString("descripcion"),
						resultados.getBoolean("activo")
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