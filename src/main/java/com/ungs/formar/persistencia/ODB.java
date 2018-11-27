package com.ungs.formar.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ungs.formar.negocios.Configuracion;

public class ODB {
	protected String driver = "com.mysql.jdbc.Driver";
	protected String usuarioBD = Configuracion.leerUsuarioBD();
	protected String passwordBD = Configuracion.leerPasswordBD();
	protected String ipBD = Configuracion.leerIP();
	protected String puertoBD = Configuracion.leerPuerto();
	protected static Connection conexion = null;
	protected String bd = "formar_grupo4";
	protected final String cadenaConexion = "jdbc:mysql://" + ipBD + ":" + puertoBD
			+ "/formar_grupo4?autoReconnect=true&useSSL=false";

	// Ejecutar sentencias que no traigan resultados
	public void ejecutarSQL(String sql) {
		try {
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD);
			Statement sentencia = conexion.createStatement();
			sentencia.execute(sql);
			sentencia.close();
			conexion.close();

		} catch (Exception e) {
			System.out.println("       ERROR: " + sql);
			e.printStackTrace();
		}
	}

	public Integer selectLastID(String tabla) {
		String sql = "select ID from " + tabla + " order by ID desc limit 1";
		Integer ret = null;
		try {
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD);
			Statement sentencia = conexion.createStatement();
			ResultSet resultados = sentencia.executeQuery(sql);

			if (resultados.next())
				ret = resultados.getInt("ID");

			resultados.close();
			sentencia.close();
			conexion.close();

		} catch (Exception e) {
			System.out.println(sql);
			e.printStackTrace();
		}

		return ret;
	}

	public Connection getConexion() {
		if (conexion == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conexion;
	}

	public void desconectar() {
		try {
			conexion.close();
			conexion = null;
		} catch (Exception ex) {
		}
	}

}