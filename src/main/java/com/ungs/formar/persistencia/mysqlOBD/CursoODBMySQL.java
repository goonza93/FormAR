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
		String campos = "cupo_minimo, cupo_maximo, fecha_inicio, fecha_fin, contenido, horas, instructor, responsable, programa, estado";
		
		String fInicio = curso.getFechaInicio() == null ? null : "'"+curso.getFechaInicio()+"'"; 
		String fFin = curso.getFechaFin() == null ? null : "'"+curso.getFechaFin()+"'"; 
		
		String valores = curso.getCupoMinimo()+", "+curso.getCupoMaximo()+", ";
		valores += fInicio+", "+fFin+", '"+curso.getContenido()+"', ";
		valores += 10 +", "+curso.getInstructor()+", "+1+", "+curso.getPrograma()+", "+curso.getEstado();
		
		String consulta = "insert into "+tabla+" ("+campos+") values ("+valores+");";
		System.out.println(consulta);
		ejecutarSQL(consulta);
	}

	public List<Curso> select() {
		String condicion = "1=1";
		List<Curso> cursos = selectByCondicion(condicion);
		return cursos;
	}

	private List<Curso> selectByCondicion(String condicion) {
		List<Curso> cursos = new ArrayList<Curso>();
		String campos = "curso_ID, cupo_minimo, cupo_maximo, fecha_inicio, fecha_fin, contenido, horas, instructor, responsable, programa, estado";
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
						resultados.getInt("horas"),
						resultados.getString("contenido"),
						resultados.getDate("fecha_inicio"),
						resultados.getDate("fecha_fin"),
						resultados.getInt("instructor"),
						resultados.getInt("programa"),
						resultados.getInt("estado"),
						resultados.getInt("responsable")
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

	public Integer selectIDMasReciente() {
		String sql = "select curso_id from for_cursos order by curso_id desc limit 1;";
		Integer ret = null;
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(sql);			
	
			if (resultados.next())
				ret = resultados.getInt("curso_id");
				
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(sql);
			e.printStackTrace();
		}
			
		return ret;
	}

	public void delete(Curso curso) {
		String condicion = "curso_ID = "+curso.getCursoID();
		String consulta = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	public void update(Curso curso) {
		String fInicio = curso.getFechaInicio() == null ? null : "'"+curso.getFechaInicio()+"'"; 
		String fFin = curso.getFechaFin() == null ? null : "'"+curso.getFechaFin()+"'"; 
		
		String consulta = "update "+tabla;
		consulta += "set cupo_minimo = "+curso.getCupoMinimo();
		consulta += ", cupo_maximo = "+curso.getCupoMaximo();
		consulta += ", fecha_inicio = "+fInicio;
		consulta += ", fecha_fin = "+fFin;
		consulta += ", contenido = "+curso.getContenido();
		consulta += ", horas = "+curso.getHoras();
		consulta += ", instructor = "+curso.getInstructor();
		consulta += ", responsable = "+curso.getResponsable();
		consulta += ", programa = "+curso.getPrograma();
		consulta += ", estado = "+curso.getEstado();
		consulta += " where curso_ID = "+curso.getCursoID()+";";
		ejecutarSQL(consulta);
	}
		
}