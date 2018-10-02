package com.ungs.formar.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ODB {
	protected String driver = "com.mysql.jdbc.Driver";
	
	// Ambiente Desarrollo
	protected String cadenaConexion = "jdbc:mysql://localhost:3306/formar"; 
	protected String usuarioBD = "root"; 
	protected String passwordBD = "root";
	
	
	// Ambiente Produccion
/*	
	protected String cadenaConexion = "jdbc:postgresql://ec2-54-243-252-91.compute-1.amazonaws.com:5432/d20o8pfi1cn2u5?sslmode=require";
	protected String usuarioBD = "szpprjpxgzpsyf"; 
	protected String passwordBD = "141e1a1197578265e25776156e0338124e6233c19daccf67b617458e09577282";
*/	
	
	// Ejecutar sentencias que no traigan resultados
	public void ejecutarSQL(String sql) {
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD);
			Statement sentencia = conexion.createStatement();
			sentencia.execute(sql);
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println("       ERROR: "+sql);
			e.printStackTrace();
		}
	}
	
}