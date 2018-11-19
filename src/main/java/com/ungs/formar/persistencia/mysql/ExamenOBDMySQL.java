package com.ungs.formar.persistencia.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Examen;
import com.ungs.formar.persistencia.interfaces.ExamenOBD;

public class ExamenOBDMySQL extends ODB implements ExamenOBD {
	private final String tabla = "for_examenes";
	private final String campos = "alumno, curso, fecha, descripcion, nota";

	public void insert(Examen examen) {
		String valores = examen.getAlumno()
				+", "+examen.getCurso()
				+", '"+examen.getFecha()+"'"
				+", '"+examen.getDescripcion()+"'"
				+", "+examen.getNota();
		String consulta = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(consulta);
	}

	public List<Examen> select() {
		return selectByCondicion("true");
	}
	
	public List<Examen> selectByCursoDescripcion(Curso curso, String descripcion) {
		String condicion = "curso = "+curso.getID()+" and descripcion = '"+descripcion+"'";
		return selectByCondicion(condicion);
	}
	
	public List<Examen> selectByDescripcion(String descripcion) {
		return selectByCondicion("descripcion = '"+descripcion+"'");
	}
	
	private List<Examen> selectByCondicion(String condicion) {
		List<Examen> ret = new ArrayList<Examen>();
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				ret.add(new Examen(
						resultados.getInt("ID"),
						resultados.getInt("alumno"),
						resultados.getInt("curso"),
						resultados.getInt("nota"),
						resultados.getString("descripcion"),
						resultados.getDate("fecha")
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
	
	public List<String> selectExamenes(Curso curso) {
		List<String> ret = new ArrayList<String>();
		String comandoSQL = "select distinct descripcion from "+tabla+" where (curso = "+curso.getID()+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next())
				ret.add(resultados.getString("descripcion"));

			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
		}
			
		return ret;
	}

	
	public Examen selectByCursoAlumnoDescripcion(Curso curso, Alumno alumno, String descripcion) {
		String condicion = "curso = "+curso.getID()
				+" and alumno = "+alumno.getID()
				+" and descripcion = '"+descripcion+"'";
		List<Examen> lista = selectByCondicion(condicion);
		if (lista.size()>0)
			return lista.get(0);
		return null;
	}
		
}