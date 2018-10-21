package com.ungs.formar.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ODB {
	protected String driver = "com.mysql.jdbc.Driver";
	protected String cadenaConexion = "jdbc:mysql://localhost:3306/formar?autoReconnect=true&useSSL=false"; 
	protected String usuarioBD = "root"; 
	protected String passwordBD = "root";
	protected Connection connection = null;
	protected String bd ="formar";

		
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
	
	public Integer selectLastID(String ID, String tabla) {
		String sql = "select "+ID+" from "+tabla+" order by "+ID+" desc limit 1";
		Integer ret = null;
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(sql);			
	
			if (resultados.next())
				ret = resultados.getInt(ID);
				
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(sql);
			e.printStackTrace();
		}
			
		return ret;
	}
	
	public ODB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD);
            if (connection != null) {
                System.out.println("Conexión a base de datos " + bd + " OK\n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void desconectar() {
        try {
            connection.close();
        } catch (Exception ex) {
        }
    }

	
}