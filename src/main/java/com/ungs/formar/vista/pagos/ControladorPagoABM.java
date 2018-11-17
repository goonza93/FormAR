package com.ungs.formar.vista.pagos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;

import javax.swing.JInternalFrame;

import com.ungs.formar.negocios.Almanaque;
import com.ungs.formar.negocios.AlumnoManager;
import com.ungs.formar.negocios.Tesoreria;
import com.ungs.formar.persistencia.entidades.Alumno;
import com.ungs.formar.persistencia.entidades.Pago;
import com.ungs.formar.vista.pagos.registrar.ControladorPagoAM;
import com.ungs.formar.vista.pantallasPrincipales.ControladorInterno;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPantallaPrincipal;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPrincipal;
import com.ungs.formar.vista.reportes.FacturaPago;
import com.ungs.formar.vista.util.Popup;

public class ControladorPagoABM implements ActionListener, ControladorInterno {
	private ControladorPrincipal invocador;
	private VentanaPagoABM ventana;

	public ControladorPagoABM(ControladorPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaPagoABM();
		ventana.getRegistrar().addActionListener(s -> registrar());
		ventana.getFactura().addActionListener(s -> verFactura());
		ventana.getBtnBuscar().addActionListener(s -> buscar());
	}

	public void actionPerformed(ActionEvent e) {}

	private void registrar() {
		invocador.getVentana().setEnabled(false);
		new ControladorPagoAM(this);
	}

	private void verFactura() {
		List<Pago> pagos = ventana.getTabla().obtenerSeleccion();
		if (pagos.size() != 1) {
			Popup.mostrar("Debe seleccionar extamente 1 pago para obtener el comprobante.");
			return;
		}
		// ventana.deshabilitar();
		FacturaPago reporte = new FacturaPago(pagos);
		reporte.mostrar();
	}

	public void mostrar() {
		ventana.mostrar();
	}

	public void buscar() {
		String dniAlumno = ventana.getInAlumno().getText();
		Date fechaDesde = ventana.getInFechaDesde().getDate();
		Date fechaHasta = ventana.getInFechaHasta().getDate();

		String mensaje = "";
		if (dniAlumno.isEmpty())
			mensaje += "- Ingrese el DNI de un alumno.\n";
		else {
			Alumno alumno = AlumnoManager.traerAlumnoSegunDNI(dniAlumno);
			if (alumno == null)
				mensaje += "No existe ningun alumno con el DNI " + dniAlumno + ".\n";
		}
		if (fechaDesde != null) {
			if (fechaDesde.after(Almanaque.hoy()))
				mensaje += "La fecha de busqueda de inicio no puede ser posterior a la de hoy";
			if (fechaHasta != null && fechaDesde.after(fechaHasta))
				mensaje += "La fecha de busqueda de inicio no puede ser posterior a la de fin";
		}
		if(!mensaje.isEmpty())
			Popup.mostrar(mensaje);
		else
			recargar();
	}

	public void recargar() {
		String dniAlumno = ventana.getInAlumno().getText();
		Date fechaDesde = ventana.getInFechaDesde().getDate();
		Date fechaHasta = ventana.getInFechaHasta().getDate();
		
		List<Pago> pagos = Tesoreria.traerPagosBusqueda(dniAlumno, fechaDesde, fechaHasta);
		ventana.getTabla().recargar(pagos);
	}

	@Override
	public boolean finalizar() {
		return true;
	}

	@Override
	public JInternalFrame getVentana() {
		return ventana;
	}
	
	public void habilitarPrincipal(){
		invocador.getVentana().setEnabled(true);
		invocador.getVentana().toFront();
	}

}