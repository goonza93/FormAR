package com.ungs.formar.persistencia;

import com.ungs.formar.persistencia.interfacesOBD.AlumnoODB;
import com.ungs.formar.persistencia.interfacesOBD.AreaOBD;
import com.ungs.formar.persistencia.interfacesOBD.CursoODB;
import com.ungs.formar.persistencia.interfacesOBD.DiaOBD;
import com.ungs.formar.persistencia.interfacesOBD.EmpleadoODB;
import com.ungs.formar.persistencia.interfacesOBD.EstadoCursoOBD;
import com.ungs.formar.persistencia.interfacesOBD.HorarioCursadaOBD;
import com.ungs.formar.persistencia.interfacesOBD.ProgramaODB;
import com.ungs.formar.persistencia.interfacesOBD.SalaODB;
import com.ungs.formar.persistencia.mysqlOBD.AlumnoODBMySQL;
import com.ungs.formar.persistencia.mysqlOBD.AreaOBDMySQL;
import com.ungs.formar.persistencia.mysqlOBD.CursoODBMySQL;
import com.ungs.formar.persistencia.mysqlOBD.DiaODBMySQL;
import com.ungs.formar.persistencia.mysqlOBD.EmpleadoODBMySQL;
import com.ungs.formar.persistencia.mysqlOBD.EstadoCursoODBMySQL;
import com.ungs.formar.persistencia.mysqlOBD.HorarioCursadaODBMySQL;
import com.ungs.formar.persistencia.mysqlOBD.ProgramaODBMySQL;
import com.ungs.formar.persistencia.mysqlOBD.SalaODBMySQL;

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
	
	public static DiaOBD crearDiaOBD() {
		return new DiaODBMySQL();
	}
	
	public static AlumnoODB crearAlumnoODB() {
		return new AlumnoODBMySQL();
	}
	
	public static EstadoCursoOBD crearEstadoCursoOBD() {
		return new EstadoCursoODBMySQL();
	}
	
	public static AreaOBD crearAreaOBD() {
		return new AreaOBDMySQL();
	}
	
	public static HorarioCursadaOBD crearHorarioCursada() {
		return new HorarioCursadaODBMySQL();
	}
	
}