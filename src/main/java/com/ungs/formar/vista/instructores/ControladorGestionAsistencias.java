package com.ungs.formar.vista.instructores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.ungs.formar.vista.pantallasPrincipales.ControladorPantallaPrincipal;

public class ControladorGestionAsistencias implements ActionListener {
	private ControladorPantallaPrincipal invocador;
	private VentanaGestionAsistencias ventana;
	
	public ControladorGestionAsistencias(ControladorPantallaPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaGestionAsistencias();
		recargar();
		ventana.botonConsultar().addActionListener(this);
		ventana.botonTomar().addActionListener(this);
		ventana.getVolver().addActionListener(this);
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				volver();
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == ventana.botonConsultar())
			consultar();
		
		else if (e.getSource() == ventana.botonTomar())
			tomar();
		
		else if (e.getSource() == ventana.getVolver())
			volver();
	}

	private void consultar() {
		//ventana.deshabilitar();
		//new ControladorPagoAM(this);
	}

	private void tomar() {
		/*List<Pago> pagos = ventana.getTabla().obtenerSeleccion();
		if (pagos.size() != 1) {
			Popup.mostrar("Debe seleccionar extamente 1 pago para modificarlo.");
			return;
		}
		
		ventana.deshabilitar();
		new ControladorPagoAM(this, pagos.get(0));*/
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
		//ventana.getTabla().recargar(Tesoreria.traerPagos());
	}
	
}