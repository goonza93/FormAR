package com.ungs.formar.persistencia.mysqlOBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Dia;
import com.ungs.formar.persistencia.interfacesOBD.DiaOBD;

public class DiaODBMySQL extends ODB implements DiaOBD{
	private final String tabla = "for_dias";

	public List<Dia> select() {
		String condicion = "1=1";
		List<Dia> dias = selectByCondicion(condicion);
		return dias;
	}

	public Dia selectByID(Integer ID) {
		return selectByCondicion("dia_ID="+ID).get(0);
	}
	
	private List<Dia> selectByCondicion(String condicion) {
		List<Dia> dias = new ArrayList<Dia>();
		String campos = "dia_ID, descripcion";
		String comandoSQL = "select "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				dias.add(new Dia(
						resultados.getInt("dia_ID"),
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
			
		return dias;
	}
	
}