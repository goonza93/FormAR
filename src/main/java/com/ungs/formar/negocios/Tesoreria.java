package com.ungs.formar.negocios;

import java.util.List;
import com.ungs.formar.persistencia.FactoryODB;
import com.ungs.formar.persistencia.entidades.Pago;
import com.ungs.formar.persistencia.interfaces.PagoOBD;

public class Tesoreria {
	
	public static List<Pago> traerPagos() {
		PagoOBD obd = FactoryODB.crearPagoOBD();
		return obd.select();
	}

	public static void eliminarPago(Pago pago) {
		PagoOBD obd = FactoryODB.crearPagoOBD();
		obd.delete(pago);
	}

}