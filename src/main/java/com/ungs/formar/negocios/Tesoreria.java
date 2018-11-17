package com.ungs.formar.negocios;

import java.sql.Date;
import java.util.ArrayList;
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
			throw new Exception(
					"El alumno " + alumno.getApellido() + " no esta inscripto a " + Formato.nombre(curso) + ".");

		Pago pago = new Pago(-1, alumno.getID(), curso.getID(), empleado.getID(), monto, mes, pagoEnTermino,
				pagoCompleto, Almanaque.hoy());
		PagoOBD obd = FactoryODB.crearPagoOBD();
		obd.insert(pago);
		verificarPagoCompleto(alumno, curso);
	}

	public static void registrarPagoCompleto(Alumno alumno, Curso curso, Empleado empleado, Integer monto,
			boolean pagoEnTermino, boolean pagoCompleto) throws Exception {

		List<Pago> pagos = traerPagosAlumno(alumno, curso);
		Integer cantCuotas = Almanaque.diferenciaEnMeses(curso.getFechaInicio(), curso.getFechaFin());
		Integer cantCuotasRestantes = cantCuotas - pagos.size();

		for (int i = 0; i < cantCuotasRestantes; i++) {
			pagoEnTermino = pagoEnTermino(alumno, curso);
			Pago pago = new Pago(-1, alumno.getID(), curso.getID(), empleado.getID(), costoMensual(curso),
					Almanaque.mesActual(), pagoEnTermino, pagoCompleto, Almanaque.hoy());
			PagoOBD obd = FactoryODB.crearPagoOBD();
			obd.insert(pago);
		}
		verificarPagoCompleto(alumno, curso);
	}

	public static List<Pago> traerPagos() {
		PagoOBD obd = FactoryODB.crearPagoOBD();
		return obd.select();
	}

	public static void eliminarPago(Pago pago) {
		PagoOBD obd = FactoryODB.crearPagoOBD();
		obd.delete(pago);
	}

	public static void actualizarPago(Pago pago) {
		PagoOBD obd = FactoryODB.crearPagoOBD();
		obd.update(pago);
	}

	public static List<Pago> traerPagosAlumno(Alumno alumno, Curso curso) {
		PagoOBD obd = FactoryODB.crearPagoOBD();
		return obd.selectByAlumno(alumno, curso);

	}

	public static boolean pagoEnTermino(Alumno alumno, Curso curso) {
		List<Pago> pagos = traerPagosAlumno(alumno, curso);
		Pago ultimoPago = null;
		if (!pagos.isEmpty()) {
			ultimoPago = pagos.get(pagos.size() - 1);

			// Le aumento un mes para saber cuando era el proximo pago.
			// Si la fecha del proximo pago, esta en el mismo mes o despues que
			// el mes actual
			// Es decir, la dif de meses es >=0, esta en termino

			java.sql.Date proximoPago = new java.sql.Date(Almanaque.aumentarUnMes(ultimoPago.getFecha()).getTime());
			if (Almanaque.diferenciaEnMeses(Almanaque.hoy(), proximoPago) < 0)
				return false;
		} else if (Almanaque.diferenciaEnMeses(Almanaque.hoy(), curso.getFechaInicio()) < 0)
			return false;
		return true;
	}

	public static Integer costoMensual(Curso curso) {

		Integer diferenciaMeses = Almanaque.diferenciaEnMeses(curso.getFechaInicio(), curso.getFechaFin());
		Integer costoMensual = curso.getPrecio() / diferenciaMeses;

		return costoMensual;
	}

	public static Integer costoRestante(Alumno alumno, Curso curso) {
		List<Pago> pagos = traerPagosAlumno(alumno, curso);
		Integer cantCuotas = Almanaque.diferenciaEnMeses(curso.getFechaInicio(), curso.getFechaFin());
		Integer cantCuotasImpagas = cantCuotas - pagos.size();
		return cantCuotasImpagas * costoMensual(curso);
	}

	public static void verificarPagoCompleto(Alumno alumno, Curso curso) {
		List<Pago> pagos = traerPagosAlumno(alumno, curso);
		Integer cantCuotasPagas = Almanaque.diferenciaEnMeses(curso.getFechaInicio(), curso.getFechaFin());

		if (pagos.size() == cantCuotasPagas)
			marcarPagosComoCompleto(alumno, curso);
	}

	public static void marcarPagosComoCompleto(Alumno alumno, Curso curso) {
		List<Pago> pagos = traerPagosAlumno(alumno, curso);
		for (Pago pago : pagos) {
			pago.setPagoCompleto(true);
			actualizarPago(pago);
		}
	}


	
	public static List<Pago> traerPagosBusqueda(String dni, java.util.Date fechaDesde, java.util.Date fechaHasta){
		PagoOBD obd = FactoryODB.crearPagoOBD();
		Alumno alumno = AlumnoManager.traerAlumnoSegunDNI(dni);
		Date desde = (fechaDesde == null) ? null : new Date(fechaDesde.getTime());
		Date hasta = (fechaHasta == null) ? null : new Date(fechaHasta.getTime());
		List<Pago> pagosAlumno = obd.selectBusqueda(alumno);
		List<Pago> retorno = new ArrayList<Pago>();
		if(!pagosAlumno.isEmpty()){
			for(Pago pago : pagosAlumno){
				if(validarDesde(pago, desde) && validarHasta(pago, hasta))
					retorno.add(pago);
			}
		}
		return retorno;
	}
	
	private static boolean validarDesde(Pago pago, Date desde){
		if(desde==null)
			return true;
		else if(desde.before(pago.getFecha()))
			return true;
		return false;
	}
	
	private static boolean validarHasta(Pago pago, Date hasta){
		if(hasta==null)
			return true;
		else if(hasta.after(pago.getFecha()))
			return true;
		return false;
	}
}