package com.ungs.formar.persistencia.mysqlOBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.interfacesOBD.EmpleadoODB;

public class EmpleadoODBMySQL extends ODB implements EmpleadoODB{
	private final String tabla = "for_empleados";

	public List<Empleado> select() {
		String condicion = "1=1";
		List<Empleado> empleados = selectByCondicion(condicion);
		return empleados;
	}

	private List<Empleado> selectByCondicion(String condicion) {
		List<Empleado> empleados = new ArrayList<Empleado>();
		String campos = "empleado_ID, dni, nombre, apellido, telefono, email, fecha_ingreso, fecha_egreso";
		String comandoSQL = "select "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				empleados.add(new Empleado(
						resultados.getInt("empleado_ID"),
						resultados.getString("legajo"),
						resultados.getString("nombre"),
						resultados.getString("apellido"),
						resultados.getString("telefono"),
						resultados.getString("email"),
						resultados.getDate("fecha_ingreso"),
						resultados.getDate("fecha_egreso")
						));
				}
			
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
		}
			
		return empleados;
	}
	
}