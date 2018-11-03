package com.ungs.formar.vista.pagos.registrar;

import javax.swing.JScrollPane;
import com.ungs.formar.negocios.Tesoreria;
import com.ungs.formar.vista.tablas.TablaPagos;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.Ventana;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class VentanaPagoAM extends Ventana{
	private static final long serialVersionUID = 1L;
	private TablaPagos tabla;
	private JButton btnRegistrar, btnCancelar;
	private JTextField inEmpleado;
	private JTextField inAlumno;
	private JTextField inCursada;
	private JButton btnEmpleado;
	private JButton btnAlumno;
	private JButton btnCursada;
	private JTextField inMonto;
	private JTextField inMes;
	private JCheckBox inPagoEnTermino;
	private JCheckBox inPagoCompleto;
	
	public VentanaPagoAM() {
		super("Administracion de pagos");
		setBounds(100, 100, 500, 231);
		
		// TABLA DE ENTRADAS
		Dimension largoEntrada = new Dimension(Short.MAX_VALUE, 25);
		Dimension largoLabel = new Dimension(100, 25);
		
		JLabel lblEmpleado = new JLabel("Empleado");
		lblEmpleado.setPreferredSize(largoLabel);
		inEmpleado = new JTextField();
		inEmpleado.setEnabled(false);
		inEmpleado.setMaximumSize(largoEntrada);
		btnEmpleado = new JButton("Seleccionar");
		PanelHorizontal panelEmpleado = new PanelHorizontal();
		panelEmpleado.add(lblEmpleado);
		panelEmpleado.add(inEmpleado);
		panelEmpleado.add(btnEmpleado);
		
		JLabel lblAlumno = new JLabel("Alumno");
		lblAlumno.setPreferredSize(largoLabel);
		inAlumno = new JTextField();
		inAlumno.setMaximumSize(largoEntrada);
		btnAlumno = new JButton("Seleccionar");
		PanelHorizontal panelAlumno = new PanelHorizontal();
		panelAlumno.add(lblAlumno);
		panelAlumno.add(inAlumno);
		panelAlumno.add(btnAlumno);

		JLabel lblCursada = new JLabel("Cursada");
		lblCursada.setPreferredSize(largoLabel);
		inCursada = new JTextField();
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
		
		inPagoEnTermino = new JCheckBox("Pago en termino");
		inPagoCompleto = new JCheckBox("Pago completo");
		PanelHorizontal panelCheck = new PanelHorizontal();
		panelCheck.add(inPagoEnTermino);
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
		panelPrincipal.add(panelEmpleado);
		panelPrincipal.add(panelAlumno);
		panelPrincipal.add(panelCursada);
		panelPrincipal.add(panelMonto);
		panelPrincipal.add(panelMes);
		panelPrincipal.add(panelCheck);
		panelPrincipal.add(panelBotones);
	}

	public TablaPagos getTabla() {
		return tabla;
	}
	
	public JButton getRegistrar() {
		return btnRegistrar;
	}

	public JButton getVolver() {
		return btnCancelar;
	}
	
}