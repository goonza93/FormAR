package com.ungs.formar.negocios;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Curso;
import com.ungs.formar.persistencia.entidades.Empleado;
import com.ungs.formar.persistencia.entidades.Pago;
import com.ungs.formar.persistencia.entidades.Programa;
import com.ungs.formar.persistencia.interfaces.PagoOBD;
import com.ungs.formar.vista.util.Formato;

public class Tesoreria {

	public static void registrarPago(Alumno alumno, Curso curso, Empleado empleado, Integer monto, Integer mes,
			boolean pagoEnTermino, boolean pagoCompleto, Integer idPago) throws Exception {

		// Si no esta inscripto no puede pagar
		if (!InscripcionManager.estaInscripto(curso, alumno))
			throw new Exception(
					"El alumno " + alumno.getApellido() + " no esta inscripto a " + Formato.nombre(curso) + ".");

		Pago pago = new Pago(idPago, alumno.getID(), curso.getID(), empleado.getID(), monto, mes, pagoEnTermino,
				true, Almanaque.hoy());
		PagoOBD obd = FactoryODB.crearPagoOBD();
		obd.update(pago);
	}

	public static void registrarPagoCompleto(Alumno alumno, Curso curso, Empleado empleado, Integer monto,
			boolean pagoEnTermino, boolean pagoCompleto) throws Exception {

		List<Pago> pagos = traerPagosAlumno(alumno, curso);
		for(Pago pago : pagos){
			if(!pago.isPagoCompleto()){
				pagoEnTermino = pagoEnTermino(alumno, curso);
				registrarPago(alumno, curso, empleado, monto, pago.getMes(), pagoEnTermino, true, pago.getID());
			}
		}
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
	
	public static List<Pago> traerPagosAlumnoPendientes(Alumno alumno, Curso curso) {
		List<Pago> todosPagos = traerPagosAlumno(alumno, curso);
		List<Pago> pagosPendientes = new ArrayList<Pago>();
		for(Pago pago: todosPagos){
			if(!pago.isPagoCompleto())
				pagosPendientes.add(pago);
		}
		return pagosPendientes;
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
			java.sql.Date proximoPago = Almanaque.hoy();
			if(ultimoPago.getFecha() != null)
				proximoPago = new java.sql.Date(Almanaque.aumentarUnMes(ultimoPago.getFecha()).getTime());
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
		Integer cantCuotasImpagas = 0;
		for(Pago pago : pagos){
			if(!pago.isPagoCompleto())
				cantCuotasImpagas++;
		}
		return cantCuotasImpagas * costoMensual(curso);
	}

	public static List<Pago> traerPagosBusqueda(String dni, String curso, java.util.Date fechaDesde,
			java.util.Date fechaHasta) {
		PagoOBD obd = FactoryODB.crearPagoOBD();
		Alumno alumno = AlumnoManager.traerAlumnoSegunDNI(dni);
		Date desde = (fechaDesde == null) ? null : new Date(fechaDesde.getTime());
		Date hasta = (fechaHasta == null) ? null : new Date(fechaHasta.getTime());
		List<Pago> pagosAlumno = obd.selectBusqueda(alumno);
		List<Pago> retorno = new ArrayList<Pago>();

		if (!pagosAlumno.isEmpty()) {
			for (Pago pago : pagosAlumno) {
				if (validarDesde(pago, desde) && validarHasta(pago, hasta)) {
					if (!curso.isEmpty()) {
						Curso cursoPago = CursoManager.traerCursoPorId(pago.getCursada());
						Programa programaPago = ProgramaManager.traerProgramaSegunID(cursoPago.getPrograma());
						if (programaPago.getNombre().equals(curso))
							retorno.add(pago);
					} else
						retorno.add(pago);
				}
			}
		}
		return retorno;
	}

	private static boolean validarDesde(Pago pago, Date desde) {
		if (desde == null)
			return true;
		else if (desde.before(pago.getFecha()))
			return true;
		return false;
	}

	private static boolean validarHasta(Pago pago, Date hasta) {
		if (hasta == null)
			return true;
		else if (hasta.after(pago.getFecha()))
			return true;
		return false;
	}

	public static void crearPagos(Curso cursoSeleccionado, Alumno alumno) {
		Integer cantCuotas = Almanaque.diferenciaEnMeses(cursoSeleccionado.getFechaInicio(),
				cursoSeleccionado.getFechaFin());
		Integer monto = cursoSeleccionado.getPrecio()/cantCuotas;
		for (int i = 0; i < cantCuotas; i++) {
			Pago pago = new Pago(-1, alumno.getID(), cursoSeleccionado.getID(), null, monto, i+1, null, false, null);
			PagoOBD obd = FactoryODB.crearPagoOBD();
			obd.insert(pago);
		}
	}

	public static Integer cantCuotas(Integer idCurso){
		Curso curso = CursoManager.traerCursoPorId(idCurso);
		return Almanaque.diferenciaEnMeses(curso.getFechaInicio(),
				curso.getFechaFin());
	}
}