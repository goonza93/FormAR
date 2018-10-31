package com.ungs.formar.persistencia.mysqlOBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Horario;
import com.ungs.formar.persistencia.interfacesOBD.HorarioOBD;

public class HorarioOBDMySQL extends ODB implements HorarioOBD{
	private final String campos = "dia, hora_inicio, hora_fin, minuto_inicio, minuto_fin";
	private final String tabla = "for_horarios";
	private final String ID = "ID";

	public void insert(Horario horario) {
		String valores =
				horario.getDia()
				+", "+horario.getHoraInicio()
				+", "+horario.getHoraFin()
				+", "+horario.getMinutoInicio()
				+", "+horario.getMinutoFin();
		
		String sql = "insert into "+tabla+"("+campos+") values ("+valores+");";
		ejecutarSQL(sql);
	}
	
	public void update(Horario horario) {
		String condicion = ID+" = "+horario.getHorarioID();
		String consulta = "update "+tabla
				+" set dia = "+horario.getDia()
				+", hora_inicio = "+horario.getHoraInicio()
				+", hora_fin = "+horario.getHoraFin()
				+", minuto_inicio = "+horario.getMinutoInicio()
				+", minuto_fin = "+horario.getMinutoFin()
				+" where ("+condicion+");";
		ejecutarSQL(consulta);
	}
	
	public void delete(Horario horario) {
		String condicion = ID+" = "+horario.getHorarioID();
		String consulta = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);
	}
	
	public List<Horario> select() {
		return selectByCondicion("1=1");
	}

	public Horario selectByID(Integer ID) {
		String condicion = this.ID+" = "+ID;
		List<Horario> horarios = selectByCondicion(condicion);
		return horarios.get(0);
	}

	private List<Horario> selectByCondicion(String condicion) {
		List<Horario> horarios = new ArrayList<Horario>();
		String comandoSQL = "select "+ID+", "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				horarios.add(new Horario(
						resultados.getInt("ID"),
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
		return selectLastID(tabla);
	}
	
}