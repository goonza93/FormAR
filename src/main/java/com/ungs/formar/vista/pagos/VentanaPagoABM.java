package com.ungs.formar.vista.pagos;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import com.ungs.formar.negocios.Tesoreria;
import com.ungs.formar.persistencia.entidades.Pago;
import com.ungs.formar.vista.tablas.TablaPagos;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.Ventana;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

public class VentanaPagoABM extends Ventana {
	private static final long serialVersionUID = 1L;
	private TablaPagos tabla;
	private JButton btnRegistrar, btnFactura, btnVolver, btnBuscar;
	private JTextField inAlumno;
	private JDateChooser inFechaDesde, inFechaHasta;

	public VentanaPagoABM() {
		super("Administracion de pagos");
		setBounds(100, 100, 713, 405);
		setLocationRelativeTo(null);

		// CREO LOS FILTROS PARA LA TABLA
		inAlumno = new JTextField();
		inFechaDesde = new JDateChooser();
		inFechaHasta = new JDateChooser();

		// ALTURA MAXIMA DE 25 PARA LAS ENTRADAS
		Dimension largoEntrada = new Dimension(Short.MAX_VALUE, 25);
		inAlumno.setMaximumSize(largoEntrada);
		inFechaDesde.setMaximumSize(largoEntrada);
		inFechaHasta.setMaximumSize(largoEntrada);

		// UBICO LOS FILTROS EN PANELES
		PanelVertical filtroAlumno = new PanelVertical();
		filtroAlumno.add(new JLabel("DNI Alumno"));
		filtroAlumno.add(inAlumno);

		PanelVertical filtroFechaDesde = new PanelVertical();
		filtroFechaDesde.add(new JLabel("Desde"));
		filtroFechaDesde.add(inFechaDesde);

		PanelVertical filtroFechaHasta = new PanelVertical();
		filtroFechaHasta.add(new JLabel("Hasta"));
		filtroFechaHasta.add(inFechaHasta);

		// PANEL BUSQUEDA
		btnBuscar = new JButton("Buscar");
		PanelHorizontal panelBusqueda = new PanelHorizontal();
		panelBusqueda.add(filtroAlumno);
		panelBusqueda.add(filtroFechaDesde);
		panelBusqueda.add(filtroFechaHasta);
		panelBusqueda.add(btnBuscar);
		Border borde = new EmptyBorder(10, 10, 10, 10);
		panelBusqueda.setBorder(borde);

		// TABLA DE PAGOS
		tabla = new TablaPagos(new ArrayList<Pago>());// NO CARGA SOLO
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
		panelPrincipal.add(panelBusqueda);
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

	
	public JButton getBtnBuscar() {
		return btnBuscar;
	}
	

	public JTextField getInAlumno() {
		return inAlumno;
	}
	

	public JDateChooser getInFechaDesde() {
		return inFechaDesde;
	}

	
	public JDateChooser getInFechaHasta() {
		return inFechaHasta;
	}

	
}