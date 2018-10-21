package com.ungs.formar.persistencia.mysqlOBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.interfacesOBD.CursoODB;

public class CursoODBMySQL extends ODB implements CursoODB{
	private final String campos = "cupo_minimo, cupo_maximo, precio, comision, fecha_inicio, fecha_fin, fecha_cierre, contenido, horas, instructor, responsable, programa, estado";
	private final String tabla = "for_cursos";
	private final String ID = "curso_ID";
	
	public void insert (Curso curso) {
		String fInicio = curso.getFechaInicio() == null ? null : "'"+curso.getFechaInicio()+"'"; 
		String fFin = curso.getFechaFin() == null ? null : "'"+curso.getFechaFin()+"'";
		String fCierre = curso.getFechaCierre() == null ? null : "'"+curso.getFechaCierre()+"'";
		String comision = curso.getComision() == null ? null : "'"+curso.getComision()+"'"; 
		
		String valores = curso.getCupoMinimo()
				+", "+ curso.getCupoMaximo()
				+", "+curso.getPrecio()
				+", "+ comision
				+", "+ fInicio
				+", "+ fFin
				+", "+ fCierre
				+", "+ curso.getContenido()
				+", "+ curso.getHoras()
				+", "+ curso.getInstructor()
				+", "+ curso.getResponsable()
				+", "+ curso.getPrograma()
				+", "+ curso.getEstado();
		
		String consulta = "insert into "+tabla+" ("+campos+") values ("+valores+");";
		ejecutarSQL(consulta);
	}

	public void delete(Curso curso) {
		String condicion = ID + "=" + curso.getCursoID();
		String consulta = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	public void update(Curso curso) {
		String fInicio = curso.getFechaInicio() == null ? null : "'"+curso.getFechaInicio()+"'"; 
		String fFin = curso.getFechaFin() == null ? null : "'"+curso.getFechaFin()+"'";
		String fCierre = curso.getFechaCierre() == null ? null : "'"+curso.getFechaCierre()+"'";
		String comision = curso.getComision() == null ? null : "'"+curso.getComision()+"'"; 
		String condicion = ID+" = "+curso.getCursoID();
		
		String consulta = "update "+ tabla
				+ " set cupo_minimo = "+curso.getCupoMinimo()
				+ ", cupo_maximo = "+curso.getCupoMaximo()
				+ ", precio = "+curso.getPrecio()
				+ ", comision = "+comision
				+ ", fecha_inicio = "+fInicio
				+ ", fecha_fin = "+fFin
				+ ", fecha_cierre = "+fCierre
				+ ", contenido = "+curso.getContenido()
				+ ", horas = "+curso.getHoras()
				+ ", instructor = "+curso.getInstructor()
				+ ", responsable = "+curso.getResponsable()
				+ ", programa = "+curso.getPrograma()
				+ ", estado = "+curso.getEstado()
				+ " where ("+condicion+");";
		ejecutarSQL(consulta);
	}
		
	public List<Curso> select() {
		return selectByCondicion("1=1");
	}
	
	public Curso selectByID(Integer ID){
		String condicion = this.ID+" = "+ID;
		List<Curso> cursos = selectByCondicion(condicion);
		Curso curso = null;
		if (cursos.size()>0)
			curso = cursos.get(0); 
		
		return curso;
	}
	
	public List<Curso> selectByPrograma(Integer IDPrograma){
		String condicion = "programa = "+IDPrograma;
		List<Curso> cursos = selectByCondicion(condicion);
		
		return cursos;
	}
	
	public List<Curso> selectByInstructor(Empleado empleado){
		String condicion = "instructor = "+empleado.getEmpleadoID();
		List<Curso> cursos = selectByCondicion(condicion);		
		return cursos;
	}

	private List<Curso> selectByCondicion(String condicion) {
		List<Curso> cursos = new ArrayList<Curso>();
		String comandoSQL = "select "+ID + ", "+campos+" from "+tabla+" where ("+condicion+");";
				
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				cursos.add(new Curso(
						resultados.getInt("curso_ID"),
						resultados.getInt("cupo_minimo"),
						resultados.getInt("cupo_maximo"),
						resultados.getInt("precio"),
						resultados.getInt("horas"),
						resultados.getInt("contenido"),
						resultados.getString("comision"),
						resultados.getDate("fecha_inicio"),
						resultados.getDate("fecha_fin"),
						resultados.getDate("fecha_cierre"),
						resultados.getInt("instructor"),
						resultados.getInt("programa"),
						resultados.getInt("estado"),
						resultados.getInt("responsable")
						));
				}
			
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
		}
			
		return cursos;
	}

	public Integer selectIDMasReciente() {
		return selectLastID(ID, tabla);
	}

}