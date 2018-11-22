package com.ungs.formar.persistencia.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.formar.persistencia.Definido;
import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Notificacion;
import com.ungs.formar.persistencia.interfaces.NotificacionOBD;

public class NotificacionOBDMySQL extends ODB implements NotificacionOBD {
	private final String campos = "tipo, empleado, contenido, mostrado, leido, fecha";
	private final String tabla = "formar_notificaciones";

	public void insert(Notificacion notificacion) {
		String empleado = "'"+notificacion.getEmpleado()+"'";
		String contenido = "'"+notificacion.getContenido()+"'";
		String fecha = notificacion.getFechaAMostrar() == null ? null :"'"+notificacion.getFechaAMostrar()+"'";
		
		String valores = Definido.tipoNotificacion(notificacion.getTipo())
				+", "+ empleado
				+", "+ contenido
				+", "+ notificacion.isMostrado()
				+", "+ notificacion.isLeido()
				+", "+ fecha;
				
		String consulta = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(consulta);
	}

	public void update(Notificacion notificacion) {
		String empleado = "'"+notificacion.getEmpleado()+"'";
		String contenido = "'"+notificacion.getContenido()+"'";
		String fecha = notificacion.getFechaAMostrar() == null ? null :"'"+notificacion.getFechaAMostrar()+"'";
		String condicion = "ID ="+notificacion.getID();
		
		String consulta = "update " + tabla
				+" set tipo = "+ Definido.tipoNotificacion(notificacion.getTipo())
				+", empleado = "+ empleado
				+", contenido = "+ contenido
				+", mostrado = "+ notificacion.isMostrado()
				+", leido = "+ notificacion.isLeido()
				+", fecha = "+ fecha
				+"  where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	public void delete(Notificacion notificacion) {
		String condicion = "ID = "+notificacion.getID();
		String consulta = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	public List<Notificacion> select() {
		String condicion = "true";
		List<Notificacion> notificaciones = selectByCondicion(condicion);
		return notificaciones;
	}
	
	public List<Notificacion> selectByEmpleado(Empleado empleado){
		String condicion = "empleado = "+empleado.getID();
		List<Notificacion> notificaciones = selectByCondicion(condicion);
		return notificaciones;
	}
	
	public List<Notificacion> selectByEmpleadoSinMostrar(Empleado empleado){
		boolean mostrado = false;
		String condicion = "empleado = "+empleado.getID()
				+" and mostrado = "+ mostrado ;
		List<Notificacion> notificaciones = selectByCondicion(condicion);
		return notificaciones;
	}
	
	public List<Notificacion> selectByEmpleadoSinLeer(Empleado empleado){
		boolean leido = false;
		String condicion = "empleado = "+empleado.getID()
				+" and leido = "+ leido ;
		List<Notificacion> notificaciones = selectByCondicion(condicion);
		return notificaciones;
	}

	private List<Notificacion> selectByCondicion(String condicion) {
		List<Notificacion> notificaciones = new ArrayList<Notificacion>();
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				notificaciones.add(new Notificacion(
						resultados.getInt("ID"),
						Definido.tipoNotificacion(resultados.getInt("tipo")),
						resultados.getInt("empleado"),
						resultados.getString("contenido"),
						resultados.getBoolean("mostrado"),
						resultados.getBoolean("leido"),
						resultados.getTimestamp("fecha")
						));
				}
			
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
		}
			
		return notificaciones;
	}

}
