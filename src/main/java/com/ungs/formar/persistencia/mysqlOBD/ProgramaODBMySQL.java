package com.ungs.formar.persistencia.mysqlOBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.persistencia.interfacesOBD.ProgramaODB;

public class ProgramaODBMySQL extends ODB implements ProgramaODB{
	private final String campos = "area, nombre, fecha_aprobacion, descripcion, horas";
	private final String tabla = "for_programas";
	private final String ID = "programa_ID";

	public void insert (Programa programa) {
		String area = "'"+programa.getAreaID()+"'";
		String nombre = "'"+programa.getNombre()+"'";
		String fechaAprobacion = "'"+programa.getFechaAprobacion()+"'";
		String descripcion ="'"+programa.getDescripcion()+"'";
		String cargaHoraria = "'"+programa.getHoras()+"'";
		
		String valores = area
				+", "+nombre
				+", "+fechaAprobacion
				+", "+descripcion
				+", "+cargaHoraria;
				
		String consulta = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(consulta);
	}
	
	public void update(Programa programa) {
		String area = "'"+programa.getAreaID()+"'";
		String nombre = "'"+programa.getNombre()+"'";
		String fechaAprobacion = "'"+programa.getFechaAprobacion()+"'";
		String descripcion ="'"+programa.getDescripcion()+"'";
		String cargaHoraria = "'"+programa.getHoras()+"'";
		String condicion = ID+"="+programa.getProgramaID();
		
		String consulta = "update " + tabla
				+" set area = "+area
				+", nombre = "+nombre
				+", fecha_aprobacion = "+fechaAprobacion
				+", descripcion = "+descripcion
				+", horas = "+cargaHoraria
				+"  where ("+condicion+");";
		ejecutarSQL(consulta);
	}
	
	public void delete(Programa programa){
		String condicion = ID+"="+programa.getProgramaID();
		String consulta = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);
	}
	
	
	public List<Programa> select() {
		String condicion = "1=1";
		List<Programa> programas= selectByCondicion(condicion);
		return programas;
	}
	
	public Programa selectByID(Integer id) {
		String condicion = "programa_ID = '"+id+"'";
		List<Programa> programas = selectByCondicion(condicion);
		Programa programa = null;
		if (programas.size()>0)
			programa = programas.get(0); 
		
		return programa;
	}

	private List<Programa> selectByCondicion(String condicion) {
		List<Programa> programas = new ArrayList<Programa>();
		String campos = "programa_ID, area, horas, nombre, fecha_aprobacion, descripcion";
		String comandoSQL = "select "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				programas.add(new Programa(
						resultados.getInt("programa_ID"),
						resultados.getInt("area"),
						resultados.getInt("horas"),
						resultados.getString("nombre"),
						resultados.getString("descripcion"),
						resultados.getDate("fecha_aprobacion")
						));
			}
			
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
		}
			
		return programas;
	}
	
}