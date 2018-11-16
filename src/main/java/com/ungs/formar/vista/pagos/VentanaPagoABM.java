package com.ungs.formar.vista.pagos;

import javax.swing.JScrollPane;
import com.ungs.formar.negocios.Tesoreria;
import com.ungs.formar.vista.tablas.TablaPagos;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.Ventana;
import javax.swing.JButton;

public class VentanaPagoABM extends Ventana{
	private static final long serialVersionUID = 1L;
	private TablaPagos tabla;
	private JButton btnRegistrar, btnFactura, btnVolver;
	
	public VentanaPagoABM() {
		super("Administracion de pagos");
		setBounds(100, 100, 713, 405);
		setLocationRelativeTo(null);
		
		// TABLA DE PAGOS
		tabla = new TablaPagos(Tesoreria.traerPagos());
		JScrollPane panelTabla = new JScrollPane();
		panelTabla.setViewportView(tabla);
		
		// BOTONES
		btnRegistrar = new JButton("Registrar pago");
		btnFactura = new JButton("Imprimir factura");
		btnVolver = new JButton("Volver");

		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnRegistrar);
		panelBotones.add(btnFactura);
		panelBotones.add(btnVolver);
		
		// ORGANIZACION DE PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		setContentPane(panelPrincipal);
		panelPrincipal.add(panelTabla);
		panelPrincipal.add(panelBotones);
	}

	public TablaPagos getTabla() {
		return tabla;
	}
	
	public JButton getRegistrar() {
		return btnRegistrar;
	}

	public JButton getFactura() {
		return btnFactura;
	}

	public JButton getVolver() {
		return btnVolver;
	}
	
}