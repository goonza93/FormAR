package com.ungs.formar.persistencia.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Asistencia;
import com.ungs.formar.persistencia.interfaces.AsistenciaOBD;

public class AsistenciaOBDMySQL extends ODB implements AsistenciaOBD {
	private final String tabla = "for_asistencias";
	private final String campos = "curso, alumno, fecha, presente";

	public void insert (Asistencia asistencia) {
		String valores = asistencia.getCurso()
				+", "+asistencia.getAlumno()
				+", '"+asistencia.getFecha()+"'"
				+", "+asistencia.isPresente();
		String consulta = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(consulta);
	}
	
	public List<Asistencia> select() {
		String condicion = "true";
		List<Asistencia> areas = selectByCondicion(condicion);
		return areas;
	}
	
	private List<Asistencia> selectByCondicion(String condicion) {
		List<Asistencia> ret = new ArrayList<Asistencia>();
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				ret.add(new Asistencia(
						resultados.getInt("ID"),
						resultados.getInt("curso"),
						resultados.getInt("alumno"),
						resultados.getDate("fecha"),
						resultados.getBoolean("presente")
						));
			}

			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
		}
			
		return ret;
	}
	
}