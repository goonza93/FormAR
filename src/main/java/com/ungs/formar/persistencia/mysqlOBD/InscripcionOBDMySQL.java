package com.ungs.formar.persistencia.mysqlOBD;

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
import com.ungs.formar.persistencia.interfacesOBD.InscripcionOBD;

public class InscripcionOBDMySQL extends ODB implements InscripcionOBD{
	private final String campos = "curso, cliente, empleado, fecha, nota";
	private final String tabla = "for_inscripciones";
	private final String ID = "inscripcion_ID";
	
	public void insert(Inscripcion inscripcion) {
		String fecha = inscripcion.getFecha() == null ? null: "'"+inscripcion.getFecha()+"'"; 
		String valores = inscripcion.getCurso()
				+", "+inscripcion.getCliente()
				+", "+inscripcion.getEmpleado()
				+", "+fecha
				+", "+inscripcion.getNota();
		String sql = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(sql);
	}
	
	
	public List<Inscripcion> select() {
		String condicion = "1=1";
		return selectByCondicion(condicion);
	}

	public List<Inscripcion> selectByAlumno(Alumno alumno) {
		String condicion = "cliente = "+alumno.getClienteID();
		return selectByCondicion(condicion);
	}


	public Inscripcion selectByCursoAlumno(Curso curso, Alumno alumno) {
		String condicion = "cliente = "+alumno.getClienteID()+" and curso = "+curso.getCursoID();
		List<Inscripcion> inscripciones = selectByCondicion(condicion);
		if (inscripciones.size()>0)
			return inscripciones.get(0);
		return null;
	}
	
	
	
	private List<Inscripcion> selectByCondicion(String condicion) {
		List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
		String comandoSQL = "select "+ID+", "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next())
				inscripciones.add(new Inscripcion(
						resultados.getInt(ID),
						resultados.getInt("cliente"),
						resultados.getInt("curso"),
						resultados.getInt("empleado"),
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

	public List<Inscripcion> selectByCurso(Curso curso) {
		String condicion = "curso = "+curso.getCursoID();
		return selectByCondicion(condicion);
	}


	public void delete(Inscripcion inscripcion) {
		String condicion = ID+"="+inscripcion.getInscripcionID();
		String consulta = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);
	}

}