package com.ungs.formar.persistencia.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.formar.persistencia.ODB;
import com.ungs.formar.persistencia.entidades.Pago;
import com.ungs.formar.persistencia.interfaces.PagoOBD;

public class PagoOBDMySQL extends ODB implements PagoOBD{
	private final String campos = "alumno, cursada, empleado, monto, mes, pago_en_termino, pago_completo, fecha";
	private final String tabla = "for_pagos";

	public void insert(Pago pago) {
		String valores = pago.getAlumno()
				+", "+pago.getCursada()
				+", "+pago.getEmpleado()
				+", "+pago.getMonto()
				+", "+pago.getMes()
				+", "+pago.isPagoEnTermino()
				+", "+pago.isPagoCompleto()
				+", '"+pago.getFecha()+"'";
		String sql = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(sql);		
	}

	public void update(Pago pago) {
		String condicion = "ID = "+pago.getID();
		String valores = "alumno = "+pago.getAlumno()
				+", cursada = "+pago.getCursada()
				+", empleado = "+pago.getEmpleado()
				+", monto = "+pago.getMonto()
				+", mes = "+pago.getMes()
				+", pago_en_termono = "+pago.isPagoEnTermino()
				+", pago_completo = "+pago.isPagoCompleto()
				+", fecha = '"+pago.getFecha()+"'";
		String sql = "update "+tabla+" set "+valores+" where ("+condicion+");";
		ejecutarSQL(sql);
	}

	public void delete(Pago pago) {
		String condicion = "ID = "+pago.getID();
		String sql = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(sql);		
	}

	public List<Pago> select() {
		String condicion = "true";
		return selectByCondicion(condicion);
	}

	private List<Pago> selectByCondicion(String condicion) {
		List<Pago> pagos = new ArrayList<Pago>();
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			while (resultados.next()) {
				pagos.add(new Pago(
						resultados.getInt("ID"),
						resultados.getInt("alumno"),
						resultados.getInt("cursada"),
						resultados.getInt("empleado"),
						resultados.getInt("monto"),
						resultados.getInt("mes"),
						resultados.getBoolean("pago_en_termino"),
						resultados.getBoolean("pago_completo"),
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
			
		return pagos;
	}
	
}