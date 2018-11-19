package com.ungs.formar.vista.reportes;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import com.ungs.formar.persistencia.entidades.Pago;
import com.ungs.formar.vista.tablas.TablaPagos;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.VentanaInterna;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GestionHistorialDeContacto{

	private static final long serialVersionUID = 1L;
	private JButton btnBuscar, btnCancelar;
	public JFrame ventana;
	private JTextField inFechaAño;
	private JLabel persona;
	private JDateChooser inFechaDesde, inFechaHasta;

	public GestionHistorialDeContacto() {
		ventana = new JFrame();
		ventana.setBounds(100, 100, 900, 500);
		ventana.setLocationRelativeTo(null);
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancelar.doClick();
			}
		});
		ventana.setSize(new Dimension(500, 200));
		ventana.setResizable(false);
		
		// CREO LOS FILTROS
		inFechaAño = new JTextField();
		persona = new JLabel();
		persona.setFont(new Font("Tahoma", Font.BOLD, 16));
		inFechaDesde = new JDateChooser();
		inFechaHasta = new JDateChooser();
		

		// ALTURA MAXIMA DE 25 PARA LAS ENTRADAS
		Dimension largoEntrada = new Dimension(Short.MAX_VALUE, 25);
		inFechaAño.setMaximumSize(largoEntrada);
		inFechaDesde.setMaximumSize(largoEntrada);
		inFechaHasta.setMaximumSize(largoEntrada);

		// UBICO LOS FILTROS EN PANELES
		PanelHorizontal mostrarNombre = new PanelHorizontal();
		mostrarNombre.add(persona);
		
		PanelVertical filtroFechaAño = new PanelVertical();
		filtroFechaAño.add(new JLabel("Año"));
		filtroFechaAño.add(inFechaAño);

		PanelVertical filtroFechaDesde = new PanelVertical();
		filtroFechaDesde.add(new JLabel("Desde"));
		filtroFechaDesde.add(inFechaDesde);

		PanelVertical filtroFechaHasta = new PanelVertical();
		filtroFechaHasta.add(new JLabel("Hasta"));
		filtroFechaHasta.add(inFechaHasta);

		// PANEL BUSQUEDA
		btnBuscar = new JButton("Buscar");
		PanelHorizontal panelBusqueda = new PanelHorizontal();
		panelBusqueda.add(filtroFechaAño);
		panelBusqueda.add(filtroFechaDesde);
		panelBusqueda.add(filtroFechaHasta);
		panelBusqueda.add(btnBuscar);
		Border borde = new EmptyBorder(10, 10, 10, 10);
		panelBusqueda.setBorder(borde);

		PanelHorizontal panelBtnCancelar = new PanelHorizontal();
		btnCancelar = new JButton("Cancelar");
		panelBtnCancelar.add(btnCancelar);
		
		// ORGANIZACION DE PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		ventana.setContentPane(panelPrincipal);
		panelPrincipal.add(mostrarNombre);
		panelPrincipal.add(panelBusqueda);
		panelPrincipal.add(panelBtnCancelar);
	}
	
	public JButton getBtnBuscar() {
		return btnBuscar;
	}
	
	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JTextField getInFechaAño() {
		return inFechaAño;
	}
	
	public JLabel getLblPersona() {
		return persona;
	}

	public JDateChooser getInFechaDesde() {
		return inFechaDesde;
	}
	
	public JDateChooser getInFechaHasta() {
		return inFechaHasta;
	}
	
}