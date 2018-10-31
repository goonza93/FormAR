package com.ungs.formar.vista.recados.nuevo;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class VentanaNuevo {
	private JFrame ventana;
	private JTextField inDestinatario;
	private JButton btnSeleccionar, btnEnviar, btnCancelar;
	private JTextArea inMensaje;
	
	public VentanaNuevo() {
		initialize();
	}

	private void initialize() {
		ventana = new JFrame();
		ventana.setBounds(100, 100, 450, 300);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.getContentPane().setLayout(new BoxLayout(ventana.getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panelDestinatario = new JPanel();
		ventana.getContentPane().add(panelDestinatario);
		
		JLabel lblDestinatario = new JLabel("Destinatario");
		panelDestinatario.add(lblDestinatario);
		
		inDestinatario = new JTextField();
		panelDestinatario.add(inDestinatario);
		inDestinatario.setColumns(10);
		inDestinatario.setEnabled(false);
		
		btnSeleccionar = new JButton("Seleccionar");
		panelDestinatario.add(btnSeleccionar);
		
		inMensaje = new JTextArea();
		ventana.getContentPane().add(inMensaje);
		
		JPanel panelBotones = new JPanel();
		ventana.getContentPane().add(panelBotones);
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
		
		btnEnviar = new JButton("Enviar");
		panelBotones.add(btnEnviar);
		
		btnCancelar = new JButton("Cancelar");
		panelBotones.add(btnCancelar);
	}

	public JFrame getVentana() {
		return ventana;
	}

	public JButton getSeleccionar() {
		return btnSeleccionar;
	}

	public JButton getEnviar() {
		return btnEnviar;
	}

	public JButton getCancelar() {
		return btnCancelar;
	}

	public JTextField getDestinatario() {
		return inDestinatario;
	}

	public void ocultar() {
		ventana.setVisible(false);
	}
	
	public void deshabilitar() {
		ventana.setEnabled(false);
	}
	
	public void mostrar() {
		ventana.setVisible(true);
		ventana.setEnabled(true);
	}

	public JTextArea getInMensaje() {
		return inMensaje;
	}
	
	
}