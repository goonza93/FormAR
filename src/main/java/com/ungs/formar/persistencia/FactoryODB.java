package com.ungs.formar.persistencia;

import com.ungs.formar.persistencia.interfaces.AlumnoODB;
import com.ungs.formar.persistencia.interfaces.AreaOBD;
import com.ungs.formar.persistencia.interfaces.AsistenciaOBD;
import com.ungs.formar.persistencia.interfaces.ConfiguracionOBD;
import com.ungs.formar.persistencia.interfaces.CursoODB;
import com.ungs.formar.persistencia.interfaces.DiaOBD;
import com.ungs.formar.persistencia.interfaces.EmpleadoODB;
import com.ungs.formar.persistencia.interfaces.ExamenOBD;
import com.ungs.formar.persistencia.interfaces.HorarioCursadaOBD;
import com.ungs.formar.persistencia.interfaces.HorarioOBD;
import com.ungs.formar.persistencia.interfaces.InscripcionOBD;
import com.ungs.formar.persistencia.interfaces.InteraccionOBD;
import com.ungs.formar.persistencia.interfaces.InteresadoOBD;
import com.ungs.formar.persistencia.interfaces.NotificacionOBD;
import com.ungs.formar.persistencia.interfaces.PagoOBD;
import com.ungs.formar.persistencia.interfaces.PdfOBD;
import com.ungs.formar.persistencia.interfaces.ProgramaODB;
import com.ungs.formar.persistencia.interfaces.RecadoOBD;
import com.ungs.formar.persistencia.interfaces.SalaODB;
import com.ungs.formar.persistencia.interfaces.TareaOBD;
import com.ungs.formar.persistencia.mysql.AlumnoODBMySQL;
import com.ungs.formar.persistencia.mysql.AreaOBDMySQL;
import com.ungs.formar.persistencia.mysql.AsistenciaOBDMySQL;
import com.ungs.formar.persistencia.mysql.ConfiguracionOBDMySQL;
import com.ungs.formar.persistencia.mysql.CursoODBMySQL;
import com.ungs.formar.persistencia.mysql.DiaODBMySQL;
import com.ungs.formar.persistencia.mysql.EmpleadoODBMySQL;
import com.ungs.formar.persistencia.mysql.ExamenOBDMySQL;
import com.ungs.formar.persistencia.mysql.HorarioCursadaODBMySQL;
import com.ungs.formar.persistencia.mysql.HorarioOBDMySQL;
import com.ungs.formar.persistencia.mysql.InscripcionOBDMySQL;
import com.ungs.formar.persistencia.mysql.InteraccionOBDMySQL;
import com.ungs.formar.persistencia.mysql.InteresadoOBDMySQL;
import com.ungs.formar.persistencia.mysql.NotificacionOBDMySQL;
import com.ungs.formar.persistencia.mysql.PagoOBDMySQL;
import com.ungs.formar.persistencia.mysql.PdfODBMySQL;
import com.ungs.formar.persistencia.mysql.ProgramaODBMySQL;
import com.ungs.formar.persistencia.mysql.RecadoOBDMySQL;
import com.ungs.formar.persistencia.mysql.SalaODBMySQL;
import com.ungs.formar.persistencia.mysql.TareaOBDMySQL;

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
	
	public static PdfOBD crearPdfOBD() {
		return new PdfODBMySQL();
	}
	
	public static RecadoOBD crearRecadoOBD() {
		return new RecadoOBDMySQL();
	}
	
	public static PagoOBD crearPagoOBD() {
		return new PagoOBDMySQL();
	}
	
	public static InteresadoOBD crearInteresadoOBD() {
		return new InteresadoOBDMySQL();
	}
	
	public static InteraccionOBD crearInteraccionOBD() {
		return new InteraccionOBDMySQL();
	}
	
	public static AsistenciaOBD crearAsistenciaOBD() {
		return new AsistenciaOBDMySQL();
	}

	public static ExamenOBD crearExamenOBD() {
		return new ExamenOBDMySQL();
	}
	
	public static TareaOBD crearTareaOBD() {
		return new TareaOBDMySQL();
	}
	
	public static NotificacionOBD crearNotificacionOBD() {
		return new NotificacionOBDMySQL();
	}

	public static ConfiguracionOBD crearConfiguracionOBD() {
		return new ConfiguracionOBDMySQL();
	}

}