package com.ungs.formar.persistencia.mysqlOBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Sala;
import com.ungs.formar.persistencia.interfacesOBD.SalaODB;

public class SalaODBMySQL extends ODB implements SalaODB{
	private final String tabla = "for_salas";

	public List<Sala> select() {
		String condicion = "1=1";
		List<Sala> empleados = selectByCondicion(condicion);
		return empleados;
	}
	
	public Sala selectByID(Integer id){
		String condicion = "sala_ID = '"+id+"'";
		List<Sala> salas = selectByCondicion(condicion);
		Sala sala = null;
		if (salas.size()>0)
			sala = salas.get(0); 
		
		return sala;
	}

	private List<Sala> selectByCondicion(String condicion) {
		List<Sala> salas = new ArrayList<Sala>();
		String campos = "sala_ID, nombre, numero, capacidad";
		String comandoSQL = "select "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				salas.add(new Sala(
						resultados.getInt("sala_ID"),
						resultados.getInt("numero"),
						resultados.getInt("capacidad"),
						resultados.getString("nombre")
						));
			}
			
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
		}
			
		return salas;
	}
	
}