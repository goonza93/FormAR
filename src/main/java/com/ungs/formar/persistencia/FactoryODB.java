package com.ungs.formar.persistencia;

import com.ungs.formar.persistencia.interfacesOBD.AlumnoODB;
import com.ungs.formar.persistencia.interfacesOBD.AreaOBD;
import com.ungs.formar.persistencia.interfacesOBD.CursoODB;
import com.ungs.formar.persistencia.interfacesOBD.DiaOBD;
import com.ungs.formar.persistencia.interfacesOBD.EmpleadoODB;
import com.ungs.formar.persistencia.interfacesOBD.HorarioCursadaOBD;
import com.ungs.formar.persistencia.interfacesOBD.HorarioOBD;
import com.ungs.formar.persistencia.interfacesOBD.InscripcionOBD;
import com.ungs.formar.persistencia.interfacesOBD.PdfOBD;
import com.ungs.formar.persistencia.interfacesOBD.ProgramaODB;
import com.ungs.formar.persistencia.interfacesOBD.RecadoOBD;
import com.ungs.formar.persistencia.interfacesOBD.SalaODB;
import com.ungs.formar.persistencia.mysqlOBD.AlumnoODBMySQL;
import com.ungs.formar.persistencia.mysqlOBD.AreaOBDMySQL;
import com.ungs.formar.persistencia.mysqlOBD.CursoODBMySQL;
import com.ungs.formar.persistencia.mysqlOBD.DiaODBMySQL;
import com.ungs.formar.persistencia.mysqlOBD.EmpleadoODBMySQL;
import com.ungs.formar.persistencia.mysqlOBD.HorarioCursadaODBMySQL;
import com.ungs.formar.persistencia.mysqlOBD.HorarioOBDMySQL;
import com.ungs.formar.persistencia.mysqlOBD.InscripcionOBDMySQL;
import com.ungs.formar.persistencia.mysqlOBD.PdfODBMySQL;
import com.ungs.formar.persistencia.mysqlOBD.ProgramaODBMySQL;
import com.ungs.formar.persistencia.mysqlOBD.RecadoOBDMySQL;
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
	
	public static AreaOBD crearAreaOBD() {
		return new AreaOBDMySQL();
	}
	
	public static HorarioCursadaOBD crearHorarioCursada() {
		return new HorarioCursadaODBMySQL();
	}
	
	public static HorarioOBD crearHorarioOBD() {
		return new HorarioOBDMySQL();
	}
	
	public static InscripcionOBD crearInscripcionOBD() {
		return new InscripcionOBDMySQL();
	}
	
	public static PdfOBD crearPdfOBD(){
		return new PdfODBMySQL();
	}
	
	public static RecadoOBD crearRecadoOBD() {
		return new RecadoOBDMySQL();
	}
	
}