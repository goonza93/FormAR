package com.ungs.formar.persistencia.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Asistencia;
import com.ungs.formar.persistencia.entidades.Curso;
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

	public List<Asistencia> selectByCursoFecha(Integer cursoID, Date fecha){
		String condicion = "curso = "+ cursoID + " and fecha = '" +fecha+"'";
		List<Asistencia> lista = selectByCondicion(condicion);
		return lista;		
	}

	public Asistencia selectByCursoAlumnoFecha(Curso curso, Alumno alumno, Date fecha){
		String condicion =
				"curso = "+curso.getID()
				+" AND alumno = "+alumno.getID()
				+" AND fecha = '" +fecha+"'";
		List<Asistencia> lista = selectByCondicion(condicion);
		if (lista.size()>0)
			return lista.get(0);
		return null;
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