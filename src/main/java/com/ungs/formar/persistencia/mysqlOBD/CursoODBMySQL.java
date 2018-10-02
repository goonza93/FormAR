package com.ungs.formar.persistencia.mysqlOBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.interfacesOBD.CursoODB;

public class CursoODBMySQL extends ODB implements CursoODB{
	private final String tabla = "for_cursos";
	
	public void insert (Curso curso) {
		String campos = "nombre, cupo_minimo, cupo_maximo, fecha_inicio, fecha_fin, contenido, clases, horas, instructor, responsable, sala, programa";
		
		String fInicio = curso.getFecha_inicio() == null ? null : "'"+curso.getFecha_inicio()+"'"; 
		String fFin = curso.getFecha_fin() == null ? null : "'"+curso.getFecha_fin()+"'"; 
		
		String valores = "'"+curso.getNombre()+"', "+curso.getCupoMinimo()+", "+curso.getCupoMaximo()+", ";
		valores += fInicio+", "+fFin+", '"+curso.getContenido()+"', "+curso.getCantidadClases()+", ";
		valores += 10 +", "+curso.getInstructor()+", "+1+", "+curso.getSala()+", "+curso.getPrograma();
		
		String consulta = "insert into "+tabla+" ("+campos+") values ("+valores+")";
		ejecutarSQL(consulta);
	}

	public List<Curso> select() {
		String condicion = "1=1";
		List<Curso> cursos = selectByCondicion(condicion);
		return cursos;
	}

	private List<Curso> selectByCondicion(String condicion) {
		List<Curso> cursos = new ArrayList<Curso>();
		String campos = "curso_ID, nombre, cupo_minimo, cupo_maximo, fecha_inicio, fecha_fin, contenido, clases, horas, instructor, responsable, sala, programa";
		String comandoSQL = "select "+campos+" from "+tabla+" where ("+condicion+");";
				
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				cursos.add(new Curso(
						resultados.getInt("curso_ID"),
						resultados.getInt("cupo_minimo"),
						resultados.getInt("cupo_maximo"),
						resultados.getInt("clases"),
						resultados.getInt("instructor"),
						resultados.getInt("sala"),
						resultados.getInt("programa"),
						resultados.getString("nombre"),
						resultados.getString("contenido"),
						resultados.getDate("fecha_inicio"),
						resultados.getDate("fecha_fin")
						));
				}
			
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
		}
			
		return cursos;
	}
	
}