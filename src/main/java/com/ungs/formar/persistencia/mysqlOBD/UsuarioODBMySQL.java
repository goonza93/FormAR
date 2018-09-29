package com.ungs.formar.persistencia.mysqlOBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Usuario;
import com.ungs.formar.persistencia.interfacesOBD.UsuarioODB;

public class UsuarioODBMySQL extends ODB implements UsuarioODB{
	private final String tabla = "tav_usuarios";
	
	public void insert (Usuario usuario) {
		String consulta = "insert into "+tabla+" (nombre, password) ";
		consulta = consulta + "values ('"+ usuario.getNombre()+"', '"+ usuario.getPassword() +"')";
		ejecutarSQL(consulta);
	}

	public Usuario selectByNombre(String nombre) {
		String condicion = "nombre = '"+nombre+"'";
		List<Usuario> usuarios = selectByCondicion(condicion);
		Usuario usuario = null;
		if (usuarios.size()>0)
			usuario = usuarios.get(0); 
		
		return usuario;
	}

	private List<Usuario> selectByCondicion(String condicion) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		String campos = "usuario_id, nombre, password";
		String comandoSQL = "select "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				usuarios.add(new Usuario(
						resultados.getInt("usuario_id"),
						resultados.getString("nombre"),
						resultados.getString("password")
						));
			}
			
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
		}
			
		return usuarios;
	}
	
}