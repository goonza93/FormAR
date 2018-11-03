package com.ungs.formar.vista.pagos.registrar;

import com.ungs.formar.persistencia.entidades.Pago;
import com.ungs.formar.vista.pagos.ControladorPagoABM;

public class ControladorPagoAM {
	private ControladorPagoABM invocador;
	private VentanaPagoAM ventana;
	private Pago pago = null;
	
	public ControladorPagoAM(ControladorPagoABM invocador) {
		ventana = new VentanaPagoAM();
		
	}

	public ControladorPagoAM(ControladorPagoABM invocador, Pago pago) {
		
	}

	
	
	
	
}
