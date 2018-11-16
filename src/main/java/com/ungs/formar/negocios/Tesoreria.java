package com.ungs.formar.negocios;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Pago;
import com.ungs.formar.persistencia.interfaces.PagoOBD;
import com.ungs.formar.vista.util.Formato;

public class Tesoreria {
	
	public static void registrarPago(Alumno alumno, Curso curso, Empleado empleado, Integer monto, Integer mes,
			boolean pagoEnTermino, boolean pagoCompleto) throws Exception {
		
		// Si no esta inscripto no puede pagar
		if (!InscripcionManager.estaInscripto(curso, alumno))
			throw new Exception("El alumno "+alumno.getApellido()+" no esta inscripto a "+Formato.nombre(curso)+".");
		
		Pago pago = new Pago(-1, alumno.getID(), curso.getID(), empleado.getID(), monto, mes, pagoEnTermino, pagoCompleto, Almanaque.hoy());
		PagoOBD obd = FactoryODB.crearPagoOBD();
		obd.insert(pago);
	}
	
	public static List<Pago> traerPagos() {
		PagoOBD obd = FactoryODB.crearPagoOBD();
		return obd.select();
	}

	public static void eliminarPago(Pago pago) {
		PagoOBD obd = FactoryODB.crearPagoOBD();
		obd.delete(pago);
	}

	public static void actualizarPago(Pago pago) throws Exception {
		Curso curso = CursoManager.traerCursoPorId(pago.getCursada());
		Alumno alumno = AlumnoManager.traerAlumnoSegunID(pago.getAlumno());

		// Si no esta inscripto no puede pagar
		if (!InscripcionManager.estaInscripto(curso, alumno))
			throw new Exception("El alumno "+alumno.getApellido()+" no esta inscripto a "+Formato.nombre(curso)+".");
		
		PagoOBD obd = FactoryODB.crearPagoOBD();
		obd.update(pago);
	}

	public static List<Pago> traerPagosAlumno(Alumno alumno){
		PagoOBD obd = FactoryODB.crearPagoOBD();
		return obd.selectByAlumno(alumno);
		
	}

	public static boolean pagoEnTermino(Alumno alumno){
		List<Pago> pagos = traerPagosAlumno(alumno);
		/*
		for(Pago pago : pagos){
			if(pago.)
		}*/
		return true;
	}
}