package com.ungs.formar.persistencia.mysqlOBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.persistencia.interfacesOBD.ProgramaODB;

public class ProgramaODBMySQL extends ODB implements ProgramaODB{
	private final String tabla = "for_programas";

	public List<Programa> select() {
		String condicion = "1=1";
		List<Programa> programas= selectByCondicion(condicion);
		return programas;
	}

	private List<Programa> selectByCondicion(String condicion) {
		List<Programa> programas = new ArrayList<Programa>();
		String campos = "programa_ID, area, nombre, fecha_aprobacion, descripcion";
		String comandoSQL = "select "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				programas.add(new Programa(
						resultados.getInt("programa_ID"),
						resultados.getInt("area"),
						resultados.getString("nombre"),
						resultados.getString("descripcion"),
						resultados.getDate("fecha_aprobacion")
						));
			}
			
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
		}
			
		return programas;
	}
	
}