package com.ungs.formar.vista.recados.nuevo;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.ungs.formar.vista.util.FormatoLimitado;
import com.ungs.formar.vista.util.PanelHorizontal;
import com.ungs.formar.vista.util.PanelVertical;
import com.ungs.formar.vista.util.Ventana;

public class VentanaNuevo extends Ventana {
	private static final long serialVersionUID = 1L;
	private JButton btnSeleccionar, btnEnviar, btnCancelar;
	private JTextField inDestinatario, inTitulo;
	private JTextArea inMensaje;
	
	public VentanaNuevo() {
		super("Enviar recado");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);

		// DESTINATARIO
		JLabel lblDestinatario = new JLabel("Destinatario");
		btnSeleccionar = new JButton("Seleccionar");
		
		inDestinatario = new JTextField();
		inDestinatario.setEnabled(false);
		inDestinatario.setMaximumSize(new Dimension(Short.MAX_VALUE, 25));		

		PanelHorizontal panelDestinatario = new PanelHorizontal();
		panelDestinatario.add(lblDestinatario);		
		panelDestinatario.add(inDestinatario);
		panelDestinatario.add(btnSeleccionar);
		
		// MENSAJE
		PanelHorizontal panelTitulo = new PanelHorizontal();
		JLabel lblTitulo = new JLabel("Titulo");
		inTitulo = new JTextField();
		inTitulo.setMaximumSize(new Dimension(Short.MAX_VALUE, 20));
		inTitulo.setDocument(new FormatoLimitado(20));
		panelTitulo.add(lblTitulo);
		panelTitulo.add(inTitulo);
		
		inMensaje = new JTextArea();		
		PanelVertical panelMensaje = new PanelVertical();
		panelMensaje.add(panelTitulo);
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
	
	public JTextField getTitulo() {
		return inTitulo;
	}
	
}