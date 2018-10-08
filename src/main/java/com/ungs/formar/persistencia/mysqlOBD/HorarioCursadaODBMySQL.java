package com.ungs.formar.persistencia.mysqlOBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.HorarioCursada;
import com.ungs.formar.persistencia.interfacesOBD.HorarioCursadaOBD;

public class HorarioCursadaODBMySQL extends ODB implements HorarioCursadaOBD {
	private final String tabla = "for_horarios_cursada";

	public void insert(HorarioCursada horarioCursada) {
		String campos = "curso, horario, sala";
		String valores = horarioCursada.getCurso()+", "+horarioCursada.getHorario()+", "+horarioCursada.getSala(); 
		String sql = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(sql);
	}

	
	public List<HorarioCursada> select() {
		String condicion = "1=1";
		List<HorarioCursada> horarios = selectByCondicion(condicion);
		return horarios;
	}

	public List<HorarioCursada> selectBySala(Integer sala) {
		String condicion = "sala = "+sala;
		List<HorarioCursada> horarios = selectByCondicion(condicion);
		return horarios;
	}
	
	public List<HorarioCursada> selectByCurso(Curso curso) {
		return selectByCondicion("curso="+curso.getCursoID());
	}
	
	
	private List<HorarioCursada> selectByCondicion(String condicion) {
		List<HorarioCursada> estados = new ArrayList<HorarioCursada>();
		String campos = "horario_ID, curso, horario, sala";
		String comandoSQL = "select "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				estados.add(new HorarioCursada(
						resultados.getInt("horario_ID"),
						resultados.getInt("curso"),
						resultados.getInt("horario"),
						resultados.getInt("sala")
						));
			}
			
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
		}
			
		return estados;
	}


	public void delete(HorarioCursada horarioCursada) {
		String condicion = "horario_id = "+horarioCursada.getHorarioID();
		String sql = "update "+tabla+" set curso = "+horarioCursada.getCurso()+
				", horario = "+horarioCursada.getHorario()+", sala = "+ horarioCursada.getSala()
				+" where ("+condicion+");";
		ejecutarSQL(sql);
	}




}