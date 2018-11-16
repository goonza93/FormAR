package com.ungs.formar.vista.pagos.registrar;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.Ventana;

public class VentanaPagoAM extends Ventana{
	private static final long serialVersionUID = 1L;
	private JButton btnRegistrar, btnCancelar, btnAlumno, btnCursada;
	private JTextField inAlumno, inCursada, inMonto, inMes;
	private JCheckBox inPagoCompleto;
	
	public VentanaPagoAM() {
		super("Registrar pago");
		setBounds(100, 100, 500, 231);
		setLocationRelativeTo(null);
		
		// TABLA DE ENTRADAS
		Dimension largoEntrada = new Dimension(Short.MAX_VALUE, 25);
		Dimension largoLabel = new Dimension(100, 25);
		
		JLabel lblAlumno = new JLabel("Alumno");
		lblAlumno.setPreferredSize(largoLabel);
		inAlumno = new JTextField();
		inAlumno.setEnabled(false);
		inAlumno.setMaximumSize(largoEntrada);
		btnAlumno = new JButton("Seleccionar");
		PanelHorizontal panelAlumno = new PanelHorizontal();
		panelAlumno.add(lblAlumno);
		panelAlumno.add(inAlumno);
		panelAlumno.add(btnAlumno);

		JLabel lblCursada = new JLabel("Cursada");
		lblCursada.setPreferredSize(largoLabel);
		inCursada = new JTextField();
		inCursada.setEnabled(false);
		inCursada.setMaximumSize(largoEntrada);
		btnCursada = new JButton("Seleccionar");
		PanelHorizontal panelCursada = new PanelHorizontal();
		panelCursada.add(lblCursada);
		panelCursada.add(inCursada);
		panelCursada.add(btnCursada);
		
		JLabel lblMonto = new JLabel("Monto");
		lblMonto.setPreferredSize(largoLabel);
		inMonto = new JTextField();
		inMonto.setMaximumSize(largoEntrada);
		PanelHorizontal panelMonto = new PanelHorizontal();
		panelMonto.add(lblMonto);
		panelMonto.add(inMonto);
		
		JLabel lblMes = new JLabel("Mes");
		lblMes.setPreferredSize(largoLabel);
		inMes = new JTextField();
		inMes.setMaximumSize(largoEntrada);
		PanelHorizontal panelMes = new PanelHorizontal();
		panelMes.add(lblMes);
		panelMes.add(inMes);
		inPagoCompleto = new JCheckBox("Pago completo");
		PanelHorizontal panelCheck = new PanelHorizontal();
		panelCheck.add(inPagoCompleto);
		
		// BOTONES
		btnRegistrar = new JButton("Registrar pago");
		btnCancelar = new JButton("Cancelar");
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.add(btnRegistrar);
		panelBotones.add(btnCancelar);
		
		// ORGANIZACION DE PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		setContentPane(panelPrincipal);
		panelPrincipal.add(panelAlumno);
		panelPrincipal.add(panelCursada);
		panelPrincipal.add(panelMonto);
		panelPrincipal.add(panelMes);
		panelPrincipal.add(panelCheck);
		panelPrincipal.add(panelBotones);
	}

	public JButton getRegistrar() {
		return btnRegistrar;
	}

	public JButton getCancelar() {
		return btnCancelar;
	}
	
	public JTextField getAlumno() {
		return inAlumno;
	}

	public JTextField getCursada() {
		return inCursada;
	}

	public JTextField getMonto() {
		return inMonto;
	}

	public JTextField getMes() {
		return inMes;
	}

	public JButton getSelAlumno() {
		return btnAlumno;
	}

	public JButton getSelCursada() {
		return btnCursada;
	}

	public JCheckBox getPagoCompleto() {
		return inPagoCompleto;
	}

}