package com.ungs.formar.persistencia.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Interaccion;
import com.ungs.formar.persistencia.interfaces.InteraccionOBD;

public class InteraccionOBDMySQL extends ODB implements InteraccionOBD {
	private final String campos = "empleado, interesado, area, curso, fecha, descripcion";
	private final String tabla = "for_interacciones";

	public void insert(Interaccion interaccion) {
		String empleado = "'"+interaccion.getEmpleadoID()+"'";
		String interesado = "'"+interaccion.getInteresadoID()+"'";
		String area = interaccion.getAreaID() == null ? null : "'"+interaccion.getAreaID()+"'";
		String curso = interaccion.getCursoID() == null ? null : "'"+interaccion.getCursoID()+"'";
		String fecha = interaccion.getFechaInteraccion() == null ? null : "'"+interaccion.getFechaInteraccion()+"'";
		
		String valores = empleado
				+", "+ interesado
				+", "+ area
				+", "+ curso
				+", "+ fecha
				+", '"+ interaccion.getDescripcion()+"'";
		
		String consulta = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(consulta);
	}

	public void update(Interaccion interaccion) {
		String empleado = "'"+interaccion.getEmpleadoID()+"'";
		String interesado = "'"+interaccion.getInteresadoID()+"'";
		String area = interaccion.getAreaID() == null ? null : "'"+interaccion.getAreaID()+"'";
		String curso = interaccion.getCursoID() == null ? null : "'"+interaccion.getCursoID()+"'";
		String fecha = interaccion.getFechaInteraccion() == null ? null : "'"+interaccion.getFechaInteraccion()+"'";
		String condicion = "ID = " + interaccion.getID();
		
		String consulta = "update " + tabla
				+" set empleado = " + empleado
				+", interesado = "+ interesado
				+", area = "+ area
				+", curso = "+ curso
				+", fecha = "+ fecha
				+", descripcion = '"+ interaccion.getDescripcion()
				+"' where (" + condicion + ");";
		ejecutarSQL(consulta);
	}

	public void delete(Interaccion interaccion) {
		String condicion = "ID = "+interaccion.getID();
		String consulta = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	public List<Interaccion> select() {
		String condicion = "true";
		List<Interaccion> interacciones = selectByCondicion(condicion);
		return interacciones;
	}
	
	public List<Interaccion> selectByID(Integer id) {
		String condicion = "ID = "+ id;
		List<Interaccion> interacciones = selectByCondicion(condicion);
		return interacciones;
	}
	
	public List<Interaccion> selectByInteresado(Integer interesadoID) {
		String condicion = "interesado = "+ interesadoID;
		List<Interaccion> interacciones = selectByCondicion(condicion);
		return interacciones;
	}
	
	public List<Interaccion> selectByEmpleado(Integer empleadoID) {
		String condicion = "empleado = "+ empleadoID;
		List<Interaccion> interacciones = selectByCondicion(condicion);
		return interacciones;
	}
	
	public List<Interaccion> selectByArea(Integer areaID) {
		String condicion = "area = "+ areaID;
		List<Interaccion> interacciones = selectByCondicion(condicion);
		return interacciones;
	}
	
	public List<Interaccion> selectByPrograma(Integer cursoID) {
		String condicion = "curso = "+ cursoID;
		List<Interaccion> interacciones = selectByCondicion(condicion);
		return interacciones;
	}

	private List<Interaccion> selectByCondicion(String condicion) {
		List<Interaccion> interacciones = new ArrayList<Interaccion>();
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				interacciones.add(new Interaccion(
						resultados.getInt("ID"),
						resultados.getInt("empleado"),
						resultados.getInt("interesado"),
						resultados.getInt("area"),
						resultados.getInt("curso"),
						resultados.getDate("fecha"),
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
			
		return interacciones;
	}

}
