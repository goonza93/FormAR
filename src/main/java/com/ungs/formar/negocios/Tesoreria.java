package com.ungs.formar.negocios;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Pago;
import com.ungs.formar.persistencia.interfaces.PagoOBD;

public class Tesoreria {
	
	public static void registrarPago(Alumno alumno, Curso curso, Empleado empleado, Integer monto, Integer mes,
			boolean pagoEnTermino, boolean pagoCompleto) {
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

}