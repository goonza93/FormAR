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
	private final String campos = "DNI, nombre, apellido, telefono, email, activo";
	private final String tabla = "for_alumnos";
	
	public void insert (Alumno alumno) {
		String dni = "'"+alumno.getDNI()+"'";
		String nombre = "'"+alumno.getNombre()+"'";
		String apellido = "'"+alumno.getApellido()+"'";
		String telefono = alumno.getTelefono() == null ? null :"'"+alumno.getTelefono()+"'"; // El telefono es opcional
		String mail = "'"+alumno.getEmail()+"'";
		
		String valores = dni
				+", "+nombre
				+", "+apellido
				+", "+telefono
				+", "+mail
				+", "+alumno.getActivo();
				
		String consulta = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(consulta);
	}
	
	public void update(Alumno alumno) {
		String dni = "'"+alumno.getDNI()+"'";
		String nombre = "'"+alumno.getNombre()+"'";
		String apellido = "'"+alumno.getApellido()+"'";
		String telefono = alumno.getTelefono() == null ? null :"'"+alumno.getTelefono()+"'"; // El telefono es opcional
		String mail = "'"+alumno.getEmail()+"'";
		String condicion = "ID ="+alumno.getID();
		
		String consulta = "update " + tabla
				+" set dni = "+dni
				+", nombre = "+nombre
				+", apellido = "+apellido
				+", telefono = "+telefono
				+", email = "+ mail
				+", activo = "+ alumno.getActivo()
				+"  where ("+condicion+");";
		ejecutarSQL(consulta);
	}
	
	public void delete(Alumno alumno){
		String condicion = "ID = "+alumno.getID();
		String consulta = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	public List<Alumno> select() {
		String condicion = "true";
		List<Alumno> alumnos = selectByCondicion(condicion);
		return alumnos;
	}
	
	public Alumno selectByNombre(String nombre) {
		String condicion = "nombre = '"+nombre+"'";
		List<Alumno> alumnos = selectByCondicion(condicion);
		if (alumnos.size()>0)
			return alumnos.get(0); 
		return null;
	}
	
	public Alumno selectByID(Integer id) {
		String condicion = "ID = "+id;
		List<Alumno> alumnos = selectByCondicion(condicion);
		if (alumnos.size()>0)
			return alumnos.get(0); 
		return null;
	}

	public Alumno selectByDNI(String dni) {
		String condicion = "DNI = '"+dni+"'";
		List<Alumno> alumnos = selectByCondicion(condicion);
		if (alumnos.size()>0)
			return alumnos.get(0); 
		return null;
	}
	
	private List<Alumno> selectByCondicion(String condicion) {
		List<Alumno> alumnos = new ArrayList<Alumno>();
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				alumnos.add(new Alumno(
						resultados.getInt("ID"),
						resultados.getString("DNI"),
						resultados.getString("nombre"),
						resultados.getString("apellido"),
						resultados.getString("telefono"),
						resultados.getString("email"),
						resultados.getBoolean("activo")
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
	
}