package com.ungs.formar.persistencia.mysqlOBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.persistencia.interfacesOBD.HorarioOBD;

public class HorarioOBDMySQL extends ODB implements HorarioOBD{
	private final String tabla = "for_horarios";

	public void insert(Horario horario) {
		String campos = "dia, hora_inicio, hora_fin, minuto_inicio, minuto_fin";
		if(horario.getMinutoInicio().toString().length()==1){
			horario.setMinutoInicio(Integer.parseInt("0"+horario.getMinutoInicio().toString()));
		}
		if(horario.getMinutoFin().toString().length()==1){
			horario.setMinutoFin(Integer.parseInt("0"+horario.getMinutoFin().toString()));
		}
		if(horario.getHoraInicio().toString().length()==1){
			horario.setHoraInicio(Integer.parseInt("0"+horario.getHoraInicio().toString()));
		}
		if(horario.getHoraFin().toString().length()==1){
			horario.setHoraFin(Integer.parseInt("0"+horario.getHoraFin().toString()));
		}
		String values = horario.getDia()+", "+horario.getHoraInicio()+", "+horario.getHoraFin()+", "+horario.getMinutoInicio()+", "+horario.getMinutoFin();
		
		
		String sql = "insert into "+tabla+"("+campos+") values ("+values+");";
		ejecutarSQL(sql);
	}
	
	public List<Horario> select() {
		return selectByCondicion("1=1");
	}

	public Horario selectByID(Integer ID) {
		return selectByCondicion("horario_ID="+ID).get(0);
	}

	private List<Horario> selectByCondicion(String condicion) {
		List<Horario> horarios = new ArrayList<Horario>();
		String campos = "horario_ID, dia, hora_inicio, hora_fin, minuto_inicio, minuto_fin";
		String comandoSQL = "select "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				horarios.add(new Horario(
						resultados.getInt("horario_ID"),
						resultados.getInt("dia"),
						resultados.getInt("hora_inicio"),
						resultados.getInt("hora_fin"),
						resultados.getInt("minuto_inicio"),
						resultados.getInt("minuto_fin")
						));
			}
			
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
		}
			
		return horarios;
	}

	public Integer selectIDMasReciente() {
		String sql = "select horario_id from for_horarios order by horario_id desc limit 1";
		Integer ret = null;
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(sql);			
	
			if (resultados.next())
				ret = resultados.getInt("horario_id");
				
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(sql);
			e.printStackTrace();
		}
			
		return ret;
	}

	public void delete(Horario horario) {
		String condicion = "horario_id = "+horario.getHorarioID();
		String consulta = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);
	}
	
}