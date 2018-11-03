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
import com.ungs.formar.persistencia.entidades.Inscripcion;
import com.ungs.formar.persistencia.interfaces.InscripcionOBD;

public class InscripcionOBDMySQL extends ODB implements InscripcionOBD{
	private final String campos = "alumno, empleado, curso, fecha, nota";
	private final String tabla = "for_inscripciones";
	
	public void insert(Inscripcion inscripcion) {
		String fecha = inscripcion.getFecha() == null ? null: "'"+inscripcion.getFecha()+"'"; 
		String valores = inscripcion.getAlumno()
				+", "+inscripcion.getEmpleado()
				+", "+inscripcion.getCurso()
				+", "+fecha
				+", "+inscripcion.getNota();
		String sql = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(sql);
	}
	
	public void delete(Inscripcion inscripcion) {
		String condicion = "ID = "+inscripcion.getID();
		String consulta = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);
	}
	
	public List<Inscripcion> select() {
		String condicion = "1=1";
		return selectByCondicion(condicion);
	}

	public List<Inscripcion> selectByAlumno(Alumno alumno) {
		String condicion = "alumno = "+alumno.getID();
		return selectByCondicion(condicion);
	}

	public List<Inscripcion> selectByCurso(Curso curso) {
		String condicion = "curso = "+curso.getID();
		return selectByCondicion(condicion);
	}

	public Inscripcion selectByCursoAlumno(Curso curso, Alumno alumno) {
		String condicion = "alumno = "+alumno.getID()+" and curso = "+curso.getID();
		List<Inscripcion> inscripciones = selectByCondicion(condicion);
		if (inscripciones.size()>0)
			return inscripciones.get(0);
		return null;
	}
	
	private List<Inscripcion> selectByCondicion(String condicion) {
		List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next())
				inscripciones.add(new Inscripcion(
						resultados.getInt("ID"),
						resultados.getInt("alumno"),
						resultados.getInt("empleado"),
						resultados.getInt("curso"),
						resultados.getDate("fecha"),
						resultados.getDouble("nota")
						));
			
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
		}
			
		return inscripciones;
	}

}