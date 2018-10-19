package com.ungs.formar.persistencia.mysqlOBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Sala;
import com.ungs.formar.persistencia.interfacesOBD.SalaODB;

public class SalaODBMySQL extends ODB implements SalaODB{
	private final String campos = "numero, nombre, capacidad";
	private final String tabla = "for_salas";
	private final String ID = "sala_ID";

	public void insert(Sala sala) {
		String nombre = sala.getNombre() == null ? null : "'"+sala.getNombre()+"'";
		String valores =
				sala.getNumero()
				+", "+nombre
				+", "+sala.getCapacidad();
				
		String sql = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(sql);
	}
	
	public void update(Sala sala) {
		String condicion = ID+"="+sala.getSalaID();
		String nombre = sala.getNombre() == null ? null : "'"+sala.getNombre()+"'";
		String sql = "update "+tabla
				+" set numero = "+sala.getNumero()+", "
				+" nombre = "+nombre+", "
				+" capacidad = "+sala.getCapacidad()
				+" where ("+condicion+");";
		
		ejecutarSQL(sql);
	}
	
	public void delete(Sala sala) {
		String condicion = ID+"="+sala.getSalaID();
		String sql = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(sql);
	}
	
	public List<Sala> select() {
		String condicion = "1=1";
		List<Sala> salas = selectByCondicion(condicion);
		return salas;
	}
	
	public Sala selectByID(Integer id){
		String condicion = ID+" = "+id;
		List<Sala> salas = selectByCondicion(condicion);
		Sala sala = null;
		if (salas.size()>0)
			sala = salas.get(0); 
		
		return sala;
	}

	private List<Sala> selectByCondicion(String condicion) {
		List<Sala> salas = new ArrayList<Sala>();
		String comandoSQL = "select "+ID+", "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				salas.add(new Sala(
						resultados.getInt("sala_ID"),
						resultados.getInt("numero"),
						resultados.getInt("capacidad"),
						resultados.getString("nombre")
						));
			}
			
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
		}
			
		return salas;
	}
	
}