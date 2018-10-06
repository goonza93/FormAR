package com.ungs.formar.persistencia.mysqlOBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.EstadoCurso;
import com.ungs.formar.persistencia.interfacesOBD.EstadoCursoOBD;

public class EstadoCursoODBMySQL extends ODB implements EstadoCursoOBD {
	private final String tabla = "for_estados_curso";

	public List<EstadoCurso> select() {
		String condicion = "1=1";
		List<EstadoCurso> estados = selectByCondicion(condicion);
		return estados;
	}

	public EstadoCurso selectByID(Integer ID) {
		String condicion = "estado_ID = "+ID;
		List<EstadoCurso> estados = selectByCondicion(condicion);
		return estados.get(0);
	}
	
	private List<EstadoCurso> selectByCondicion(String condicion) {
		List<EstadoCurso> estados = new ArrayList<EstadoCurso>();
		String campos = "estado_ID, descripcion";
		String comandoSQL = "select "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				estados.add(new EstadoCurso(
						resultados.getInt("estado_ID"),
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
			
		return estados;
	}

}