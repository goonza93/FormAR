package com.ungs.formar.persistencia.mysqlOBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.interfacesOBD.EmpleadoODB;

public class EmpleadoODBMySQL extends ODB implements EmpleadoODB{
	private final String tabla = "for_empleados";

	public void insert(Empleado empleado) {
		String consulta = "insert into "+tabla+" (DNI, rol, nombre, apellido, telefono, email, fecha_ingreso, fecha_egreso) ";
		String valores = empleado.getRol() +", '"+ empleado.getDNI() +"', '"+ empleado.getNombre() +"', '"
				+ empleado.getApellido() +"', '"+ empleado.getTelefono() +"', '"+ empleado.getEmail() +"', '"
				+ empleado.getFechaIngreso()+"', '"+ empleado.getFechaEgreso()+"'";
		consulta += "values ("+valores+");";
		ejecutarSQL(consulta);
	}

	public void update(Empleado empleado) {
		String consulta = "update "+tabla+" set ";
		String valores = "DNI = '"+ empleado.getDNI() +"', nombre = '"+ empleado.getNombre() +"', apellido = '"
		+ empleado.getApellido() +"', telefono = '"+ empleado.getTelefono() +"', email = '"+ empleado.getEmail() +"'";
		consulta += valores+" where empleado_ID = '"+empleado.getEmpleadoID() +"';";
		ejecutarSQL(consulta);
	}

	public void delete(Empleado empleado) {
		String consulta = "delete from "+tabla+" where ";
		String valor = "empleado_ID = '"+empleado.getEmpleadoID()+"'";
		consulta += valor+";";
		ejecutarSQL(consulta);
	}
	
	public List<Empleado> select() {
		String condicion = "1=1";
		List<Empleado> empleados = selectByCondicion(condicion);
		return empleados;
	}
	
	public Empleado selectByNombre(String nombre) {
		String condicion = "nombre = '"+nombre+"'";
		List<Empleado> empleados = selectByCondicion(condicion);
		Empleado empleado = null;
		if (empleados.size()>0)
			empleado = empleados.get(0); 
		
		return empleado;
	}

	public Empleado selectByID(Integer id) {
		String condicion = "empleado_ID = '"+id+"'";
		List<Empleado> empleados = selectByCondicion(condicion);
		Empleado empleado = null;
		if (empleados.size()>0)
			empleado = empleados.get(0); 
		
		return empleado;
	}
	
	public List<Empleado> selectByRol(Integer rolID){
		String condicion = "rol = '"+rolID+"'";
		return selectByCondicion(condicion);
	}


	private List<Empleado> selectByCondicion(String condicion) {
		List<Empleado> empleados = new ArrayList<Empleado>();
		String campos = "empleado_ID, rol, dni, nombre, apellido, telefono, email, fecha_ingreso, fecha_egreso";
		String comandoSQL = "select "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				empleados.add(new Empleado(
						resultados.getInt("empleado_ID"),
						resultados.getInt("rol"),
						resultados.getString("dni"),
						resultados.getString("nombre"),
						resultados.getString("apellido"),
						resultados.getString("telefono"),
						resultados.getString("email"),
						resultados.getDate("fecha_ingreso"),
						resultados.getDate("fecha_egreso")
						));
				}
			
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
		}
			
		return empleados;
	}	
}