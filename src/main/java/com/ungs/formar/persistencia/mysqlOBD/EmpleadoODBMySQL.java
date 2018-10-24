package com.ungs.formar.persistencia.mysqlOBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ungs.formar.persistencia.Definido;
import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.definidos.Rol;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.interfacesOBD.EmpleadoODB;

public class EmpleadoODBMySQL extends ODB implements EmpleadoODB{
	private final String campos = "rol, DNI, nombre, apellido, telefono, email, fecha_ingreso, fecha_egreso";
	private final String tabla = "for_empleados";
	private final String ID = "empleado_ID";

	public void insert(Empleado empleado) {
		String dni = empleado.getDNI()==null ? null: "'"+empleado.getDNI()+"'";
		String nombre = empleado.getNombre()==null ? null: "'"+empleado.getNombre()+"'";
		String apellido = empleado.getApellido()==null ? null: "'"+empleado.getApellido()+"'";
		String telefono = empleado.getTelefono()==null ? null: "'"+empleado.getTelefono()+"'";
		String email = empleado.getEmail()==null ? null: "'"+empleado.getEmail()+"'";
		String fIngreso = empleado.getFechaIngreso()==null ? null: "'"+empleado.getFechaIngreso()+"'";
		String fEgreso = empleado.getFechaEgreso()==null ? null: "'"+empleado.getFechaEgreso()+"'";
		
		String valores = Definido.rol(empleado.getRol())
				+", "+ dni
				+", "+ nombre
				+", "+ apellido
				+", "+ telefono
				+", "+ email
				+", "+ fIngreso
				+", "+ fEgreso;
		
		String sql = "insert into "+tabla+"("+campos+") values ("+valores+");";
		ejecutarSQL(sql);
	}

	public void update(Empleado empleado) {
		String dni = empleado.getDNI()==null ? null: "'"+empleado.getDNI()+"'";
		String nombre = empleado.getNombre()==null ? null: "'"+empleado.getNombre()+"'";
		String apellido = empleado.getApellido()==null ? null: "'"+empleado.getApellido()+"'";
		String telefono = empleado.getTelefono()==null ? null: "'"+empleado.getTelefono()+"'";
		String email = empleado.getEmail()==null ? null: "'"+empleado.getEmail()+"'";
		String fIngreso = empleado.getFechaIngreso()==null ? null: "'"+empleado.getFechaIngreso()+"'";
		String fEgreso = empleado.getFechaEgreso()==null ? null: "'"+empleado.getFechaEgreso()+"'";
		
		String valores = " rol = "+Definido.rol(empleado.getRol())
				+", DNI = "+ dni
				+", nombre = "+ nombre
				+", apellido = "+apellido
				+", telefono = "+ telefono
				+", email = "+ email
				+", fecha_ingreso = "+ fIngreso
				+", fecha_egreso = "+ fEgreso;
		
		String condicion = ID +"="+empleado.getEmpleadoID();		
		String sql = "update "+tabla+" set "+valores+" where ("+condicion+");";
		ejecutarSQL(sql);
	}

	public void delete(Empleado empleado) {
		String condicion = ID +"="+empleado.getEmpleadoID();
		String sql = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(sql);
	}
	
	public List<Empleado> select() {
		String condicion = "1=1";
		List<Empleado> empleados = selectByCondicion(condicion);
		return empleados;
	}
	
	// Carlos: ¿Que esto? ¿acaso no pueden haber dos empleados con el mismo nombre?
	public Empleado selectByNombre(String nombre) {
		String condicion = "nombre = '"+nombre+"'";
		List<Empleado> empleados = selectByCondicion(condicion);
		Empleado empleado = null;
		if (empleados.size()>0)
			empleado = empleados.get(0); 
		
		return empleado;
	}
	
	public Empleado selectByDNI(String dni) {
		String condicion = "dni = '"+dni+"'";
		List<Empleado> empleados = selectByCondicion(condicion);
		Empleado empleado = null;
		if (empleados.size()>0)
			empleado = empleados.get(0); 
		
		return empleado;
	}

	public Empleado selectByID(Integer id) {
		String condicion = ID+" = "+id;
		List<Empleado> empleados = selectByCondicion(condicion);
		Empleado empleado = null;
		if (empleados.size()>0)
			empleado = empleados.get(0); 
		
		return empleado;
	}
	
	public List<Empleado> selectByRol(Rol rol){
		String condicion = "rol = "+Definido.rol(rol);
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
						resultados.getString("dni"),
						resultados.getString("nombre"),
						resultados.getString("apellido"),
						resultados.getString("telefono"),
						resultados.getString("email"),
						resultados.getDate("fecha_ingreso"),
						resultados.getDate("fecha_egreso"),
						Definido.rol(resultados.getInt("rol"))
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