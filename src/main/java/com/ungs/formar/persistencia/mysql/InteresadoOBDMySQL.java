package com.ungs.formar.persistencia.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Interesado;
import com.ungs.formar.persistencia.interfaces.InteresadoOBD;

public class InteresadoOBDMySQL extends ODB implements InteresadoOBD {
	private final String campos = "DNI, nombre, apellido, telefono, email";
	private final String tabla = "for_interesados";

	public void insert(Interesado interesado) {
		String dni = "'"+interesado.getDNI()+"'";
		String nombre = "'"+interesado.getNombre()+"'";
		String apellido = "'"+interesado.getApellido()+"'";
		String telefono = interesado.getTelefono() == null ? null :"'"+interesado.getTelefono()+"'"; // El telefono es opcional
		String mail = "'"+interesado.getEmail()+"'";
		
		String valores = dni
				+", "+nombre
				+", "+apellido
				+", "+telefono
				+", "+mail;
				
		String consulta = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(consulta);
	}

	public void update(Interesado interesado) {
		String dni = "'"+interesado.getDNI()+"'";
		String nombre = "'"+interesado.getNombre()+"'";
		String apellido = "'"+interesado.getApellido()+"'";
		String telefono = interesado.getTelefono() == null ? null :"'"+interesado.getTelefono()+"'"; // El telefono es opcional
		String mail = "'"+interesado.getEmail()+"'";
		String condicion = "ID ="+interesado.getID();
		
		String consulta = "update " + tabla
				+" set dni = "+dni
				+", nombre = "+nombre
				+", apellido = "+apellido
				+", telefono = "+telefono
				+", email = "+ mail
				+"  where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	public void delete(Interesado interesado) {
		String condicion = "ID = "+interesado.getID();
		String consulta = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	public List<Interesado> select() {
		String condicion = "true";
		List<Interesado> interesados = selectByCondicion(condicion);
		return interesados;
	}
	
	public Interesado selectByID(Integer id) {
		String condicion = "ID = " + id;
		List<Interesado> interesados = selectByCondicion(condicion);
		Interesado interesado = null;
		if (interesados.size()>0){
			interesado = interesados.get(0);
		}
		return interesado;
	}
	
	public Interesado selectByDNI(String dni) {
		String condicion = "DNI = " + dni;
		List<Interesado> interesados = selectByCondicion(condicion);
		Interesado interesado = null;
		if (interesados.size()>0){
			interesado = interesados.get(0);
		}
		return interesado;
	}

	private List<Interesado> selectByCondicion(String condicion) {
		List<Interesado> interesados = new ArrayList<Interesado>();
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				interesados.add(new Interesado(
						resultados.getInt("ID"),
						resultados.getString("DNI"),
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
			
		return interesados;
	}

}
