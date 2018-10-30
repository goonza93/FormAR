package com.ungs.formar.persistencia.mysqlOBD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Area;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Recado;
import com.ungs.formar.persistencia.interfacesOBD.RecadoOBD;

public class RecadoOBDMySQL extends ODB implements RecadoOBD{
	private final String tabla = "for_recados";
	private final String campos = "emisor, receptor, empleado, contenido, leido, archivado, fecha";


	public void insert(Recado recado) {
		// TODO Auto-generated method stub
		
	}

	public void update(Recado recado) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Recado recado) {
		// TODO Auto-generated method stub
		
	}

	public List<Recado> select() {
		String condicion = "true";
		List<Recado> areas = selectByCondicion(condicion);
		return areas;
	}

	public List<Recado> selectByReceptor(Empleado receptor) {
		String condicion = "receptor = "+receptor.getID();
		List<Recado> areas = selectByCondicion(condicion);
		return areas;
	}

	private List<Recado> selectByCondicion(String condicion) {
		List<Recado> areas = new ArrayList<Recado>();
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				areas.add(new Recado(
						resultados.getInt("ID"),
						resultados.getInt("empleado"),
						resultados.getInt("receptor"),
						resultados.getInt("emisor"),
						resultados.getString("contenido"),
						resultados.getBoolean("leido"),
						resultados.getBoolean("archivado"),
						resultados.getDate("fecha")
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
