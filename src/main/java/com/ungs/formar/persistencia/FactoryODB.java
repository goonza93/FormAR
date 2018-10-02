package com.ungs.formar.persistencia;

import com.ungs.formar.persistencia.interfacesOBD.CursoODB;
import com.ungs.formar.persistencia.interfacesOBD.EmpleadoODB;
import com.ungs.formar.persistencia.interfacesOBD.ProgramaODB;
import com.ungs.formar.persistencia.interfacesOBD.SalaODB;
import com.ungs.formar.persistencia.interfacesOBD.UsuarioODB;
import com.ungs.formar.persistencia.mysqlOBD.CursoODBMySQL;
import com.ungs.formar.persistencia.mysqlOBD.EmpleadoODBMySQL;
import com.ungs.formar.persistencia.mysqlOBD.ProgramaODBMySQL;
import com.ungs.formar.persistencia.mysqlOBD.SalaODBMySQL;
import com.ungs.formar.persistencia.mysqlOBD.UsuarioODBMySQL;

public class FactoryODB {
	
	public static CursoODB crearCursoODB() {
		return new CursoODBMySQL();
	}
	
	public static EmpleadoODB crearEmpleadoODB() {
		return new EmpleadoODBMySQL();
	}
	
	public static SalaODB crearSalaODB() {
		return new SalaODBMySQL();
	}
	
	public static ProgramaODB crearProgramaODB() {
		return new ProgramaODBMySQL();
	}
	
	public static UsuarioODB getUsuarioODB() {
		return new UsuarioODBMySQL();
	}

}