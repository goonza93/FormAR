package com.ungs.formar.vista.recados.nuevo;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.Ventana;

public class VentanaNuevo extends Ventana {
	private static final long serialVersionUID = 1L;
	private JButton btnSeleccionar, btnEnviar, btnCancelar;
	private JTextField inDestinatario;
	private JTextArea inMensaje;
	
	public VentanaNuevo() {
		super("Enviar recado");
		setBounds(100, 100, 450, 300);

		// DESTINATARIO
		PanelHorizontal panelDestinatario = new PanelHorizontal();
		JLabel lblDestinatario = new JLabel("Destinatario");
		
		inDestinatario = new JTextField();
		inDestinatario.setEnabled(false);
		inDestinatario.setMaximumSize(new Dimension(Short.MAX_VALUE, 25));
		
		btnSeleccionar = new JButton("Seleccionar");

		panelDestinatario.add(lblDestinatario);		
		panelDestinatario.add(inDestinatario);
		panelDestinatario.add(btnSeleccionar);

		// MENSAJE
		inMensaje = new JTextArea();
		PanelHorizontal panelMensaje = new PanelHorizontal();
		panelMensaje.add(inMensaje);
		
		// BOTONES
		btnEnviar = new JButton("Enviar");
		btnCancelar = new JButton("Cancelar");
		PanelHorizontal panelBotones = new PanelHorizontal();		
		panelBotones.add(btnEnviar);
		panelBotones.add(btnCancelar);
		
		// ORGANIZACION DE PANELES
		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.add(panelDestinatario);
		panelPrincipal.add(panelMensaje);
		panelPrincipal.add(panelBotones);
		setContentPane(panelPrincipal);
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

	public JTextArea getMensaje() {
		return inMensaje;
	}
	
}