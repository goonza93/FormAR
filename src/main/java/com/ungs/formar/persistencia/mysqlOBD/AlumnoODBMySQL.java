package com.ungs.formar.persistencia.mysqlOBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.interfacesOBD.AlumnoODB;

public class AlumnoODBMySQL extends ODB implements AlumnoODB{
	private final String tabla = "for_clientes";
	
	public void insert (Alumno alumno) {
		String consulta = "insert into "+tabla+" (dni, nombre, apellido, telefono, email) ";
		String valores = "'"+ alumno.getDni() +"', '"+ alumno.getNombre() +"', '"
		+ alumno.getApellido() +"', '"+ alumno.getTelefono() +"', '"+ alumno.getEmail() +"'";
		consulta += "values ("+valores+");";
		ejecutarSQL(consulta);
	}
	
	public void edit(Alumno alumno) {
		String consulta = "update "+tabla+" set ";
		String valores = "dni = '"+ alumno.getDni() +"', nombre = '"+ alumno.getNombre() +"', apellido = '"
		+ alumno.getApellido() +"', telefono = '"+ alumno.getTelefono() +"', email = '"+ alumno.getEmail() +"'";
		consulta += valores+" where cliente_ID = '"+alumno.getClienteID() +"';";
		ejecutarSQL(consulta);
	}
	
	public void delete(Alumno alumno){
		String consulta = "delete from "+tabla+" where ";
		String valor = "cliente_ID = '"+alumno.getClienteID()+"'";
		consulta += valor+";";
		ejecutarSQL(consulta);
	}

	public List<Alumno> select() {
		String condicion = "1=1";
		List<Alumno> alumnos = selectByCondicion(condicion);
		return alumnos;
	}
	
	public Alumno selectByNombre(String nombre) {
		String condicion = "nombre = '"+nombre+"'";
		List<Alumno> alumnos = selectByCondicion(condicion);
		Alumno alumno = null;
		if (alumnos.size()>0)
			alumno = alumnos.get(0); 
		
		return alumno;
	}
	
	public Alumno selectByID(Integer id) {
		String condicion = "cliente_ID = '"+id+"'";
		List<Alumno> alumnos = selectByCondicion(condicion);
		Alumno alumno = null;
		if (alumnos.size()>0)
			alumno = alumnos.get(0); 
		
		return alumno;
	}

	private List<Alumno> selectByCondicion(String condicion) {
		List<Alumno> alumnos = new ArrayList<Alumno>();
		String campos = "cliente_ID, dni, nombre, apellido, telefono, email";
		String comandoSQL = "select "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				alumnos.add(new Alumno(
						resultados.getInt("cliente_ID"),
						resultados.getString("dni"),
						resultados.getString("nombre"),
						resultados.getString("apellido"),
						resultados.getString("telefono"),
						resultados.getString("email")
						));
				}
			
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
		}
			
		return alumnos;
	}

	public boolean selectByDNI(String dni) {
		String condicion = "dni = '"+dni+"'";
		List<Alumno> alumnos = selectByCondicion(condicion);
		if (alumnos.size()>0)
			return true;
		
		return false;
	}
	
}