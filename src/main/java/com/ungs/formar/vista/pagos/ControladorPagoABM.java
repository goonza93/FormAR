package com.ungs.formar.vista.pagos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import com.ungs.formar.negocios.Tesoreria;
import com.ungs.formar.persistencia.entidades.Pago;
import com.ungs.formar.vista.pagos.registrar.ControladorPagoAM;
import com.ungs.formar.vista.pantallasPrincipales.ControladorPantallaPrincipal;
import com.ungs.formar.vista.util.Popup;

public class ControladorPagoABM implements ActionListener {
	private ControladorPantallaPrincipal invocador;
	private VentanaPagoABM ventana;
	
	public ControladorPagoABM(ControladorPantallaPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaPagoABM();
		recargar();
		ventana.getRegistrar().addActionListener(this);
		ventana.getModificar().addActionListener(this);
		ventana.getEliminar().addActionListener(this);
		ventana.getFactura().addActionListener(this);
		ventana.getVolver().addActionListener(this);
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				volver();
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		// BOTON REGISTRAR PAGO DE LA VENTANA PAGOS
		if (e.getSource() == ventana.getRegistrar())
			registrar();
		
		// BOTON MODIFICAR DE LA VENTANA PAGOS
		else if (e.getSource() == ventana.getModificar())
			modificar();
		
		// BOTON ELIMINAR DE LA VENTANA PAGOS
		else if (e.getSource() == ventana.getEliminar())
			eliminar();
		
		// BOTON VER FACTURA DE LA VENTANA PAGOS
		else if (e.getSource() == ventana.getFactura())
			verFactura();
		
		// BOTON VOLVER DE LA VENTANA PAGOS
		else if (e.getSource() == ventana.getVolver())
			volver();
	}

	private void registrar() {
		ventana.deshabilitar();
		new ControladorPagoAM(this);
	}

	private void modificar() {
		List<Pago> pagos = ventana.getTabla().obtenerSeleccion();
		if (pagos.size() != 1) {
			Popup.mostrar("Debe seleccionar extamente 1 pago para modificarlo.");
			return;
		}
		
		ventana.deshabilitar();
		new ControladorPagoAM(this, pagos.get(0));
	}
	
	private void eliminar() {
		List<Pago> pagos = ventana.getTabla().obtenerSeleccion();
		if (pagos.size() == 0)
			Popup.mostrar("Seleccione al menos un pago para eliminar.");
		else {
			for (Pago pago : pagos)
				Tesoreria.eliminarPago(pago);
			recargar();
		}
	}
	
	private void verFactura() {
		Popup.mostrar("Esta funcionalidad aun no esta disponible.\nDisculpe las molestias.");
	}

	private void volver() {
		ventana.dispose();
		ventana = null;
		invocador.inicializar();
	}

	public void mostrar() {
		ventana.mostrar();
	}
	
	public void recargar() {
		ventana.getTabla().recargar(Tesoreria.traerPagos());
	}
	
}