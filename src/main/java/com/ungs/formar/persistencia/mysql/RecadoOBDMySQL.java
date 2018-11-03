package com.ungs.formar.persistencia.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Recado;
import com.ungs.formar.persistencia.interfaces.RecadoOBD;

public class RecadoOBDMySQL extends ODB implements RecadoOBD{
	private final String tabla = "for_recados";
	private final String campos = "emisor, receptor, empleado, contenido, titulo, leido, archivado, fecha";

	public void insert(Recado recado) {
		String valores = recado.getEmisor()
				+", "+recado.getReceptor()
				+", "+recado.getEmpleado()
				+", '"+recado.getContenido()+"'"
				+", '"+recado.getTitulo()+"'"
				+", "+recado.isLeido()
				+", "+recado.isArchivado()
				+", '"+recado.getFecha()+"'";
				
		String consulta = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(consulta);
	}

	public void update(Recado recado) {
		String condicion = "ID ="+recado.getID();
		String valores =
				"emisor = "+recado.getEmisor()
				+", receptor = "+recado.getReceptor()
				+", empleado = "+recado.getEmpleado()
				+", contenido = '"+recado.getContenido()+"'"
				+", titulo = '"+recado.getTitulo()+"'"
				+", leido = "+recado.isLeido()
				+", archivado = "+recado.isArchivado()
				+", fecha = '"+recado.getFecha()+"'";
		String consulta = "update "+tabla+" set "+valores+""+"  where ("+condicion+");";
		ejecutarSQL(consulta);	
	}
	
	public void delete(Recado recado) {
		String condicion = "ID = "+recado.getID();
		String consulta = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);	
	}

	public List<Recado> select() {
		String condicion = "true";
		List<Recado> recados = selectByCondicion(condicion);
		return recados;
	}

	public List<Recado> selectByReceptor(Empleado receptor) {
		String condicion = "receptor = "+receptor.getID();
		List<Recado> recados = selectByCondicion(condicion);
		return recados;
	}

	public List<Recado> selectByEmpleadoReceptorArchivado(Empleado empleado, Empleado receptor, boolean archivado) {
		String condicion = "empleado = "+empleado.getID()
				+" and receptor = "+receptor.getID()
				+" and archivado = "+archivado;
		List<Recado> recados = selectByCondicion(condicion);
		return recados;
	}

	public List<Recado> selectByEmpleadoEmisorArchivado(Empleado empleado, Empleado emisor, boolean archivado) {
		String condicion = "empleado = "+empleado.getID()
				+" and emisor = "+emisor.getID()
				+" and archivado = "+archivado;
		List<Recado> recados = selectByCondicion(condicion);
		return recados;
	}
	
	private List<Recado> selectByCondicion(String condicion) {
		List<Recado> recados = new ArrayList<Recado>();
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				recados.add(new Recado(
						resultados.getInt("ID"),
						resultados.getInt("empleado"),
						resultados.getInt("receptor"),
						resultados.getInt("emisor"),
						resultados.getString("contenido"),
						resultados.getString("titulo"),
						resultados.getBoolean("leido"),
						resultados.getBoolean("archivado"),
						resultados.getDate("fecha")
						));
			}

			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
		}
			
		return recados;
	}
	
}